//
//  Array.cpp
//  Esercizi
//
//  Created by Kevin Della schiava on 03/10/18.
//  Copyright © 2018 esercizi. All rights reserved.
//

#include "Array.hpp"
#include <iostream>


int Array::maxPosition(int array[], int size){
    int max = INT_MIN;
    int maxIndex = 0;
    for (int i=0; i < size; i++) {
        if(array[i] > max){
            max = array[i];
            maxIndex = i;
        }
    }
    
    return maxIndex;
}
