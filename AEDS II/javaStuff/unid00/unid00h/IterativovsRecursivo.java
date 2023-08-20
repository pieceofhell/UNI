// import java.util.Scanner;

public class IterativovsRecursivo {
    public static void fatIterativo(int n) {
        for (int i = 0; i <= n; i++) {
            System.out.println(i);
        }
    }

    public static void fatRecursivo(int n, int i) {
            if (i <= n) {
            System.out.println(i);
            fatRecursivo(n, i + 1);
            }
    }
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        int n = 3;
        System.out.println("Metodo Iterativo");
        fatIterativo(n);
        System.out.println("Metodo Recursivo");
        fatRecursivo(n, 0);
        // sc.close();
    }
}