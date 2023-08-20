import java.util.Scanner;

public class TorreHanoi {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o numero de discos: ");
        int n = sc.nextInt();
        torreHanoi(n, 'A', 'B', 'C');
        sc.close();
    }
    public static void torreHanoi(int n, char origem, char auxiliar, char destino) {
        if (n > 0) {
            torreHanoi(n - 1, origem, destino, auxiliar);
            System.out.println("Mova o disco " + n + " da torre " + origem + " para a torre " + destino);
            torreHanoi(n - 1, auxiliar, origem, destino);
        }
    }
}