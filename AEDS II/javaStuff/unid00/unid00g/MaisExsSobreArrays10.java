import java.util.*;

public class MaisExsSobreArrays10 {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] arr = {10, 5, 8, 2, 8};

    for (int i = 0; i < arr.length; i++) {
      System.out.println("Numeros contidos na array: " + arr[i]);
    }
    sc.close();
  }
}