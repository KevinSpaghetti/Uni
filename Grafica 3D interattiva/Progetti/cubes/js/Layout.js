class Layout extends THREE.Object3D{

    constructor(){
        super();

        this.objects = [];

        this.origin = new THREE.Object3D();
        this.add(this.origin)

    }

    addElements(){};
    layoutElements(){};
    displayElements(){};
    updateElements(){};
    removeAllElements(){};

 
}