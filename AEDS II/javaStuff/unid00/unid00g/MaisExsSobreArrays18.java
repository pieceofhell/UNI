import java.util.*;

public class MaisExsSobreArrays18 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 0, m = 0, tamanho = 0;

    System.out.println("Insira o tamanho do primeiro vetor:");

    n = sc.nextInt();

    int[] arr1 = new int[n];

    System.out.println("Insira o tamanho do segundo vetor:");

    m = sc.nextInt();

    if (n > m) {
      tamanho = n;
    } else {
      tamanho = m;
    }

    int[] arr2 = new int[m];

    System.out.println("Insira os valores do primeiro vetor:");

    for (int i = 0; i < n; i++) {
      if (sc.hasNextInt()) {
        arr1[i] = sc.nextInt();
      }
    }

    System.out.println("Insira os valores do segundo vetor:");

    for (int i = 0; i < m; i++) {
      if (sc.hasNextInt()) {
        arr2[i] = sc.nextInt();
      }
    }

    System.out.println("Uniao das duas arrays:");

    for (int i = 0; i < tamanho; i++) {
      int j = i + 1;
      if (i < n) {
        System.out.print("Elemento - " + "[" + i + "]" + " - " + arr1[i] + "\n");
      }
      if (i < m) {
        System.out.print("Elemento - " + "[" + j + "]" + " - " + arr2[i] + "\n");
      }
    }
    sc.close();
  }
}