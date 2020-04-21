'use strict'

//Image data reading algorithm taken from: http://danni-three.blogspot.it/2013/09/threejs-heightmaps.html
    

class HeightMapReader{

    constructor(){}

    //Read Image data and return an HeightMap
    //returns data in [-1,1] range
    readDataFromImage(image){
        var canvas = document.createElement('canvas');
        canvas.width = image.width;
        canvas.height = image.height;
        var context = canvas.getContext('2d');

        var size = image.width * image.height;
        var data = new Float32Array(size);
        for(let i = 0; i < size; i++) data[i] = 0;

        context.drawImage(image, 0, 0);

        var image_data = context.getImageData(0,0, image.width, image.height);
        var pixel_data = image_data.data;

        var j=0;
        for (let i = 0; i < pixel_data.length; i+=4) {
            var all = pixel_data[i] + pixel_data[i+1] + pixel_data[i+2]; //Read R G B values and sum
            var normalizedHeight = (all/3 - 0) * (1 - -1) / (255 - 0) + -1;
            data[j++] = normalizedHeight //Average the 3 values and scale
            
        }

        return new HeightMap(data, image.width, image.height);
    };

    //Loads image from src string and returns an HeightMap
    readDataFromSrc(src, callback){
        var image = new Image();
        image.onload = function(){
            var reader = new HeightMapReader();
            var data = reader.readDataFromImage(image);
            return callback(data);
        }
        image.src = src;
    };

}