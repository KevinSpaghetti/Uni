//
//  Complex.h
//  C
//
//  Created by Kevin Della schiava on 13/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#ifndef Complex_h
#define Complex_h

#include <stdio.h>

struct complex {
    float real;
    float immaginary;
};

float _cabs(struct complex input);
float _angle(struct complex input);

struct complex *from_polar(float module, float arg);

#endif /* Complex_h */
