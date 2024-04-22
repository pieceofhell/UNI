    .text
    .globl _start
_start:

# Laço para calcular A[i] = B[i] * (i + 1) + A[i], onde i vai de 0 a 4
# i = 0
    la     s0, A          # Endereço de A
    la     s1, B          # Endereço de B

    lw     t0, 0(s0)      # Carrega A[0] em t0
    lw     t1, 0(s1)      # Carrega B[0] em t1
    addi   t2, zero, 1    # i + 1
    mul    t1, t1, t2     # B[i] * (i + 1)
    add    t0, t0, t1     # B[i] * (i + 1) + A[i]
    sw     t0, 0(s0)      # Armazena o resultado em A[0]

# i = 1
    lw     t0, 4(s0)      # Carrega A[1] em t0
    lw     t1, 4(s1)      # Carrega B[1] em t1
    addi   t2, zero, 2    # i + 1
    mul    t1, t1, t2     # B[i] * (i + 1)
    add    t0, t0, t1     # B[i] * (i + 1) + A[i]
    sw     t0, 4(s0)      # Armazena o resultado em A[1]

# i = 2
    lw     t0, 8(s0)      # Carrega A[2] em t0
    lw     t1, 8(s1)      # Carrega B[2] em t1
    addi   t2, zero, 3    # i + 1
    mul    t1, t1, t2     # B[i] * (i + 1)
    add    t0, t0, t1     # B[i] * (i + 1) + A[i]
    sw     t0, 8(s0)      # Armazena o resultado em A[2]

# i = 3
    lw     t0, 12(s0)     # Carrega A[3] em t0
    lw     t1, 12(s1)     # Carrega B[3] em t1
    addi   t2, zero, 4    # i + 1
    mul    t1, t1, t2     # B[i] * (i + 1)
    add    t0, t0, t1     # B[i] * (i + 1) + A[i]
    sw     t0, 12(s0)     # Armazena o resultado em A[3]

# i = 4
    lw     t0, 16(s0)     # Carrega A[4] em t0
    lw     t1, 16(s1)     # Carrega B[4] em t1
    addi   t2, zero, 5    # i + 1
    mul    t1, t1, t2     # B[i] * (i + 1)
    add    t0, t0, t1     # B[i] * (i + 1) + A[i]
    sw     t0, 16(s0)     # Armazena o resultado em A[4]

    nop                   # Não faz nada, apenas uma instrução de preenchimento

    .data
A:
    .word  1, 3, 5, 7, 9  # int A[] = {1,3,5,7,9};
B:
    .word  2, 4, 6, 8, 10 # int B[] = {2,4,6,8,10};

