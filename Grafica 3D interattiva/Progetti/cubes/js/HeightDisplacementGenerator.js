'use strict'

//Generates height displacement using an instance of HeightMap
class HeightDisplacementGenerator extends Generator{

    constructor(map){
        super();
        this.map = map;
    }

    //Returns y displacement for the position (i,j) on the heightmap
    displacementFor(i, j, time){
        return this.map.heightForPosition(i,j);
    }

}