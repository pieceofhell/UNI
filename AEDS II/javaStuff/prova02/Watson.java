import java.util.Scanner;

public class Watson {

  public static int sort(int[] arr) {
    int trocas = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          int temp = arr[i];
          arr[i] = arr[j];
          arr[j] = temp;
          trocas++;
        }
      }
    }
    return trocas;
  }

  public static int[] stringArrayToInt(String[] string) {
    int[] numbers = new int[string.length];
    for (int i = 0; i < string.length; i++) {
      numbers[i] = Integer.parseInt(string[i]);
    }
    return numbers;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int instancias = sc.nextInt();
    int numInteiros = 0;

    for (int i = 0; i < instancias; i++) {
      numInteiros = sc.nextInt();
      String[] arrStringNums = new String[numInteiros];
      for (int j = 0; j < numInteiros; j++) {
        arrStringNums[j] = sc.next();
      }
      int[] numeros = stringArrayToInt(arrStringNums);

      int trocas = sort(numeros);

      for (int j = 0; j < numeros.length; j++) {
        System.out.println(numeros[j]);  
      }
      
      System.out.println(trocas);
    }

    sc.close();
  }
}
