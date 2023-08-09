#include <stdio.h>
#include <time.h>
#define e = 2.71828;

/* Trabalho Prático I - Cálculo 
Objetivo do projeto: 
Estudo e codificação de algoritmos para resolução de problemas sobre Logaritmos.
Tarefas: No artigo é proposto um método para o cálculo de logarítmos. Cada grupo deve codificar
um programa que use a teoria proposta para efetuar os cálculos. Detalhes da codificação e a
apresentação dos resultados devem ser organizados na forma de slides e enviados via Canvas.
Os slides devem conter os seguintes itens:
a) a representação computacional escolhida e orientações de uso.
b) apresentar os detalhes mais importantes de codificação.
d) O seu programa deverá solicitar ao usuário o limite inferior e o limite superior
   e executar para os intervalos 0,01, 0,1 e 0,2 para o cálculo dos logarítmos.
   Assim como a base que será feito o cálculo.
e) Apresentar o tempo de execução de cada intervalo.
f) Os resultados deverão ser mostrados em uma tabela. */

double pot(double x, int y){
  double total = x;
  for(int i = 0; i < y-1; i++){
    total *= x;
  }
  return total;
}

double findNumber(double n){
  return (n - 1) / (n + 1);
}

double ln(double x){
  double n = findNumber(x);
  double total = 0.0;
  for(int i = 1; i < 500; i += 2){
    total += pot(n, i) / i;
  }
  return 2 * total;
}

double log10(double n){
  return ln(n) / ln(10.0);
}

double logaritmo(double n1, double n2){
  return ln(n1) / ln(n2);
}

void calcularIntervalo(double lim1, double lim2, double intervalo, double base){
  for(double i = lim1; i <= lim2 + 0.0000001; i += intervalo){
    printf("Logaritmo de %.2lf na base %.2lf: %lf\n", i, base, logaritmo(i, base));
  }
}

int main(void){
  clock_t inicio = clock();

  int x;
  double lim1, lim2, intervalo, base;

  printf("Calculadora de logaritmos:\n\nDigite o número equivalente ao tipo de operação a ser realizada.\n[1] Logaritmo de apenas um número\n[2] Logaritmo com número de intervalos e base\n[9] Finalizar código \n");
  scanf("%d", &x);

  switch (x){
   case 1:
     printf("Insira o valor do logaritmando e da base: ");
     scanf("%lf %lf", &lim1, &lim2);
     printf("%lf", logaritmo(lim1, lim2));
   break;

   case 2:
     printf("Insira o número do limite inferior e limite superior. ");
  scanf("%lf %lf", &lim1, &lim2);

  printf("Insira o número dos intervalos (ex.: 0.01 , 0.1 , 0.2) e da base. ");
  scanf("%lf %lf", &intervalo, &base);
  
  // calcularIntervalo(1, 2, 0.0000001, 10);
  calcularIntervalo(lim1, lim2, intervalo, base);
   break;
}  

  clock_t fim = clock();
  double tempo_gasto = (double) (fim - inicio) / CLOCKS_PER_SEC;

  printf("\n\nTempo gasto na execução do código: %lf segundos", tempo_gasto);
  return 0;
}