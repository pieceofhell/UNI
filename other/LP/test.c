#include <stdio.h>
int main(int argc, char const *argv[])
{
    int a[24];
    a[0] = 25;
    for (int i = 0; i < 100; i++)
    {
        printf("%d\n", a[i]);
    }
    
    return 0;
}
