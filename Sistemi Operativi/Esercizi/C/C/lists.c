//
//  lists.c
//  C
//
//  Created by Kevin Della schiava on 13/11/18.
//  Copyright © 2018 Esercizi. All rights reserved.
//

#include "lists.h"
#include <stdlib.h>

struct node *create(int data){
    struct node *new_node = malloc(sizeof(struct node));
    new_node->data = data;
    new_node->next = NULL;
    
    return new_node;
}

int length(struct node *head){
    if(!head) return 0;
    return length(head->next) + 1;
}

int find(struct node *head, int data){
    if(!head) return -1;
    if(head->data == data) return 0;
    int val = find(head->next, data);
    if(val >= 0) return ++val;
    return -1;
}

struct node *last(struct node *head){
    if(!(head->next)) return head;
    return last(head->next);
}

void append(struct node *head, int data){
    struct node *tail = malloc(sizeof(struct node));
    tail->data = data;
    tail->next = NULL;
    struct node *last_node = last(head);
    last_node->next = tail;
}

void destroy(struct node *head){
    if(head->next) {
        free(head);
        destroy(head->next);
    }
}
