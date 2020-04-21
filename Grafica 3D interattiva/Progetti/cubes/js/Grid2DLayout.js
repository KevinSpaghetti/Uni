'use strict'


//Extends Layout class to place objects 
//in a rows*columns grid
class Grid2DLayout extends Layout{

    constructor(rows, columns){
        super();
        this.rows = rows;
        this.columns = columns;

        this.objects = new Array(rows);
        for(let row=0; row < rows; row++){
            this.objects[row] = new Array(columns);
        } 

        this.spacing = {
            rows: 1,
            columns: 1
        }

    }

    //Set the rows and columns spacing and updates the grid
    setSpacing(rows, columns){
        this.spacing.rows = rows;
        this.spacing.columns = columns;
        this.layoutElements();
    }

    //Add elements to the grid
    //elementForPosition must take the grid coordinates
    //i and j and return an Object3D to add at that position on the 
    //logical grid
    addElements(elementForPosition){
        for(let i=0; i < this.rows; i++){
            for(let j=0; j< this.columns; j++){
                var object = elementForPosition(i,j);
                this.objects[i][j] = object; 
            }
        }
    }

    //Place all elements in their place on the grid
    //with respect to the origin
    //positionForElement is a function that must accept
    //2 integer parameters, the discrete position (i,j) in the grid,
    //and must return a THREE.Vector3 that represents the position
    //of the final point with respect to the grid origin in the 
    //middle of the grid
    //Does not display the elements
    layoutElements(){
        for(let i=0; i < this.rows; i++){
            for(let j=0; j< this.columns; j++){
                var object = this.objects[i][j];
                var position = object.position ? object.position : new THREE.Vector3(0,0,0);
				position.x = (i - this.rows/2) * this.spacing.rows;
				position.z = (j - this.columns/2) * this.spacing.columns;
                object.position.set(position.x, position.y, position.z);
            }
        }
    }

    //Display elements by adding them to the grid object
    displayElements(){
        for(let i=0; i < this.rows; i++){
            for(let j=0; j< this.columns; j++){
                var object = this.objects[i][j];
                this.add(object);
            }
        }
    }

    //Updates all elements in the grid using 
    //the function changesForElement which must take
    //the grid coords (i,j), an Object3D and return 
    //nothing
    updateElements(changesForElement){
        for(let i=0; i < this.rows; i++){
            for(let j=0; j< this.columns; j++){
                var object = this.objects[i][j];
                changesForElement(i, j, object);
            }
        }
    }

    removeAllElements(){
        for(let i=0; i < this.rows; i++){
            for(let j=0; j< this.columns; j++){
                var object = this.objects[i][j];
                this.remove(object);
                this.objects[i][j] = null;
            }
        }
    }



}