/*
Tarefa 23 - Paralelização para GPU da Multiplicação de Matrizes

Autor: Henrique Oliveira

Compilação e execução:
nvcc -O3 mm.cu -o mm_cuda -Xcompiler -fopenmp

Para rodar (exemplos):
./mm_cuda seq        # só sequencial
./mm_cuda omp        # só OpenMP
./mm_cuda cuda       # CUDA simples
./mm_cuda cuda_tiled # CUDA com shared memory (tiled)
./mm_cuda all        # roda todas as versões em sequência (útil para comparar)

Saída obtida (N = 2000):

Matrix multiplication test. N = 2000. Mode = all
SEQ time (O3): 73707.746 ms
OpenMP time (O3 -fopenmp): 16391.885 ms
OpenMP vs SEQ: max_abs = 0.000000e+00, relative_error = 0.000000e+00
CUDA simple: HtoD = 13.544 ms, kernel = 354.780 ms, DtoH = 4.495 ms, total GPU = 372.819 ms
CUDA simple vs SEQ: max_abs = 0.000000e+00, relative_error = 0.000000e+00
CUDA tiled (BLOCK=16): HtoD = 10.422 ms, kernel = 81.881 ms, DtoH = 4.718 ms, total GPU = 97.021 ms
CUDA tiled vs SEQ: max_abs = 0.000000e+00, relative_error = 0.000000e+00

Resumo dos tempos:

* Versão sequencial (CPU):              73707.746 ms
* Versão paralela multicore (OpenMP):   16391.885 ms
* Versão GPU (CUDA simples):              372.819 ms
* Versão GPU otimizada (**shared**):       97.021 ms

Tempo detalhado da versão CUDA (nsys):
HtoD = 17.8 ms, kernel = 103.5 ms, DtoH = 8.0 ms

Análise de desempenho (Nsight Systems):
O perfil do Nsight mostrou que a execução é dominada por:
- cudaEventCreate / cudaEventSynchronize (~80% do tempo total): espera da CPU pela GPU.
- cudaMemcpy (~17%): transferências HtoD e DtoH.
- cudaLaunchKernel (~0.1%): lançamento do kernel, custo muito pequeno.
Isso confirma que o tempo de processamento está concentrado no kernel e que a principal sobrecarga vem da cópia de dados entre host e device.

- Perfil de GPU obtido com:
nsys profile -o report1 ./mm_cuda cuda_tiled 2000
e análise com:
nsys stats report1.nsys-rep (gerou as seguintes tabelas):

** OS Runtime Summary (osrt_sum):

 Time (%)  Total Time (ns)  Num Calls    Avg (ns)      Med (ns)    Min (ns)   Max (ns)    StdDev (ns)        Name
 --------  ---------------  ---------  ------------  ------------  --------  -----------  ------------  --------------
     71.2      501,877,437          9  55,764,159.7  55,352,071.0   808,299  100,206,548  44,896,871.4  poll
     24.6      173,165,708        659     262,770.4      12,400.0     1,200   20,121,489   1,280,977.7  ioctl
      2.8       19,418,491          7   2,774,070.1     428,400.0     3,400   15,701,392   5,721,689.7  fread
      1.0        7,023,700         14     501,692.9     377,050.0   159,700    1,551,900     360,746.0  pthread_create
      0.2        1,523,198         27      56,414.7       3,500.0     1,800      609,699     142,155.0  fopen
      0.0          343,500         22      15,613.6      17,050.0     3,200       29,400       7,356.9  mmap
      0.0          323,400         10      32,340.0       2,700.0     1,200      223,100      69,019.3  fclose
      0.0          283,400          1     283,400.0     283,400.0   283,400      283,400           0.0  pthread_join
      0.0          201,800          2     100,900.0     100,900.0    99,000      102,800       2,687.0  sem_timedwait
      0.0          127,100          4      31,775.0      31,800.0    30,100       33,400       1,408.0  write
      0.0          108,899          3      36,299.7      39,599.0     9,400       59,900      25,411.2  pipe2
      0.0           86,100          6      14,350.0       2,500.0     1,100       47,300      19,959.3  fcntl
      0.0           40,400          1      40,400.0      40,400.0    40,400       40,400           0.0  fgets
      0.0           16,100          3       5,366.7       7,000.0     1,900        7,200       3,003.9  open
      0.0            7,700          4       1,925.0       2,050.0     1,500        2,100         287.2  read
      0.0            5,500          2       2,750.0       2,750.0     2,600        2,900         212.1  fwrite
      0.0            1,600          1       1,600.0       1,600.0     1,600        1,600           0.0  close

Processing [report1.sqlite] with [/opt/nvidia/nsight-systems/2025.3.2/host-linux-x64/reports/cuda_api_sum.py]...

 ** CUDA API Summary (cuda_api_sum):

 Time (%)  Total Time (ns)  Num Calls    Avg (ns)      Med (ns)    Min (ns)    Max (ns)     StdDev (ns)            Name 
 --------  ---------------  ---------  ------------  ------------  ---------  -----------  -------------  ----------------------
     45.5      220,037,386          4  55,009,346.5       5,950.0        600  220,024,886  110,010,359.8  cudaEventCreate
     34.8      168,474,512          6  28,079,085.3     473,299.5     19,200   85,425,856   43,126,083.2  cudaEventSynchronize
     17.0       82,492,557          6  13,748,759.5  13,673,043.0  3,950,098   24,206,687    9,814,742.2  cudaMemcpy    
      1.0        4,632,398          1   4,632,398.0   4,632,398.0  4,632,398    4,632,398            0.0  cuLibraryLoadData
      0.9        4,399,798          6     733,299.7     742,099.5    609,600      833,199       76,090.8  cudaMalloc    
      0.4        2,137,199          6     356,199.8     357,850.0    208,499      547,900      115,793.7  cudaFree      
      0.3        1,388,199         12     115,683.3      25,200.0      6,300      668,700      201,423.6  cudaEventRecord
      0.1          417,899          2     208,949.5     208,949.5     73,500      344,399      191,554.5  cudaLaunchKernel
      0.0            6,900          4       1,725.0       1,650.0        400        3,200        1,481.8  cudaEventDestroy
      0.0            1,900          2         950.0         950.0        500        1,400          636.4  cuLibraryGetKernel
      0.0              900          2         450.0         450.0        400          500           70.7  cuKernelGetName
      0.0              800          1         800.0         800.0        800          800            0.0  cuModuleGetLoadingMode


Conclusões:

* A versão OpenMP foi ~4,5x mais rápida que a sequencial.
* A versão CUDA simples foi ~44x mais rápida que a OpenMP.
* A versão CUDA otimizada com memória compartilhada (**shared**) foi ~170x mais rápida que a OpenMP e ~760x mais rápida que a versão sequencial.
* O uso de memória compartilhada reduziu significativamente o tempo do kernel (de 354 ms para 82 ms), mostrando o impacto da redução de acessos à memória global.


*/


#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <chrono>
#include <cmath>

#ifdef _OPENMP
#include <omp.h>
#endif

#include <cuda_runtime.h>

// Utility: check CUDA error
#define CUDA_CHECK(call)                                          \
    do                                                            \
    {                                                             \
        cudaError_t err = (call);                                 \
        if (err != cudaSuccess)                                   \
        {                                                         \
            fprintf(stderr, "CUDA error %s:%d: %s\n",             \
                    __FILE__, __LINE__, cudaGetErrorString(err)); \
            exit(EXIT_FAILURE);                                   \
        }                                                         \
    } while (0)

// Sequential matrix multiplication (row-major)
void mm_seq(const double *a, const double *b, double *c, int width)
{
    for (int i = 0; i < width; ++i)
    {
        for (int j = 0; j < width; ++j)
        {
            double sum = 0.0;
            int base_a = i * width;
            for (int k = 0; k < width; ++k)
            {
                double x = a[base_a + k];
                double y = b[k * width + j];
                sum += x * y;
            }
            c[base_a + j] = sum;
        }
    }
}

// OpenMP version: parallelize over i and j (collapse)
void mm_omp(const double *a, const double *b, double *c, int width)
{
#ifdef _OPENMP
#pragma omp parallel for collapse(2) schedule(static)
    for (int i = 0; i < width; ++i)
    {
        for (int j = 0; j < width; ++j)
        {
            double sum = 0.0;
            int base_a = i * width;
            for (int k = 0; k < width; ++k)
            {
                double x = a[base_a + k];
                double y = b[k * width + j];
                sum += x * y;
            }
            c[base_a + j] = sum;
        }
    }
#else
    // Fallback to sequential if OpenMP not present
    mm_seq(a, b, c, width);
#endif
}

// Simple CUDA kernel (no shared memory)
__global__ void mm_kernel_simple(const double *A, const double *B, double *C, int N)
{
    int row = blockIdx.y * blockDim.y + threadIdx.y;
    int col = blockIdx.x * blockDim.x + threadIdx.x;
    if (row < N && col < N)
    {
        double sum = 0.0;
        int rowBase = row * N;
        for (int k = 0; k < N; ++k)
        {
            double a = A[rowBase + k];
            double b = B[k * N + col];
            sum += a * b;
        }
        C[rowBase + col] = sum;
    }
}

// Tiled kernel using shared memory (BLOCK_DIM x BLOCK_DIM tiles)
template <int BLOCK_DIM>
__global__ void mm_kernel_tiled(const double *A, const double *B, double *C, int N)
{
    __shared__ double sA[BLOCK_DIM][BLOCK_DIM];
    __shared__ double sB[BLOCK_DIM][BLOCK_DIM];

    int row = blockIdx.y * BLOCK_DIM + threadIdx.y;
    int col = blockIdx.x * BLOCK_DIM + threadIdx.x;

    double sum = 0.0;

    for (int t = 0; t < (N + BLOCK_DIM - 1) / BLOCK_DIM; ++t)
    {
        int aRow = row;
        int aCol = t * BLOCK_DIM + threadIdx.x;
        int bRow = t * BLOCK_DIM + threadIdx.y;
        int bCol = col;

        if (aRow < N && aCol < N)
            sA[threadIdx.y][threadIdx.x] = A[aRow * N + aCol];
        else
            sA[threadIdx.y][threadIdx.x] = 0.0;

        if (bRow < N && bCol < N)
            sB[threadIdx.y][threadIdx.x] = B[bRow * N + bCol];
        else
            sB[threadIdx.y][threadIdx.x] = 0.0;

        __syncthreads();

        for (int k = 0; k < BLOCK_DIM; ++k)
        {
            sum += sA[threadIdx.y][k] * sB[k][threadIdx.x];
        }
        __syncthreads();
    }

    if (row < N && col < N)
        C[row * N + col] = sum;
}

// Host wrapper for CUDA simple
void run_cuda_simple(const double *h_A, const double *h_B, double *h_C, int N,
                     float &time_htod_ms, float &time_kernel_ms, float &time_dtoh_ms)
{
    size_t bytes = sizeof(double) * (size_t)N * (size_t)N;
    double *d_A = nullptr, *d_B = nullptr, *d_C = nullptr;

    cudaEvent_t start, stop;
    CUDA_CHECK(cudaEventCreate(&start));
    CUDA_CHECK(cudaEventCreate(&stop));

    // HtoD
    CUDA_CHECK(cudaEventRecord(start));
    CUDA_CHECK(cudaMalloc(&d_A, bytes));
    CUDA_CHECK(cudaMalloc(&d_B, bytes));
    CUDA_CHECK(cudaMalloc(&d_C, bytes));
    CUDA_CHECK(cudaMemcpy(d_A, h_A, bytes, cudaMemcpyHostToDevice));
    CUDA_CHECK(cudaMemcpy(d_B, h_B, bytes, cudaMemcpyHostToDevice));
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_htod_ms, start, stop));

    // kernel
    dim3 block(16, 16);
    dim3 grid((N + block.x - 1) / block.x, (N + block.y - 1) / block.y);

    CUDA_CHECK(cudaEventRecord(start));
    mm_kernel_simple<<<grid, block>>>(d_A, d_B, d_C, N);
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_kernel_ms, start, stop));

    // DtoH
    CUDA_CHECK(cudaEventRecord(start));
    CUDA_CHECK(cudaMemcpy(h_C, d_C, bytes, cudaMemcpyDeviceToHost));
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_dtoh_ms, start, stop));

    // cleanup
    CUDA_CHECK(cudaFree(d_A));
    CUDA_CHECK(cudaFree(d_B));
    CUDA_CHECK(cudaFree(d_C));
    CUDA_CHECK(cudaEventDestroy(start));
    CUDA_CHECK(cudaEventDestroy(stop));
}

// Host wrapper for CUDA tiled (choose BLOCK_DIM at compile/run)
template <int BLOCK_DIM>
void run_cuda_tiled(const double *h_A, const double *h_B, double *h_C, int N,
                    float &time_htod_ms, float &time_kernel_ms, float &time_dtoh_ms)
{
    size_t bytes = sizeof(double) * (size_t)N * (size_t)N;
    double *d_A = nullptr, *d_B = nullptr, *d_C = nullptr;

    cudaEvent_t start, stop;
    CUDA_CHECK(cudaEventCreate(&start));
    CUDA_CHECK(cudaEventCreate(&stop));

    // HtoD
    CUDA_CHECK(cudaEventRecord(start));
    CUDA_CHECK(cudaMalloc(&d_A, bytes));
    CUDA_CHECK(cudaMalloc(&d_B, bytes));
    CUDA_CHECK(cudaMalloc(&d_C, bytes));
    CUDA_CHECK(cudaMemcpy(d_A, h_A, bytes, cudaMemcpyHostToDevice));
    CUDA_CHECK(cudaMemcpy(d_B, h_B, bytes, cudaMemcpyHostToDevice));
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_htod_ms, start, stop));

    // kernel
    dim3 block(BLOCK_DIM, BLOCK_DIM);
    dim3 grid((N + BLOCK_DIM - 1) / BLOCK_DIM, (N + BLOCK_DIM - 1) / BLOCK_DIM);

    CUDA_CHECK(cudaEventRecord(start));
    mm_kernel_tiled<BLOCK_DIM><<<grid, block>>>(d_A, d_B, d_C, N);
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_kernel_ms, start, stop));

    // DtoH
    CUDA_CHECK(cudaEventRecord(start));
    CUDA_CHECK(cudaMemcpy(h_C, d_C, bytes, cudaMemcpyDeviceToHost));
    CUDA_CHECK(cudaEventRecord(stop));
    CUDA_CHECK(cudaEventSynchronize(stop));
    CUDA_CHECK(cudaEventElapsedTime(&time_dtoh_ms, start, stop));

    // cleanup
    CUDA_CHECK(cudaFree(d_A));
    CUDA_CHECK(cudaFree(d_B));
    CUDA_CHECK(cudaFree(d_C));
    CUDA_CHECK(cudaEventDestroy(start));
    CUDA_CHECK(cudaEventDestroy(stop));
}

// Simple checksum difference (max absolute diff and relative)
void compute_error(const double *ref, const double *other, int N, double &max_abs, double &rel_error)
{
    max_abs = 0.0;
    double norm_ref = 0.0;
    for (size_t i = 0; i < (size_t)N * (size_t)N; ++i)
    {
        double diff = fabs(ref[i] - other[i]);
        if (diff > max_abs)
            max_abs = diff;
        norm_ref += fabs(ref[i]);
    }
    if (norm_ref == 0.0)
        rel_error = max_abs;
    else
        rel_error = max_abs / (norm_ref / ((double)N * (double)N));
}

int main(int argc, char *argv[])
{
    int width = 2000; // default
    if (argc >= 2)
    {
        // allow 'seq', 'omp', 'cuda', 'cuda_tiled', 'all'
    }
    if (argc >= 3)
    {
        width = atoi(argv[2]);
        if (width <= 0)
            width = 2000;
    }

    const char *mode = "all";
    if (argc >= 2)
        mode = argv[1];

    printf("Matrix multiplication test. N = %d. Mode = %s\n", width, mode);

    size_t bytes = sizeof(double) * (size_t)width * (size_t)width;
    double *A = (double *)malloc(bytes);
    double *B = (double *)malloc(bytes);
    double *C_seq = (double *)malloc(bytes);
    double *C_omp = (double *)malloc(bytes);
    double *C_cuda = (double *)malloc(bytes);
    double *C_cuda_tiled = (double *)malloc(bytes);

    if (!A || !B || !C_seq || !C_omp || !C_cuda || !C_cuda_tiled)
    {
        fprintf(stderr, "Allocation failed (N=%d)\n", width);
        return 1;
    }

    // init
    for (int i = 0; i < width; ++i)
    {
        for (int j = 0; j < width; ++j)
        {
            A[i * width + j] = (double)i;
            B[i * width + j] = (double)j;
            C_seq[i * width + j] = 0.0;
            C_omp[i * width + j] = 0.0;
            C_cuda[i * width + j] = 0.0;
            C_cuda_tiled[i * width + j] = 0.0;
        }
    }

    // 1) Sequential
    if (strcmp(mode, "seq") == 0 || strcmp(mode, "all") == 0)
    {
        auto t0 = std::chrono::high_resolution_clock::now();
        mm_seq(A, B, C_seq, width);
        auto t1 = std::chrono::high_resolution_clock::now();
        double elapsed_ms = std::chrono::duration<double, std::milli>(t1 - t0).count();
        printf("SEQ time (O3): %.3f ms\n", elapsed_ms);
        if (strcmp(mode, "seq") == 0)
        {
            free(A);
            free(B);
            free(C_seq);
            free(C_omp);
            free(C_cuda);
            free(C_cuda_tiled);
            return 0;
        }
    }

    // 2) OpenMP
    if (strcmp(mode, "omp") == 0 || strcmp(mode, "all") == 0)
    {
        auto t0 = std::chrono::high_resolution_clock::now();
        mm_omp(A, B, C_omp, width);
        auto t1 = std::chrono::high_resolution_clock::now();
        double elapsed_ms = std::chrono::duration<double, std::milli>(t1 - t0).count();
        printf("OpenMP time (O3 -fopenmp): %.3f ms\n", elapsed_ms);

        // compare to seq if seq available
        double max_abs = 0.0, rel_err = 0.0;
        compute_error(C_seq, C_omp, width, max_abs, rel_err);
        printf("OpenMP vs SEQ: max_abs = %.6e, relative_error = %.6e\n", max_abs, rel_err);

        if (strcmp(mode, "omp") == 0)
        {
            free(A);
            free(B);
            free(C_seq);
            free(C_omp);
            free(C_cuda);
            free(C_cuda_tiled);
            return 0;
        }
    }

    // 3) CUDA simple
    if (strcmp(mode, "cuda") == 0 || strcmp(mode, "all") == 0)
    {
        float htod = 0.0f, ktime = 0.0f, dtoh = 0.0f;
        run_cuda_simple(A, B, C_cuda, width, htod, ktime, dtoh);
        printf("CUDA simple: HtoD = %.3f ms, kernel = %.3f ms, DtoH = %.3f ms, total GPU = %.3f ms\n",
               htod, ktime, dtoh, htod + ktime + dtoh);

        double max_abs = 0.0, rel_err = 0.0;
        compute_error(C_seq, C_cuda, width, max_abs, rel_err);
        printf("CUDA simple vs SEQ: max_abs = %.6e, relative_error = %.6e\n", max_abs, rel_err);

        if (strcmp(mode, "cuda") == 0)
        {
            free(A);
            free(B);
            free(C_seq);
            free(C_omp);
            free(C_cuda);
            free(C_cuda_tiled);
            return 0;
        }
    }

    // 4) CUDA tiled (shared memory)
    if (strcmp(mode, "cuda_tiled") == 0 || strcmp(mode, "all") == 0)
    {
        // Choose block size: try 16 (good balance for double). You can experiment with 8/16/32.
        const int BLOCK = 16;
        float htod = 0.0f, ktime = 0.0f, dtoh = 0.0f;

        if (BLOCK == 8)
        {
            run_cuda_tiled<8>(A, B, C_cuda_tiled, width, htod, ktime, dtoh);
        }
        else if (BLOCK == 16)
        {
            run_cuda_tiled<16>(A, B, C_cuda_tiled, width, htod, ktime, dtoh);
        }
        else if (BLOCK == 32)
        {
            run_cuda_tiled<32>(A, B, C_cuda_tiled, width, htod, ktime, dtoh);
        }
        else
        {
            // default fallback
            run_cuda_tiled<16>(A, B, C_cuda_tiled, width, htod, ktime, dtoh);
        }

        printf("CUDA tiled (BLOCK=%d): HtoD = %.3f ms, kernel = %.3f ms, DtoH = %.3f ms, total GPU = %.3f ms\n",
               BLOCK, htod, ktime, dtoh, htod + ktime + dtoh);

        double max_abs = 0.0, rel_err = 0.0;
        compute_error(C_seq, C_cuda_tiled, width, max_abs, rel_err);
        printf("CUDA tiled vs SEQ: max_abs = %.6e, relative_error = %.6e\n", max_abs, rel_err);

        // Suggest running nvprof externally to capture warps_launched and warp_execution_efficiency:
        printf("\nRun nvprof locally to capture GPU metrics, e.g.:\n"
               "  nvprof --events warps_launched --metrics warp_execution_efficiency ./mm_cuda cuda_tiled %d\n\n",
               width);

        if (strcmp(mode, "cuda_tiled") == 0)
        {
            free(A);
            free(B);
            free(C_seq);
            free(C_omp);
            free(C_cuda);
            free(C_cuda_tiled);
            return 0;
        }
    }

    // cleanup
    free(A);
    free(B);
    free(C_seq);
    free(C_omp);
    free(C_cuda);
    free(C_cuda_tiled);
    printf("Done.\n");
    return 0;
}
