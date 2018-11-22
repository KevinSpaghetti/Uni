//
//  main.c
//  Structs
//
//  Created by Kevin Della schiava on 22/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdio.h>

#include "Complex.h"

int main(int argc, const char * argv[]) {
    struct complex number = {
        8.0,
        4.0
    };
    printf("cabs: %f \n", _cabs(number));
    printf("angle: %f \n", _angle(number));
    struct complex *created = from_polar(20.0, 10.0);
    printf("Created: %f, %f \n", created->real, created->immaginary);
    return 0;
}
