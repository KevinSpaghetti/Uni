// 
#include <stdio.h>
#include <pthread.h>
#include <limits.h>
#include <stdlib.h>
#include <sys/stat.h>

#include "p_readfile.h"

struct data_t {
    int *data;
    int base;
    int limit;
};

void *init_array_chunk(void *ptr);

/* Legge un file, lo interpreta come una
 * sequenza di numeri e ne trova il massimo
 * parallelizzando il lavoro
 */
void p_readfile(int argc, char **argv){
    const int array_size = 100;
    int *array = malloc(array_size);
    
    int thread_number = 10;
    pthread_t *threads = malloc(thread_number * sizeof(pthread_t));
    
    
    int workload_per_thread = array_size / thread_number;
    printf("%d\n", workload_per_thread);
    for (int i = 0; (i * workload_per_thread) < array_size; i++) {
        pthread_t thread;
        struct data_t data = {
            array,
            i * workload_per_thread,
            (i+1) * workload_per_thread - 1
        };
        printf("base: %d\n", i * workload_per_thread);
        printf("limit: %d\n", (i+1) * workload_per_thread - 1);
        pthread_create(&thread, NULL, init_array_chunk, &data);
        threads[i] = thread;
    }
    
    void *results = malloc(thread_number * sizeof(void*));
    for (int i = 0; i < thread_number; i++) {
        pthread_join(threads[i], &results[i]);
    }
    
    for (int i = 0; i < array_size; i++) {
        printf("%d\n", array[i]);
    }
}

void *init_array_chunk(void *ptr){
    struct data_t data = *(struct data_t *)ptr;
    
    int* array = data.data;
    int base = data.base;
    int limit = data.limit;
    for (int i = base; i <= limit; i++) {
        array[i] = i;
    }
    
    return 0;
}
