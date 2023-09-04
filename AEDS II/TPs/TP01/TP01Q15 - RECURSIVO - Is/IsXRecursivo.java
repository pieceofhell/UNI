import java.util.Scanner;

public class IsXRecursivo {

  static boolean isVogal(String str, int i) {
    if (i < str.length()) {
      char c = Character.toLowerCase(str.charAt(i));
      if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        return isVogal(str, i + 1);
      } else {
        return false;
      }
    }
    return true;
  }

  static boolean isConsoante(String str, int i) {
    if (i < str.length()) {
      char c = Character.toLowerCase(str.charAt(i));
      if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
        return isConsoante(str, i + 1);
      } else {
        return false;
      }
    }
    return true;
  }

  static boolean isInteger(String str, int i) {
    if (i < str.length()) {
      char c = str.charAt(i);
      if (!(Character.isDigit(c)) && c != ',' && c != '.') {
        return false;
      } else {
        return isInteger(str, i + 1);
      }
    }
    return true;
  }

  static boolean isFloat(String str, int i) {
    int count = 0;
    if (i < str.length()) {
      char c = str.charAt(i);
      if (c == ',' || c == '.'){
        count++;
      }
      if (!(Character.isDigit(c)) || count >= 2) {
        return false;
      } else {
        return isFloat(str, i + 1);
      }
    }
    return true;
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String str;
    while (true) {
      str = in.nextLine();
      if (str.equals("FIM")) {
        break;
      }
      if (isVogal(str, 0)) {
        System.out.print("SIM ");
      } else {
        System.out.print("NAO ");
      }

      if (isConsoante(str, 0)) {
        System.out.print("SIM ");
      } else {
        System.out.print("NAO ");
      }

      if (isInteger(str, 0)) {
        System.out.print("SIM ");
      } else {
        System.out.print("NAO ");
      }

      if (isFloat(str, 0)) {
        System.out.print("SIM ");
      } else {
        System.out.print("NAO\n");
      }
    }
    in.close();
  }
}