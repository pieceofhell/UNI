#include <iostream>
#include <vector>
#include <fstream>
#include <climits>
#include <algorithm>
#include <stdexcept>
#include <string>
#include <chrono>
#include <cmath>
#include <numeric>
#include <set>
#include <iterator>

using namespace std;
using namespace std::chrono;

class Graph {
private:
    int n;  //  vertices
    int m;  //  edges
    int k;  //  centers
    vector<vector<pair<int, int>>> G;  // Adjacency list (vertex, weight)
    vector<vector<int>> dist;  // Distance matrix

public:
    Graph(const string& filename) {
        ifstream file(filename);
        if (!file.is_open()) {
            throw runtime_error("Error opening file: " + filename);
        }

        file >> n >> m >> k;
        
        G.resize(n + 1);
        dist.resize(n + 1, vector<int>(n + 1, INT_MAX));

        for (int i = 0; i < m; i++) {
            int v, w, c;
            file >> v >> w >> c;
            
            G[v].push_back({w, c});
            G[w].push_back({v, c});

            dist[v][w] = c;
            dist[w][v] = c;
        }

        // Initialize self-distances to 0
        for (int i = 1; i <= n; i++) {
            dist[i][i] = 0;
        }

        // Floyd-Warshall
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != INT_MAX && dist[k][j] != INT_MAX) {
                        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
    }

    int minDistanceToCenter(int vertex, const vector<int>& centers) {
        int min_dist = INT_MAX;
        for (int center : centers) {
            min_dist = min(min_dist, dist[vertex][center]);
        }
        return min_dist;
    }

    // Greedy k-center selection (existing method)
    // https://www.geeksforgeeks.org/greedy-approximate-algorithm-for-k-centers-problem/ e 
    //CMSC 451: Lecture 8
    //Greedy Approximation Algorithms: The k-Center Problem - Dave Mount
    // foram consultados para implementação
    pair<int, vector<int>> kCitiesGreedy() {
        vector<int> centers;
        vector<int> min_distances(n + 1, INT_MAX);
        
        
        int first_center = 1;
        int max_min_dist = 0;
        for (int v = 1; v <= n; v++) {
            int min_dist = INT_MAX;
            for (int u = 1; u <= n; u++) {
                min_dist = min(min_dist, dist[v][u]);
            }
            if (min_dist > max_min_dist) {
                max_min_dist = min_dist;
                first_center = v;
            }
        }

        centers.push_back(first_center);

        
        while (centers.size() < k) {
            int max_min_distance_vertex = 0;
            int max_min_distance = 0;

            for (int v = 1; v <= n; v++) {
                
                if (find(centers.begin(), centers.end(), v) != centers.end()) continue;

                int min_dist = minDistanceToCenter(v, centers);
                if (min_dist > max_min_distance) {
                    max_min_distance = min_dist;
                    max_min_distance_vertex = v;
                }
            }

            centers.push_back(max_min_distance_vertex);
        }

        
        int max_radius = 0;
        for (int v = 1; v <= n; v++) {
            int min_dist = minDistanceToCenter(v, centers);
            max_radius = max(max_radius, min_dist);
        }

        return {max_radius, centers};
    }

    pair<int, vector<int>> kCitiesExact() {
        vector<int> all_vertices(n);
        iota(all_vertices.begin(), all_vertices.end(), 1);
        
        vector<vector<int>> center_combinations;
        
        
        vector<bool> combination_mask(n, false);
        fill(combination_mask.begin(), combination_mask.begin() + k, true);

        int min_max_radius = INT_MAX;
        vector<int> best_centers;

        do {
            vector<int> current_centers;
            for (int i = 0; i < n; i++) {
                if (combination_mask[i]) {
                    current_centers.push_back(all_vertices[i]);
                }
            }

            int max_radius = 0;
            for (int v = 1; v <= n; v++) {
                int min_dist = minDistanceToCenter(v, current_centers);
                max_radius = max(max_radius, min_dist);
            }

            
            if (max_radius < min_max_radius) {
                min_max_radius = max_radius;
                best_centers = current_centers;
            }
        } while (prev_permutation(combination_mask.begin(), combination_mask.end()));

        return {min_max_radius, best_centers};
    }

    void printGraphDetails() {
        cout << "Vertices: " << n << ", Edges: " << m << ", Centers to select: " << k << endl;
    }
};

using namespace std;
using namespace std::chrono;

int main() {
    try {
        string base_filename = "arquivos_pmed/pmed";
        ofstream results_file("results.txt");

        if (!results_file.is_open()) {
            throw runtime_error("Não foi possível abrir o arquivo de saída para salvar os resultados.");
        }

        results_file << "Arquivo,Tempo_Greedy(microsegundos),Raio_Maximo_Greedy,Centros_Greedy,Tempo_Exato(microsegundos),Raio_Maximo_Exato,Centros_Exato\n";

        // Laço para o algoritmo Greedy
        for (int i = 1; i <= 40; i++) {
            string filename = base_filename + to_string(i) + ".txt";
            cout << "Processando arquivo (Greedy): " << filename << endl;

            Graph graph(filename);

            auto start_greedy = high_resolution_clock::now();
            auto [radius_greedy, centers_greedy] = graph.kCitiesGreedy();
            auto end_greedy = high_resolution_clock::now();
            auto duration_greedy = duration_cast<microseconds>(end_greedy - start_greedy);

            results_file << filename << ","
                         << duration_greedy.count() << ","
                         << radius_greedy << ",\"";

            for (size_t j = 0; j < centers_greedy.size(); j++) {
                results_file << centers_greedy[j];
                if (j < centers_greedy.size() - 1) results_file << " ";
            }

            results_file <<  "\",,,\n";
        }

        results_file << "-1,\"-\"\n";

        for (int i = 1; i <= 40; i++) {
            string filename = base_filename + to_string(i) + ".txt";
            cout << "Processando arquivo (Exato): " << filename << endl;

            Graph graph(filename);

            
            auto start_exact = high_resolution_clock::now();
            auto [radius_exact, centers_exact] = graph.kCitiesExact();
            auto end_exact = high_resolution_clock::now();
            auto duration_exact = duration_cast<microseconds>(end_exact - start_exact);

            results_file.seekp(0, ios::end);
            results_file << duration_exact.count() << ","
                         << radius_exact << ",\"";

            for (size_t j = 0; j < centers_exact.size(); j++) {
                results_file << centers_exact[j];
                if (j < centers_exact.size() - 1) results_file << " ";
            }

            results_file << "\"\n";
        }

        results_file.close();
        cout << "Resultados salvos no arquivo results.txt" << endl;

    } catch (const exception& e) {
        cerr << "Erro: " << e.what() << endl;
        return 1;
    }

    return 0;
}
