//
//  permissions.c
//  C
//
//  Created by Kevin Della schiava on 14/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include "permissions.h"
#include <unistd.h>
#include <stdlib.h>

//file_exec file.txt cat -f @
void file_exec(int argc, char **argv, char **envp){
    
    char *filename = argv[1];
    char *command = argv[2];
    
    FILE *filep = fopen(filename, "r");
    if (!filep) { return ; }
    
    char line[256];

    while(fgets(line, 256, filep)){
        printf("Line: %s \n", line);
        char **cmd_argv = malloc(sizeof(char*) * argc-2);
        int index = 0;
        int arg_index = 2;
        while (argv[arg_index]) {
            printf("%d :", index);
            if(argv[arg_index][0] == '@'){
                cmd_argv[++index] = line;
                arg_index++;
            }else{
                cmd_argv[++index] = argv[arg_index++];
            }
            printf("%s \n", cmd_argv[index]);
        }
        cmd_argv[index] = NULL;
        
        execve(command, cmd_argv, envp);
        
        free(cmd_argv);
    }
}
