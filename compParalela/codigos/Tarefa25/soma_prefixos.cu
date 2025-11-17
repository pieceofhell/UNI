/*
Tarefa 25 - Padrão SCAN em CUDA

Autor: Henrique Oliveira

-- Instruções de compilação:
  nvcc -O3 scan_prefix.cu -o scan_prefix
  nsys profile ./soma_prefixos


dummy@dummy-desktop:~/code/25$ nsys profile ./soma_prefixos
Collecting data...
Array size N = 1048576, blockDim = 256, numBlocks = 4096
CPU inclusive scan time: 4.930 ms
GPU stage1 (per-block scan) time: 1.876 ms
GPU stage2 (add block sums) time: 0.108 ms
GPU approximate total time: 1.983 ms
SUCCESS: GPU result matches CPU result.
First 8 elements (in/gold/gpu):
1 -> 1 / 1
1 -> 2 / 2
1 -> 3 / 3
1 -> 4 / 4
1 -> 5 / 5
1 -> 6 / 6
1 -> 7 / 7
1 -> 8 / 8
Generating '/tmp/nsys-report-fb91.qdstrm'
[1/1] [========================100%] report1.nsys-rep
Generated:
        /home/dummy/code/25/report1.nsys-rep

dummy@dummy-desktop:~/code/25$ ./soma_prefixos
Array size N = 1048576, blockDim = 256, numBlocks = 4096 CPU inclusive scan time: 1.635 ms
GPU stage1 (per-block scan) time: 214.176 ms
GPU stage2 (add block sums) time: 0.044 ms
GPU approximate total time: 214.220 ms
SUCCESS: GPU result matches CPU result.
First 8 elements (in/gold/gpu):
1 -> 1 / 1
1 -> 2 / 2
1 -> 3 / 3
1 -> 4 / 4
1 -> 5 / 5
1 -> 6 / 6
1 -> 7 / 7
1 -> 8 / 8

*/

#include <stdio.h>
#include <stdlib.h>
#include <chrono>
#include <cuda_runtime.h>

#define CHECK_CUDA(call)                                                                         \
  do                                                                                             \
  {                                                                                              \
    cudaError_t err = call;                                                                      \
    if (err != cudaSuccess)                                                                      \
    {                                                                                            \
      fprintf(stderr, "CUDA error at %s:%d: %s\n", __FILE__, __LINE__, cudaGetErrorString(err)); \
      exit(EXIT_FAILURE);                                                                        \
    }                                                                                            \
  } while (0)

void cpu_inclusive_scan(int *in, int *out, int n)
{
  if (n <= 0)
    return;
  out[0] = in[0];
  for (int i = 1; i < n; ++i)
  {
    out[i] = out[i - 1] + in[i];
  }
}

__global__ void block_scan_kernel(const int *d_in, int *d_out, int *d_block_sums, int n)
{
  extern __shared__ int sdata[];
  int t = threadIdx.x;
  int gidx = blockIdx.x * blockDim.x + t;

  int x = 0;
  if (gidx < n)
    x = d_in[gidx];
  sdata[t] = x;
  __syncthreads();

  for (int offset = 1; offset < blockDim.x; offset <<= 1)
  {
    int val = 0;
    if (t >= offset)
      val = sdata[t] + sdata[t - offset];
    __syncthreads();
    if (t >= offset)
      sdata[t] = val;
    __syncthreads();
  }

  if (gidx < n)
    d_out[gidx] = sdata[t];

  if (t == blockDim.x - 1)
  {

    d_block_sums[blockIdx.x + 1] = sdata[t];
  }
}

__global__ void add_block_sums_kernel(int *d_out, const int *d_block_prefix_sums, int n)
{
  int gidx = blockIdx.x * blockDim.x + threadIdx.x;
  if (gidx < n)
  {
    int add = d_block_prefix_sums[blockIdx.x];
    d_out[gidx] += add;
  }
}

int main(int argc, char **argv)
{

  int N = 1 << 20;
  if (argc > 1)
    N = atoi(argv[1]);
  const int BLOCK_DIM = 256;
  const int numBlocks = (N + BLOCK_DIM - 1) / BLOCK_DIM;

  printf("Array size N = %d, blockDim = %d, numBlocks = %d\n", N, BLOCK_DIM, numBlocks);

  int *h_in = (int *)malloc(N * sizeof(int));
  int *h_out_cpu = (int *)malloc(N * sizeof(int));
  int *h_out_gpu = (int *)malloc(N * sizeof(int));

  for (int i = 0; i < N; ++i)
  {
    h_in[i] = 1;
  }

  auto t0 = std::chrono::high_resolution_clock::now();
  cpu_inclusive_scan(h_in, h_out_cpu, N);
  auto t1 = std::chrono::high_resolution_clock::now();
  double cpu_ms = std::chrono::duration<double, std::milli>(t1 - t0).count();
  printf("CPU inclusive scan time: %.3f ms\n", cpu_ms);

  int *d_in = nullptr;
  int *d_out = nullptr;
  int *d_block_sums = nullptr;
  CHECK_CUDA(cudaMalloc((void **)&d_in, N * sizeof(int)));
  CHECK_CUDA(cudaMalloc((void **)&d_out, N * sizeof(int)));

  CHECK_CUDA(cudaMalloc((void **)&d_block_sums, (numBlocks + 1) * sizeof(int)));

  CHECK_CUDA(cudaMemcpy(d_in, h_in, N * sizeof(int), cudaMemcpyHostToDevice));

  CHECK_CUDA(cudaMemset(d_block_sums, 0, (numBlocks + 1) * sizeof(int)));

  cudaEvent_t start, stop;
  CHECK_CUDA(cudaEventCreate(&start));
  CHECK_CUDA(cudaEventCreate(&stop));
  CHECK_CUDA(cudaEventRecord(start));

  size_t shmem = BLOCK_DIM * sizeof(int);
  block_scan_kernel<<<numBlocks, BLOCK_DIM, shmem>>>(d_in, d_out, d_block_sums, N);
  CHECK_CUDA(cudaGetLastError());

  CHECK_CUDA(cudaEventRecord(stop));
  CHECK_CUDA(cudaEventSynchronize(stop));
  float gpu_stage1_ms = 0.0f;
  CHECK_CUDA(cudaEventElapsedTime(&gpu_stage1_ms, start, stop));
  printf("GPU stage1 (per-block scan) time: %.3f ms\n", gpu_stage1_ms);

  int *h_block_sums = (int *)malloc((numBlocks + 1) * sizeof(int));

  CHECK_CUDA(cudaMemcpy(h_block_sums, d_block_sums, (numBlocks + 1) * sizeof(int), cudaMemcpyDeviceToHost));

  for (int i = 1; i <= numBlocks; ++i)
  {
    h_block_sums[i] += h_block_sums[i - 1];
  }

  CHECK_CUDA(cudaMemcpy(d_block_sums, h_block_sums, (numBlocks + 1) * sizeof(int), cudaMemcpyHostToDevice));

  CHECK_CUDA(cudaEventRecord(start));
  add_block_sums_kernel<<<numBlocks, BLOCK_DIM>>>(d_out, d_block_sums, N);
  CHECK_CUDA(cudaGetLastError());
  CHECK_CUDA(cudaEventRecord(stop));
  CHECK_CUDA(cudaEventSynchronize(stop));
  float gpu_stage2_ms = 0.0f;
  CHECK_CUDA(cudaEventElapsedTime(&gpu_stage2_ms, start, stop));
  printf("GPU stage2 (add block sums) time: %.3f ms\n", gpu_stage2_ms);

  float gpu_total_ms = gpu_stage1_ms + gpu_stage2_ms;
  printf("GPU approximate total time: %.3f ms\n", gpu_total_ms);

  CHECK_CUDA(cudaMemcpy(h_out_gpu, d_out, N * sizeof(int), cudaMemcpyDeviceToHost));

  bool ok = true;
  for (int i = 0; i < N; ++i)
  {
    if (h_out_gpu[i] != h_out_cpu[i])
    {
      printf("Mismatch at i=%d: cpu=%d gpu=%d\n", i, h_out_cpu[i], h_out_gpu[i]);
      ok = false;
      break;
    }
  }
  if (ok) // verificacao
  {
    printf("SUCCESS: GPU result matches CPU result.\n");
  }
  else
  {
    printf("ERROR: GPU result does NOT match CPU result.\n");
  }

  int show = 8;
  printf("First %d elements (in/gold/gpu):\n", show);
  for (int i = 0; i < show && i < N; ++i)
  {
    printf("%d -> %d / %d\n", h_in[i], h_out_cpu[i], h_out_gpu[i]);
  }

  free(h_in);
  free(h_out_cpu);
  free(h_out_gpu);
  free(h_block_sums);
  CHECK_CUDA(cudaFree(d_in));
  CHECK_CUDA(cudaFree(d_out));
  CHECK_CUDA(cudaFree(d_block_sums));
  CHECK_CUDA(cudaEventDestroy(start));
  CHECK_CUDA(cudaEventDestroy(stop));

  return 0;
}
