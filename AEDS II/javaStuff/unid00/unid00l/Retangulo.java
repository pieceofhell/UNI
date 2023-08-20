import java.util.Scanner;
import java.lang.Math;

public class Retangulo {
    private double base;
    private double altura;

    public Retangulo(double b, double h) {
        base = b;
        altura = h;
    }

    public Retangulo() {
        base = 0.0;
        altura = 0.0;
    }

    public double getBase() {
        return base;
    }

    public void setBase(double b) {
        base = b;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double h) {
        altura = h;
    }

    public double getArea() {
        return base * altura;
    }

    public double getPerimetro() {
        return 2 * (base + altura);
    }

    public double getDiagonal() {
        return Math.sqrt(base * base + altura * altura);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira a base:");
        double b = sc.nextDouble();
        System.out.println("Insira a altura:");
        double h = sc.nextDouble();
        Retangulo retangulo1 = new Retangulo(b, h);

        System.out.println("Base: " + retangulo1.getBase());
        System.out.println("Altura: " + retangulo1.getAltura());
        System.out.println("Area: " + retangulo1.getArea());
        System.out.println("Perimetro: " + retangulo1.getPerimetro());
        System.out.println("Diagonal: " + retangulo1.getDiagonal());
        sc.close();
    }
}