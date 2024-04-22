# Considere a seguinte expressão: y = 127x - 65z + 1
    .text
    .globl _start
_start:

    la     t0, x
    la     t1, z
    la     t2, y

    lw     s0, 0(t0)
    lw     s1, 0(t1)
    lw     s2, 0(t2)

    addi   t3, zero, 127
    addi   t4, zero, 65
    addi   t5, zero, 1

    mul    s3, s0, t3
    mul    s4, s1, t4
    sub    s5, s3, s4
    add    s2, s5, t5

    sw     s2, 0(t2)

    nop

    .data
x:
    .word  5
z:
    .word  7
y:
    .word  0             # esse valor deverá ser sobrescrito após a execução do programa