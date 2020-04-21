//Copia di weightedmedian adattato per permettere la selezione del pivot
//anche attraverso selection

#ifndef WEIGHTED_MEDIAN
#define WEIGHTED_MEDIAN
#endif

#include "../libs/utility.c"
#include "../libs/medians.c"

#ifdef SELECTION
    #include "../libs/selection.c"
#endif

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#ifndef FRAME_SIZE
#define FRAME_SIZE 5
#endif

/*
//Calcola la mediana inferiore pesata
//w: vettore di valori positivi 
//l: left, tiene traccia dell'estremo sinistro dell'intervallo su cui stiamo lavorando
//r: right, ad ogni iterazione lavoriamo solo sull'intervallo (l,r)
//carry: contiene la somma dei valori nell'intervallo (0,l), deve essere inizializzata a 0
//W: deve essere inizializzata con la somma di tutti i valori nel vettore w, è utilizzata 
//   per non dover essere ricalcolata ogni iterazione
//Ritorna la posizione della mediana pesata nel vettore w, che viene modificato durante
//l'esecuzione dell'algoritmo.
*/
double weightedMedianImplementation(double w[], int l, int r, double carry ,double W){
    
    #ifdef SELECTION
        double p = selection(w, l, r, ((r-l)/2)+l);    
    #else
        double p = medianOfMedians(w, l, r);    
    #endif


    int k_l, k_r;
    partition(w, l, r, &k_l, &k_r, p);

    //sum_l è la somma di tutti gli elementi nell'intervallo (l,r)
    //minori del pivot che dopo il partition sono nell'intervallo (l,k_l-1)
    double sum_l = sum(w, l, k_l-1);
    //sum_L invece è la somma di tutti gli elementi nell'intervallo (0, k_l-1)
    //che calcoliamo con il carry + sum_l poichè il carry contiene la somma
    //(0,l) e sum_l contiene (l,k_l-1), quindi (0,l) + (l,k_l-1) = (0,k_l-1)
    double sum_L = carry + sum_l;

    //Se c'è un solo elemento oppure l'intervallo individuato durante le precedenti iterazioni
    //contiene solo copie di uno stesso valore allora la mediana inferiore pesata non potrà che essere 
    //quel valore
    if(k_l == l && k_r == r) return w[k_l];

    //Se la condizione è soddisfatta ritorniamo il valore
    if(sum_L < W/2 && sum_L + sum(w, k_l, k_r) >= W/2){
        return w[k_l];
    }
    if(sum_L > W/2){
        //La somma degli elementi a sinistra del pivot è già maggiore di W/2
        //quindi dobbiamo cercare la mediana inferiore pesata 
        //nell'intervallo (l,k_l-1)

        //Il carry rimane lo stesso perchè l'estremo l non è stato modificato,
        //quindi la somma degli elementi da (0,l) non va modificata 
        return weightedMedianImplementation(w, l, k_l-1, carry, W);        
    }else{
        //La somma degli elementi a sinistra è ancora minore di W/2 e la condizione 
        //non è soddisfatta quindi dobbiamo cercare la mediana inferiore pesata nell'intervallo (k_r+1,r)

        //Il carry deve essere aumentato perchè l'estremo l è diventato k_r+1, quindi 
        //dobbiamo aggiungere al carry la somma di tutti i valori nell'intervallo (l,k_r)
        //abbiamo già la somma degli elementi in (0,l) nella variabile sum_L quindi basta 
        //aggiungere la somma delle copie del pivot a sum_L
        return weightedMedianImplementation(w, k_r+1, r, sum_L + sum(w, k_l, k_r), W);
    }
}

//Calcola la mediana pesata del vettore array di dimensione size
double weightedMedian(double array[], int size){
    double W = sum(array, 0, size);
    return weightedMedianImplementation(array, 0, size-1, 0.0, W);
}

#ifndef PROFILER

int main(int argc, char **argv){

    //Quanti elementi abbiamo letto
    int inputCount = 0;
    //I frame sono i gruppi di numeri di dimensione FRAME_SIZE
    int frames = 1;
    double *inputValues = (double *)malloc(sizeof(double) * FRAME_SIZE);

    double input;
    char delimiter;
    do{
        scanf("%lf %c", &input, &delimiter);

        //Se abbiamo raggiunto la dimensione del vettore allochiamo altri FRAME_SIZE elementi
        if(inputCount == FRAME_SIZE * frames){
            frames++;
            inputValues = (double *)realloc(inputValues, sizeof(double) * FRAME_SIZE * frames);
        }

        inputValues[inputCount] = input;
        inputCount++;

    }while(delimiter != '.');

    printf("%lf", weightedMedian(inputValues, inputCount));

    return 0;
}

#endif