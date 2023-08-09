#include <iostream>

class classeExemplo {
private: // tudo declarado a partir daqui é visto apenas nessa classe
  int membroPrivado;

protected: // pode ser acessado diretamente na classe filha
  int membroProtegido;

public:
  // metodo
  void atribuiValorMembros(int privado, int protegido) {
    membroPrivado = privado;
    membroProtegido = protegido;
  }


void exibeDetalhes() {
  std::cout << "Valor de membro Privado: " << membroPrivado << std::endl;
  std::cout << "Valor de membro Protegido: " << membroProtegido << std::endl;
}
};

class subClasse: public classeExemplo {
public:
// metodos
void modificaMembros(int privado, int protegido) {
  membroProtegido = protegido;
} atribuiValorMembros(privado, protegido);

void imprimeDetalhes() {
  // exibeDetalhes;
  // std::cout << membroPrivado << std::endl;
  std::cout << membroProtegido << std::endl;
}
};

int main() {
  std::cout << "Hello World!\n\n"; 
  classeExemplo obj1;
  subClasse obj2;
  obj1.atribuiValorMembros(100, 100);
  obj1.exibeDetalhes();
  obj2.modificaMembros(20, 200);
  obj2.imprimeDetalhes();
}