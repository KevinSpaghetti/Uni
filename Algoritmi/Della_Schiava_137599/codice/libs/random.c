#ifndef RANDOM
#define RANDOM

#include <math.h>

double randomUniform(double seed, int increment, int duplicates){
    //CC
    if(duplicates != -1){
        seed += (int)increment % duplicates;
    }else{
        seed += increment;
    }
    int a = 16807;
    int m = 2147483647;
    int q = 127773;
    int r = 2836;
    int hi = seed/q; 
    double lo = seed - q*hi;
    double test = a*lo - r*hi;
    if(test < 0){
        seed = test + m;
    }else{
        seed = test;
    }
    return seed/m;
}

#endif