#include <iostream>
#include <stack>
using namespace std;

int main() {
    int n;

    cout << "Digite a quantidade de numeros a serem lidos: ";
    cin >> n;

    stack<int> numsArr;

    for (int i = 0; i < n; i++) {
        int num;
        cout << "Digite o numero " << i + 1 << ": ";
        cin >> num;
        numsArr.push(num);
    }

    cout << "Numeros em ordem inversa:" << endl;

    while (!numsArr.empty()) {
        cout << numsArr.top() << endl;
        numsArr.pop();
    }

    return 0;
}