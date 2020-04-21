#ifndef UTILITY
#define UTILITY

#include <stdio.h>

void partition(double array[], int start, int end, int* i, int* j,  double pivot);
void swapArrayElements(double *array, int first, int second);

//Partiziona l'array da start a end(inclusi) attorno al numero
//pivot, divide il vettore in 3 intervalli:
//i numeri minori del pivot: [l,p_l[
//i numeri maggiori del pivot: ]p_r,r]
//i numeri uguali al pivot: [p_l,p_r]
//poi ritorna l'inizio(p_l) in i e la fine(p_r) in j del gruppo di numeri uguali
void partition(double array[], int l, int r, int* i, int* j, double pivot){
    int p_l = l;
    int p_r = r;
    int mid = l;

    while(mid <= p_r){
        if(array[mid] < pivot){
            swapArrayElements(array, p_l, mid);
            p_l++;
            mid++;
        }
        if(array[mid] > pivot){
            swapArrayElements(array, mid, p_r);
            p_r--;
        }
        if(array[mid] == pivot){
            mid++;
        }
        
    }

    *i = p_l;
    *j = p_r;
}

//Scambia il valore dei due numeri in posizione first e second nel vettore array
void swapArrayElements(double *array, int first, int second){
    double temp = array[first];
    array[first] = array[second];
    array[second] = temp;
}

//Ritorna la somma da start(incluso) a end(incluso)
double sum(double arr[], int start, int end){
    double sum = 0;
    for(int i = start; i <= end; i++) sum += arr[i];
    return sum;
}

#endif