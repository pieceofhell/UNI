#include <iostream>

using namespace std;
class Frete {
private:
  string tipo;

public:
  double distancia, velocidade, preco_km, preco_fin;
  double taxa;
  bool urgencia;
  Frete() {
    tipo = "";
    distancia = 0;
    velocidade = 0;
    preco_km = 0;
    preco_fin = 0;
  }

  void setDados(string a, double x, double y, double z, double w, bool u) {
    tipo = a;
    distancia = x;
    velocidade = y;
    preco_km = z;
    taxa = w;
    urgencia = u;
  }

  double calcFrete() {
    if (urgencia) {
      preco_fin = (preco_km * 2) * distancia + taxa;
    } else
      preco_fin = preco_km * distancia + taxa;
    return preco_fin;
  }
  double calcTempo() {
    double tempo = distancia / velocidade;
    return tempo * 60;
  }
  void exibirDados() {
    cout << "Veículo utilizado para transporte: " << tipo << endl;
    cout << "Valor do frete: " << calcFrete() << endl;
    cout << "Tempo da entrega: " << calcTempo() << " minutos" << endl;
  }
};

int main() {
  Frete moto;
  Frete bike;
  Frete patinete;
  double dist;
  cout << "Insira a distância da entrega.\n";
  cin >> dist;

  int urg;
  int taxa;
  cout << "Escreva 1 se for urgente, 0 se não for.\n";
  cin >> urg;
  bool urgencia;
  if (urg == 1) {
    urgencia = true;
  } else {
    urgencia = false;
  }
  bike.setDados("Bicicleta", dist, 15, 2, 0, urgencia);
  patinete.setDados("Patinete", dist, 10, 1, 0, urgencia);
  moto.setDados("Moto", dist, 35, 5, 15, urgencia);

  if (dist > 5) {
    moto.setDados("Moto", dist, 35, 5, 20, urgencia);
    moto.exibirDados();
  } else if (dist <= 5) {
    moto.exibirDados();
    bike.exibirDados();
    patinete.exibirDados();
  }

  return 0;
}