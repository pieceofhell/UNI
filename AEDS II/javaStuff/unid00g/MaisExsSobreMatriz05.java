import java.util.*;

public class MaisExsSobreMatriz05 {

  public static void matAvg(int[][] arr1) {
    System.out.println("Media dos elementos da matriz:");
    double sum = 0;
    int count = 0;
    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        sum += arr1[i][j];
        count++;
      }
    }
    sum /= count;
    System.out.println(sum);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o tamanho de linhas e de colunas:");

    int n = sc.nextInt();

    int[][] arr1 = new int[n][n];

    System.out.println("Insira os valores da matriz:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (sc.hasNextInt()) {
          arr1[i][j] = sc.nextInt();
        }
      }
    }

    System.out.println();
    for (int i = 0; i < arr1.length; i++) {
      for (int j = 0; j < arr1[i].length; j++) {
        System.out.print("[ " + arr1[i][j] + " ]\t");
      }
      System.out.println();
    }

    matAvg(arr1);

    sc.close();
  }
}
