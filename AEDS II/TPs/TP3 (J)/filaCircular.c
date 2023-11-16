#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Jogador{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

void readFile(FILE* mignon, Jogador* jogadores){
    char linha[300];
    fscanf(mignon, "%s", linha);
    char props[8][100];
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
}


void imprimir(Jogador jogador){
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
}

typedef struct FilaCircular{
    Jogador* arr;
    int inicio;
    int fim;
    int total;
} FilaCircular;

FilaCircular* newFilaCircular(){
    FilaCircular* fc = (FilaCircular*) malloc(sizeof(FilaCircular));
    fc->arr = (Jogador*) malloc(6 * sizeof(Jogador));
    fc->inicio = 0;
    fc->fim = 0;
    fc->total = 6;
    return fc;
}

void inserir(FilaCircular* fc, Jogador jogador){
    if((fc->fim+1)%fc->total == fc->inicio){
        fc->inicio = (fc->inicio+1)%fc->total;
    }
    fc->arr[fc->fim] = jogador;
    fc->fim = (fc->fim+1)%fc->total;
}

Jogador remover(FilaCircular* fc){
    if(fc->inicio == fc->fim){
        printf("Erro ao remover no inicio");
        exit(1);
    }
    Jogador removido = fc->arr[fc->inicio];
    fc->inicio = (fc->inicio+1)%fc->total;
    return removido;
}

void print(FilaCircular* fc){
    int a = 0;
    for(int i=fc->inicio;i!=fc->fim;i = (i+1)%fc->total){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", a, fc->arr[i].nome, fc->arr[i].altura, fc->arr[i].peso, fc->arr[i].anoNascimento, fc->arr[i].universidade, fc->arr[i].cidadeNascimento, fc->arr[i].estadoNascimento);
        a++;
    }
}

int getAverageAltura(FilaCircular* fc){
    float alturaTotal = 0;
    int amount = 0;
    for(int i=fc->inicio; i!=fc->fim; i = (i+1)%fc->total){
        alturaTotal += fc->arr[i].altura;
        amount++;
    }
    alturaTotal /= amount;
    return (int)(alturaTotal+0.5); 
}

void scanCommand(FilaCircular* fc, Jogador* jogadores){
    char* command = (char*) malloc(3 * sizeof(char));
    scanf("%s", command);
    if(strcmp(command, "I") == 0){
        int id;
        scanf("%d", &id);
        inserir(fc, jogadores[id]);
        printf("%d\n", getAverageAltura(fc));
    }else if(strcmp(command, "R") == 0){
        Jogador removido = remover(fc);
        printf("(R) %s\n", removido.nome);
    }
    free(command);
}




int main(void){
    FILE* mignon = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    readFile(mignon, jogadores);

    FilaCircular* fc = newFilaCircular();

    char* line = (char*) malloc(23 * sizeof(char));
    
    scanf("%s", line);

    while(strcmp(line, "FIM") != 0){
        inserir(fc, jogadores[atoi(line)]);
        printf("%d\n", getAverageAltura(fc));
        scanf("%s", line);
    }

    int n;
    scanf("%d", &n);

    for(int i=0;i<n;i++){
        scanCommand(fc, jogadores);
    }

    print(fc);

    return 0;
}