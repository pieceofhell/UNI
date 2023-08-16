import java.util.*;

public class MaisExsSobreMatriz02 {

  public static void Transposta(int[][] array, int l, int c) {
    System.out.println("Matriz transposta:");
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < c; j++) {
        System.out.print("[ " + array[j][i] + " ]\t");
      }
      System.out.println();
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Insira o tamanho de linhas e de colunas:");

    int n = sc.nextInt();
    int m = sc.nextInt();

    int[][] arr = new int[n][m];

    System.out.println("Insira os valores:");

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (sc.hasNextInt()) {
          arr[i][j] = sc.nextInt();
        }
      }
    }

    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print("[ " + arr[i][j] + " ]\t");
      }
      System.out.println();
    }

    Transposta(arr, n, m);

    sc.close();
  }
}