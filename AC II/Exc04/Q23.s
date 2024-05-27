    .text
    .globl _start

_start:
    j      main

fib:
    li     t0, 1
    bgt    a0, t0, SENAO1
    li     a0, 1
    ret
SENAO1:
    addi   sp, sp, -4
    sw     ra, 0(sp)
    addi   sp, sp, -4
    sw     a0, 0(sp)

    addi   a0, a0, -1
    jal    fib

    addi   sp, sp, -4
    sw     a0, 0(sp)

    lw     a0, 4(sp)
    addi   a0, a0, -2
    jal    fib

    mv     t2, a0
    lw     t1, 0(sp)

    add    a0, t1, t2

    lw     ra, 8(sp)
    addi   sp, sp, 12

    ret

fat:
    bgt    a0, zero, SENAO2
    li     a0, 1
    ret
SENAO2:
    addi   sp, sp, -4
    sw     ra, 0(sp)
    addi   sp, sp, -4
    sw     a0, 0(sp)

    addi   a0, a0, -1
    jal    fat

    lw     t0, 0(sp)
    lw     ra, 4(sp)
    addi   sp, sp, 8

    mul    a0, a0, t0

    ret

main:
    li     a0, 5

    jal    fib
    mv     s0, a0
    li     a0, 5

    jal    fat
    mv     s1, a0
    NOP