#include <math.h>
#include <stdio.h>

int ex1_1a(void) {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += i;
    printf("%d ", soma);
  }
  return 0;
}

int ex1_1b(void) {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += pow(i, 2);
    printf("%d ", soma);
  }
  return 0;
}

int ex1_1c(void) {
  float i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += pow(i, i);
    printf("\n%.f ", soma);
  }
  return 0;
}

int ex1_2a(void) {
  float i, n = 20, produto = 1;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    produto *= i;
    printf("\n%.f ", produto);
  }
  return 0;
}

int ex1_2b(void) {
  float i, n = 20, produto = 1;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    produto *= pow(produto, i);
    printf("\n%.f", produto);
  }
  return 0;
}

int ex1_2c(void) {
  float i, n = 20, produto = 1;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    produto *= i;
    printf("\n%.f ", pow(i, produto));
  }
  return 0;
}

int ex2_1a(void) {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += pow(-1, i + 1) * pow(i, 2);
    printf("%d ", soma);
  }
  return 0;
}

int ex2_1b(void) {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += pow(-1, i + 1) * pow(i, i);
    printf("%d ", soma);
  }
  return 0;
}

int ex2_2a(void) {
  int i, n = 20, soma = 1;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma *= pow(-1, i + 1) * pow(i, 2);
    printf("%d ", soma);
  }
  return 0;
}

int ex2_2b(void) {
  int i, n = 20;
  float soma = 1;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma *= pow(-1, i + 1) * pow(i, i);
    printf("%.f ", soma);
  }
  return 0;
}

int ex2_3a(void) {
  int i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += sqrt(i);
    printf("%d ", soma);
  }
  return 0;
}

int ex2_3b(void) {
  float i, n = 20, soma = 0;
  printf("\n\n");
  for (i = 1; i <= n; ++i) {
    soma += pow(i, i) / sqrt(i);
    printf("%.f \n", soma);
  }
  return 0;
}

int main() {
  /* Para executar o código do exercício desejado, apenas retire as barras de
  comentário */
  int ex;
  printf("Selecione o exercício que você deseja que seja executado. "); 
  scanf(" %d", &ex);
  if (ex == 1) {
    ex1_1a();
  } else if (ex == 2) {
    ex1_1b();
  } else if (ex == 3) {
    ex1_1c();
  } else if (ex == 4) {
    ex1_2a();
  } else if (ex == 5) {
    ex1_2b();
  } else if (ex == 6) {
    ex1_2c();
  } else if (ex == 7) {
    ex2_1a();
  } else if (ex == 8) {
    ex2_1b();
  } else if (ex == 9) {
    ex2_2a();
  } else if (ex == 10) {
    ex2_2b();
  } else if (ex == 11) {
    ex2_3a();
  } else if (ex == 12) {
    ex2_3b();
  }
}