    .text
    .globl _start
_start:
    addi   sp, sp, -4
    li     t0, 2
    sw     t0, 0(sp)


    lw     s0, 0(sp)
    andi   t0, s0, 1

    beq    t0, zero, par
    mv     t1, s0
    mul    t1, t1, s0
    mul    t1, t1, s0
    addi   t2, s0, 1
    addi   t3, s0, -1
    mul    s0, t1, t2
    mul    s0, s0, t3
    addi   s0, s0, 1
    j      fimSe
par:
    mul    t1, s0, s0
    addi   t2, s0, 2
    addi   t3, s0, -1
    mul    s0, t1, t2
    mul    s0, s0, t3

fimSe:

    addi   sp, sp, -4
    sw     s0, 0(sp)
    nop