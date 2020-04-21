'use strict'

class ConstantDisplacementGenerator extends Generator{

    constructor(value){
        super();

        this.value = value;
    }

    displacementFor(i, j, time){
        return this.value;
    }

}