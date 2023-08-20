import java.util.Scanner;

public class OrdernaRecursivo {

    public static int[] recursiveSort(int[] arr, int i) {
        if (i < arr.length) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
                int aux = arr[min];
                arr[min] = arr[i];
                arr[i] = aux;
            }
            recursiveSort(arr, i + 1);
        }
        return arr;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];

        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        recursiveSort(arr, 0);

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }

        sc.close();
    }
}