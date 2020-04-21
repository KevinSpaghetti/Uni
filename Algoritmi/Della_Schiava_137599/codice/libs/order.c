
#ifndef ORDER
#define ORDER

//Ordina il vettore dalla posizione di start (inclusa)
//a quella di end (inclusa), la posizione di start e di end
//devono essere contenute nel vettore
void order(double *vector, int start, int end){
    for (int index = start; index < end; index++) {
        double aux = vector[index];
        int j = index;
        while(j > start && aux < vector[j-1]){
            vector[j] = vector[j-1];
            j--;
        }
        vector[j] = aux;
    }
}

#endif