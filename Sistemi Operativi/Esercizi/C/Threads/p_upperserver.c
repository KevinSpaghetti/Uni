// 
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/un.h>
#include <sys/socket.h>
#include <sys/wait.h>
#include <pthread.h>
#include <limits.h>
#include <stdlib.h>

#include "p_upperserver.h"

void upperlines(int, int);
void *handle_connection(void*);
void handle_sigchld(int);

#define SOCKADDR "/tmp/upperserver.socket"

/*
 * Implementazione di upperserver utilizzando
 * i thread al posto di creare altri processi
 */
void start_upperserver(int argc, char **argv){
    
    signal(SIGCHLD, handle_sigchld);
    
    int sock = socket(AF_LOCAL, SOCK_STREAM, 0);
    
    struct sockaddr_un addr = {
        .sun_family = AF_LOCAL,
        .sun_path = SOCKADDR
    };
    
    unlink(SOCKADDR);
    bind(sock, (struct sockaddr *)&addr, sizeof(addr));
    listen(sock, 10);
    
    printf("Listening \n");
    
    while (1) {
        //Prendo l'indirizzo del client
        struct sockaddr_un client_addr;
        socklen_t client_len = sizeof(client_addr);
        //Creo un file descriptor
        int fd = accept(sock, (struct sockaddr *)&client_addr, &client_len);
        
        //Ogni volta che il server accetta una nuova connessione creo un nuovo thread
        pthread_t connection_thread;
        pthread_create(&connection_thread, NULL, handle_connection, &fd);
        
    }
}

#define BLOCKSIZE 40

void *handle_connection(void *ptr){
    printf("Connection opened");
    int client_fd = *(int *)ptr;

    char greet[] = "Benvenuto all'UpperServer 1.0!\n";
    int greetlen = strlen(greet) + 1;
    send(client_fd, &greetlen, sizeof(greetlen), 0); // Invio lunghezza del messaggio
    send(client_fd, greet, greetlen, 0); // Invio il messaggio
    
    char inputline[BLOCKSIZE];
    int  len = 0;
    
    while ((len = recv(client_fd, inputline, BLOCKSIZE, 0)) > 0) {
        for (int i = 0; i < len; i++){
            inputline[i] = toupper(inputline[i]);
        }
        send(client_fd, inputline, len, 0);
    }
    
    close(client_fd); // Alla fine chiudiamo la connessione

    return 0;
}

void handle_sigchld(int x){
    int saved_errno = errno;
    while (waitpid(-1, 0, WNOHANG) > 0);
    errno = saved_errno;
}
