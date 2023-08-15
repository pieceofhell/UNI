import java.util.*;

public class MaisExsSobreArrays06 {

  public static void maxmin(int[] vet) {
    int maior = vet[0], menor = vet[0];
    for (int i = 0; i < vet.length; i++) {
      if (maior < vet[i]) {
        maior = vet[i];
      }

      if (menor > vet[i]) {
        menor = vet[i];
      }
    }
    System.out.println("Maior numero da array: " + maior);
    System.out.println("Menor numero da array: " + menor);
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 0;

    System.out.println("Insira o tamanho do vetor:");

    n = sc.nextInt();

    int[] arr = new int[n];

    System.out.println("Insira os valores:");

    for (int i = 0; i < n; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    maxmin(arr);

    sc.close();
  }
}