#include <iostream>
#include <fstream>
#include <vector>
using namespace std;

int main() {
    int n;
    
    cout << "Digite a quantidade de numeros a serem lidos: ";
    cin >> n;

    vector<int> nums;

    for (int i = 0; i < n; i++) {
        int num;
        cout << "Digite o numero " << i + 1 << ": ";
        cin >> num;
        nums.push_back(num);
    }

    ofstream out("numeros.txt");
    
    if (!out.is_open()) {
        cerr << "Erro ao abrir o arquivo para escrita." << endl;
        return 1;
    }

    for (int num : nums) {
        out << num << endl;
    }

    out.close();

    ifstream input("numeros.txt");

    if (!input.is_open()) {
        cerr << "Erro ao abrir o arquivo para leitura." << endl;
        return 1;
    }

    int reader;
    cout << "Numeros lidos do arquivo:" << endl;

    while (input >> reader) {
        cout << reader << endl;
    }

    input.close();

    return 0;
}