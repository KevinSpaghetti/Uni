
#include <stdio.h>

void printSum(void);
void _reverse(int*, int);
void _qsort(int*, int);
void reverseFromStdin(void);
void sortFromStdin(void);

int main(int argc, const char * argv[]) {
    int array[] = {5, 4, 3, 2, 1, 7, 8, 5};
    
    printf("Print the sum: \n");
    printSum();
    printf("Reverse \n");
    reverse(array, sizeof(array)/sizeof(int));
    printf("Sort \n");
    _qsort(array, sizeof(array)/sizeof(int));
    printf("Reverse from stdin \n");
    reverseFromStdin();
    printf("Sort from stdin \n");
    sortFromStdin();
    
    return 0;
}

void printSum(){
    int count = 10;
    int input_number = 0;
    int sum = 0;
    while (count > 0) {
        printf("Insert number: ");
        scanf("%d", &input_number);
        sum += input_number;
        count--;
    }
    printf("Sum is: %d \n", sum);
}

void _reverse(int *array, int size){
    for (int i = 0; i < size/2 ; i++) {
        int a = array[i];
        array[i] = array[size - i - 1];
        array[size - i - 1] = a;
    }
}

void _qsort(int *array, int size){
    if(size == 1){ return ; }
    int max_index = 0;
    for (int i = 0; i < size; i++) {
        if (array[i] > array[max_index]) { max_index = i; }
    }
    int a = array[max_index];
    array[max_index] = array[size - 1];
    array[size - 1] = a;
    _qsort(array, size - 1);
}

void reverseFromStdin(){
    int count = 10;
    int input_number = 0;
    int *array = (int*) malloc(sizeof(int)*count);
    for (int i=0; i < count; i++) {
        printf("Insert number: ");
        scanf("%d", &input_number);
        array[i] = input_number;
    }
    _reverse(array, count);
    for (int i = 0; i < count; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}

void sortFromStdin(){
    int count = 10;
    int input_number = 0;
    int *array = (int*) malloc(sizeof(int) * count);
    for (int i=0; i < count; i++) {
        printf("Insert number: ");
        scanf("%d", &input_number);
        array[i] = input_number;
    }
    _qsort(array, count);
    for (int i = 0; i < count; i++) { printf("%d ", array[i]); }
    printf("\n");
}
