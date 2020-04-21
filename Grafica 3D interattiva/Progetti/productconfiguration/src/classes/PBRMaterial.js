import {
    ShaderMaterial,
    UniformsLib,
    UniformsUtils
} from "three";

export class PBRMaterial {

    constructor(name, vxShader, fgShader, textures, envmap) {
        //Merge uniforms to enable custom shader to be lit by
        //threejs lights
        const mergedUniforms = UniformsUtils.merge([
            UniformsLib['lights'],
            UniformsLib['ambient'],
            {
                diffuse: {} ,
                roughness: {},
                normalmap: {},
                envmap: {}
            }]);
        console.log(mergedUniforms);
        //Directly merging already assigned textures to the uniform
        //doesn't work per
        //https://github.com/mrdoob/three.js/issues/8016
        mergedUniforms.diffuse = { type: 't', value:textures.diffuse };
        mergedUniforms.roughness = { type: 't', value:textures.roughness };
        mergedUniforms.normalmap = { type: 't', value:textures.normalmap };
        mergedUniforms.envmap = { type: 't', value:envmap };
        const shader = new ShaderMaterial({
            vertexShader: vxShader,
            fragmentShader: fgShader,
            uniforms: mergedUniforms,
            extensions: {
                derivatives: true,
                shaderTextureLOD: true
            },
            lights: true
        });

        this.name = name;
        this.textures = {
            diffuse: {} ,
            roughness: {},
            normalmap: {}
        }
        this.shader = shader;
    }

    getShader(){
        return this.shader;
    }
}