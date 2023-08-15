import java.util.*;

public class ExemploSobreArray {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 0;

    System.out.println("Insira o tamanho da array:");

    n = sc.nextInt();

    int[] arr = new int[n];

    System.out.println("Insira os numeros:");
    for (int i = 0; i < n; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    System.out.println("\n");

     for (int i : arr) {
      System.out.println(i);
     }

    sc.close();
  }
}