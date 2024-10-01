#include <iostream>
#include "MainGraph.cpp"  // Inclui a definição da classe MainGraph
#include "DepthFirstSearch.cpp"  // Inclui a definição da classe DepthFirstSearch

int main() {
    // Criação do objeto MainGraph
    MainGraph graph;

    // Leitura do arquivo de entrada contendo o grafo
    std::string filePath = "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-100-1.txt";  // Substitua pelo caminho correto do arquivo
    std::string filePath2 = "D:/gaming/site inovador/code/github/UNI/TGC/files/graph-test-50000-1.txt";
    graph.read(filePath2);

    // Criação do objeto DepthFirstSearch para o grafo lido
    DepthFirstSearch dfs(graph.numVertices);

    // Execução da busca em profundidade
    dfs.depthFirstSearch(graph);

    // Imprime os tempos de descoberta e término
    // dfs.printTimes();

    // Salva os resultados em um arquivo
    std::string outputFilePath = "output_results.txt";  // Nome do arquivo de saída
    dfs.saveToFile(outputFilePath);

    return 0;
}
