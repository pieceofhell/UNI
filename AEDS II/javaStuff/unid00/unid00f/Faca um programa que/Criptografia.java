import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Criptografia {

  public static void decoder(String encryptedString) {
    encryptedString.toCharArray();
    StringBuilder originalString = new StringBuilder();

    for (int i = 0; i < encryptedString.length(); i++) {
      char ch = encryptedString.charAt(i);
      // Adicione 3 ao valor numérico do caractere, mas limite a 127 para caracteres
      // ASCII
      char encryptedChar = (char) Math.min(127, ch - 3);
      originalString.append(encryptedChar);
    }

    System.out.println(originalString.toString());
  }

  public static void main(String args[]) {
    try {
      File ArqR = new File("frase.txt");

      Scanner sc = new Scanner(ArqR);

      while (sc.hasNextLine()) {
        String linha = sc.nextLine();
        StringBuilder crypto = new StringBuilder();

        for (int i = 0; i < linha.length(); i++) {
          char ch = linha.charAt(i);
          // Adicione 3 ao valor numérico do caractere, mas limite a 127 para caracteres
          // ASCII
          char encryptedChar = (char) Math.min(127, ch + 3);
          crypto.append(encryptedChar);
        }

        System.out.println(crypto.toString());
        decoder(crypto.toString());
      }

      sc.close();
    } catch (FileNotFoundException e) {
      System.out.println("Ocorreu um erro.");
      e.printStackTrace();
    }
  }
}
