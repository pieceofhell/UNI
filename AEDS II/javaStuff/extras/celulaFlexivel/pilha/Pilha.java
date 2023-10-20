public class Pilha {

  private Cell first;
  private Cell last;

  public Pilha() {
    first = new Cell();
    last = first;
  }

  // public void insert(int x) {
  //   Cell tmp = new Cell(x);
  //   last.next = tmp;
  //   last = tmp;
  // }

  public void insertPos(int x, int n) {
    Cell tmp = new Cell(x);

    if (n == 0) {
      tmp.next = first;
      first = tmp;
      return;
    }

    Cell aux = first;
    for (int i = 0; i < n - 1; i++) {
      aux = aux.next;
    }
    tmp.next = aux.next;
    aux.next = tmp;
  }

  public int remove() {
    int num = first.element;
    first = first.next;
    return num;
  }

  public void print() {
    for (Cell i = first; i != null; i = i.next) {
      System.out.println("[\t" + i.element + "\t]");
    }
  }
}