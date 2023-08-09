#include <iostream>
#include <fstream>
#include <string>

using namespace std;

class Boi
{
private:
    std::string nome;
    double peso;

public:
    Boi()
    {
        nome = "";
        peso = 0.0;
    }

    /*    Boi(string nome, double peso) {
            this->nome = nome;
            this->peso = peso;
        }*/

    Boi(string nome, double peso)
        : nome(nome), peso(peso) {}

    ~Boi() {}

    string getNome()
    {
        return nome;
    }

    void setNome(string nome)
    {
        this->nome = nome;
    }

    double getPeso()
    {
        return peso;
    }

    void setPeso(double peso)
    {
        this->peso = peso;
    }

    void exibe()
    {
        cout << "Nome: " << nome << endl;
        cout << "Peso: " << peso << " arrobas" << endl;
    }
};

void preencherDados(Boi &boi, const string &arquivo)
{
    ifstream ArqEntrada(arquivo);

    if (ArqEntrada.is_open())
    {
        string nome;
        double peso;

        ArqEntrada >> nome >> peso;
        boi.setNome(nome);
        boi.setPeso(peso);

        ArqEntrada.close();
    }
    else
    {
        cout << "Erro ao abrir o arquivo de entrada." << endl;
    }
}

bool verificaPeso(Boi &boi)
{
    if (boi.getPeso() > 15)
    {
        return true;
    }
    else
    {
        return false;
    }
}

int main()
{
    string arquivoEntrada, arquivoSaida;
    Boi boi;

    // boi.exibe();

    Boi boi2("cinzento", 16);

    boi2.exibe();

    cout << "Digite o nome do arquivo de entrada: ";
    cin >> arquivoEntrada;

    cout << "Digite o nome do arquivo de saida: ";
    cin >> arquivoSaida;

    preencherDados(boi, arquivoEntrada);

    ofstream output(arquivoSaida);

    boi.exibe();

    if (output.is_open())
    {
        if (verificaPeso(boi))
        {
            output << "Boi para venda" << endl;
        }
        else
        {
            output << "Boi deve voltar para confinamento" << endl;
        }

        output.close();
    }
    else
    {
        cout << "Erro ao abrir o arquivo de saída." << endl;
    }

    return 0;
}
