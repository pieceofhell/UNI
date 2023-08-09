#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class transporte
{

private:
    double velocidade = 0.0, distancia = 0.0;

public:
    void vel(double velocid)
    {
        velocidade = velocid;
    }

    double calcTempo()
    {
        double tempo = distancia/velocidade;
        return tempo*60;
    }

    void exibeInfo() {
        cout << "\n\nVelocidade do veículo = " << velocidade << "\nDistância entre as cidades = " << distancia << "\nTempo para chegar à cidade = " << calcTempo();
    }
};

int main()
{
    transporte tr1;
    transporte tr2;
    transporte tr3;
    double x = 0.0, y = 0.0;
    cout << "Insira a velocidade média. ";
    cin >> x;
    cout << "Insira a distância entre as cidades. ";
    cin >> y;
    tr1.vel(x);
    tr1.exibeInfo();

    return 0;
}