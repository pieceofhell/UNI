import java.util.*;

public class Palindromo {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String pal;
    boolean hasDif = false;
    while (true) {
      hasDif = false;
      pal = in.nextLine();
      if (pal.equals("FIM")) {
        break;
      }
      for (int i = 0; i < pal.length() / 2; i++) {
        if (pal.charAt(i) != pal.charAt(pal.length() - i - 1)) {
          hasDif = true;
          break;
        }
      }
      if (hasDif) {
        System.out.println("NAO");
      } else {
        System.out.println("SIM");
      }
    }
    in.close();
  }
}
