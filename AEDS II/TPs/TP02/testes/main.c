#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

typedef struct Jogador{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[50];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

void imprimir(Jogador jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

int cmpStrings(char* str1, char* str2){
    int i = 0;
    while(str1[i] != '\0' && str2[i] != '\0'){
        if(str1[i] > str2[i]){
            return 1;
        }else if(str1[i] < str2[i]){
            return -1;
        }
        i++;
    }
    if(str1[i] == '\0' && str2[i] != '\0'){
        return -1;
    }else if(str1[i] != '\0' && str2[i] == '\0'){
        return 1;
    }else{
        return 0;
    }
}

void bubbleSort(Jogador* jogadores, int n, int* comparacoes, int* movimentacoes){
    for(int i=0;i<n-1;i++){
        for(int j=0;j<n-i-1;j++){
            if(jogadores[j].anoNascimento>jogadores[j+1].anoNascimento || (jogadores[j].anoNascimento == jogadores[j+1].anoNascimento && cmpStrings(jogadores[j].nome, jogadores[j+1].nome) == 1)){
                (*comparacoes)++;
                Jogador aux = jogadores[j];
                jogadores[j] = jogadores[j+1];
                jogadores[j+1] = aux;
                *(movimentacoes)+=3;
            }
        }
    }
}


int main(void){
    FILE* mignon;
    mignon = fopen("/tmp/players.csv", "r");
    char linha[300];
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));
    fscanf(mignon, "%s", linha);
    char props[8][50];
    int i = 0;
    fgets(linha, 300, mignon);
    while(!feof(mignon)){
        fgets(linha, 300, mignon);

        int j = 0;
        int a = 0;
        for(int k=0;k<strlen(linha);k++){
            if(linha[k] == ',' || linha[k] == '\n'){
                props[j][a] = '\0';
                j++;
                a = 0;
            }else{
                props[j][a] = linha[k];
                a++;
            }
        }

        for(int b=0;b<8;b++){
            if(strcmp(props[b], "") == 0){
                strcpy(props[b], "nao informado");
            }
        }

        jogadores[i].id = atoi(props[0]);
        strcpy(jogadores[i].nome, props[1]);
        jogadores[i].altura = atoi(props[2]);
        jogadores[i].peso = atoi(props[3]);
        strcpy(jogadores[i].universidade, props[4]);
        jogadores[i].anoNascimento = atoi(props[5]);
        strcpy(jogadores[i].cidadeNascimento, props[6]);
        strcpy(jogadores[i].estadoNascimento, props[7]);

        i++;
    }
    fclose(mignon);
    char entrada[5];
    scanf("%s", entrada);
    int id = atoi(entrada);
    int n = 1;
    int ids[10000];
    ids[0] = id;
    while(strcmp(entrada, "FIM") != 0){
        scanf("%s", entrada);
        if(strcmp(entrada, "FIM") == 0){
            break;
        }
        id = atoi(entrada);
        ids[n] = id;
        n++;
    }
    ids[n+1] = -1;
    char nomes[n][50];
    
    Jogador players[n];

    for(int i=0;i<n;i++){
        players[i] = jogadores[ids[i]];
    }

    char entradaNome[50];
    int comparacoes = 0;
    int movimentacoes = 0;
    clock_t before = clock();
    
    bubbleSort(players, n, &comparacoes, &movimentacoes);

    clock_t after = clock();
    double tempo = ((double) (after - before)) / CLOCKS_PER_SEC * 1000.0;

    FILE* alcatra = fopen("matri­cula_bolha.txt", "w");
    fprintf(alcatra, "808356\t%d\t%d\t%lf", comparacoes, movimentacoes, tempo);
    fclose(alcatra);

    for(int i=0;i<n;i++){
        imprimir(players[i]);
        printf("\n");
    }

    return 0;
}