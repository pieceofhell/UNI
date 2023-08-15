import java.util.*;

public class MaisExsSobreArrays14 {

  public static void findPar(int[] vet) {
    int[] pares = new int[vet.length];
    for (int i = 0; i < vet.length; i++) {
      if (vet[i] % 2 == 0) {
        pares[i] = vet[i];
      }
    }
    System.out.println(Arrays.toString(pares));
  }

  public static void findTres(int[] vet) {
    int[] div3 = new int[vet.length];
    for (int i = 0; i < vet.length; i++) {
      if (vet[i] % 3 == 0) {
        div3[i] = vet[i];
      }
    }
    System.out.println(Arrays.toString(div3));
  }

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

    System.out.println("Os valores pares sao:");

    findPar(arr);

    System.out.println("Os valores divisiveis por 3 sao:");

    findTres(arr);

    sc.close();
  }
}