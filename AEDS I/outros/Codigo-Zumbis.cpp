#include <cmath>
#include <iostream>
class Montante {
private:
  double principal;
  double taxa;
  int tempo;

public:
  Montante(double principal, double taxa, int tempo)
      : principal(principal), taxa(taxa), tempo(tempo) {}
  double calcular() { return principal * pow((1 + taxa), tempo); }
};
class PopulacaoZumbi {
private:
  int populacacao_inicial;
  Montante *capitalizacao;

public:
  PopulacaoZumbi(int populacacao_inicial, double taxa, int tempo) {
    this->populacacao_inicial = populacacao_inicial;
    this->capitalizacao = new Montante(populacacao_inicial, taxa, tempo);
  }
  ~PopulacaoZumbi() { delete capitalizacao; }
  int calcular_population(int dias, int mortes_por_dia) {
    int populacao_zumbi = (int)this->capitalizacao->calcular();
    if (dias > 10) {
      Montante capitalizacao_equipes(2, 0.02, dias - 10);
      int cont_equipes = (int)capitalizacao_equipes.calcular();
      int mortes = cont_equipes * 7 * mortes_por_dia;
      populacao_zumbi -= mortes;
    }
    return populacao_zumbi > 0 ? populacao_zumbi : 0;
  }
};
int main() {
  PopulacaoZumbi zombies(50, 0.1, 30);
  int final_population = zombies.calcular_population(30, 20);
  std::cout << "A população de zumbis após 30 dias é: " << final_population << std::endl;
  return 0;
}