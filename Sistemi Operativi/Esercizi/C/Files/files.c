//
//  files.c
//  C
//
//  Created by Kevin Della schiava on 13/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <time.h>

#include "files.h"

//Definisco la macro per usare st_mtime
//anche su mac, evitando di scrivere st_mtimespec.tv_sec
#ifdef __APPLE__
    #define st_mtime st_mtimespec.tv_sec
#endif

//Argument counter: quanti argomenti ci sono
//Argument value: quali argomenti ci sono
int cat(int argc, char **filenames){
    
    if(argc < 2){
        fprintf(stderr, "Fornire il nome di almeno un file \n");
        return 1;
    }
    
    //Il primo argomento è sempre il nome del file 😱
    //[cat] [-f] [file] [file]
    
    //Per ogni file partendo dal secondo
    for (int file_index = 1; file_index < argc; file_index++) {
        fprintf(stdout, "%s \n", filenames[file_index]);
        
        FILE *opened_file = fopen(filenames[file_index], "r");
        if(opened_file < 0){
            return -1;
        }
        char carachter;
        while ((carachter = fgetc(opened_file), carachter != EOF)) {
            fputc(carachter, stdout);
        }
        fclose(opened_file);
    }
    
    return 0;
}

int diff(int argc, char **filenames){
    
    FILE *cmp_from = fopen(filenames[1], "r");
    FILE *cmp_to = fopen(filenames[2], "r");
    
    int line = 0;
    
    char from = fgetc(cmp_from);
    char to = fgetc(cmp_to);
    while (from != EOF && to != EOF) {
        if(from != to){
            printf("Diff on line %d \n", line);
            return 0;
        }
        
        if((from == to) && from == '\n') line++;
        
        from = fgetc(cmp_from);
        to = fgetc(cmp_to);
    }

    printf("No diffs");
    return 0;
}

void print_random(int count){
    
    FILE *file = fopen("/dev/urandom", "rb");
    
    int *current = malloc(sizeof(int));
    while ( count > 0 ) {
        fread(current, sizeof(int)/4, 1, file);
        printf("%d \n", *current);
        count--;
    }
    
}

void posix_print_random(int count){
    int file_descriptor = open("/dev/urandom", O_RDONLY);
    int *current = malloc(sizeof(int));
    while ( count > 0 ){
        read(file_descriptor, current, sizeof(int)/4);
        printf("%d \n", *current);
        count--;
    }
}

void count_file_modifies(const char *filename, int seconds){
    struct stat file_stat;
    
    int modify_count = 0;
    
    stat(filename, &file_stat);
    //il tempo di ultima modifica del file osservato
    time_t last_modify_time = file_stat.st_mtime;
    
    //Prendo il tempo corrente e il tempo in cui il programma dovrà
    //terminare
    time_t current_time = time(NULL);
    time_t end_time = current_time + seconds;

    while( current_time < end_time ){
        //Ricarico i metadati del file
        stat(filename, &file_stat);
        //Controllo se la data di modifica del file non corrisponde
        //a quella precedente, nel caso segno la modifca e prendo
        //la data corrente come data di ultima modifica
        if (last_modify_time != file_stat.st_mtime) {
            modify_count++;
            printf("1");
            last_modify_time = file_stat.st_mtime;
        }
        current_time = time(NULL);
    }
    
    printf("%d\n", modify_count);
}

