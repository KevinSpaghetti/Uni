#include "medians.c"
#include "utility.c"

//Ritorna la posizione del valore che finirebbe in posizione
//K se l'array fosse ordinato
double selection(double array[], int start, int end, int k){
    double medianOfMediansVal = medianOfMedians(array, start, end);

    int start_pos, end_pos;
    partition(array, start, end, &start_pos, &end_pos, medianOfMediansVal);

    if(start_pos <= k && end_pos >= k){
        return array[start_pos];
    }
    if(start_pos > k){
        return selection(array, start, start_pos-1, k);
    }
    if(end_pos < k){
        return selection(array, end_pos+1, end, k);
    }

}