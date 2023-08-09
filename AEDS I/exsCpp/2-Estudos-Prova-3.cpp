#include <iostream>
#include <iomanip>
using namespace std;

typedef struct datas {
  int ano, mes, dia, hora, minuto, segundo;
} DH;

datas data;

/* Desenvolver o código em linguagem C ou C++ dataValida para informar se uma
struct com data e horário é válida.

Por exemplo, o mês deve ser entre 1 e 12. A seguir a struct e a sintaxe da
função:

typedef struct {
int ano, mes, dia, hora, minuto, segundo;
} DH;

bool dataValida(DH& dataHora);

Considere a data no formato ano (4 números), mês (2 números) e dia (2 números),
o horário com dois números para hora, minuto e segundo */

bool dataValida(DH &dataHora) {
  if (data.ano >= 0 && data.mes > 0 && data.mes <= 12 && data.dia >= 1 &&
      data.dia <= 31 && data.hora >= 0 && data.hora <= 23 && data.minuto >= 1 &&
      data.minuto <= 59 && data.segundo >= 1 && data.segundo <= 59) {
    return true;
  } else {
    return false;
  }
}

int main() {
  cin >> data.ano;
  DH dataHora = {data.mes = 05, data.dia = 21, data.hora = 14, data.minuto = 24,
                 data.segundo = 20};
  if (dataValida(dataHora) == true) {
    cout << "A data apresentada é válida.\n" << endl;
  } else {
    cout << "A data apresentada não é válida.\n" << endl;
  }

  cout << "Data inserida: " << data.ano << "/" << data.mes << "/" << data.dia << "\n" << data.hora << ":" << data.minuto << ":" << data.segundo << endl;

  return 0;
}