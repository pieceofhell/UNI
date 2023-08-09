#include <iostream>
#include <fstream>

using namespace std;
// Ponteiros, abrir e fechar arquivos e passar função (modularidade e encapsulamento)

int sum(int a, int b)
{
  int fsum = a + b;
  return fsum;
}

int circle()

{
  double radius, circumference, area;
  const double PI = 3.14159265;

  cout << "Insira o raio: ";
  cin >> radius;

  area = radius * radius * PI;
  circumference = 2.0 * radius * PI;

  cout << "Medida do raio: " << radius << "\n";
  cout << "Medida da area: " << area << "\n";
  cout << "Medida da circumferencia: " << circumference << "\n";

  return 0;
}

int fileHandling()
{
  fstream test;
  test.open("teste.txt", ios::out);
  if (!test)
  {
    cout << "Erro ao criar o arquivo";
  }
  else
  {
    cout << "Arquivo criado com sucesso.\n";
    test << "Texto escrito no arquivo com sucesso.";
    test.close();
  }

  return 0;
}

int main()
{
  int x, o, k, f;
  string job = "entrepreneur";
  string jeb = "flob";
  string *ptr = &jeb;
  x = 12 + 12;

  /*
  cout << "\nTesting for variable printing syntax - " << x << "\n";

  cout << "\nTesting for variable (pointer) printing syntax - " << *ptr << "\n";

  fileHandling();

  cout << "\n\n";

  for (int i = 0; i <= 10; i++) {
    cout << "Testing\n" << i;
  }

  cout << "Insert the value of variables 'o' and 'k'. ";
  cin >> o >> k;

  cout << "The assigned value to variable 'o' is " << o << "\n";
  cout << "The assigned value to variable 'k' is " << k << "\n";

  cout << "The value of the sum between the variables entered above is " << sum(o, k) << "\n\n";

  if (sum(o, k) >= 50)
  {
    circle();
  }
  else
  {
    cout << "Program finished.";
  } */
  
  //   ex2();

  return 0;
}