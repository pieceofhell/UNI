import java.util.Scanner;
import java.lang.Math;

public class SequenciaRecursiva {

    public static int sequencia1(int n) {
        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 2;
        } else {
            return sequencia1(n - 1) * sequencia1(n - 2) - sequencia1(n - 1);
        }
    }

    public static double sequencia2(double n) {
        if (n == 0) {
            return 1;
        } else {
            return Math.pow(sequencia2(n - 1), 2);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int res1 = sequencia1(n);
        System.out.println("T(" + n + ") = " + res1);
        int m = sc.nextInt();
        double res2 = sequencia2(m);
        System.out.println("T(" + m + ") = " + res2);
        sc.close();
    }
}