.text
.globl _start
_start:
    j main

fibonacci:
    li t0, 1
    bgt a0, t0, elseFib
        li a0, 1
        ret
    elseFib:
    addi sp, sp, -4
    sw ra, 0(sp)
    addi sp, sp, -4
    sw a0, 0(sp)

    addi a0, a0, -1
    jal fibonacci
    
    addi sp, sp, -4
    sw a0, 0(sp)

    lw a0, 4(sp)
    addi a0, a0, -2
    jal fibonacci

    mv t2, a0
    lw t1, 0(sp)

    add a0, t1, t2

    lw ra, 8(sp)
    addi sp, sp, 12
    
    ret

fatorial:
    bgt a0, zero, elseFat
        li a0, 1
        ret
    elseFat:
    addi sp, sp, -4
    sw ra, 0(sp)
    addi sp, sp, -4
    sw a0, 0(sp)

    addi a0, a0, -1
    jal fatorial

    lw t0, 0(sp)
    lw ra, 4(sp)
    addi sp, sp, 8

    mul a0, a0, t0

    ret

main:
    li a0, 5
    
    jal fibonacci
    mv s0, a0
    li a0, 5

    jal fatorial
    mv s1, a0
    nop