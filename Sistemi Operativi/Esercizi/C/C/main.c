#include <stdio.h>
#include <math.h>

#include "Complex.h"
#include "lists.h"
#include "files.h"

#define BOOL int
#define FALSE 0
#define TRUE  1

#define LOG_BASE 10

//Esercizi
void printEOF(void);
void countChars(void);
void wordLengthIstogram(void);
void wordCount(void);

//Funzione
int is_whitespace(char);
int lg(int);
void order(int*,int);

//Puntatori
void printSum(void);
void _reverse(int*, int);
void _qsort(int*, int);
void reverseFromStdin(void);
void sortFromStdin(void);


int main(int argc, char **argv) {
    
    /*
    int array[] = {5, 4, 3, 2, 1, 7, 8, 5};
    
    printEOF();
    countChars();
    wordLengthIstogram();
    wordCount();
    
    order(array, 5);
    
    printSum();
    reverse(array, sizeof(array)/sizeof(int));
    _qsort(array, sizeof(array)/sizeof(int));
    reverseFromStdin();
    sortFromStdin();
    */
    
    /* //Structs e puntatori
    struct complex number = {
        8.0,
        4.0
    };
    
    printf("cabs: %f \n", _cabs(number));
    printf("angle: %f \n", _angle(number));
    
    struct complex *created = from_polar(20.0, 10.0);
    printf("Created: %f, %f \n", created->real, created->immaginary);
    
    /*
    struct node *head = create(5);
    append(head, 6);
    append(head, 9);
    append(head, 5);
    
    printf("List count: %d \n", length(head));
    printf("List find element 6: %d \n", find(head, 6));
    printf("List find element 1: %d \n", find(head, 1));
    */
    
    //File
    /*
    cat(argc, argv);
    */
    diff(argc, argv);
    
    
    return 0;
}

void printEOF(){
    printf("%c\n", EOF);
}

void countChars(){
    
    long spaces = 0;
    long tabs = 0;
    long newlines = 0;
    
    char input = getchar();
    
    while (input != EOF) {
        
        if( input == ' ' ){ spaces++; }
        if( input == '\n' ){ newlines++; }
        if( input == '\t' ) {tabs++; }
        
        input = getchar();
    }
    
    printf("Spaces: %ld \n", spaces);
    printf("Tabs: %ld \n", tabs);
    printf("Newlines: %ld \n", newlines);
}

void wordLengthIstogram(){
    
    char input = getchar();
    
    while (input != EOF) {
        if( input != '\n' ){
            putchar('-');
        }else{
            putchar('\n');
        }
        
        input = getchar();
    }
    
    
}

void wordCount(){
    long wordCount = 0;
    
    char input = getchar();
    while ( input != EOF ) {
        if( input == ' ') { wordCount++; }
        input = getchar();
    }
    wordCount++;
    
    printf("Word Count: %ld \n", wordCount);
}

int is_whitespace(char input){
    if( input == ' ' ){
        return TRUE;
    }else{
        return FALSE;
    }
}

int lg(int n){
    int counter = 1;
    while ( pow(LOG_BASE, counter) <= n) {
        counter++;
    }
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
    if(size == 1){
        return ;
    }
    
    int max_index = 0;
    for (int i = 0; i < size; i++) {
        if (array[i] > array[max_index]) {
            max_index = i;
        }
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
    
    for (int i = 0; i < count; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");
}
