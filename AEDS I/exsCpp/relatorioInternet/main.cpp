#include <iostream>

using namespace std;

int main()
{
    int x, n, *M, temp = 0, sobra;
    cin >> x;
    cin >> n;
    M = (int*) malloc (sizeof(int)*n);

    for (int i = 0; i < n; i++)
    {
        cin >> M[i];
        temp += x - M[i];
    }
    sobra = x + temp;
    cout << sobra;
    return 0;
}