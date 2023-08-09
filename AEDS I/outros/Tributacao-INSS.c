#include <stdio.h>

int main() {
  // Declaração de variáveis relativas ao salário e suas respectivas contas.
  double salario_bruto, salario_bruto1, salario_bruto2, salario_bruto3,
      salario_bruto4, salario_bruto5, final, final1, final2, final3;
  // Declaração de variáveis das faixas salariais.
  double fx_1, fx_2, fx_3, fx_4;
  double pgto, pgto1, pgto2, pgto3, pgto4, diff1, diff2, diff3;
  // Declaração de variáveis das faixas das alíquotas.
  double a_fx_0, a_fx_1, a_fx_2, a_fx_3, a_fx_4;

  fx_1 = 1302;
  fx_2 = 2571.29;
  fx_3 = 3856.94;
  fx_4 = 7507.49;

  a_fx_1 = 0.075;
  a_fx_2 = 0.09;
  a_fx_3 = 0.12;
  a_fx_4 = 0.14;

  printf("Digite o seu salario: ");
  scanf(" %lf", &salario_bruto);

  if (salario_bruto <= fx_1) {
    pgto = salario_bruto * a_fx_1;
    printf("Valora: %.2f", pgto);
  } else if (fx_1 <= salario_bruto && salario_bruto <= fx_2) {
    salario_bruto1 = salario_bruto - fx_1;
    // valor residual do salário após passar por 1 faixa
    pgto1 = fx_1 * a_fx_1;
    // 97,65
    printf("Valorb: %.2f", pgto1 + salario_bruto1 * a_fx_2);

  } else if (fx_2 <= salario_bruto && salario_bruto <= fx_3) {
    // valor residual do salário após passar por 2 faixas
    salario_bruto1 = salario_bruto - fx_2;
    // 1269,29
    diff1 = fx_2 - fx_1;
    final1 = (salario_bruto1 * a_fx_3) + (fx_1 * a_fx_1) + (diff1 * a_fx_2);
    printf("Valorc: %.2f", final1);

  } else if (fx_3 <= salario_bruto && salario_bruto <= fx_4) {
    // valor residual do salário após passar por 3 faixas
    salario_bruto1 = salario_bruto - fx_3;
    diff1 = fx_2 - fx_1;
    diff2 = fx_3 - fx_2;
    final1 = (salario_bruto1 * a_fx_4) + (fx_1 * a_fx_1) + (diff1 * a_fx_2) +
             (diff2 * a_fx_3);
    printf("Valord: %.2f", final1);
  } else if (fx_4 <= salario_bruto) {
    salario_bruto1 = salario_bruto - fx_4;
    diff1 = fx_2 - fx_1;
    diff2 = fx_3 - fx_2;
    diff3 = fx_4 - fx_3;
    final1 = (salario_bruto1 * a_fx_4) + (fx_1 * a_fx_1) + (diff1 * a_fx_2) +
             (diff2 * a_fx_3) + (diff3 * a_fx_4);
    printf("Valore: %.2f", final1);
  }

  return 0;
}
