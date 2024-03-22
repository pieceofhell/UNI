import java.util.Scanner;

class Palin {

  public static boolean isPalindromeRecursive(String str, int start, int end) {
    str = str.replaceAll("\\s+", "").toLowerCase();
    if (start >= end) {
      return true;
    }
    if (str.charAt(start) != str.charAt(end)) {
      return false;
    }
    return isPalindromeRecursive(str, start + 1, end - 1);
  }

  public static void main(String[] args) {
    String Palin = "";
    Scanner Palavra = new Scanner(System.in);
    while (true) {
      Palin = Palavra.nextLine();
      if (Palin.equals("FIM")) {
        break;
      }
      if (isPalindromeRecursive(Palin, 0, Palin.length() - 1)) {
        System.out.println("SIM");
      } else {
        System.out.println("NAO");
      }
    }
    Palavra.close();
  }
}
