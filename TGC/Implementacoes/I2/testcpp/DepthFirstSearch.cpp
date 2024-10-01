#pragma once  // Impede a inclusão múltipla do arquivo

#include <iostream>
#include <vector>
#include <fstream>
#include "MainGraph.cpp"  // Inclui a definição da classe MainGraph

class DepthFirstSearch {
public:
    std::vector<bool> visited;
    std::vector<int> discoveryTime;
    std::vector<int> finishTime;
    std::vector<int> parent;
    int time;
    std::vector<std::string> edgeClassifications; // Para armazenar as classificações de arestas

    DepthFirstSearch(int numVertices) {
        visited.resize(numVertices + 1, false);
        discoveryTime.resize(numVertices + 1, 0);
        finishTime.resize(numVertices + 1, 0);
        parent.resize(numVertices + 1, -1);
        time = 0;
    }

    void depthFirstSearch(MainGraph &graph) {
        for (int v = 1; v <= graph.numVertices; v++) {
            if (!visited[v]) {
                depthFirstSearchVisit(graph, v);
            }
        }
        printf("Fim da busca em profundidade\n");
    }

    void depthFirstSearchVisit(MainGraph &graph, int v) {
        time++;
        discoveryTime[v] = time;
        visited[v] = true;

        for (int w : graph.getAdjacents(v)) {
            if (!visited[w]) {
                parent[w] = v;
                std::string edgeInfo = "Aresta (" + std::to_string(v) + ", " + std::to_string(w) + ") classificada como TREE";
                std::cout << edgeInfo << std::endl;
                edgeClassifications.push_back(edgeInfo);
                depthFirstSearchVisit(graph, w);
            } else if (finishTime[w] == 0) {
                std::string edgeInfo = "Aresta (" + std::to_string(v) + ", " + std::to_string(w) + ") classificada como BACK";
                std::cout << edgeInfo << std::endl;
                edgeClassifications.push_back(edgeInfo);
            } else if (discoveryTime[v] < discoveryTime[w]) {
                std::string edgeInfo = "Aresta (" + std::to_string(v) + ", " + std::to_string(w) + ") classificada como FORWARD";
                std::cout << edgeInfo << std::endl;
                edgeClassifications.push_back(edgeInfo);
            } else {
                std::string edgeInfo = "Aresta (" + std::to_string(v) + ", " + std::to_string(w) + ") classificada como CROSS";
                std::cout << edgeInfo << std::endl;
                edgeClassifications.push_back(edgeInfo);
            }
        }

        time++;
        finishTime[v] = time;
    }

    void printTimes() const {
        for (size_t i = 1; i < discoveryTime.size(); i++) {
            std::cout << "Vértice: " << i << " TD: " << discoveryTime[i] << " TT: " << finishTime[i] << "\n";
        }
    }

    // Função para salvar as saídas em um arquivo
    void saveToFile(const std::string &fileName) const {
        std::ofstream outFile(fileName); // Cria ou sobrescreve o arquivo

        if (!outFile.is_open()) {
            std::cerr << "Erro ao abrir o arquivo para escrita." << std::endl;
            return;
        }

        // Salvar as classificações de arestas
        for (const auto &edgeInfo : edgeClassifications) {
            outFile << edgeInfo << std::endl;
        }

        // Salvar os tempos de descoberta e término
        outFile << "\nTempos de descoberta e término:\n";
        for (size_t i = 1; i < discoveryTime.size(); i++) {
            outFile << "Vértice: " << i << " TD: " << discoveryTime[i] << " TT: " << finishTime[i] << "\n";
        }

        outFile.close();
        std::cout << "Resultados salvos no arquivo: " << fileName << std::endl;
    }
};
