//
//  main.c
//  Files
//
//  Created by Kevin Della schiava on 22/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdio.h>

#include "files.h"

int main(int argc, char **argv, char **envp) {
    printf("cat %s %s \n", argv[1], argv[2]);
    cat(argc, argv);
    printf("\n");
    printf("diff %s %s \n", argv[1], argv[2]);
    diff(argc, argv);
    printf("Print output from /dev/urandom");
    print_random(10);
    printf("Print output from /dev/urandom (POSIX version)\n");
    posix_print_random(10);
    
    printf("Printing file modifications\n");
    char *filename = argv[1];
    int seconds = 0;
    sscanf(argv[2], "%d", &seconds);
    count_file_modifies(filename, seconds);
    return 0;
}
