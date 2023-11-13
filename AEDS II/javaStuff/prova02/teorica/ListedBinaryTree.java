// import java.util.List;

/**
 * Arvore binaria de pesquisa
 * @author Max do Val Machado
 */
public class ListedBinaryTree {

  private No raiz; // Raiz da arvore.

  /**
   * Construtor da classe.
   */
  public ListedBinaryTree() {
    raiz = null;
  }

  /**
   * Metodo publico iterativo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */

  // CODIGO MORTO
  // public boolean pesquisar(int x) {
  // 	return pesquisar(x, raiz);
  // }

  /**
   * Metodo privado recursivo para pesquisar elemento.
   * @param x Elemento que sera procurado.
   * @param i No em analise.
   * @return <code>true</code> se o elemento existir,
   * <code>false</code> em caso contrario.
   */

  // CODIGO MORTO
  // private boolean pesquisar(int x, No i) {
  //   boolean resp;
  // 	if (i == null) {
  //      resp = false;

  //   } else if (x == i.elemento) {
  //      resp = true;

  //   } else if (x < i.elemento) {
  //      resp = pesquisar(x, i.esq);

  //   } else {
  //      resp = pesquisar(x, i.dir);
  //   }
  //   return resp;
  // }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharCentral() {
    System.out.print("[ ");
    caminharCentral(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharCentral(No i) {
    if (i != null) {
      caminharCentral(i.esq); // Elementos da esquerda.
      i.lista.mostrar();
      System.out.print(" "); // Conteudo do no.
      caminharCentral(i.dir); // Elementos da direita.
    }
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharPre() {
    System.out.print("[ ");
    caminharPre(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharPre(No i) {
    if (i != null) {
      i.lista.mostrar(); // Conteudo do no (LISTA).
      System.out.print(" ");
      caminharPre(i.esq); // Elementos da esquerda.
      caminharPre(i.dir); // Elementos da direita.
    }
  }

  /**
   * Metodo publico iterativo para exibir elementos.
   */
  public void caminharPos() {
    System.out.print("[ ");
    caminharPos(raiz);
    System.out.println("]");
  }

  /**
   * Metodo privado recursivo para exibir elementos.
   * @param i No em analise.
   */
  private void caminharPos(No i) {
    if (i != null) {
      caminharPos(i.esq); // Elementos da esquerda.
      caminharPos(i.dir); // Elementos da direita.
      i.lista.mostrar(); // Conteudo do no (LISTA).
      System.out.print(" ");
    }
  }

  /**
   * Metodo publico iterativo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserir(Lista newLista) throws Exception {
    raiz = inserir(newLista, raiz);
  }

  /**
   * Metodo privado recursivo para inserir elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se o elemento existir.
   */
  private No inserir(Lista newLista, No i) throws Exception {
    if (i == null) {
      i = new No(newLista);
    } else if (
      newLista.mediaLista() <= i.lista.mediaLista()
    ) {
      i.esq = inserir(newLista, i.esq);
    } else if (
      newLista.mediaLista() >= i.lista.mediaLista()
    ) {
      i.dir = inserir(newLista, i.dir);
    } else {
      throw new Exception("Erro ao inserir!");
    }

    return i;
  }

  /**
   * Metodo publico para inserir elemento.
   * @param x Elemento a ser inserido.
   * @throws Exception Se o elemento existir.
   */
  public void inserirPai(Lista newLista) throws Exception {
    if (raiz == null) {
      raiz = new No(newLista);
    } else if (
      newLista.mediaLista() < raiz.lista.mediaLista()
    ) {
      inserirPai(newLista, raiz.esq, raiz);
    } else if (
      newLista.mediaLista() > raiz.lista.mediaLista()
    ) {
      inserirPai(newLista, raiz.dir, raiz);
    } else {
      throw new Exception("Erro ao inserirPai!");
    }
  }

  /**
   * Metodo privado recursivo para inserirPai elemento.
   * @param x Elemento a ser inserido.
   * @param i No em analise.
   * @param pai No superior ao em analise.
   * @throws Exception Se o elemento existir.
   */
  private void inserirPai(Lista newLista, No i, No pai) throws Exception {
    if (i == null) {
      if (newLista.mediaLista() < pai.lista.mediaLista()) {
        pai.esq = new No(newLista);
      } else {
        pai.dir = new No(newLista);
      }
    } else if (
      newLista.mediaLista() < i.lista.mediaLista()
    ) {
      inserirPai(newLista, i.esq, i);
    } else if (
      newLista.mediaLista() > i.lista.mediaLista()
    ) {
      inserirPai(newLista, i.dir, i);
    } else {
      throw new Exception("Erro ao inserirPai!");
    }
  }

  /**
   * Metodo publico iterativo para remover elemento.
   * @param x Elemento a ser removido.
   * @throws Exception Se nao encontrar elemento.
   */
  public void remover(Lista removedLista) throws Exception {
    raiz = remover(removedLista, raiz);
  }

  /**
   * Metodo privado recursivo para remover elemento.
   * @param x Elemento a ser removido.
   * @param i No em analise.
   * @return No em analise, alterado ou nao.
   * @throws Exception Se nao encontrar elemento.
   */
  private No remover(Lista removedLista, No i) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover!");
    } else if (
      removedLista.mediaLista() < i.lista.mediaLista()
    ) {
      i.esq = remover(removedLista, i.esq);
    } else if (
      removedLista.mediaLista() > i.lista.mediaLista()
    ) {
      i.dir = remover(removedLista, i.dir);
      // Sem no a direita.
    } else if (i.dir == null) {
      i = i.esq;
      // Sem no a esquerda.
    } else if (i.esq == null) {
      i = i.dir;
      // No a esquerda e no a direita.
    } else {
      i.esq = maiorEsq(i, i.esq);
    }

    return i;
  }

  /**
   * Metodo para trocar o elemento "removido" pelo maior da esquerda.
   * @param i No que teve o elemento removido.
   * @param j No da subarvore esquerda.
   * @return No em analise, alterado ou nao.
   */
  private No maiorEsq(No i, No j) {
    // Encontrou o maximo da subarvore esquerda.
    if (j.dir == null) {
      i.lista = j.lista; // Substitui i por j.
      j = j.esq; // Substitui j por j.ESQ.
      // Existe no a direita.
    } else {
      // Caminha para direita.
      j.dir = maiorEsq(i, j.dir);
    }
    return j;
  }

  /**
   * Metodo que retorna o maior elemento da árvore
   * @return int maior elemento da árvore
   */
  public int getMaior() {
    int resp = -1;

    if (raiz != null) {
      No i;
      for (i = raiz; i.dir != null; i = i.dir);
      resp = i.lista.mediaLista();
    }

    return resp;
  }

  /**
   * Metodo que retorna o menor elemento da árvore
   * @return int menor elemento da árvore
   */
  public int getMenor() {
    int resp = -1;

    if (raiz != null) {
      No i;
      for (i = raiz; i.esq != null; i = i.esq);
      resp = i.lista.mediaLista();
    }

    return resp;
  }

  /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
  public int getAltura() {
    return getAltura(raiz, 0);
  }

  /**
   * Metodo que retorna a altura da árvore
   * @return int altura da árvore
   */
  public int getAltura(No i, int altura) {
    if (i == null) {
      altura--;
    } else {
      int alturaEsq = getAltura(i.esq, altura + 1);
      int alturaDir = getAltura(i.dir, altura + 1);
      altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
    }
    return altura;
  }

  /**
   * Metodo publico iterativo para remover elemento.
   * @param x Elemento a ser removido.
   * @throws Exception Se nao encontrar elemento.
   */
  public void remover2(Lista removedLista) throws Exception {
    if (raiz == null) {
      throw new Exception("Erro ao remover2!");
    } else if (
      removedLista.mediaLista() < raiz.lista.mediaLista()
    ) {
      remover2(removedLista, raiz.esq, raiz);
    } else if (
      removedLista.mediaLista() > raiz.lista.mediaLista()
    ) {
      remover2(removedLista, raiz.dir, raiz);
    } else if (raiz.dir == null) {
      raiz = raiz.esq;
    } else if (raiz.esq == null) {
      raiz = raiz.dir;
    } else {
      raiz.esq = maiorEsq(raiz, raiz.esq);
    }
  }

  /**
   * Metodo privado recursivo para remover elemento.
   * @param x Elemento a ser removido.
   * @param i No em analise.
   * @param pai do No em analise.
   * @throws Exception Se nao encontrar elemento.
   */
  private void remover2(Lista removedLista, No i, No pai) throws Exception {
    if (i == null) {
      throw new Exception("Erro ao remover2!");
    } else if (removedLista.mediaLista() < i.lista.mediaLista()) {
      remover2(removedLista, i.esq, i);
    } else if (removedLista.mediaLista() > i.lista.mediaLista()) {
      remover2(removedLista, i.dir, i);
    } else if (i.dir == null) {
      pai = i.esq;
    } else if (i.esq == null) {
      pai = i.dir;
    } else {
      i.esq = maiorEsq(i, i.esq);
    }
  }

  public Lista getRaiz() throws Exception {
    return raiz.lista;
  }

  public static boolean isMediaIgual(ListedBinaryTree a1, ListedBinaryTree a2) {
    return isMediaIgual(a1.raiz, a2.raiz);
  }

  private static boolean isMediaIgual(No i1, No i2) {
    boolean resp;
    if (i1 != null && i2 != null) {
      resp =
        (i1.lista.mediaLista() == i2.lista.mediaLista()) &&
        isMediaIgual(i1.esq, i2.esq) &&
        isMediaIgual(i1.dir, i2.dir);
    } else if (i1 == null && i2 == null) {
      resp = true;
    } else {
      resp = false;
    }
    return resp;
  }

  public int soma() {
    return soma(raiz);
  }

  public int soma(No i) {
    int resp = 0;
    if (i != null) {
      resp = i.lista.sum() + soma(i.esq) + soma(i.dir);
    }
    return resp;
  }

  public int quantidadePares() {
    return quantidadePares(raiz);
  }

  public int quantidadePares(No i) {
    int resp = 0;
    if (i != null) {
      resp =
        ((i.lista.mediaLista() % 2 == 0) ? 1 : 0) +
        quantidadePares(i.esq) +
        quantidadePares(i.dir);
    }
    return resp;
  }
}
