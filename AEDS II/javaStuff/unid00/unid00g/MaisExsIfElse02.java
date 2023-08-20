import java.util.Scanner;

public class MaisExsIfElse02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira os 10 numeros:");

        int[] nums = new int[10];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();    
        }       

        int maior = nums[0], menor = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (maior < nums[i]){
                maior = nums[i];
            }

            if (menor > nums[i]){
                menor = nums[i];
            }
        }

        System.out.println("Maior numero: " + maior);
        System.out.println("Menor numero: " + menor);

        sc.close();
    }
}