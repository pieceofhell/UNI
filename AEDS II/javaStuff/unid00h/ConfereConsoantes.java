import java.util.Scanner;

public class ConfereConsoantes {

    public static int isConsoante(char[] s, int i) {
        int countC = 0;
        if (i < s.length) {
            if (s[i] != 'a' && s[i] != 'e' && s[i] != 'i' && s[i] != 'o'
                    && s[i] != 'u' && s[i] != 'A' && s[i] != 'E' && s[i] != 'I' && s[i] != 'O' && s[i] != 'U') {
                countC++;
            }
            countC += isConsoante(s, i + 1);
            return countC;
        } else {
            return 0;            
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pal;
        pal = sc.nextLine();
        System.out.println(isConsoante(pal.toCharArray(), 0));
        sc.close();
    }
}