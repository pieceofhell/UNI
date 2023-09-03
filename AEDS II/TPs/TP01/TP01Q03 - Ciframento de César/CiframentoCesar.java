import java.util.Scanner;

public class CiframentoCesar {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        String linha = "";
        boolean fim = false;
        while (!fim) {
            linha = sc.nextLine();
            String crypto = "";

            for (int i = 0; i < linha.length(); i++) {
                char ch = linha.charAt(i);

                if (ch == 65533) {
                    crypto += ch;
                } else if (linha.equals("FIM")) {
                    fim = true;
                } else {
                    char encryptedChar = (char) (ch + 3);
                    crypto += encryptedChar;
                }
            }
            System.out.println(crypto);
        }
        sc.close();
    }
}