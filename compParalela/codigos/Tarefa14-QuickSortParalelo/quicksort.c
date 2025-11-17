/*
Tempos de execução:

Sequencial

real    0m4.040s
user    0m2.838s
sys     0m1.150s

Paralela (2 threads)

real    0m1.279s
user    0m2.280s
sys     0m0.024s

- Speedup de aprox. 3.16x

Verificação:
$ diff seq_output.txt par_output.txt
0a1
> Tempo de execução: 1.095 segundos
(vem do printf no main da versão paralela)
*/

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

// Função auxiliar para trocar dois elementos
void swap(int* a, int* b) {
    int t = *a;
    *a = *b;
    *b = t;
}

// Função de partição
int partition(int arr[], int low, int high) {
    int pivot = arr[high];    // pivô
    int i = (low - 1);        // índice do menor elemento

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] <= pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

// Implementação paralela do QuickSort
void quickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);

        // Versão paralela usando tasks
        #pragma omp task shared(arr) if (high - low > 10000)
        quickSort(arr, low, pi - 1);

        #pragma omp task shared(arr) if (high - low > 10000)
        quickSort(arr, pi + 1, high);
    }
}

// Função para imprimir o array
void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

int main() {
    int n = 10000000;
    int *arr = (int*) malloc(n * sizeof(int));

    for (int i = 0; i < n; i++)
        arr[i] = rand() % n;

    double start = omp_get_wtime();

    // Região paralela com 2 threads
    #pragma omp parallel num_threads(2)
    {
        #pragma omp single nowait
        {
            quickSort(arr, 0, n - 1);
        }
    }

    double end = omp_get_wtime();

    // printf("Tempo de execução: %.3f segundos\n", end - start);

    // Descomente para verificar a correção c/ diff
    // printf("Sorted array: \n");
    // printArray(arr, n);

    free(arr);
    return 0;
}
