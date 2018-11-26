
#include "pipes.h"
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>

#define LINE_SIZE 2048

#define STDIN 0
#define STDOUT 1
#define STDERR 2

void cat_pipes(int argc, char **argv, char **envp){
    
    char *filename = argv[1];
    unsigned long filename_size = strlen(filename);
    
    int father_to_son[2] = { };
    if(pipe(father_to_son) == -1){
        perror("Pipe() error");
    }
    
    pid_t process_pid = fork();
    
    if(process_pid > 0){
        //Chiudo la pipe in lettura, voglio solo scrivere
        close(father_to_son[0]);
        //Scrivo il nome del file in lettura
        FILE *file = fopen(filename, "r");
        char cr = getc(file);
        while (cr != EOF) {
            write(father_to_son[1], &cr, 1);
            cr = getc(file);
        }
        close(father_to_son[1]);
    }
    
    if(process_pid == 0){
        //Chiudo la pipe father_to_son in scrittura
        //Siccome il figlio non deve poter scrivere al padre
        close(father_to_son[1]);
        //Redireziono la pipe father_to_son in lettura allo stdin
        dup2(father_to_son[0], STDIN);
        execlp("cat", "cat", NULL);
    }
    
    close(father_to_son[0]);
    close(father_to_son[1]);
    waitpid(process_pid, NULL, 0);
}
