import java.util.Scanner;

public class MaisExsIfElse04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira os gols do mandante:");

        int golsMan, golsVis;
        golsMan = sc.nextInt();

        System.out.println("Insira os gols do visitante:");

        golsVis = sc.nextInt();

        if (golsMan > golsVis) {
            System.out.println("O time vencedor foi o mandante");
        } else if (golsVis > golsMan) {
            System.out.println("O time vencedor foi o visitante");
        } else {
            System.out.println("Empate");
        }

        sc.close();
    }
}