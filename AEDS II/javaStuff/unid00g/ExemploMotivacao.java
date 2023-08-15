import java.util.*;

public class ExemploMotivacao {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = 0;

    n = sc.nextInt();

    int[] arr = new int[n];

    System.out.println("Insira os valores:\n");

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

    for (int i = 0; i < arr.length; i++) {
        if (arr[i] >= 80) {
            System.out.println("Notas acima de 80: " + arr[i]);                
        }        
    }

    System.out.println("Medias das notas: " + avg);

    sc.close();
  }
}