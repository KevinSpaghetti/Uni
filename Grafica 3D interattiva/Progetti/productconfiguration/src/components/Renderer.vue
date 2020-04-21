<template>
    <div class='full-container' id='canvas-container'>
        <canvas id='viewport-canvas'>

        </canvas>
    </div>
</template>

<script>
    import {
        WebGLRenderer,
        Scene,
        PerspectiveCamera,

        TextureLoader,

        Mesh,
        SphereGeometry,

        GridHelper,

        PointLight,

        Object3D,

        Box3, Box3Helper,
        LinearMipMapLinearFilter,

        ShaderMaterial,
        UniformsLib,
        UniformsUtils,
        PCFSoftShadowMap,

        Vector3, ACESFilmicToneMapping, AmbientLight
    } from "three";

    import { EffectComposer } from 'three/examples/jsm/postprocessing/EffectComposer.js';
    import { RenderPass } from 'three/examples/jsm/postprocessing/RenderPass.js';
    import { SAOPass } from 'three/examples/jsm/postprocessing/SAOPass.js';

    import {
        OrbitControls
    } from 'three/examples/jsm/controls/OrbitControls';

    import {
        PBRTextureSet
    } from '@/classes/PBRTextureSet.js';

    import {
        StudioLights
    } from "@/classes/StudioLights";

    import {
        AssetLoader
    } from "@/classes/AssetLoader";

    import {
        PBRMaterial
    } from "@/classes/PBRMaterial";
    import {LengthDimension} from "@/classes/LengthDimension";

    export default {
        name: "Renderer",

        props: {
            configuration: {
                type: Object,
                required: true
            },
            options: {
                type: Object,
                required: false,
                default: () => ({
                    lights: {
                        intensity: 1,
                        color: 0x404040
                    },
                    showObjectDimensions: false
                })
            }
        },

        data: () => {
            return {

                //Actual model and materials
                //corresponding to the data model
                configuratorModel: {
                    model: {
                        name: '', //Name of the mesh same as in the dataModel
                        mesh: null, //The complete group of meshes
                        dimensions: null, //Used to show model dimensions
                        shaders: null, //Shaders used in the materials
                        components: [   //Components in the mesh
                            {
                                name: '', //Name of the component same as in dataMesh
                                mesh: null, //Mesh of the component,
                                material: null, //PBRMaterial of the component
                            }
                        ],
                        //Array of PBRMaterials
                        materials: [],
                        envmap: {
                            texture: null
                        }
                    }
                },

                //Server Side path info
                base_paths: {
                    models: './assets/models/',
                    textures: './assets/textures/',
                    envmaps: './assets/cubemaps/',
                    shaders: './assets/shaders/'
                },

                //Rendering data
                renderer: null,
                composer: null,
                scene: null,
                camera: null,
                lights: null, //StudioLights instance

                //Camera settings
                camera_settings: {
                    FOV: null,
                    aspectRatio: null,
                    near: null,
                    far: null,
                },

                //Loading stuff settings
                isLoading: false,
                //Error data
                isError: false,
                error: null, //the instance of the error
                errorMessage: '',

                //Components that manages the asset loading
                assetLoader: null,

            }
        },

        methods: {
            animate: function() {
                requestAnimationFrame(this.animate);
                this.composer.render();
            },

            onResize: function() {
                //update the renderer width and height
                let canvas = document.querySelector('#viewport-canvas');
                let canvasContainer = document.querySelector('#canvas-container');

                canvas.width = canvasContainer.clientWidth;
                canvas.height = canvasContainer.clientHeight;

                this.camera_settings.aspectRatio = canvasContainer.clientWidth / canvasContainer.clientHeight;
                this.camera.aspectRatio = this.camera_settings.aspectRatio;
                this.camera.updateProjectionMatrix();

                this.renderer.setSize(canvasContainer.clientWidth, canvasContainer.clientHeight);
            },

            initRendering(){
                let canvas = document.querySelector('#viewport-canvas');
                let canvasContainer = document.querySelector('#canvas-container');

                canvas.width = canvasContainer.clientWidth;
                canvas.height = canvasContainer.clientHeight;

                //Renderer init
                this.renderer = new WebGLRenderer({
                    canvas: canvas,
                    alpha: true,
                    antialias: true,
                });
                this.renderer.setSize(canvasContainer.clientWidth, canvasContainer.clientHeight);
                this.renderer.setClearColor(0xbdc3c7,0.0);

                //Tone Mapping
                this.renderer.toneMapping = ACESFilmicToneMapping;
                this.renderer.toneMappingWhitePoint = 0.8;

                //Init scene
                this.scene = new Scene();

                //Init camera params
                this.camera_settings.FOV = 75;
                this.camera_settings.aspectRatio = canvasContainer.clientWidth / canvasContainer.clientHeight;
                this.camera_settings.near = 0.1;
                this.camera_settings.far = 1000;

                this.camera = new PerspectiveCamera(this.camera_settings.FOV,
                    this.camera_settings.aspectRatio,
                    this.camera_settings.near,
                    this.camera_settings.far);

                const controls = new OrbitControls(this.camera, canvas);
                controls.enablePan = false;
                this.camera.position.set(1, 1, 1);
                this.camera.lookAt(new Vector3(0,0,0));
                controls.update();


                //Add post processing effects
                const composer = new EffectComposer(this.renderer);
                composer.addPass(new RenderPass(this.scene, this.camera));
                this.composer = composer;
            },

            //Method called when the model in the
            //configuratorModel is ready to be displayed

            readyToDisplay(configuratorModel){
                console.log(configuratorModel);

                this.scene.background = configuratorModel.model.envmap.texture;
                this.scene.environment = configuratorModel.model.envmap.texture;

                //Create the x,y,z indicators to show the dimensions
                const box = new Box3().setFromObject(configuratorModel.model.mesh);
                const modelDimensions = this.createModelDimensions(box);

                this.configuratorModel.model.mesh.position.setY(-(box.max.y - box.min.y) / 2);

                this.configuratorModel.model.dimensions = modelDimensions;
                this.configuratorModel.model.dimensions.visible = this.options.showObjectDimensions;
                this.configuratorModel.model.mesh.add(modelDimensions);

                this.scene.add(configuratorModel.model.mesh);
            },

            createModelDimensions(box){

                const modelDimensions = new Object3D();

                const xLength = box.max.x - box.min.x;
                const yLength = box.max.y - box.min.y;
                const zLength = box.max.z - box.min.z;

                const xDim = new LengthDimension(xLength, xLength.toFixed(2) + 'm', 0xff0000, false);
                const yDim = new LengthDimension(yLength, yLength.toFixed(2) + 'm', 0x00ff00, false);
                const zDim = new LengthDimension(zLength, zLength.toFixed(2) + 'm', 0x0000ff, false);

                xDim.position.setZ(-zLength/2);
                zDim.position.setX(-xLength/2);
                zDim.rotateY(90 * Math.PI/180);

                yDim.rotateZ(90 * Math.PI/180);
                yDim.position.setX(-xLength/2);
                yDim.position.setZ(-zLength/2);
                yDim.position.setY(yLength/2);

                modelDimensions.add(xDim);
                modelDimensions.add(yDim);
                modelDimensions.add(zDim);

                return modelDimensions;
            },

            /*
            initDataModel(){
                return {
                    model: {
                        name: 'Chair',
                        filetype: 'obj',
                        components: [
                            {
                                name: 'Back',
                                material: 'wood_1',
                                options: [ 'wood_1', 'wood_2' ]
                            },
                            {
                                name: 'Legs',
                                material: 'wood_2',
                                options: [ 'wood_1', 'wood_2']
                            },
                            {
                                name: 'Support',
                                material: 'wood_1',
                                options: [ 'wood_1', 'wood_2']
                            },
                            {
                                name: 'Foam',
                                material: 'fabric',
                                options: [ 'fabric', 'fabric']
                            }
                        ],
                        materials: [
                            { name: 'wood_1', filetype: 'png' },
                            { name: 'wood_2', filetype: 'png' },
                            { name: 'fabric', filetype: 'jpg' },
                        ]
                    }
                }

            },
             */
            initConfiguratorModel(dataModel, model, shaders, envmap, materials){
                console.log(model, shaders, materials, envmap);

                const PBRMaterials = [];
                materials.forEach((textureSet) => {
                    PBRMaterials.push(
                        new PBRMaterial(
                            textureSet.name,
                            shaders.vertex,
                            shaders.fragment,
                            textureSet.textures,
                            envmap
                        )
                    )
                });
                const components = [];

                dataModel.model.components.forEach(
                    (component) => {
                        const fullComponentName = dataModel.model.name + ':' + component.name;

                        const componentMesh = model.getObjectByName(fullComponentName);

                        //Find the material of the component in materials array
                        const componentMaterial = PBRMaterials.find( (material) => material.name === component.material);
                        componentMesh.material = componentMaterial.getShader();

                        components.push({
                            name: component.name,
                            mesh: componentMesh,
                            material: componentMaterial
                        });
                    }
                )

                return {
                    model: {
                        name: dataModel.model.name,
                        mesh: model,
                        shaders: shaders,
                        components: components,
                        //Array of PBRTextureSet to keep track of
                        //material names and images
                        materials: PBRMaterials,
                        envmap: {
                            texture: envmap
                        }
                    }
                }
            },

            loadDataModelAssets(dataModel, loadUnusedMaterials = false){

                //Create the asset loader
                this.assetLoader = new AssetLoader();

                const modelURL = this.base_paths.models + dataModel.model.name + '.' + dataModel.model.filetype;
                const shadersURL = this.base_paths.shaders + 'CookTorrance/';
                const envMapURL = this.base_paths.envmaps + dataModel.model.envmap.name;

                //Load models and shaders
                var promises = [
                    this.assetLoader.loadModel(modelURL)
                        .then(function(model) {
                            return model;
                        })
                        .catch(function(error) {
                            console.log(error);
                        }),
                    this.assetLoader.loadShaders(shadersURL)
                        .then(function([vertexShader, fragmentShader]) {
                            return {
                                vertex: vertexShader,
                                fragment: fragmentShader
                            }
                        })
                        .catch(function(error) {
                            console.log(error);
                        }),
                    this.assetLoader.loadEnvMap(envMapURL, 'png')
                        .then(function(cubemap){
                            cubemap.minFilter = LinearMipMapLinearFilter;
                            return cubemap;
                        })
                        .catch(function(error){
                            console.log(error);
                        })
                ];

                var materialsToLoad = [];
                if(loadUnusedMaterials == true){
                    materialsToLoad = dataModel.model.materials;
                }else{
                    const assignedMaterials = [];
                    dataModel.model.components.forEach( (component) => assignedMaterials.push(component.material) );
                    //Get all the assigned materials from the materials array
                    //to get the filetypes
                    dataModel.model.materials.forEach( (material) => {
                            if ( assignedMaterials.includes(material.name) )
                                materialsToLoad.push(material);
                        }
                    );
                }

                const materialPromises = [];
                materialsToLoad.forEach( (material) => {
                    const textureUrl = this.base_paths.textures + material.name + '/';
                    materialPromises.push(
                        this.assetLoader.loadPBRTextures(textureUrl, material.filetype)
                            .then(function([diffuse, roughness, normal]) {
                                const textureSet = new PBRTextureSet(material.name, {
                                    diffuse: diffuse,
                                    roughness: roughness,
                                    normalmap: normal
                                });
                                return textureSet;
                            })
                            .catch(function(error) {
                                console.log(error);
                            })
                    );
                });
                //We want to load all materials in a promise group
                promises.push(Promise.all(materialPromises));

                return Promise.all(promises)
            },

        },

        mounted() {

            this.initRendering();
            //First loading steps:
            //1. Load model
            //2. Load shaders
            //3. Load textures (only those needed)
            //perform 1 to 3 in parallel
            //when every step is finished
            //Attach everything to scene and display

            //Create point lights
            this.lights = new StudioLights(
                this.options.lights.color,
                this.options.lights.intensity,
                100,
                2,
                new SphereGeometry(6, 4, 4));

            //create ambient light
            this.scene.add(new AmbientLight(0x020202));
            this.scene.add(this.lights);

            //this.dataModel = this.initDataModel();
            this.loadDataModelAssets(this.configuration, true).then(
                ([model, shaders, envmap, materials]) => {
                    this.configuratorModel = this.initConfiguratorModel(
                        this.configuration,
                        model,
                        shaders,
                        envmap,
                        materials);
                    this.readyToDisplay(this.configuratorModel);
                }
            );

            //Start the rendering
            this.animate();

            //Listen to changes in the component's materials
            for (let i = 0; i < this.configuration.model.components.length; i++){
                const componentName = 'configuration.model.components.' + i + '';
                this.$watch(componentName,
                    function (newVal, oldVal) {
                        //Get the corresponding mesh and the new material
                        const component = this.configuratorModel.model.components.find((comp) => (comp.name === newVal.name));
                        const componentMesh = component.mesh;
                        const componentNewMaterial = this.configuratorModel.model.materials.find( (mat) => (mat.name === newVal.material));

                        componentMesh.material = componentNewMaterial.getShader();
                        componentMesh.material.needsUpdate = true;

                        component.material = componentNewMaterial;

                    }, {deep: true});
            }

            //Listen for window resize event
            window.addEventListener('resize', this.onResize);
        },

        watch: {
            'options.lights.intensity': function (newVal, oldVal) {
                this.lights.setIntensity(newVal);
            },
            'options.lights.color': function (newVal, oldVal) {
                this.lights.setColor(newVal);
            },
            'options.showObjectDimensions': function(newVal, oldVal){
                if(this.configuratorModel.model.mesh) {
                    this.configuratorModel.model.dimensions.visible = newVal;
                }
            }
        },
        updated() {

        },

        beforeDestroy(){
            window.removeEventListener('resize', this.onResize);
        }

    }
</script>

<style scoped>
    .full-container{
        width: 100%;
        height: 100%;
    }
</style>