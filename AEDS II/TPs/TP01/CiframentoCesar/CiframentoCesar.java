import java.util.Scanner;

public class CiframentoCesar {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        String linha = "";
        boolean fim = false;
        while (!fim) {
            linha = sc.nextLine();
            StringBuilder crypto = new StringBuilder();

            for (int i = 0; i < linha.length(); i++) {
                char ch = linha.charAt(i);

                char encryptedChar = (char) Math.min(127, ch + 3);
                crypto.append(encryptedChar);
            }

            if (linha.equals("FIM")) {
                fim = true;
            }
            System.out.println(crypto.toString());
        }
        sc.close();
    }
}