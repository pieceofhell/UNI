#include <fstream>
#include <iostream>
#include <string>

using namespace std;

class transporte {

private:
  double velocidade, distancia;

public:
  transporte() {
    velocidade = 0;
    distancia = 0;
  } 

  void setSpeed(double v) { velocidade = v; }

  double getSpeed() { return velocidade; }

  void setDist(double d) { distancia = d; }

  double getDist() { return distancia; }

  double calcTempo() {
    double tempo = distancia / velocidade;
    return tempo;
  }

  void exibeInfo() {
    cout << "\n\nVelocidade do veículo = " << velocidade
         << "\nDistância entre as cidades = " << distancia
         << "\nTempo para chegar à cidade = " << calcTempo() << " hora(s), ou "
         << calcTempo() * 60 << " minuto(s)";
  }
};

int main() {

  transporte carro;
  transporte onibus;
  transporte pe;
  transporte bike;

  double x = 0.0, y = 0.0;
  /* cout << "Insira a velocidade média. ";
  cin >> x; */
  cout << "Insira a distância entre as cidades. ";
  cin >> y;

  carro.setSpeed(28);
  carro.setDist(y);

  onibus.setSpeed(15);
  onibus.setDist(y);

  pe.setSpeed(2.3);
  pe.setDist(y);

  bike.setSpeed(20);
  bike.setDist(y);

  carro.exibeInfo();

  return 0;
}