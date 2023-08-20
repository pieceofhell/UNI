public class Lixao {
    public static void main(String[] args) {
        Retangulo retangulo1 = new Retangulo(5.0, 3.0);
        Retangulo retangulo2 = new Retangulo(7.0, 4.0);

        System.out.println("Retângulo 1:");
        System.out.println("Base: " + retangulo1.getBase());
        System.out.println("Altura: " + retangulo1.getAltura());
        System.out.println("Área: " + retangulo1.getArea());
        System.out.println("Perímetro: " + retangulo1.getPerimetro());
        System.out.println("Diagonal: " + retangulo1.getDiagonal());

        System.out.println("\nRetângulo 2:");
        System.out.println("Base: " + retangulo2.getBase());
        System.out.println("Altura: " + retangulo2.getAltura());
        System.out.println("Área: " + retangulo2.getArea());
        System.out.println("Perímetro: " + retangulo2.getPerimetro());
        System.out.println("Diagonal: " + retangulo2.getDiagonal());
    }
}