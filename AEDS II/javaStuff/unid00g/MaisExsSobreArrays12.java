import java.util.*;

public class MaisExsSobreArrays12 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 0;

    System.out.println("Insira o tamanho da array:");
    n = sc.nextInt();

    int[] arr = new int[n];

    System.out.println("Insira os numeros:\n");
    for (int i = 0; i < n; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    int avg = 0;

    for (int i = 0; i < arr.length; i++) {
      avg += arr[i];
    }
    
    avg /= n;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= avg) {
        System.out.println("Numeros acima da media: " + arr[i]);
      }
    }

    System.out.println("Medias dos numeros: " + avg);

    sc.close();
  }
}
