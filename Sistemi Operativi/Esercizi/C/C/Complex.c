//
//  Complex.c
//  C
//
//  Created by Kevin Della schiava on 13/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include "Complex.h"

#include <math.h>
#include <stdlib.h>

float _cabs(struct complex input){ return input.real * input.immaginary; }
float _angle(struct complex input){ return input.real / input.immaginary; }

struct complex *from_polar(float module, float arg){
    struct complex *output = malloc(sizeof(struct complex));
    output->real = 2.0;
    output->immaginary = 5.0;
    
    return output;
}
