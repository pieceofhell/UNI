import java.util.Scanner;

public class CiframentoCesarRecursivo {

  static String encrypt(String linha, int i) {
    if (i >= linha.length()) {
      return ""; // Base case for recursion
    }

    char ch = linha.charAt(i);
    String crypto = "";
    if (ch == 65533) {
      crypto += ch;
    } else {
      char encryptedChar = (char) (ch + 3);
      crypto += encryptedChar;
    }
    // Append the result of the recursive call to crypto
    crypto += encrypt(linha, i + 1);
    return crypto;
  }

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    String linha = "";
    boolean fim = false;
    while (!fim) {
      linha = sc.nextLine();
      if (linha.equals("FIM")) {
        fim = true;
      } else {
        String encryptedLine = encrypt(linha, 0);
        System.out.println(encryptedLine);
      }
    }
    sc.close();
  }
}