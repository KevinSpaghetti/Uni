'use strict'

class NoiseDisplacementGenerator extends Generator{

    constructor(){
        super();

    }

    displacementFor(i, j, time){
        var x = Math.sin(time++) * 10000;
        return x - Math.floor(x);
    }

}