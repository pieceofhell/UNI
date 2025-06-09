import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa um item com peso, valor e ID original.
 */
class Item {
    int id;
    int weight;
    int value;
    double ratio; // Valor por unidade de peso

    public Item(int id, int weight, int value) {
        this.id = id;
        this.weight = weight;
        this.value = value;
        this.ratio = (double) value / weight;
    }

    @Override
    public String toString() {
        return "Item " + id + " (P:" + weight + ", V:" + value + ", R:" + String.format("%.2f", ratio) + ")";
    }
}

/**
 * Representa um nó na árvore de espaço de estados.
 */
class Node {
    int level;  // Nível do nó (índice do próximo item a ser considerado)
    int profit; // Lucro acumulado
    int weight; // Peso acumulado
    double bound;  // Limite superior (upper bound)
    List<Integer> path; // Caminho (IDs dos itens incluídos)

    public Node(int level, int profit, int weight, List<Integer> path) {
        this.level = level;
        this.profit = profit;
        this.weight = weight;
        this.path = new ArrayList<>(path);
    }

    @Override
    public String toString() {
        return "Nó[Nível=" + level + ", L=" + profit + ", P=" + weight + ", Lim=" + String.format("%.2f", bound) + ", Caminho=" + path + "]";
    }
}

/**
 * Compara os nós com base em seu limite superior (maior primeiro).
 */
class NodeComparator implements Comparator<Node> {
    public int compare(Node a, Node b) {
        return Double.compare(b.bound, a.bound);
    }
}

/**
 * Implementa a solução para o Problema da Mochila 0/1 usando Branch and Bound com logging.
 */
public class KnapsackBranchAndBoundDetailed {

    /**
     * Calcula o limite superior para um nó.
     */
    static double bound(Node u, int n, int W, Item arr[]) {
        if (u.weight >= W) {
            return 0;
        }

        double profitBound = u.profit;
        int j = u.level; // Começa a partir do nível atual (próximo item)
        int totWeight = u.weight;

        while (j < n && totWeight + arr[j].weight <= W) {
            totWeight += arr[j].weight;
            profitBound += arr[j].value;
            j++;
        }

        if (j < n) {
            profitBound += (W - totWeight) * arr[j].ratio;
        }

        return profitBound;
    }

    /**
     * Retorna uma string de indentação baseada no nível.
     */
    private static String getIndent(int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    /**
     * Resolve o problema da mochila 0/1 com logging detalhado.
     */
    public static int knapsack(int W, Item arr[], int n) {
        // Ordena os itens pela razão valor/peso em ordem decrescente.
        Arrays.sort(arr, (a, b) -> Double.compare(b.ratio, a.ratio));

        System.out.println("--- INÍCIO DO BRANCH AND BOUND ---");
        System.out.println("Capacidade da Mochila (W): " + W);
        System.out.println("Itens Ordenados por Razão V/P:");
        for (Item item : arr) {
            System.out.println("  " + item);
        }
        System.out.println("------------------------------------");

        PriorityQueue<Node> Q = new PriorityQueue<>(new NodeComparator());

        // Nó raiz: Nível 0, sem lucro, sem peso, caminho vazio.
        Node u = new Node(0, 0, 0, new ArrayList<>());
        u.bound = bound(u, n, W, arr); // Calcula o limite inicial
        Q.add(u);

        System.out.println("Nó Raiz Adicionado: " + u);
        System.out.println("------------------------------------");

        int maxProfit = 0;
        List<Integer> bestPath = new ArrayList<>();
        int nodesExplored = 0;

        while (!Q.isEmpty()) {
            // Remove o nó mais promissor
            u = Q.poll();
            nodesExplored++;
            String indent = getIndent(u.level);

            System.out.println("\n" + indent + "[" + nodesExplored + "] Explorando: " + u + " | MaxLucroAtual = " + maxProfit);

            // Se o limite do nó atual não é melhor que a melhor solução, poda [cite: 173]
            if (u.bound <= maxProfit) {
                System.out.println(indent + "  >> PODA (Limite <= MaxLucro). Nó descartado.");
                continue;
            }

            // Se chegamos ao fim (consideramos todos os itens), para.
            if (u.level == n) {
                 System.out.println(indent + "  >> Nó Folha. Fim deste ramo.");
                continue;
            }

            Item currentItem = arr[u.level];
            System.out.println(indent + "  Considerando " + currentItem);

            // --- Ramo 1: Incluir o item atual ---
            List<Integer> pathWith = new ArrayList<>(u.path);
            pathWith.add(currentItem.id);
            Node v = new Node(u.level + 1, u.profit + currentItem.value, u.weight + currentItem.weight, pathWith);
            System.out.print(indent + "  -> Incluir? ");

            if (v.weight <= W) {
                System.out.print("Sim (Peso " + v.weight + " <= " + W + "). ");
                // Se o lucro atual é melhor, atualiza
                if (v.profit > maxProfit) {
                    maxProfit = v.profit;
                    bestPath = v.path;
                    System.out.println("\n" + indent + "     *** NOVA MELHOR SOLUÇÃO ENCONTRADA: " + maxProfit + " com " + bestPath + " ***");
                }
                v.bound = bound(v, n, W, arr);
                v.path = pathWith; // Garante que o path está correto
                // Se o limite é promissor, adiciona à fila [cite: 174]
                if (v.bound > maxProfit) {
                    Q.add(v);
                    System.out.println(indent + "     Nó (Incluir) Adicionado: " + v);
                } else {
                    System.out.println(indent + "     >> PODA (Incluir - Limite " + String.format("%.2f", v.bound) + " <= MaxLucro " + maxProfit + ")");
                }
            } else {
                System.out.println(indent + "     >> PODA (Incluir - Peso " + v.weight + " > " + W + ")");
            }

            // --- Ramo 2: Não incluir o item atual ---
            Node w = new Node(u.level + 1, u.profit, u.weight, u.path);
            w.bound = bound(w, n, W, arr);
            System.out.print(indent + "  -> Excluir? ");

            // Se o limite é promissor, adiciona à fila
            if (w.bound > maxProfit) {
                Q.add(w);
                System.out.println("Sim. Nó (Excluir) Adicionado: " + w);
            } else {
                System.out.println(">> PODA (Excluir - Limite " + String.format("%.2f", w.bound) + " <= MaxLucro " + maxProfit + ")");
            }
             System.out.println(indent + "------------------------------------");
        }

        System.out.println("\n--- FIM DO BRANCH AND BOUND ---");
        System.out.println("Nós explorados: " + nodesExplored);
        System.out.println("Melhor caminho encontrado: " + bestPath);
        return maxProfit;
    }

    /**
     * Método principal para testar o algoritmo.
     */
    public static void main(String args[]) {
        int W = 10; // Capacidade da mochila
        Item[] arr = { // Itens (ID, peso, valor)
                new Item(1, 4, 40),
                new Item(2, 7, 42),
                new Item(3, 5, 25),
                new Item(4, 3, 12)
        };
        int n = arr.length;

        int maxProfit = knapsack(W, arr, n);
        System.out.println("===================================");
        System.out.println("Valor máximo na mochila = " + maxProfit);
        System.out.println("===================================");
    }
}