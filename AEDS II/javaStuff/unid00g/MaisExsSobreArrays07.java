import java.util.*;

public class MaisExsSobreArrays07 {

  public static void minpos(int[] vet) {
    int menor = vet[0], pos = 0;
    for (int i = 0; i < vet.length; i++) {
      if (menor > vet[i]) {
        menor = vet[i];
        pos = i + 1;
      }
    }
    System.out.println("Posicao do menor numero da array: " + pos); 
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

    minpos(arr);

    sc.close();
  }
}