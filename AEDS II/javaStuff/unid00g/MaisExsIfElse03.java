import java.util.Scanner;

public class MaisExsIfElse03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira os 2 numeros:");

        int[] nums = new int[2];

        String nome = "Roberto Carlos";

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }

        if (nums[0] > 45 || nums[1] > 45) {
            nums[0] = nums[0] + nums[1];
        } else if (nums[0] > 20 && nums[1] > 20) {
            if (nums[0] > nums[1]) {
                nums[0] -= nums[1];
            } else {
                nums[1] -= nums[0];
            }
        } else if (nums[0] < 10 || nums[1] < 10 && nums[0] != 0 && nums[1] != 0) {
            nums[0] /= nums[1];
        } else {
            System.out.println(nome);
        }
        
        sc.close();
    }
}