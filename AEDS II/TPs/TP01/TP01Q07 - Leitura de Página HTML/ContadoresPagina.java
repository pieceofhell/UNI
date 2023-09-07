import java.util.Scanner;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;

public class ContadoresPagina {

  public static String obterPagina(String url) {
    try {
      URL pagina = new URL(url);

      HttpURLConnection conexao = (HttpURLConnection) pagina.openConnection();

      conexao.setRequestMethod("GET");

      int responseCode = conexao.getResponseCode();

      if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader leitor = new BufferedReader(
          new InputStreamReader(conexao.getInputStream())
        );
        String line;
        StringBuilder omegaStringContent = new StringBuilder();

        while ((line = leitor.readLine()) != null) {
          omegaStringContent.append(line);
        }

        leitor.close();
        conexao.disconnect();

        return omegaStringContent.toString();
      } else {
        System.out.println("Erro: " + responseCode);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static int contador(String str, char c, int i) {
    int res = 0;
    while (i < str.length()) {
      if (str.charAt(i) == c) {
        res++;
      }
      i++;
    }
    return res;
  }

  public static boolean isVogal(char c) {
    String vogais = "aeiouáéíóúàèìòùãõâêîôûAEIOUÁÉÍÓÚÀÈÌÒÙÃÕÂÊÎÔÛ";
    return vogais.indexOf(c) != -1;
}

public static int contadorConsoante(String str) {
    int contConsoantes = 0;
    for (int i = 0; i < str.length(); i++) {
        char currentChar = str.charAt(i);
        if (!isVogal(currentChar) && (currentChar >= 'a' && currentChar <= 'z')) {
            contConsoantes++;
        }
    }
    return contConsoantes;
}

  public static int excecoes(String str, String sub) {
    int res = 0;
    int i = 0;
    boolean igual = false;
    while (i < str.length()) {
      igual = true;
      for (int j = 0; j < sub.length(); j++) {
        if (str.charAt(i + j) != sub.charAt(j)) {
          igual = false;
          break;
        }
      }
      if (igual) {
        res++;
        i += sub.length();
      } else {
        i++;
      }
    }
    return res;
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    String nome = scan.nextLine();
    String url = "";
    while (!nome.equals("FIM")) {
      url = scan.nextLine();
      String omegaString = obterPagina(url);
      System.out.print("a(" + contador(omegaString, 'a', 0) + ") ");
      System.out.print("e(" + contador(omegaString, 'e', 0) + ") ");
      System.out.print("i(" + contador(omegaString, 'i', 0) + ") ");
      System.out.print("o(" + contador(omegaString, 'o', 0) + ") ");
      System.out.print("u(" + contador(omegaString, 'u', 0) + ") ");
      System.out.print(
        '\u00E1' + "(" + contador(omegaString, '\u00E1', 0) + ") "
      );
      System.out.print(
        '\u00E9' + "(" + contador(omegaString, '\u00E9', 0) + ") "
      );
      System.out.print(
        '\u00ED' + "(" + contador(omegaString, '\u00ED', 0) + ") "
      );
      System.out.print(
        '\u00F3' + "(" + contador(omegaString, '\u00F3', 0) + ") "
      );
      System.out.print(
        '\u00FA' + "(" + contador(omegaString, '\u00FA', 0) + ") "
      );
      System.out.print(
        '\u00E0' + "(" + contador(omegaString, '\u00E0', 0) + ") "
      );
      System.out.print(
        '\u00E8' + "(" + contador(omegaString, '\u00E8', 0) + ") "
      );
      System.out.print(
        '\u00EC' + "(" + contador(omegaString, '\u00EC', 0) + ") "
      );
      System.out.print(
        '\u00F2' + "(" + contador(omegaString, '\u00F2', 0) + ") "
      );
      System.out.print(
        '\u00F9' + "(" + contador(omegaString, '\u00F9', 0) + ") "
      );
      System.out.print(
        '\u00E3' + "(" + contador(omegaString, '\u00E3', 0) + ") "
      );
      System.out.print(
        '\u00F5' + "(" + contador(omegaString, '\u00F5', 0) + ") "
      );
      System.out.print(
        '\u00E2' + "(" + contador(omegaString, '\u00E2', 0) + ") "
      );
      System.out.print(
        '\u00EA' + "(" + contador(omegaString, '\u00EA', 0) + ") "
      );
      System.out.print(
        '\u00EE' + "(" + contador(omegaString, '\u00EE', 0) + ") "
      );
      System.out.print(
        '\u00F4' + "(" + contador(omegaString, '\u00F4', 0) + ") "
      );
      System.out.print(
        '\u00FB' + "(" + contador(omegaString, '\u00FB', 0) + ") "
      );

      System.out.print("consoante(" + contadorConsoante(omegaString) + ") ");

      System.out.print("<br>(" + excecoes(omegaString, "<br>") + ") ");
      System.out.print(
        "<table>(" + excecoes(omegaString, "<table>") + ") " + nome + "\n"
      );

      nome = scan.nextLine();
    }
    scan.close();
  }
}
