import {
    Dimension
} from "@/classes/Dimension";

import {
    Mesh,
    CylinderBufferGeometry,
    MeshBasicMaterial, Object3D, TextBufferGeometry, FontLoader, Box3Helper, BoxHelper
} from "three";

//Create an indicator for the length of objects in 3d Space
//length is defined in threejs units,
//this indicator is not meant to be precise,
//is just meant to show roughly the dimensions of object
//in space
//Center of the indicator is in the middle, so every side has a length of
//length/2,
//when the object has no transforms the bar that indicates the length is oriented
//towards +X
export class LengthDimension extends Dimension{

    //Length to display in units
    constructor(length, text, color, tip = true) {
        super();

        this.length = length;
        this.text = text;
        this.color = color;
        this.tip = tip;

        this.init(this.length, this.text, this.color);
    }

    init(length, text, color){

        //Create length indicator mesh
        this.lengthIndicator = this.createLengthIndicator(length, color);
        this.add(this.lengthIndicator);

        //create text mesh
        var loader = new FontLoader();

        loader.load( 'https://cdn.rawgit.com/mrdoob/three.js/master/examples/fonts/helvetiker_regular.typeface.json', ( font ) => {
            var geometry = new TextBufferGeometry( text, {
                font: font,
                size: 140,
                height: 20,
                curveSegments: 24,
                bevelEnabled: true,
                bevelThickness: 2,
                bevelSize: 8,
                bevelOffset: 0,
                bevelSegments: 5
            } );
            var material = new MeshBasicMaterial({
                color: color
            });

            const textScale = 0.001;
            const textUp = 0.1;

            var mesh = new Mesh(geometry, material);
            geometry.computeBoundingBox();

            //We need to scale the geometryWidth since threejs calculates just the bounding box of
            //the geometry(unscaled) and not that of the mesh (that is scaled).
            var geometryWidth = (geometry.boundingBox.max.x-geometry.boundingBox.min.x) *textScale;

            mesh.scale.set(textScale, textScale, textScale);
            mesh.position.setX(-(geometryWidth)/2);
            mesh.position.setY(textUp);

            this.add(mesh);
        } );

    }

    createLengthIndicator(length, color){
        const radius = length/100;
        const tipHeight = 0.01;
        const tipRadius = radius*4;

        var material = new MeshBasicMaterial({
            color: color
        });

        const barGeometry = new CylinderBufferGeometry(radius,
            radius,
            length,
            32,
            5);


        //Bar mesh
        var barMesh = new Mesh(barGeometry, material);

        if(this.tip){
            const tipGeometry = new CylinderBufferGeometry(tipRadius,
                tipRadius,
                tipHeight,
                64,
                10);

            //Tip meshes
            var topTipMesh = new Mesh(tipGeometry, material);
            var bottomTipMesh = new Mesh(tipGeometry, material);

            //Position objects
            barMesh.add(topTipMesh);
            barMesh.add(bottomTipMesh);

            const tipOffset = length/2 - tipHeight/2;
            topTipMesh.position.setY(tipOffset);
            bottomTipMesh.position.setY(-tipOffset);
        }


        barMesh.rotateZ( -90 * Math.PI/180);

        var lengthIndicatorMesh = new Object3D();
        lengthIndicatorMesh.add(barMesh);

        return lengthIndicatorMesh;
    }

}