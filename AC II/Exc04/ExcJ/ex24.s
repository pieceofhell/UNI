.text
.globl _start
_start:
j main

modulo:
    bge a0, zero, else
        li a1, 1
        sub a0, zero, a0
        j endIf
    else:
        li a1, 0
    endIf:
        ret

main:
    li t0, -23
    addi sp, sp, -4
    sw t0, 0(sp)

    lw a0, 0(sp)
    jal modulo
    nop