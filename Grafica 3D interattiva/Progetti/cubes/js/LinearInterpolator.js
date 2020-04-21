'use strict'

//Linearly interpolates between values over time
class LinearInterpolator extends Interpolator{

    //Domain of the interpolation in seconds
    //animationStart = value1
    //animationDuration = value2
    constructor(animationStart, animationDuration){
        super();

        this.animationStart = animationStart;
        this.animationDuration = animationDuration;
    }

    //Value interpolated linearly between value1 and value2 at time
    between(value1, value2, time){
        var factor = (time-this.animationStart)/this.animationDuration;
        factor = Math.max(Math.min(factor, 1), 0);
        
        var value = factor*value2 + (1-factor)*value1;	
        return value;
    }

}