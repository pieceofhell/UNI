public class No {
    char letra;
    No esq, dir;
    Celula primeiro, ultimo;

    public No(char letra) {
        this(letra, null, null);
    }

    /**
     * Construtor da classe.
     * @param letra Conteudo do no.
     * @param esq No da esquerda.
     * @param dir No da direita.
     */
    public No(char letra, No esq, No dir) {
        this.letra = letra;
        this.esq = esq;
        this.dir = dir;
    }
}
