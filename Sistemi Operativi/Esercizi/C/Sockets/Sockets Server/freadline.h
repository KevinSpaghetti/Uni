/*
 * freadline.h
 *
 * File contenente funzioni varie utili per l'implementazione di file_exec.c
 */

#ifndef FREADLINE_H
#define FREADLINE_H

#include <stdio.h>

/*
 * Legge caratteri in input da 'file' fino ad EOF, o fino al newline '\n',
 * e ritorna una stringa allocata dinamicamente con il contenuto.
 * Se 'line' non è NULL, sovrascrive la stringa puntata da 'line', che deve
 * essere stata precedentemente allocata dinamicamente, che verrà realocata in
 * caso di necessità.
 *
 * Se la funzione viene chiamata su un file già finito (feof(file) restituisce
 * true), la funzione libera la memoria di 'line' e restituisce NULL.
 */
char *freadline(FILE *file, char *line);

#endif
