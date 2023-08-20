import java.util.*;

// Orientação dos exercícios:
// • Faça um método que receba um array de inteiros e mostre na
// tela o maior e o menor elementos do array.

// • Repita o exercício acima fazendo menos comparações com
// os elementos do array.

public class Slide3 {

  public static void minmax(int[] array) {
    int max = 0;
    int min = array[0];
    for (int i = 0; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }

    for (int i = 0; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }

    System.out.println("Numero maximo: " + max);
    System.out.println("Numero minimo: " + min);
  }

  public static void maxmin(int[] array) {
    // mesma coisa que método acima, mas com menos comparações
    int max = 0;
    int min = array[0];
    for (int i = 0; i < array.length / 2; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }

    for (int i = array.length / 2; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }

    for (int i = 0; i < array.length / 2; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }

    for (int i = array.length / 2; i < array.length; i++) {
      if (array[i] < min) {
        min = array[i];
      }
    }

    System.out.println("Numero maximo: " + max);
    System.out.println("Numero minimo: " + min);
  }

  public static void main(String[] args) {
    // scanner de tag sc
    Scanner sc = new Scanner(System.in);

    // Take the array size from the user
    System.out.println("Insira o tamanho da array:\n");
    int arrSize = 0;
    if (sc.hasNextInt()) {
      arrSize = sc.nextInt();
    }

    int[] arr = new int[arrSize];

    System.out.println("Insira os elementos da array:\n");
    for (int i = 0; i < arrSize; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    //minmax(arr);

    maxmin(arr);

    sc.close();
  }
}
