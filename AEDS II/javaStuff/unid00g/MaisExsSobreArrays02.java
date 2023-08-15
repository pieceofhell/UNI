import java.util.*;

public class MaisExsSobreArrays02 {

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

    for (int i = 0; i < arr.length; i++) {
      arr[i] += arr[2*i+1];
      if (arr[i] < n) {
        break;
      }
    }
    
    sc.close();
  }
}
