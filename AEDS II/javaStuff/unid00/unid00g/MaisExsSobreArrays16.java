import java.util.*;

public class MaisExsSobreArrays16 {
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

    System.out.println("A soma dos elementos multiplos de 3 é de: " + Arrays.stream(arr).filter(x -> x % 3 == 0).sum());

    sc.close();
  }
}