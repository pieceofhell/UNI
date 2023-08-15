import java.util.*;

public class MaisExsSobreArrays08 {

  public static int[] ezSort(int[] vet) {
    Arrays.sort(vet);
    return vet;
  }

  public static int[] hardSort(int[] vet) {
    for (int i = 0; i < vet.length; i++) {
      for (int j = i + 1; j < vet.length; j++) {
        int tmp = 0;
        if (vet[i] > vet[j]) {
          tmp = vet[i];
          vet[i] = vet[j];
          vet[j] = tmp;
        }
      }
    }
    return vet;
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

    ezSort(arr);

    System.out.println(
      "Array modificada (funcao facil, da util Arrays): " + Arrays.toString(arr)
    );

    hardSort(arr);

    System.out.println(
      "Array modificada (funcao logica, feita a mao): " + Arrays.toString(arr)
    );

    System.out.println(
      "Note que os resultados sao os mesmos, ja que a logica utilizada em ambas funcoes sao as mesmas."
    );
    sc.close();
  }
}