import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class og {

  public static int contador(String str, int ch) {
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      if ((int) str.charAt(i) == ch) {
        count++;
      }
    }
    return count;
  }

  public static void main(String args[]) {
    Scanner input = new Scanner(System.in);
    String nome = "";
    String urlSite;
    
    try {
      while (!nome.equals("FIM")) {
        System.out.println("Digite um nome (ou 'FIM' para sair):");
        nome = input.nextLine();
        
        if (nome.equals("FIM")) {
          break; // Saia do loop se o usuário digitou "FIM"
        }
        
        System.out.println("Digite uma URL:");
        urlSite = input.nextLine();
        URL url = new URL(urlSite);
        Scanner sc = new Scanner(url.openStream());

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
          sb.append(sc.next());
        }
        String omegaString = sb.toString();
        
        int count = contador(omegaString, 225);
        System.out.println("a("+count+")");

        sc.close();
      }
    } catch (IOException e) {
      e.printStackTrace(); // Trate a exceção de forma apropriada
    } finally {
      input.close(); // Feche o scanner de entrada
    }
  }
}