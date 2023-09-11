#include <stdio.h>
#include <string.h>

int main()
{
    int N = 3;
    scanf("%d", &N);

    while (N--)
    {
        char str1[51], str2[51];
        scanf(" %s %s", str1, str2);

        int len1 = strlen(str1);
        int len2 = strlen(str2);

        int i = 0, j = 0;
        char resultado[101];

        int k = 0;

        while (i < len1 || j < len2)
        {
            if (i < len1)
            {
                resultado[k] = str1[i];
                i++;
                k++;
            }
            if (j < len2)
            {
                resultado[k] = str2[j];
                j++;
                k++;
            }
        }

        resultado[k] = '\0';

        printf("%s\n", resultado);
    }

    return 0;
}