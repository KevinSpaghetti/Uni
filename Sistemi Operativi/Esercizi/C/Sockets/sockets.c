
#include "sockets.h"
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/un.h>

#define SOCKADDR "/tmp/upperserver.socket"

char *read_handshake(int socket){
    int size = 0;
    recv(socket, &size, sizeof(int), 0);
    char *message = malloc(size);
    recv(socket, message, size, 0);
    return message;
}

char *read_next(int socket, int size){
    char *message = malloc(size);
    recv(socket, message, size, 0);
    return message;
}

void send_message(int socket ,char* message){
    send(socket, message, strlen(message), 0);
}

void read_line(char* message){
    char c = getchar();
    int index = 1;
    while (c != '\n') {
        *(message++) = c;
        c = getchar();
    }
}

void echo_client(int argc, char **argv){
    int sock = socket(AF_LOCAL, SOCK_STREAM,  0);
    struct sockaddr_un addr = {
        .sun_family = AF_LOCAL,
        .sun_path = SOCKADDR
    };
    connect(sock, (struct sockaddr *)&addr, sizeof(addr));
    char *initial_message = read_handshake(sock);
    printf("%s", initial_message);
    while (1) {
        char* message = malloc(128);
        read_line(message);
        int msg_size = strlen(message);
        send_message(sock, message);
        message = read_next(sock, msg_size);
        printf("%s\n", message);
    }
    
}
