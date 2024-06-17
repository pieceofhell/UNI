/*Escreva um código em C, que calcule o fatorial de um número, usando o método recursivo, informado como argumento. Argumento, nesse caso, sendo fornecido pelo terminal, como por exemplo: ./meuprograma 20*/

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h> // Add this line to include the necessary header file

int fatorial(int n){
    if(n == 0){
        return 1;
    }else{
        return n*fatorial(n-1);
    }
}

int main(int argc, char *argv[]){
    unsigned long long n = atoll(argv[1]);
    printf("Fatorial de %llu = %llu\n", n, fatorial(n));
    return 0;
}