'use strict'

//Generic class to generate various kinds of displacement based on positions
//and time or other attributes
class Generator{

    constructor(){
        //The time used when generating the heightMap
        this.snapShotTime = 0;

    }

    //Must return the displacement of the object at (i,j)
    //at time 
    displacementFor(i, j, time){}

    //Generate an heightmap Width x Height pixels that
    //represents the generator by querying its values
    //at a time
    //return an object of type HeightMap 
    heightMap(width, height){
        var size = width * height
        var data = new Float32Array(size);
        for(let i = 0; i < size; i++) data[i] = 0;
        for(let i = 0; i < size; i++){
            var row = Math.floor(i/width);
            var column = Math.floor(i%width);
            var displacement = this.displacementFor(row, column, this.snapShotTime);
            data[i] = displacement;
        }
        return new HeightMap(data, width, height);
    }
}