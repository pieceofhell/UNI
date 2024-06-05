class GenericMethodTest {

  // método generico
  public static <E> void printArray(E[] inputArray) {
    // Imprime os elementos do array
    for (E element : inputArray) {
      System.out.printf("%s ", element);
    }
    System.out.println();
  }

  public static void main(String args[]) {
    //  Arrays inteiros, double e chars
    Integer[] intArray = { 1, 2, 3, 4, 5 };
    Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
    Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

    System.out.println("Array integerArray:");
    printArray(intArray); // passando um array de inteiros

    System.out.println("\nArray doubleArray:");
    printArray(doubleArray); // passando um array de double

    System.out.println("\nArray characterArray:");
    printArray(charArray); // passando um array de chars
  }
}

/**
 * Celula
 */
class Celula <E> {
  private E elemento;
  private Celula<E> proximo;

  public Celula(E elemento) {
    this.elemento = elemento;
    this.proximo = null;
  }

  public E getElemento() {
    return elemento;
  }

  public void setElemento(E elemento) {
    this.elemento = elemento;
  }

  public Celula<E> getProximo() {
    return proximo;
  }

  public void setProximo(Celula<E> proximo) {
    this.proximo = proximo;
  }
  
}

/**
 * ListaEncadeada
 */

class ListaEncadeada <E> {
  private Celula<E> primeiro;
  private Celula<E> ultimo;

  public ListaEncadeada() {
    this.primeiro = null;
    this.ultimo = null;
  }

  public void adiciona(E elemento) {
    Celula<E> nova = new Celula<E>(elemento);
    if (primeiro == null) {
      primeiro = nova;
      ultimo = nova;
    } else {
      ultimo.setProximo(nova);
      ultimo = nova;
    }
  }

  public void remove(E elemento) {
    Celula<E> anterior = null;
    Celula<E> atual = primeiro;
    while (atual != null && !atual.getElemento().equals(elemento)) {
      anterior = atual;
      atual = atual.getProximo();
    }
    if (atual != null) {
      if (atual == primeiro) {
        primeiro = atual.getProximo();
      } else {
        anterior.setProximo(atual.getProximo());
      }
      if (atual == ultimo) {
        ultimo = anterior;
      }
    }
  }

  public void imprime() {
    Celula<E> atual = primeiro;
    while (atual != null) {
      System.out.println(atual.getElemento());
      atual = atual.getProximo();
    }
  }
}

/**
 * TesteListaEncadeada
 */

public class TesteListaEncadeada {
  public static void main(String[] args) {
    ListaEncadeada<Integer> lista = new ListaEncadeada<Integer>();
    lista.adiciona(1);
    lista.adiciona(2);
    lista.adiciona(3);
    lista.adiciona(4);
    lista.adiciona(5);
    lista.imprime();
    lista.remove(3);
    lista.imprime();
  }
}
