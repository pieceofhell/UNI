#pragma once  // Impede a inclusão múltipla do arquivo

#include <iostream>
#include <vector>
#include <list>
#include <fstream>
#include <sstream>

class MainGraph {
public:
    int numVertices;
    int numEdges;
    std::vector<int> firstColumn;
    std::vector<int> secondColumn;
    std::vector<std::list<int>> vertices;

    MainGraph() : numVertices(0), numEdges(0) {}

    void read(const std::string &filePath) {
        std::ifstream file(filePath);
        std::string line;

        // Leitura da primeira linha
        if (std::getline(file, line)) {
            std::istringstream iss(line);
            iss >> numVertices >> numEdges;
        }

        // Inicialização das listas de adjacência
        vertices.resize(numVertices + 1);

        // Leitura das linhas e armazenamento dos vértices
        while (std::getline(file, line)) {
            std::istringstream iss(line);
            int u, v;
            iss >> u >> v;
            firstColumn.push_back(u);
            secondColumn.push_back(v);
            vertices[u].push_back(v);  // Grafo direcionado
        }

        file.close();
    }

    const std::list<int>& getAdjacents(int v) const {
        return vertices[v];
    }
};
