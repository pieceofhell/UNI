.data
    arr: .word -3,-2,-1,0,1,2,3

.text
.globl _start
_start:
    j main

getMaior:
    li t0, 0
    lw t1, 0(a2)
    li t3, 0

    loop:
        beq t0, a3, endLoop
        lw t2, 0(a2)

        sltu t4, t2, t1
        bnez t4, else
            mv t1, t2
            mv t3, a2
        else:
        addi a2, a2, 4
        addi t0, t0, 1
        j loop
    endLoop:
    mv a0, t1
    mv a1, t3
    
    ret


main:
    la a2, arr
    li a3, 7

    jal getMaior

    nop