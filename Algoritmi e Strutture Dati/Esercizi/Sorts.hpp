//
//  Sorts.hpp
//  Esercizi
//
//  Created by Kevin Della schiava on 02/10/18.
//  Copyright © 2018 esercizi. All rights reserved.
//

#ifndef Sorts_hpp
#define Sorts_hpp

#include <stdio.h>
#include <iostream>
#include "Array.hpp"

class Sort{
    public:
    static void selection(int array[], int size);
    static void merge(int array[], int size);
    
    private:
    static void merge(int array[], int p, int q);
};

#endif /* Sorts_hpp */
