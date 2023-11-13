/**
 * No da arvore binaria
 * @author Max do Val Machado
 */

class No {

  public Lista lista; // Conteudo do no.
  public No esq, dir; // Filhos da esq e dir.

  /**
   * Construtor da classe.
   * @param elemento Conteudo do no.
   */
  public No(Lista lista) {
    this(lista, null, null);
  }

  /**
   * Construtor da classe.
   * @param lista Conteudo do no.
   * @param esq No da esquerda.
   * @param dir No da direita.
   */
  public No(Lista lista, No esq, No dir) {
    this.lista = lista;
    this.esq = esq;
    this.dir = dir;
  }
}
