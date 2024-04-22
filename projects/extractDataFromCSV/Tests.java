import java.util.Scanner;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Tests {

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
    float a = 0 * -1;
    System.out.println(a);
    // Scanner sc = new Scanner(System.in);
    // String entradaUsuario = sc.nextLine();
    // System.out.println(removeAccents2(entradaUsuario));
    // sc.close();
  }
}
