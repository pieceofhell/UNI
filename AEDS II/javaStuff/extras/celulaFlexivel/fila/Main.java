public class Main {

  public static void main(String[] args) {
    Fila fila = new Fila();
    int counter = 0;
    for (int i = 0; i < 10; i++) {
      counter += 5;
      fila.insert(counter);
    }
    fila.print();
  }
}
