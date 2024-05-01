    .text
    .globl _start
_start:

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

# conjunto de testes
#primeiramente, será verificado se A[0] == B[0]
#caso seja verdadeiro, o programa irá para a próxima verificação
#caso seja falso, o programa irá para a label rip
#caso A[0] == B[0], será verificado se A[1] == B[1]
#caso seja verdadeiro, o programa irá para a label rip
#caso seja falso, o programa irá para a label correct
#caso A[1] == B[1], o programa irá para a label rip

    lw t0, 0(s0) 
    lw t1, 4(s0)

    beq t0, t1, rip

correct:
    lw t2, 0(s1)
    lw t3, 4(s1)
    beq t2, t3, rip

rip:
    nop




    .data
A:
    .word  1, 3
B:
    .word  2, 4

