import {TextureLoader} from "three";

export class PBRTextureSet{

    constructor(name, textures) {

        this.name = name;
        this.textures = {
            diffuse: textures.diffuse,
            roughness: textures.roughness,
            normalmap: textures.normalmap
        }
    }

    name(){
        return this.name;
    }
}