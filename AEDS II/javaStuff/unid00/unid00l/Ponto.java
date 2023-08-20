public class Ponto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    public Ponto() {
        this.x = 0.0;
        this.y = 0.0;
        this.id = nextID;
        nextID++;
    }

    public Ponto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID++;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void main(String[] args) {
        Ponto ponto1 = new Ponto();
        Ponto ponto2 = new Ponto(2.5, 3.5);

        System.out.println("Ponto 1 - ID: " + ponto1.getId());
        System.out.println("Ponto 2 - ID: " + ponto2.getId());

        System.out.println("Next ID: " + Ponto.getNextID());
    }
}