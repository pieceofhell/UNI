.text
.globl _start
_start:
    addi sp, sp, -4
    li t0, -2
    sw t0, 0(sp)

    lw s0, 0(sp)
    
    bgt s0, zero, positivo
        mul s0, s0, s0
        mul s0, s0, s0
        addi s0, s0, -1
    j endIf
    positivo:
        mv t1, s0
        mul s0, s0, s0
        mul s0, s0, t1
        addi s0, s0, 1
    endIf:
    addi sp, sp, -4
    sw s0, 0(sp)
    nop