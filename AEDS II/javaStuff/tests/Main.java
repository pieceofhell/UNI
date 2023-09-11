public class Main {

  public static void FILO() {
    Pilha john = new Pilha(10);
    for (int i = 0; i < 10; i++) {
      john.inserir(i);
    }
    john.print();
    System.out.println();
    john.remover();
    john.print();
  }

  public static void FIFO() {
    Fila doe = new Fila(10);
    for (int i = 0; i < 10; i++) {
      doe.inserir(i);
    }
    doe.print();
    System.out.println();
    doe.remover();
    doe.print();
  }

  public static void FIFOC() {
    FilaCircular jeff = new FilaCircular(11);
    for (int i = 0; i < 10; i++) {
      jeff.inserir(i);
    }
    jeff.print();
    System.out.println();
    jeff.remover();
    jeff.print();
  }

  public static void main(String[] args) {
    FILO();
    // FIFO();
    // FIFOC();
  }
}