import java.util.*;

public class MaisExsSobreArrays11 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = new int[5];

    System.out.println("Insira as notas dos alunos:");
    for (int i = 0; i < 5; i++) {
      if (sc.hasNextInt()) {
        arr[i] = sc.nextInt();
      }
    }

    int avg = 0, sum = 0, menor = arr[0];
    for (int i = 0; i < arr.length; i++) {
      avg += arr[i];
      sum += arr[i];
    }

    for (int i = 0; i < arr.length; i++) {
      if (menor > arr[i]) {
        menor = arr[i];
      }
    }

    avg /= arr.length;

    System.out.println("Soma das notas: " + sum);
    System.out.println("Medias das notas: " + avg);
    System.out.println("Menor nota: " + menor);

    sc.close();
  }
}