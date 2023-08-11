import java.util.*;

// Orientação dos exercícios:
// • Faça um método que receba um array de inteiros e um
// número inteiro x e retorne um valor booleano informando se o
// elemento x está contido no array.

// • Repita o exercício acima considerando que os elementos do
// array estão ordenados de forma crescente. Uma sugestão
// seria começar a pesquisa pelo meio do array.

public class Slide2 {

  public static void main(String[] args) {
    // scanner de tag sc
    Scanner sc = new Scanner(System.in);

    // Take the array size from the user
    System.out.println("Insira o tamanho da array:");
    int arrSize = 0;
    if (sc.hasNextInt()) {
      arrSize = sc.nextInt();
    }

    int[] arr = new int[arrSize];

    System.out.println("Insira os elementos da array:");
    for (int i = 0; i < arrSize; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    System.out.println("Insira o numero desejado.");
    int num = sc.nextInt();

    boolean found = false;

    for (int i = 0; i < arr.length / 2; i++) {
      if (arr[i] == num) {
        found = true;
        break;
      }
    }

    for (int i = arrSize / 2; i < arr.length; i++) {
      if (arr[i] == num) {
        found = true;
        break;
      }

    }

    if (found) {
      System.out.println("O numero esta contido na array.");
    } else {
      System.out.println("O numero nao esta contido na array.");
    }

    // System.out.println(
    // "Elementos da array:\n");
    // for (int i = 0; i < arrSize; i++) {
    // // prints the elements of an array
    // System.out.print(arr[i] + " ")2
    // }
    sc.close();

  }
}