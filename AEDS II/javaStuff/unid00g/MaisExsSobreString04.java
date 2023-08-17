import java.util.*;

public class MaisExsSobreString04 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();
    int count = 0;
    int countM = 0;
    char c = ' ';

    char[] chars = str.toCharArray();
    for (int i = 0; i < chars.length; i++) {
      count++;
      c = str.charAt(i);
      if (Character.isUpperCase(c)) {
        countM++;   
      }
    }
    sc.close();

  }
}
