import java.util.Scanner;

public class AchaMaiorArray {
    public static int achaMaior(int[] array, int n) {
        int maior = array[0];
        if (n == 1) {
            return array[0];
        } else {
            maior = achaMaior(array, n - 1);
            if (maior < array[n - 1]) {
                maior = array[n - 1];
            }
        }
        return maior;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o tamanho da array:");
        int n = sc.nextInt();
        int[] array = new int[n];
        System.out.println("Digite os elementos da array:");
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
        System.out.println("Maior numero presente na array fornecida: "+ achaMaior(array, n));
        sc.close();
    }
}