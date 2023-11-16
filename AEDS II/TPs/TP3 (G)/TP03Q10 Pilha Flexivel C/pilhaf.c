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

void ler(FILE* entrada, Jogador* jogadores){
    char linha[300];
    fscanf(entrada, "%s", linha);
    char props[8][100];
    int i = 0;
    fgets(linha, 300, entrada);
    while(!feof(entrada)){
        fgets(linha, 300, entrada);

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
    fclose(entrada);
}

typedef struct Cel{
    Jogador jogador;
    struct Cel* prox;
} Cel;


typedef struct Pilha{
    Cel* topo;
} Pilha;

Cel* newCel(Jogador jogador){
    Cel* tmp = (Cel*) malloc(sizeof(Cel));
    tmp->jogador = jogador;
    tmp->prox = NULL;
    return tmp;
}

Pilha* newPilha(){
    Pilha* pilha = (Pilha*) malloc(sizeof(Pilha));
    pilha->topo = (Cel*) malloc(sizeof(Cel));
    pilha->topo->prox = NULL;
    return pilha;
}


Jogador remover(Pilha* pilha){
    Cel* tmp = pilha->topo->prox;
    Jogador removido = tmp->jogador;
    pilha->topo->prox = tmp->prox;
    printf("(R) %s\n", removido.nome);
    free(tmp);
    return removido;
}


void inserir(Pilha* pilha, Jogador jogador){
    Cel* tmp = newCel(jogador);
    tmp->prox = pilha->topo->prox;
    pilha->topo->prox = tmp;
}

void mostrar(Pilha* pilha){
    int count = 0;
    for(Cel* i = pilha->topo->prox;i!=NULL;i=i->prox){
        count++;
    }
    Cel* tmp = pilha->topo->prox;
    for(int i=count-1;i>=0;i--){
        tmp = pilha->topo->prox;
        for(int j=0;j<i;j++){
            tmp = tmp->prox;
        }
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", count - i - 1, tmp->jogador.nome, tmp->jogador.altura, tmp->jogador.peso, tmp->jogador.anoNascimento, tmp->jogador.universidade, tmp->jogador.cidadeNascimento, tmp->jogador.estadoNascimento);

    }
}

int main(void){
    FILE* entrada = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    ler(entrada, jogadores);

    Pilha* pilha = newPilha();

    char* linha = (char*) malloc(23 * sizeof(char));

    scanf("%s", linha);

    while(strcmp(linha, "FIM") != 0){
        inserir(pilha, jogadores[atoi(linha)]);
        scanf("%s", linha);
    }

    int n;
    scanf("%d", &n);

    for(int i=0;i<n;i++){
      char* metodo = (char*) malloc(3 * sizeof(char));
      scanf("%s", metodo);
      if(strcmp(metodo, "I") == 0){
        int id;
        scanf("%d", &id);
        inserir(pilha, jogadores[id]);
      }else if(strcmp(metodo, "R") == 0){
        remover(pilha);
      }
      free(metodo);
    }

    mostrar(pilha);

    return 0;
}