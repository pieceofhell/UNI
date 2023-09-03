import java.util.*;

public class PalindromoRecursivo {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String pal;
    while (true) {
      pal = in.nextLine();
      if (pal.equals("FIM")) {
        break;
      }
      if (isPalindromo(pal)) {
        System.out.println("SIM");
      } else {
        System.out.println("NAO");
      }
    }
    in.close();
  }

  static boolean isPalindromo(String str) {
    return isPalindromoRec(str, 0, str.length() - 1);
  }

  static boolean isPalindromoRec(String str, int start, int end) {
    if (start >= end) {
      return true;
    }
    if (str.charAt(start) != str.charAt(end)) {
      return false;
    }
    return isPalindromoRec(str, start + 1, end - 1);
  }
}