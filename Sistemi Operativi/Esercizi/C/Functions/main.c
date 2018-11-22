//
//  main.c
//  Functions
//
//  Created by Kevin Della schiava on 22/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdio.h>
#include <math.h>

#define BOOL int
#define FALSE 0
#define TRUE  1

#define LOG_BASE 10

int is_whitespace(char);
int lg(int);
void order(int*,int);

int main(int argc, const char * argv[]) {
    
    printf("Is whitespace: ' '\n");
    printf(is_whitespace(' ') ? "True\n" : "False\n");
    printf("Is whitespace: 'a'\n");
    printf(is_whitespace('a') ? "True\n" : "False\n");
    printf("Logarithm base 10 of 1000\n");
    printf("Log: %d \n", lg(1000));
    printf("Insert 5 numbers \n");
    int array[5] = {};
    for (int i = 0; i < 5; i++){ scanf("%d", array+i); }
    order(array, 5);
    for (int i = 0; i < 5; i++){ printf("%d ", array[i]); }
    
    return 0;
}

int is_whitespace(char input){ return (input == ' ') ? TRUE : FALSE; }

int lg(int n){
    int counter = 0;
    while(pow(LOG_BASE, ++counter) < n);
    return counter;
}

void order(int* array, int size){
    BOOL flag = TRUE;
    while (flag) {
        flag = FALSE;
        for (int i = 0; i < size - 1; i++) {
            if( array[i] > array[i + 1]){
                int a = array[i];
                array[i] = array[i+1];
                array[i+1] = a;
                flag = TRUE;
            }
        }
    }
    
}
