    .data
arr:
    .word  -3,-2,-1,0,1,2,3

    .text
    .globl _start
_start:
    j      main

achaMario:
    li     t0, 0
    lw     t1, 0(a2)
    li     t3, 0

loop:
    beq    t0, a3, fimLoop
    lw     t2, 0(a2)

    sltu   t4, t2, t1
    bnez   t4, senao
    mv     t1, t2
    mv     t3, a2
senao:
    addi   a2, a2, 4
    addi   t0, t0, 1
    j      loop
fimLoop:
    mv     a0, t1
    mv     a1, t3

    ret


main:
    la     a2, arr
    li     a3, 7

    jal    achaMario

    nop