import java.util.Scanner;

public class ExemploIf {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira a nota do aluno:");
        int nota = sc.nextInt();

        if(nota >= 80) {
            System.out.println("Parabens, muito bom");
        } else if (nota >= 70 && nota < 80) {
            System.out.println("Parabens, aprovado");
        } else {
            System.out.println("Infelizmente, reprovado");
        }

        sc.close();       
    }
}