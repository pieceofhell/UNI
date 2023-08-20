import java.util.Scanner;

public class MaisExsSobreArrays12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o numero de elementos (N): ");
        int N = sc.nextInt();

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            System.out.print("Digite o " + (i + 1) + "º elemento: ");
            arr[i] = sc.nextInt();
        }

        if (N % 2 == 0) {
            System.out.println("Soma dos elementos adjacentes:");
            for (int i = 0; i < N; i += 2) {
                int sum = arr[i] + arr[i + 1];
                System.out.println(arr[i] + " + " + arr[i + 1] + " = " + sum);
            }
        } else {
            System.out.println("O numero de elementos é impar, não eh possivel realizar a soma dos adjacentes.");
        }

        sc.close();
    }
}
