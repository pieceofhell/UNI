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
    struct Cell* prox;
    struct Cell* ant;
} Cell;


typedef struct ListaFlexivel{
    Cell* head;
    Cell* tail;
    int size;
} ListaFlexivel;

Cell* newCell(Jogador jogador){
    Cell* tmp = (Cell*) malloc(sizeof(Cell));
    tmp->jogador = jogador;
    tmp->prox = NULL;
    tmp->ant = NULL;
    return tmp;
}

ListaFlexivel* newListaFlexivel(){
    ListaFlexivel* lf = (ListaFlexivel*) malloc(sizeof(ListaFlexivel));
    lf->head = NULL;
    lf->tail = NULL;
    lf->size = 0;
    return lf;
}


void inserirInicio(ListaFlexivel* lf, Jogador jogador){
    if(lf->head == NULL){
        lf->head = newCell(jogador);
        lf->tail = lf->head;
        return;
    }

    lf->head->ant = newCell(jogador);
    lf->head->ant->prox = lf->head;
    lf->head = lf->head->ant;
}

void inserirFim(ListaFlexivel* lf, Jogador jogador){
    if(lf->tail == NULL){
        lf->tail = newCell(jogador);
        lf->head = lf->tail;
        return;
    }

    lf->tail->prox = newCell(jogador);
    lf->tail->prox->ant = lf->tail;
    lf->tail = lf->tail->prox;
}

void inserir(ListaFlexivel* lf, Jogador jogador, int pos){
    if(pos==0){
        inserirInicio(lf, jogador);
        return;
    }

    Cell* aux = lf->head;
    for(int i=0;i<pos;i++){
        aux = aux->prox;
        if(aux == NULL){
            printf("Posição n existe");
            return;
        }
    }

    if(aux->prox == NULL){
        inserirFim(lf, jogador);
        return;
    }

    Cell* tmp = newCell(jogador);
    tmp->ant = aux->ant;
    tmp->prox = aux;
    aux->prox->ant = tmp;
    aux->prox = tmp;
}


Jogador removerInicio(ListaFlexivel* lf){
    if(lf->head == NULL){
        printf("Erro");
        Jogador j;
        return j;
    }

    Cell* tmp = lf->head;
    Jogador res = lf->head->jogador;
    lf->head = lf->head->prox;
    lf->head->ant = NULL;

    free(tmp);
    return res;
}


Jogador removerFim(ListaFlexivel* lf){
    if(lf->tail==NULL){
        printf("Erro");
        Jogador j;
        return j;
    }

    Cell* tmp = lf->tail;
    Jogador res = tmp->jogador;
    lf->tail = lf->tail->ant;
    lf->tail->prox = NULL;

    free(tmp);
    return res;
}

Jogador remover(ListaFlexivel* lf, int pos){
    if(pos == 0){
        return removerInicio(lf);
    }

    Cell* aux = lf->head;
    for(int i=0;i<pos;i++){
        aux = aux->prox;
        if(aux == NULL){
            printf("Erro");
            Jogador j;
            return j;
        }
    }

    aux->ant->prox = aux->prox;
    aux->prox->ant = aux->ant;

    Jogador res = aux->jogador;
    free(aux);
    
    return res;
}


void print(ListaFlexivel* lf){
    for(Cell* i=lf->head; i!=NULL; i=i->prox){
        imprimir(i->jogador);
        printf("\n");
    }
}


int cmpStrings(char* str1, char* str2){
    int i = 0;
    
    while(i<strlen(str1) && i<strlen(str2)){
        if(str1[i]<str2[i]){
            return -1;
        }
        if(str1[i]>str2[i]){
            return 1;
        }
        i++;
    }

    if(strlen(str1)<strlen(str2)){
        return -1;
    }
    if(strlen(str1) > strlen(str2)){
        return 1;
    }
    return 0;
}

int cmpJogadores(Jogador j1, Jogador j2){
    int a = cmpStrings(j1.estadoNascimento, j2.estadoNascimento);
    if(a!=0) return a;

    return cmpStrings(j1.nome, j2.nome);
}

void swap(ListaFlexivel* lf, Cell* i, Cell* j){
    Jogador aux = i->jogador;
    i->jogador = j->jogador;
    j->jogador = aux;
}

void quicksortR(ListaFlexivel* lf, Cell* left, Cell* right){
    Cell* i = left;
    Cell* j = right;
    Jogador pivot = right->jogador;

    while(j!=i->ant && j!=i->ant->ant){
        while(cmpJogadores(i->jogador, pivot) == -1) i = i->prox;
        while(cmpJogadores(pivot, j->jogador) == -1) j = j->ant;

        if(j!=i->ant && j!=i->ant->ant){
            swap(lf, i, j);
            i = i->prox;
            j = j->ant;
        }
    }

    if(left != j && j!=left->ant){
        quicksortR(lf, left, j);
    }
    if(right != i && i!=right->prox){
        quicksortR(lf, i, right);
    }
}

void quicksort(ListaFlexivel* lf){
    Jogador j;
    Cell* c1 = newCell(j);
    Cell* c2 = newCell(j);
    lf->head->ant = c1;
    lf->head->ant->prox = lf->head;
    lf->tail->prox = c2;
    lf->tail->prox->ant = lf->tail;

    quicksortR(lf, lf->head, lf->tail);

    lf->head->ant = NULL;
    lf->tail->prox = NULL;

    free(c1);
    free(c2);
}

int main(void){
    FILE* mignon = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    readFile(mignon, jogadores);

    ListaFlexivel* lf = newListaFlexivel();

    char* line = (char*) malloc(23 * sizeof(char));
    
    scanf("%s", line);

    while(strcmp(line, "FIM") != 0){
        inserirFim(lf, jogadores[atoi(line)]);
        scanf("%s", line);
    }


    quicksort(lf);
    print(lf);

    return 0;
}