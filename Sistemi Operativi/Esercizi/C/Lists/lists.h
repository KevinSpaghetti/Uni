
#ifndef lists_h
#define lists_h

#include <stdio.h>

struct node {
    int data;
    struct node *next;
};

struct node *create(int data);
int length(struct node *head);
int find(struct node *head, int data);
struct node *last(struct node *head);
void append(struct node *head, int data);
void destroy(struct node *head);

#endif /* lists_h */
