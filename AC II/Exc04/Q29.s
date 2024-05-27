    .text
    .globl _start
_start:
    addi   sp, sp, -4
    li     t0, -2
    sw     t0, 0(sp)

    lw     s0, 0(sp)

    bgt    s0, zero, mais
    mul    s0, s0, s0
    mul    s0, s0, s0
    addi   s0, s0, -1
    j      fimSe
mais:
    mv     t1, s0
    mul    s0, s0, s0
    mul    s0, s0, t1
    addi   s0, s0, 1
fimSe:
    addi   sp, sp, -4
    sw     s0, 0(sp)
    nop