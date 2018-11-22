//
//  main.c
//  I/O
//
//  Created by Kevin Della schiava on 22/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdio.h>

void printEOF(void);
void countChars(void);
void wordLengthIstogram(void);
void wordCount(void);

int main(int argc, const char * argv[]) {
    
    printf("Print EOF \n");
    printEOF();
    printf("Count chars \n");
    countChars();
    printf("Word length istogram \n");
    wordLengthIstogram();
    printf("Word count \n");
    wordCount();
    return 0;
}

void printEOF(){ printf("%c\n", EOF); }

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
    printf("%ld \n", wordCount);
}
