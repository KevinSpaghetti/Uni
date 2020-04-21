'use strict'

class HeightMap{

    //Init HeightMap with an array of values between -1 and 1
    constructor(data, width, height){
        this.data = data;
        this.width = width;
        this.height = height;
    }

    //Returns the height value for position (i,j)
    //Never returns a value out of the interval (-1,1)
    //clamps if necessary
    heightForPosition(i, j){
        //If asked to return an out of bounds value return 0 the midlevel
        if(i < 0 || i > this.height) return 0;
        if(j < 0 || j > this.width) return 0;
        var value = this.data[this.width * i + j]; 
        return Math.max(Math.min(value, 1), -1);
    }

    //Returns a canvas with the image 
    image(){
        var canvas = document.createElement('canvas');
        canvas.width = this.width;
        canvas.height = this.height;
        var context = canvas.getContext('2d');
        var imgData = context.getImageData(0, 0, this.width, this.height);
        
        var index = 0;
        for(var i=0; i < imgData.data.length; i+=4){
            //Remap displacement from (-1,1) to (0,255)
            var row = Math.floor(index/this.width);
            var column = Math.floor(index%this.width);
            var displacement = (this.heightForPosition(row,column) - (-1)) * (255) / (1 - (-1)) + 0;
            imgData.data[i] = displacement;
            imgData.data[i+1] = displacement;
            imgData.data[i+2] = displacement;
            imgData.data[i+3] = 255;
            index++;
        }
        
        context.putImageData(imgData, 0, 0);
        return canvas;
    }

}