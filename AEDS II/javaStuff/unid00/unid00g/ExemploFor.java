import java.util.*;

public class ExemploFor {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = new int[5];

    System.out.println("Insira as notas dos alunos:\n");
    for (int i = 0; i < 5; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    int avg = 0;
    for (int i = 0; i < arr.length; i++) {
      avg += arr[i];
    }

    avg /= arr.length;

    System.out.println("Medias das notas: " + avg);

    sc.close();
  }
}