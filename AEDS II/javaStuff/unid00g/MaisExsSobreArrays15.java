import java.util.*;

public class MaisExsSobreArrays15 {
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

    System.out.println("O vetor inserido foi: " + Arrays.toString(arr));

    for (int i = 0; i < arr.length; i++) {
      int sum = arr[i] + arr[arr.length - 1 - i];
      System.out.println("Soma do elemento " + (i+1) + " com o elemento " + (arr.length - i) + " : " + sum);
    }

    sc.close();
  }
}