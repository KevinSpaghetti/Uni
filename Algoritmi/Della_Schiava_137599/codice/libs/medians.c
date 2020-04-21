
#ifndef MEDIANS
#define MEDIANS

#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include "order.c"

//Un frame è un intervallo dell'array
//utilizzato per l'algoritmo medianOfMedians

//definisce la dimensione di un frame
#define FRAME_SIZE 5

//Trova il mediano ordinando l'array tra start e end
double findMedian(double array[], int start, int end){
    order(array, start, end);
    int medianIndex = start + floor((end-start)/2);
    return array[medianIndex];
}

//Algoritmo MEDIANOFMEDIANS per trovare un elemento
//che approssima il mediano
double medianOfMedians(double array[], int start, int end){

    //Se ci sono meno di 5 elementi allora abbiamo il 
    //mediano dei mediani
    if(end-start < FRAME_SIZE){
        order(array, start, end);
        int m_pos = start + floor((end-start) / 2);
        return array[m_pos];
    }

    //Per ogni gruppo troviamo il mediano, lo scambiamo 
    //con uno dei primi elementi e poi ricorriamo su questa parte
    //di array che ora contiene i mediani di ogni gruppo. 

    int nOfGroups = floor((end-start)/FRAME_SIZE);
    int nOfMedians = nOfGroups;

    //i tiene traccia della parte iniziale del vettore che
    //è stata riempita con i mediani.
    int i = 0;
    while(i < nOfGroups){
        int frameStart = start + i*FRAME_SIZE;
        int frameEnd = frameStart+FRAME_SIZE;
        int m_pos = frameStart + floor(FRAME_SIZE / 2);
        order(array, frameStart, frameEnd);
        //Scambio il mediano mettendolo nella prima posizione libera   
        swapArrayElements(array, m_pos, start+i);
        i++;
    }
    
    int nOfLastItems = (end-start) % FRAME_SIZE;
    if(nOfLastItems > 0){        
        order(array, start + FRAME_SIZE*nOfGroups, end);
        int m_pos = start + FRAME_SIZE*nOfGroups + floor((nOfLastItems) / 2);
        swapArrayElements(array, m_pos, start+i);
        i++;
        nOfMedians++;
    }

    //Ricorriamo sulla parte del vettore che contiene i mediani
    return medianOfMedians(array, start, start+nOfMedians);
}

#endif