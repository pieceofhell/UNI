#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Jogador {
  int id;
  char nome[50];
  int altura;
  int peso;
  char universidade[100];
  int anoNasc;
  char cidadeNasc[50];
  char estadoNasc[50];
} Jogador;

void ler(FILE *entrada, Jogador *jogadores) {
  char linha[300];
  fscanf(entrada, "%s", linha);
  char props[8][100];
  int i = 0;
  fgets(linha, 300, entrada);
  while (!feof(entrada)) {
    fgets(linha, 300, entrada);

    int j = 0;
    int a = 0;
    for (int k = 0; k < strlen(linha); k++) {
      if (linha[k] == ',' || linha[k] == '\n') {
        props[j][a] = '\0';
        j++;
        a = 0;
      } else {
        props[j][a] = linha[k];
        a++;
      }
    }

    for (int b = 0; b < 8; b++) {
      if (strcmp(props[b], "") == 0) {
        strcpy(props[b], "nao informado");
      }
    }

    jogadores[i].id = atoi(props[0]);
    strcpy(jogadores[i].nome, props[1]);
    jogadores[i].altura = atoi(props[2]);
    jogadores[i].peso = atoi(props[3]);
    strcpy(jogadores[i].universidade, props[4]);
    jogadores[i].anoNasc = atoi(props[5]);
    strcpy(jogadores[i].cidadeNasc, props[6]);
    strcpy(jogadores[i].estadoNasc, props[7]);

    i++;
  }
  fclose(entrada);
}

typedef struct Fila {
  Jogador *arr;
  int inicio;
  int fim;
  int total;
} Fila;

Fila *newFila() {
  Fila *fila = (Fila *)malloc(sizeof(Fila));
  fila->arr = (Jogador *)malloc(6 * sizeof(Jogador));
  fila->inicio = 0;
  fila->fim = 0;
  fila->total = 6;
  return fila;
}

void inserir(Fila *fila, Jogador jogador) {
  if ((fila->fim + 1) % fila->total == fila->inicio) {
    fila->inicio = (fila->inicio + 1) % fila->total;
  }
  fila->arr[fila->fim] = jogador;
  fila->fim = (fila->fim + 1) % fila->total;
  float media = 0;
  int amount = 0;
  int i = fila->inicio;
  while (i != fila->fim) {
    media += fila->arr[i].altura;
    amount++;
    i = (i + 1) % fila->total;
  }
  media /= amount;
  printf("%d\n", (int)(media + 0.5));
}

void remover(Fila *fila) {
  Jogador removido = fila->arr[fila->inicio];
  fila->inicio = (fila->inicio + 1) % fila->total;
  imprimirMensagemRemocao(removido.nome);
}

void imprimirMensagemRemocao(char *nome) { printf("(R) %s\n", nome); }

void metodos(Fila *fila, Jogador *jogadores) {
  char *metodo = (char *)malloc(3 * sizeof(char));
  scanf("%s", metodo);
  if (strcmp(metodo, "I") == 0) {
    int id;
    scanf("%d", &id);
    inserir(fila, jogadores[id]);
  } else if (strcmp(metodo, "R") == 0) {
    int id;
    scanf("%d", &id);
    remover(fila);
  }
  free(metodo);
}

void mostrar(Fila *fila) {
  int a = 0;
  for (int i = fila->inicio; i != fila->fim; i = (i + 1) % fila->total) {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", a,
           fila->arr[i].nome, fila->arr[i].altura, fila->arr[i].peso,
           fila->arr[i].anoNasc, fila->arr[i].universidade,
           fila->arr[i].cidadeNasc, fila->arr[i].estadoNasc);
    a++;
  }
}

int main(void) {
  FILE *entrada = fopen("/tmp/players.csv", "r");
  Jogador *jogadores = (Jogador *)malloc(10000 * sizeof(Jogador));
  Fila *fila = newFila();
  char *linha = (char *)malloc(23 * sizeof(char));
  int n;
  ler(entrada, jogadores);
  scanf("%s", linha);
  while (strcmp(linha, "FIM") != 0) {
    inserir(fila, jogadores[atoi(linha)]);
    scanf("%s", linha);
  }
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    metodos(fila, jogadores);
  }
  mostrar(fila);
  return 0;
}
