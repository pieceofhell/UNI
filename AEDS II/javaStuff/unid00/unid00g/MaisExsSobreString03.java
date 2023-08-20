import java.util.*;

public class MaisExsSobreString03 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String pal;
    boolean hasDif = false;
    while (true) {
      hasDif = false;
      pal = sc.nextLine();
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
    sc.close();
  }
}