import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class test {

  public static float replaceCommaToDot(String valor) {
    System.out.println(valor);
    valor = valor.replace(".", "");
    System.out.println(valor);
    return Float.parseFloat(valor.replace(',', '.'));
  }

  public static String removeAcentos(String str) {
    return str
      .replaceAll("[éêè]", "e")
      .replaceAll("[óôò]", "o")
      .replaceAll("[áãâà]", "a")
      .replaceAll("[í]", "i")
      .replaceAll("[ú]", "u")
      .replaceAll("[ç]", "c");
  }

  public static String removeAccents2(String input) {
    if (input == null) {
      return null;
    }

    // Normalize the string to decompose accented characters into their combining forms
    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

    // Use regex to remove combining diacritical marks
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    return pattern.matcher(normalized).replaceAll("");
  }

  public static String removeDoubleSpaces(String str) {
    return str.replaceAll("\\s+", " ");
  }

  public static void main(String[] args) {
    String entrada = "-1.939,24\n";
    String joao = "pênis";
    String historia = "joão se cagóú no rio. ele gósta de cómer bóstâ.";
    String deb = "Café a là carte.";
    String cred =
      "essa é  uma string       de teste para   testar espaços    .";
    // float valor = replaceCommaToDot(entrada);
    String bagu = removeAccents2(deb);
    cred = removeDoubleSpaces(cred);
    System.out.println(bagu);
  }
}
