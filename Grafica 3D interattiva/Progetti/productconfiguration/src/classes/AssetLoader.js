
//Manages all the loading of
//models, shaders and textures
import {
    CubeTextureLoader,
    TextureLoader
} from "three";

import {
    OBJLoader
} from 'three/examples/jsm/loaders/OBJLoader';

export class AssetLoader{

    constructor() {

    }

    loadModel(modelLink){
        return new Promise((resolve, reject) => {
            var loader = new OBJLoader();

            loader.load(
                modelLink,
                (file) => {
                    resolve(file);
                },null,
                (error) => {
                    reject(error);
                }
            )
        })
    }

    loadShaders(folder){
        return Promise.all([
                               fetch(folder + 'VertexShader.glsl').then((x) => x.text()),
                               fetch(folder + 'FragmentShader.glsl').then((x) => x.text()),
                           ])
    }

    //Base Url + materials folder + name of the material + filetype of the textures
    //Load all the textures required for the pbr shader
    //1. Diffuse
    //2. Specular
    //3. Roughness
    //4. normal
    //Material = Shader + Textures
    loadPBRTextures(materialUrl, filetype='jpg') {
        var textures = [
            'diffuse',
            'roughness',
            'normal'
        ];
        var promises = [];
        textures.forEach((textureName) => {
            promises.push(new Promise((resolve, reject) => {
                var loader = new TextureLoader();
                loader.load(materialUrl + textureName + '.' + filetype,
                    (texture) => {resolve(texture);},
                    (error) => {reject(error)}
                );
            }))
        });

        return Promise.all(promises);
    }

    loadEnvMap(mapPath, filetype='jpg'){
        return new Promise((resolve, reject) => {
            const loader = new CubeTextureLoader();
            loader.setPath(mapPath+'/');
            loader.load( [
                    'px.'+filetype,
                    'nx.'+filetype,
                    'py.'+filetype,
                    'ny.'+filetype,
                    'pz.'+filetype,
                    'nz.'+filetype
                ],
                (texture) => {
                    console.log(texture);
                    resolve(texture)
                },
                null,
                (error) => { reject(error) });
        });

    }

}