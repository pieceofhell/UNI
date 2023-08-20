import java.util.Scanner;

public class ExercicioIfElse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o lado 1 do triangulo: ");
        double lado1 = sc.nextDouble();

        System.out.print("Digite o lado 2 do triangulo: ");
        double lado2 = sc.nextDouble();

        System.out.print("Digite o lado 3 do triangulo: ");
        double lado3 = sc.nextDouble();

        if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1) {
            if (lado1 == lado2 && lado2 == lado3) {
                System.out.println("Tipo do Triangulo: Equilatero.");
            } else if (lado1 == lado2 || lado1 == lado3 || lado2 == lado3) {
                System.out.println("Tipo do Triangulo: Isosceles.");
            } else {
                System.out.println("Tipo do Triangulo: Escaleno.");
            }
        } else {
            System.out.println("Os lados não formam um triangulo valido.");
        }

        sc.close();
    }
}