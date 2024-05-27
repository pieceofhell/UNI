    .text
    .globl _start
_start:
    j      main

MOD:
    bge    a0, zero, senao
    li     a1, 1
    sub    a0, zero, a0
    j      FIM
senao:
    li     a1, 0
FIM:
    ret

main:
    li     t0, -23
    addi   sp, sp, -4
    sw     t0, 0(sp)

    lw     a0, 0(sp)
    jal    MOD
    NOP