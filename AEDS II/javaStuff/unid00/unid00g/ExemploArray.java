import java.util.*;

public class ExemploArray {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = new int[5];
    // int[] criterio = new int[x];

    System.out.println("Insira as notas dos alunos:\n");
    for (int i = 0; i < 5; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    int avg = 0;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= 80) {
        avg += arr[i];
      }
    }

    avg /= 5;

    for (int i = 0; i < arr.length; i++) {
      if (arr[i] >= avg) {
        System.out.println("Notas acima da media: " + arr[i]);
      }
    }

    System.out.println("Medias das notas: " + avg);

    sc.close();
  }
}
