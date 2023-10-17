public class Fila {

  private Cell first;
  private Cell last;

  Fila() {
    first = new Cell();
    last = first;
  }

  public void insert(int x) {
    Cell tmp = new Cell(x);
    last.next = tmp;
    last = tmp;
    tmp = null;
  }

  public void insertPos(int x, int n) {
    Cell tmp = new Cell(x);
    Cell aux = first;
    for (int i = 0; i < n - 1; i++) {
      aux = aux.next;
    }
    tmp.next = aux.next;
    aux.next = tmp;
    aux = null;
    tmp = null;
  }

  public int remove() {
    int num = first.element;
    first = first.next;
    return num;
  }

  public void print(){
    for (Cell i = first; first != null; first = first.next){
        System.out.println("[\t" + i.element + "\t]");
    }
  }
}
