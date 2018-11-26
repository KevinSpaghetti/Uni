
#include <stdio.h>

#include "pipes.h"

int main(int argc, char **argv, char **envp) {
    printf("Cat using pipes \n");
    cat_pipes(argc, argv, envp);
    printf("\n");
    return 0;
}
