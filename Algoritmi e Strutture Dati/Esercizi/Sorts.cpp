//
//  Sorts.cpp
//  Esercizi
//
//  Created by Kevin Della schiava on 02/10/18.
//  Copyright © 2018 esercizi. All rights reserved.
//

#include "Sorts.hpp"
#include <math.h>

void Sort::selection(int array[], int size){
    int maxIndex = 0;
    if(size > 1){
        maxIndex = Array::maxPosition(array, size);
        int a = array[maxIndex];
        array[maxIndex] = array[size-1];
        array[size-1] = a;
        Sort::selection(array, size-1);
    }
}

void Sort::merge(int array[], int size){
    Sort::merge(array, 0, size-1);
}

void Sort::merge(int array[], int p, int q){
    
    if((q-p) == 1){
        std::cout << array[p] << ":" << array[q] << std::endl;
        if(array[p] > array[q]){
            std::swap(array[q], array[p]);
        }
        return;
    }
    
    if((q-p) == 2){
        std::cout << array[p] << ":" << array[q] << std::endl;
        if(array[p] > array[q]){
            std::swap(array[q], array[p]);
        }
        return;
    }
    
    int middle = floor((q + p)/2);
    
    Sort::merge(array, p, middle);
    Sort::merge(array, middle+1, q);
    
    int *supp = new int[q-p+1];
    int suppIndex = 0;
    
    //Ricopio l'array risultato nell'array originale
    for (int i = 0; i < q-p; i++) {
        supp[i] = array[p+i];
    }
    
    Sort::selection(supp, q-p+1);

    for (int i = 0; i < q-p; i++) {
        array[p+i] = supp[i];
    }

}

