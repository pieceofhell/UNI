    .data
Vetor:
    .word  1, 3, 5, 7, 9, 11, 13, 0, 2, 4, 6, 8, 10, 12
Soma:
    .word  -1
Maior:
    .word  -1

    .text
    .globl _start
_start:
    la     s0, Vetor
    lw     s2, 0(s0)
    li     s3, 14
    li     s4, 0

loop:
    beq    s4, s3, fimLoopp

    lw     s5, 0(s0)
    add    s1, s1, s5

    ble    s5, s2, senao
    mv     s2, s5
senao:
    addi   s4, s4, 1
    addi   s0, s0, 4
    j      loop
fimLoopp:

    la     t0, Soma
    sw     s1, 0(t0)

    la     t0, Maior
    sw     s2, 0(t0)

    nop