import java.util.Scanner;

public class ConfereVogais {

    public static int isVogal(char[] s, int i) {
        int countV = 0;
        if (i < s.length) {
            if (s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o'
                    || s[i] == 'u' || s[i] == 'A' || s[i] == 'E' || s[i] == 'I' || s[i] == 'O' || s[i] == 'U') {
                countV++;
            }
            countV += isVogal(s, i + 1);
        }
        return countV;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pal;
        pal = sc.nextLine();
        System.out.println(isVogal(pal.toCharArray(), 0));
        sc.close();
    }
}