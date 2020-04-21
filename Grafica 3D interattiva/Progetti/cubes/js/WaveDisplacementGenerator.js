'use strict'

class WaveDisplacementGenerator extends Generator{

    //direction must be a number between 0 and Math.PI/2 that rapresents
    //the rotation starting from +X, so
    //0 = +X
    //Math.PI/2 = +Z
    //y will be ignored
    //amplitude and frequency as numbers 
    constructor(direction, amplitude, frequency){
        super();

        this.direction = direction;
        this.amplitude = amplitude;
        this.frequency = frequency;
    }

    //Returns the y displacement for a wave at position (i,j) and a time
    displacementFor(i, j, time){
        return Math.sin((time + (i*(Math.PI/2-this.direction)+j*this.direction)) * this.frequency) * this.amplitude;
    }

    /*
    //Returns the y displacement for a wave at position (i,j) and a time, with an
    //amplitude and frequency
    displacementForParameters(i,j,time, direction, amplitude, frequency){
        return Math.sin((i*direction.x+j*1-direction) * frequency) * amplitude;
    }
    */

}