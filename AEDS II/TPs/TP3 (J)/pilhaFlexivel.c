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

typedef struct Cell{
    Jogador jogador;
    struct Cell* next;
} Cell;


typedef struct PilhaFlexivel{
    Cell* head;
} PilhaFlexivel;

Cell* newCell(Jogador jogador){
    Cell* tmp = (Cell*) malloc(sizeof(Cell));
    tmp->jogador = jogador;
    tmp->next = NULL;
    return tmp;
}

PilhaFlexivel* newPilhaFlexivel(){
    PilhaFlexivel* pf = (PilhaFlexivel*) malloc(sizeof(PilhaFlexivel));
    pf->head = (Cell*) malloc(sizeof(Cell));
    pf->head->next = NULL;
    return pf;
}


Jogador remover(PilhaFlexivel* pf){
    if(pf->head->next == NULL){
        printf("Erro ao remover no inicio");
        exit(1);
    }
    Cell* tmp = pf->head->next;
    Jogador removido = tmp->jogador;
    pf->head->next = tmp->next;
    free(tmp);
    return removido;
}


void inserir(PilhaFlexivel* pf, Jogador jogador){
    Cell* tmp = newCell(jogador);
    tmp->next = pf->head->next;
    pf->head->next = tmp;
}

void print(PilhaFlexivel* pf){
    int count = 0;
    for(Cell* i = pf->head->next;i!=NULL;i=i->next){
        count++;
    }
    Cell* tmp = pf->head->next;
    for(int i=count-1;i>=0;i--){
        tmp = pf->head->next;
        for(int j=0;j<i;j++){
            tmp = tmp->next;
        }
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", count - i - 1, tmp->jogador.nome, tmp->jogador.altura, tmp->jogador.peso, tmp->jogador.anoNascimento, tmp->jogador.universidade, tmp->jogador.cidadeNascimento, tmp->jogador.estadoNascimento);

    }
}

void scanCommand(PilhaFlexivel* pf, Jogador* jogadores){
    char* command = (char*) malloc(3 * sizeof(char));
    scanf("%s", command);
    if(strcmp(command, "I") == 0){
        int id;
        scanf("%d", &id);
        inserir(pf, jogadores[id]);
    }else if(strcmp(command, "R") == 0){
        Jogador removido = remover(pf);
        printf("(R) %s\n", removido.nome);
    }
    free(command);
}




int main(void){
    FILE* mignon = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    readFile(mignon, jogadores);

    PilhaFlexivel* pf = newPilhaFlexivel();

    char* line = (char*) malloc(23 * sizeof(char));
    
    scanf("%s", line);

    while(strcmp(line, "FIM") != 0){
        inserir(pf, jogadores[atoi(line)]);
        scanf("%s", line);
    }

    int n;
    scanf("%d", &n);

    for(int i=0;i<n;i++){
        scanCommand(pf, jogadores);
    }

    print(pf);

    return 0;
}