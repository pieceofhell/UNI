import java.util.Scanner;
import java.lang.Math;

public class MaisExsIfElse06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira os 2 numeros:");

        double[] nums = new double[2];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextDouble();    
        }       

        double maior = nums[0], menor = nums[0];

        for (int i = 0; i < nums.length; i++) {
            if (maior < nums[i]){
                maior = nums[i];
            }

            if (menor > nums[i]){
                menor = nums[i];
            }
        }
    
        menor = Math.cbrt(menor);
        maior = Math.log(maior) / Math.log(menor);

        System.out.println("Raiz cubica do menor numero: " + menor);
        System.out.println("Logaritmo do menor numero (considerando o maior como base): " + maior);

        sc.close();
    }
}