#include <stdio.h>

int main() {
  float salario_bruto;
  // declaracao de variaveis das faixas salariais
  float fx_1, fx_2, fx_3, fx_4;
  float v_liq;
  // declaracao de variaveis das faixas das aliquotas
  float a_fx_0, a_fx_1, a_fx_2, a_fx_3, a_fx_4;

  fx_1 = 1903.99;
  fx_2 = 2826.65;
  fx_3 = 3751.05;
  fx_3 = 4664.68;

  a_fx_0 = 0.0;
  a_fx_1 = 0.075;
  a_fx_2 = 0.15;
  a_fx_3 = 0.225;
  a_fx_4 = 0.275;

  printf("Digite o seu salario bruto: ");
  scanf(" %f", &salario_bruto);

  if (salario_bruto <= fx_1) {
    v_liq = salario_bruto * (1 - a_fx_0);
  } else if (salario_bruto <= fx_2) {
    v_liq = salario_bruto * (1 - a_fx_1);
  } else if (salario_bruto <= fx_3) {
    v_liq = salario_bruto * (1 - a_fx_2);
  } else if (salario_bruto <= fx_4) {
    v_liq = salario_bruto * (1 - a_fx_3);
  } else {
    v_liq = salario_bruto * (1 - a_fx_4);
  }
  printf("Valor do salario liquido:");
  printf(" %.2f", v_liq);
  return 0;
}
