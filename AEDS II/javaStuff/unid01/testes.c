#include <stdio.h>

int main(int argc, char const *argv[])
{
    // int i = 10, b = 10;
    // while (i > 0)
    // {
    //     b--;
    //     i = i >> 1;
    //     printf("%d\n", b);
    // }
    // i = 0;
    // while (i < 15)
    // {
    //     b--;
    //     i += 2;
    //     printf("\n%d\n", b);
    // }

    // int n = 4, a = 1;

    // for (int i = 0; i < n; i++)
    // {
    //     for (int j = 0; j < n - 3; j++)
    //     {
    //         a *= 2;
    //         printf("%d\n", a);
    //     }
    // }

    // int n = 8, a = 1;

    // for (int i = n - 7; i >= 1; i--)
    // {
    //     for (int j = 0; j < n; j++)
    //     {
    //         a *= 2;
    //         printf("%d\n", a);
    //     }
    // }

    int n = 10, a = 1;

    for (int i = n - 7; i >= 1; i--)
    {
        for (int j = n - 7; j >= 1; j--)
        {
            a *= 2;
            printf("%d\n", a);
        }
    }
    return 0;
}