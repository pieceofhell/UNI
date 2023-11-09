public class Test {

  // declaração da array

  static int numElementos = 10;
  static int[] array = new int[numElementos];

  public static double logBase(double num, double base) {
    double res = (int) (Math.log(num) / Math.log(base));
    return res;
  }

  public static void swap(int a, int b) {
    int temp = array[a];
    array[a] = array[b];
    array[b] = temp;
  }

  public static void preencherArray(int[] arr) {
    int count = 0;
    for (int i = numElementos - 1; i >= 0; i--) {
      count += 2;
      array[i] = count;
    }
  }

  public static void selectionSort(int[] arr) {
    for (int i = 0; i < (numElementos - 1); i++) {
      int menor = i;
      for (int j = (i + 1); j < numElementos; j++) {
        if (arr[menor] > arr[j]) {
          menor = j;
        }
      }
      swap(menor, i);
    }
  }

  public void insertionSort(int[] arr) {
    for (int i = 1; i < numElementos; i++) {
      int tmp = arr[i];
      int j = i - 1;

      while ((j >= 0) && (arr[j] > tmp)) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = tmp;
    }
  }

  public static boolean binarySearch(int[] arr, int target) {
    int left = 0;
    int right = numElementos - 1;
    int mid;
    while (left <= right) {
      mid = (left + right) / 2;
      if (target > arr[mid]) {
        left = mid + 1;
      } else if (target < arr[mid]) {
        right = mid - 1;
      } else if (target == arr[mid]) {
        return true;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    // chamada da função de preenchimento da array, de trás para frente
    preencherArray(array);

    // Impressao da array (desordenada):
    for (int i = 1; i <= numElementos; i++) {
      System.out.print("[ " + array[i - 1] + " ]");
      if (i % 8 == 0) {
        System.out.println();
      }
    }

    selectionSort(array);

    System.out.println(binarySearch(array, 16));
    // Codigo para teste de um for com função de complexidade = lg(n) - 1
    // int n = 300, x = 1, count = 0;
    // for (int i = n; i > 0; i /= 2) {
    //   x *= 2;
    //   count++;
    // }
    // System.out.println("Valor do log de n: " + logB(n, 2));

    // Chamada do Selection Sort

    // System.out.println("Numero de iteracoes realizadas: " + count);
    // System.out.println(x);

  }
}
