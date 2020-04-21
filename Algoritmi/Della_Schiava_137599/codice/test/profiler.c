//Programma per raccogliere dati sperimentali sull'algoritmo

#define PROFILER

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <string.h>
#include <unistd.h>
 
#include "./weightedmedian.c"
#include "../libs/random.c"

#define RANDOM_SEED 12345678

double secondsFromClock(clock_t end, clock_t start){
    clock_t elapsedClocks = (end - start);
    return ((double)elapsedClocks) / CLOCKS_PER_SEC;
}

double granularita(){
    clock_t t0 = clock();
    clock_t t1 = clock();
    while(t0 == t1){
        t1 = clock();
    }
    return secondsFromClock(t1, t0);
}

//Ritorna un vettore in cui ogni valore ha duplicates valori distinti
double* createSampleData(int size, int duplicates){

    double *values = (double *)malloc(sizeof(double) * size);    

    for (int i = 0; i < size;i++){
        double value = randomUniform(RANDOM_SEED, i, duplicates);
        values[i] = value;
    }

    return values;
}

//Calcola quante volte devo eseguire il programma
//per arrivare a misurare un tempo tMin
int calcolaRipTotale(int size, clock_t tMin, int duplicates){
    clock_t t0, t1 = 0;
    int rip = 1;
    while(secondsFromClock(t1, t0) < tMin){
        rip *= 2;
        double *array;
        t0 = clock();
        for (int i = 1; i < rip; i++){
            array = createSampleData(size, duplicates);
            weightedMedian(array, size);
            free(array);
        }
        t1 = clock();
    }

    int max = rip;
    int min = rip/2;
    int cicliErrati = 5;
    while((max - min) >= cicliErrati){
        rip = (max+min)/2;
        double *array;
        t0 = clock();
        for (int i = 0; i < rip; i++){
            array = createSampleData(size, duplicates);
            weightedMedian(array, size);
            free(array);
        }
        t1 = clock();
        if(secondsFromClock(t1, t0)<=tMin){
            min = rip;
        }else{
            max = rip;
        }
        
    }
    return max;
}

int calcolaRipPrepara(int size, double tMin, double duplicates){
    clock_t t0, t1 = 0;
    int rip = 1;
    while(secondsFromClock(t1, t0) <= tMin){
        rip *= 2;
        double *data;
        t0 = clock();
        for (int i = 1; i <= rip; i++){
            data = createSampleData(size, duplicates);
            free(data);
        }
        t1 = clock();
    }
    
    int max = rip;
    int min = rip/2;
    int cicliErrati = 5;
    while(max - min >= cicliErrati){
        rip = (max+min)/2;
        double *data;
        t0 = clock();
        for (int i = 1; i <= rip; i++){
            data = createSampleData(size, duplicates);
            free(data);
        }
        t1 = clock();
        if(secondsFromClock(t1, t0)<=tMin){
            min = rip;
        }else{
            max = rip;
        }
    }
    
    return max;
}

//Calcola il tempo totale di esecuzione di rip ripetizioni 
//del programma e il tempo dell' esecuzione singTime
void calcoloTempi(double array[], int size, int rip){
    clock_t t0 = clock();
    for (int i = 0; i < rip; i++){
        weightedMedian(array, size);
    }
    clock_t t1 = clock();

    double totalTime = secondsFromClock(t1, t0);
    double singTime = totalTime / rip;

    printf("%d , %lf , %lf \n", size, totalTime, singTime);
}

double tempoMedioNetto(int size, double tMin, int duplicates){
    double ripTara = calcolaRipPrepara(size, tMin, duplicates);
    double ripLordo = calcolaRipTotale(size, tMin, duplicates);
    clock_t t0 = clock();
    for(int i=1; i <= ripTara; i++){
        double *e = createSampleData(size, duplicates);
        free(e);
    }
    clock_t t1 = clock();
    double tTara = secondsFromClock(t1, t0);
    t0 = clock();
    for(int i=1; i <= ripLordo; i++){
        double *e = createSampleData(size, duplicates);
        weightedMedian(e, size);
        free(e);
    }
    t1 = clock();
    double tLordo = secondsFromClock(t1, t0);
    
    double tMedio = ((tLordo / ripLordo) - (tTara / ripTara));

    return tMedio;
}

void misurazione(int size, int c1, double za, double tMin, double bound, double* e, double *d, int duplicates){
    double t = 0;
    double sum2 = 0;
    double cn = 0;
    do{
        for(int i=1; i<=c1; i++){
            double m = tempoMedioNetto(size, tMin, duplicates);
            t = t + m;
            sum2 += (m*m);
        }
        cn = cn +c1;
        *e = t/cn;
        double s = sqrt(sum2/cn - ((*e)*(*e)));
        *d = (1/sqrt(cn)) * za * s;
    }while(*d<=bound);
    
}

int main(int argc, char **argv){

    int opt;
    int arg_passed = 0;
    int start = 1000, end = 1000, step = 1000;
    //-1 vuol dire che non ci sono ripetizioni in quantità significative
    int duplicates = -1;

    while((opt = getopt(argc, argv, "s:m:r:")) != -1){
        switch (opt) {
            case 's':
                arg_passed = 1;
                sscanf(optarg, "%d", &start);
                end = start;
                step = start;
                break;
            case 'm':
                arg_passed = 1;
                sscanf(optarg, "%d", &start);
                sscanf(argv[optind], "%d", &end);
                sscanf(argv[optind+1], "%d", &step);
                break;
            case 'r':
                arg_passed = 1;
                sscanf(optarg, "%d", &duplicates);
                break;
        }
    }

    if(arg_passed == 0){
        printf("-s [n]: Misurazione singola con n dati\n");
        printf("-m [start] [end] [step]: Misurazioni multiple partendo da start fino a end con passo step\n");    
        return 1;
    }

    double e = 0;
    double delta = 0;
    double deltaBound = granularita() * 0.05;
    double tMin = granularita();
    int repetitions = 10;
    double za = 2.33;

    //e è in millisecondi

    printf("size, e, tMin, delta, delta-bound, repetitions, za\n");
    for (int i = start; i <= end; i+=step){
        misurazione(i, repetitions, za, tMin, deltaBound, &e, &delta, duplicates);
        printf("%d, %lf, %lf, %lf, %e, %d, %lf \n", i, e*1000, tMin, delta, deltaBound, repetitions,  za);
    }

    return 0;
}
