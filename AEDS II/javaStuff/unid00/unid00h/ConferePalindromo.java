import java.util.*;

public class ConferePalindromo {

  boolean isPalindromo(String s){
    return isPalindromo(s, 0);
    }
    boolean isPalindromo(String s, int i){
    boolean resp;
    if (i >= s.length() / 2){
    resp = true;
    } else if (s.charAt(i) != s.charAt(s.length() - 1 - i)){
    resp = false;
    } else {
    resp = isPalindromo(s, i + 1);
    }
    return resp;
    }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String pal;
    pal = sc.nextLine();

    ConferePalindromo cp = new ConferePalindromo();
    System.out.println(cp.isPalindromo(pal));
    sc.close();
  }
}