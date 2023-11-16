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

void imprimirMensagemRemocao(char *nome) { printf("(R) %s\n", nome); }

typedef struct Lista {
  Jogador *arr;
  int n;
  int total;
} Lista;

Lista *newLista() {
  Lista *lista = (Lista *)malloc(sizeof(Lista));
  lista->arr = (Jogador *)malloc(10000 * sizeof(Jogador));
  lista->n = 0;
  lista->total = 10000;
  return lista;
}

Lista *inserirInicio(Lista *lista, Jogador jogador) {
  for (int i = lista->n; i > 0; i--) {
    lista->arr[i] = lista->arr[i - 1];
  }
  lista->arr[0] = jogador;
  lista->n++;
  return lista;
}

Lista *inserirFim(Lista *lista, Jogador jogador) {
  lista->arr[lista->n] = jogador;
  lista->n++;
  return lista;
}

Lista *removerInicio(Lista *lista) {
  if (lista->n > 0) {
    imprimirMensagemRemocao(lista->arr[0].nome);
    for (int i = 0; i < lista->n - 1; i++) {
      lista->arr[i] = lista->arr[i + 1];
    }
    lista->n--;
  }
  return lista;
}

Lista *removerFim(Lista *lista) {
  if (lista->n > 0) {
    imprimirMensagemRemocao(lista->arr[lista->n - 1].nome);
    lista->n--;
  }
  return lista;
}

Lista *inserir(Lista *lista, Jogador jogador, int pos) {
  for (int i = lista->n; i > pos; i--) {
    lista->arr[i] = lista->arr[i - 1];
  }
  lista->arr[pos] = jogador;
  lista->n++;
  return lista;
}

Lista *remover(Lista *lista, int pos) {
  if (pos >= 0 && pos < lista->n) {
    imprimirMensagemRemocao(lista->arr[pos].nome);
    for (int i = pos; i < lista->n - 1; i++) {
      lista->arr[i] = lista->arr[i + 1];
    }
    lista->n--;
  }
  return lista;
}

void metodos(Lista *lista, Jogador *jogadores) {
  char *metodo = (char *)malloc(3 * sizeof(char));
  scanf("%s", metodo);
  if (strcmp(metodo, "II") == 0) {
    int id;
    scanf("%d", &id);
    inserirInicio(lista, jogadores[id]);
  } else if (strcmp(metodo, "IF") == 0) {
    int id;
    scanf("%d", &id);
    inserirFim(lista, jogadores[id]);
  } else if (strcmp(metodo, "I*") == 0) {
    int id;
    int pos;
    scanf("%d", &pos);
    scanf("%d", &id);
    inserir(lista, jogadores[id], pos);
  } else if (strcmp(metodo, "RI") == 0) {
    removerInicio(lista);
  } else if (strcmp(metodo, "RF") == 0) {
    removerFim(lista);
  } else if (strcmp(metodo, "R*") == 0) {
    int pos;
    scanf("%d", &pos);
    remover(lista, pos);
  }
  free(metodo);
}

void mostrar(Lista *lista) {
  for (int i = 0; i < lista->n; i++) {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", i,
           lista->arr[i].nome, lista->arr[i].altura, lista->arr[i].peso,
           lista->arr[i].anoNasc, lista->arr[i].universidade,
           lista->arr[i].cidadeNasc, lista->arr[i].estadoNasc);
  }
}

int main(void) {
  FILE *entrada = fopen("/tmp/players.csv", "r");
  Jogador *jogadores = (Jogador *)malloc(10000 * sizeof(Jogador));
  Lista *lista = newLista();
  char *linha = (char *)malloc(23 * sizeof(char));
  int n;
  ler(entrada, jogadores);
  scanf("%s", linha);
  while (strcmp(linha, "FIM") != 0) {
    inserirFim(lista, jogadores[atoi(linha)]);
    scanf("%s", linha);
  }
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    metodos(lista, jogadores);
  }
  mostrar(lista);
  return 0;
}