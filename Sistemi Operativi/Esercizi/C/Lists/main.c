
#include <stdio.h>

#include "lists.h"

int main(int argc, const char * argv[]) {
    struct node *head = create(5);
    append(head, 4);
    append(head, 6);
    append(head, 9);
    printf("List count: %d \n", length(head));
    printf("List find element 6: %d \n", find(head, 6));
    printf("List find element 1: %d \n", find(head, 1));
    return 0;
}
