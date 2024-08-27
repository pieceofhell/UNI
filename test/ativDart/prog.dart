import 'dart:io';

void ativ1() {
  String? nome = stdin.readLineSync();
  print("Hello, $nome!");
}

void ativ2() {
  // Criar uma lista de números inteiros
  List<int> numeros = [10, 5, 3, 8, 2];
// Imprimir a lista original
  print('Lista original: $numeros');
// Adicionar um número à lista
  numeros.add(7);
  print('Depois de adicionar 7: $numeros');
// Remover um número específico da lista
  numeros.remove(5);
  print('Depois de remover 5: $numeros');
// Exibir um número específico da lista
  print('Número na posição 3: ${numeros[3]}');
// Ordenar a lista em ordem crescente
  numeros.sort();
  print('Lista ordenada: $numeros');
}

void main() {
  ativ1();
}
