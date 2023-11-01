public class Agenda {
    No raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public Agenda() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar letra.
	 * @param x letra que sera procurado.
	 * @return <code>true</code> se o letra existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(char x) {
		return pesquisar(x, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar letra.
	 * @param x letra que sera procurado.
	 * @param i No em analise.
	 * @return <code>true</code> se o letra existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(char x, No i) {
      boolean resp;
		if (i == null) {
         resp = false;

      } else if (x == i.letra) {
         resp = true;

      } else if (x < i.letra) {
         resp = pesquisar(x, i.esq);

      } else {
         resp = pesquisar(x, i.dir);
      }
      return resp;
	}

	/**
	 * Metodo publico iterativo para exibir letras.
	 */
	public void caminharCentral() {
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir letras.
	 * @param i No em analise.
	 */
	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq); // letras da esquerda.
			System.out.print(i.letra + " "); // Conteudo do no.
			caminharCentral(i.dir); // letras da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir letras.
	 */
	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir letras.
	 * @param i No em analise.
	 */
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.letra + " "); // Conteudo do no.
			caminharPre(i.esq); // letras da esquerda.
			caminharPre(i.dir); // letras da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir letras.
	 */
	public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir letras.
	 * @param i No em analise.
	 */
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // letras da esquerda.
			caminharPos(i.dir); // letras da direita.
			System.out.print(i.letra + " "); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir letra.
	 * @param x letra a ser inserido.
	 * @throws Exception Se o letra existir.
	 */
	public void inserir(char x) throws Exception {
		raiz = inserir(x, raiz);
	}

	/**
	 * Metodo privado recursivo para inserir letra.
	 * @param x letra a ser inserido.
	 * @param i No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se o letra existir.
	 */
	private No inserir(char x, No i) throws Exception {
		if (i == null) {
         i = new No(x);

      } else if (x < i.letra) {
         i.esq = inserir(x, i.esq);

      } else if (x > i.letra) {
         i.dir = inserir(x, i.dir);

      } else {
         throw new Exception("Erro ao inserir!");
      }

		return i;
	}
}