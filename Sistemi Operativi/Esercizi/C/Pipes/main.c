//
//  main.c
//  Pipes
//
//  Created by Kevin Della schiava on 22/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include <stdio.h>

#include "pipes.h"

int main(int argc, char **argv, char **envp) {
    printf("Cat using pipes \n");
    cat_pipes(argc, argv, envp);
    printf("\n");
    return 0;
}
