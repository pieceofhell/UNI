#include <iostream>
using namespace std;
#define TAM 100

const int TAM2 = 200;

int main() {
char a[TAM]; // um vetor de caracteres com 100 registros.
float b[TAM2]; // um vetor de números reais com 200 registros.
cout << "Tamanho do vetor A: " << sizeof(a) << endl;
cout << "Tamanho do vetor B: " << sizeof(b) << endl;
}