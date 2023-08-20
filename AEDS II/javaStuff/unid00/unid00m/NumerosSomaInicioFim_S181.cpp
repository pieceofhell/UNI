#include <iostream>
using namespace std;

int main() {
    int n;

    cout << "Digite a quantidade de numeros a serem lidos: ";
    cin >> n;

    int sumPairs = n / 2;

    for (int i = 0; i < sumPairs; i++) {
        int firstNumber, lastNumber;
        
        cout << "Digite o " << i + 1 << "º numero: ";
        cin >> firstNumber;

        cout << "Digite o " << n - i << "º numero: ";
        cin >> lastNumber;

        int sum = firstNumber + lastNumber;
        cout << "Soma: " << sum << endl;
    }

    if (n % 2 != 0) {
        int middleNumber;
        cout << "Digite o numero central: ";
        cin >> middleNumber;
        cout << "Numero central: " << middleNumber << endl;
    }

    return 0;
}
