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


typedef struct FilaFlexivel{
    Cell* head;
    Cell* tail;
    int size;
} FilaFlexivel;

Cell* newCell(Jogador jogador){
    Cell* tmp = (Cell*) malloc(sizeof(Cell));
    tmp->jogador = jogador;
    tmp->next = NULL;
    return tmp;
}

FilaFlexivel* newFilaFlexivel(){
    FilaFlexivel* ff = (FilaFlexivel*) malloc(sizeof(FilaFlexivel));
    ff->head = (Cell*) malloc(sizeof(Cell));
    ff->tail = ff->head;
    ff->size = 0;
    return ff;
}


Jogador remover(FilaFlexivel* ff){
    if(ff->head == ff->tail){
        printf("Erro ao remover no inicio");
        exit(1);
    }
    Jogador removido = ff->head->next->jogador;
    Cell* tmp = ff->head;
    ff->head = ff->head->next;
    free(tmp);
    (ff->size)--;
    return removido;
}

void inserir(FilaFlexivel* ff, Jogador jogador){
    if(ff->size == 5){
        remover(ff);
    }
    Cell* tmp = newCell(jogador);
    ff->tail->next = tmp;
    ff->tail = tmp;
    (ff->size)++;
}

void print(FilaFlexivel* ff){
    int a = 0;
    for(Cell* i=ff->head->next; i!=NULL; i=i->next){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", a, i->jogador.nome, i->jogador.altura, i->jogador.peso, i->jogador.anoNascimento, i->jogador.universidade, i->jogador.cidadeNascimento, i->jogador.estadoNascimento);
        a++;
    }
}

int getAverageAltura(FilaFlexivel* ff){
    float alturaTotal = 0;
    for(Cell* i=ff->head->next; i != NULL; i = i->next){
        alturaTotal += i->jogador.altura;
    }
    alturaTotal /= ff->size;
    return (int)(alturaTotal+0.5); 
}

void scanCommand(FilaFlexivel* ff, Jogador* jogadores){
    char* command = (char*) malloc(3 * sizeof(char));
    scanf("%s", command);
    if(strcmp(command, "I") == 0){
        int id;
        scanf("%d", &id);
        inserir(ff, jogadores[id]);
        printf("%d\n", getAverageAltura(ff));
    }else if(strcmp(command, "R") == 0){
        Jogador removido = remover(ff);
        printf("(R) %s\n", removido.nome);
    }
    free(command);
}




int main(void){
    FILE* mignon = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    readFile(mignon, jogadores);

    FilaFlexivel* ff = newFilaFlexivel();

    char* line = (char*) malloc(23 * sizeof(char));
    
    scanf("%s", line);

    while(strcmp(line, "FIM") != 0){
        inserir(ff, jogadores[atoi(line)]);
        printf("%d\n", getAverageAltura(ff));
        scanf("%s", line);
    }

    int n;
    scanf("%d", &n);

    for(int i=0;i<n;i++){
        scanCommand(ff, jogadores);
    }

    print(ff);

    return 0;
}