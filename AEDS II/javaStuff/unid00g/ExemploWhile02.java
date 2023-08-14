import java.lang.Math;
import java.text.DecimalFormat;

public class ExemploWhile02 {
    // Faça um programa que mostre na tela o logaritmo na base 10 dos
    // números 1 à 10
    public static void main(String[] args) {
        int num = 1;

        while (num <= 10) {
            double log = Math.log10(num);
            DecimalFormat df = new DecimalFormat("#.###");
            System.out.println("Logaritmo de " + num + " na base 10: " + df.format(log));
            num++;
        }
    }
}