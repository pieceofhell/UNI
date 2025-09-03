/**
 *
Tarefa 01 - Padrão MAP
Autor: Henrique Oliveira
Obs: A saída deve ser 1 1, 2 2, 3 3, mas a ordem das threads pode variar.
Dica: https://bisqwit.iki.fi/story/howto/openmp/#OrderedClause
*/

#include <stdio.h>
#include <omp.h>

int main()
{
    int i;

#pragma omp parallel num_threads(2) // seta o número de threads em 2
#pragma omp for ordered             // paraleliza o for e garante a ordem de execução
    for (i = 1; i <= 3; i++)
    {
        int tid = omp_get_thread_num(); // lê o identificador da thread

#pragma omp ordered // garante a ordem de execução
        {
            printf("[PRINT1] T%d = %d \n", tid, i);
            printf("[PRINT2] T%d = %d \n", tid, i);
        }
    }
    return 0;
}