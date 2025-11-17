#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

/*
  devec.c

  resultado da compilação:

 devec.c:62:23: missed: couldn't vectorize loop
devec.c:62:23: missed: not vectorized: unsupported control flow in loop.
devec.c:44:27: missed: couldn't vectorize loop
devec.c:44:27: missed: not vectorized: number of iterations cannot be computed.
/usr/include/stdlib.h:483:16: missed: statement clobbers memory: 63 = strtol (_1, 0B, 10);
devec.c:28:27: missed: statement clobbers memory: a 46 = malloc (_3);
devec.c:29:27: missed: statement clobbers memory: b_48 = malloc (_3);
devec.c:69:17: missed: not vectorized: volatile type: 25 ={v} g_flag;
devec.c:88:5: missed: statement clobbers memory: printf ("sum=%.3f\n", s_50);
devec.c:90:5: missed: statement clobbers memory: free (a_46);
devec.c:91:5: missed: statement clobbers memory: free (b_48);
*/

volatile int g_flag = 0; /* leitura volátil dentro do laço -> desvetorização */

int main(int argc, char **argv)
{
    /* trip count conhecido quando entramos no laço: n */
    int n = 1000000;
    if (argc > 1)
    {
        int t = atoi(argv[1]); /* leitura fora do laço */
        if (t > 0)
            n = t;
    }

    /* alocações e inicializações feitas antes do laço (sem loops) */
    double *a = (double *)malloc(sizeof(double) * (size_t)n);
    double *b = (double *)malloc(sizeof(double) * (size_t)n);
    if (!a || !b)
        return 1;

    /* inicialização por chamada de biblioteca (fora do laço único) */
    for (int i = 0; i < 1; ++i)
    {
    }

    a[0] = 1.0;
    for (int i = 1; i < 8 && i < n; ++i)
    { /* inicialização curta e segura antes do laço principal */
        a[i] = (double)i;
        b[i] = (double)(i % 3) - 1.0;
    }

    double *p_ambig = a;
    volatile int *vptr = &g_flag;

    for (int i = 1; i < n; ++i)
    {

        double prev = a[i - 1];

        if (b[i] > 0.0)
        {

            if (*vptr)
            {

                a[i] = prev + b[i] + 1.0;
            }
            else
            {
                a[i] = prev + b[i];
            }
        }
        else
        {

            double tmp = *(p_ambig + i);
            a[i] = prev - tmp;
        }
    }

    double s = 0.0;
    for (int i = 0; i < n; i += (n > 0 ? n : 1))
    {
        s += a[i];
        break;
    }

    printf("sum=%.3f\n", s);

    free(a);
    free(b);
    return 0;
}
