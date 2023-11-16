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

typedef struct Lista{
    Jogador* arr;
    int n;
    int total;
} Lista;

Lista* newLista(){
    Lista* lista = (Lista*) malloc(sizeof(Lista));
    lista->arr = (Jogador*) malloc(10000 * sizeof(Jogador));
    lista->n = 0;
    lista->total = 10000;
    return lista;
}

Lista* inserirInicio(Lista* lista, Jogador jogador){
    if(lista->n == lista->total){
        printf("Erro ao inserir no inicio");
        exit(1);
    }
    for(int i=lista->n;i>0;i--){
        lista->arr[i] = lista->arr[i-1];
    }
    lista->arr[0] = jogador;
    lista->n++;
    return lista;
}

Lista* inserirFim(Lista* lista, Jogador jogador){
    if(lista->n == lista->total){
        printf("Erro ao inserir no fim");
        exit(1);
    }
    lista->arr[lista->n] = jogador;
    lista->n++;
    return lista;
}

Lista* inserir(Lista* lista, Jogador jogador, int pos){
    if(pos > lista->n || pos < 0 || lista->n == lista->total){
        printf("Erro ao inserir");
        exit(1);
    }

    for(int i=lista->n;i>pos;i--){
        lista->arr[i] = lista->arr[i-1];
    }

    lista->arr[pos] = jogador;
    lista->n++;
    return lista;
}

Lista* removerInicio(Lista* lista){
    if(lista->n == 0){
        printf("Erro ao remover no inicio");
        exit(1);
    }
    for(int i=0;i<lista->n-1;i++){
        lista->arr[i] = lista->arr[i+1];
    }
    lista->n--;
    return lista;
}

Lista* removerFim(Lista* lista){
    if(lista->n == 0){
        printf("Erro ao remover no fim");
        exit(1);
    }
    lista->n--;
    return lista;
}

Lista* remover(Lista* lista, int pos){
    if(pos >= lista->n || pos < 0 || lista->n == 0){
        printf("Erro ao remover");
        exit(1);
    }

    for(int i=pos;i<lista->n-1;i++){
        lista->arr[i] = lista->arr[i+1];
    }

    lista->n--;
    return lista;
}

void print(Lista* lista){
    for(int i=0;i<lista->n;i++){
        printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", i, lista->arr[i].nome, lista->arr[i].altura, lista->arr[i].peso, lista->arr[i].anoNascimento, lista->arr[i].universidade, lista->arr[i].cidadeNascimento, lista->arr[i].estadoNascimento);
    }
}

void scanCommand(Lista* lista, Jogador* jogadores){
    char* command = (char*) malloc(3 * sizeof(char));
    scanf("%s", command);
    if(strcmp(command, "II") == 0){
        int id;
        scanf("%d", &id);
        inserirInicio(lista, jogadores[id]);
    }else if(strcmp(command, "IF") == 0){
        int id;
        scanf("%d", &id);
        inserirFim(lista, jogadores[id]);
    }else if(strcmp(command, "I*") == 0){
        int id, pos;
        scanf("%d", &pos);
        scanf("%d", &id);
        inserir(lista, jogadores[id], pos);
    }else if(strcmp(command, "RI") == 0){
        printf("(R) %s\n", lista->arr[0].nome);
        removerInicio(lista);
    }else if(strcmp(command, "RF") == 0){
        printf("(R) %s\n", lista->arr[lista->n-1].nome);
        removerFim(lista);
    }else if(strcmp(command, "R*") == 0){
        int pos;
        scanf("%d", &pos);
        printf("(R) %s\n", lista->arr[pos].nome);
        remover(lista, pos);
    }
    free(command);
}



int main(void){
    FILE* mignon = fopen("/tmp/players.csv", "r");
    Jogador* jogadores = (Jogador*) malloc(10000 * sizeof(Jogador));

    readFile(mignon, jogadores);

    Lista* lista = newLista();

    char* line = (char*) malloc(23 * sizeof(char));
    
    scanf("%s", line);

    while(strcmp(line, "FIM") != 0){
        inserirFim(lista, jogadores[atoi(line)]);
        scanf("%s", line);
    }

    int n;
    scanf("%d", &n);

    for(int i=0;i<n;i++){
        scanCommand(lista, jogadores);
    }

    print(lista);

    return 0;
}