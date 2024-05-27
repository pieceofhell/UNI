    .data
vetor:
    .word  1,2,3,4,5,6,7,100,300

    .text
    .globl _start
_start:
    j      main

maximo:
    lw     t0, 0(a0)
    li     t1, 0

loop:
    beq    t1, a1, fimLoop

    lw     t2, 0(a0)
    ble    t2, t0, senao
    mv     t0, t2
senao:
    addi   a0, a0, 4
    addi   t1, t1, 1
    j      loop
fimLoop:

    mv     a0, t0
    ret

main:
    la     a0, vetor
    li     a1, 9
    jal    maximo
    nop