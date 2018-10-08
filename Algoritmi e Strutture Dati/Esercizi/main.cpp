//
//  main.cpp
//  Esercizi
//
//  Created by Kevin Della schiava on 02/10/18.
//  Copyright © 2018 esercizi. All rights reserved.
//

#include "Sorts.hpp"
#include <iostream>

using namespace std;

int main(){
    
    int array[] = {1, 10, 2, 3, 9, 8, 5, 12, 34, 50};
    int size = 10;
    
    Sort::merge(array, size);
    
}
