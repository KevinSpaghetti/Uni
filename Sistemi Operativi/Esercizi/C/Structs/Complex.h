
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
