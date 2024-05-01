.text
.globl _start
_start:
    la s0, A
    li s1, 0

    li t5, 10

    loop:
        beq s1, t5, fim

        andi t0, s1, 1
        beqz t0, par
        impar:
            slli t1, s1, 2
            add t1, t1, s0
            lw t2, 0(t1)
            slli t2, t2, 1
            sw t2, 0(t1)
            j endif
        par:
            slli t1, s1, 2
            add t1, t1, s0
            lw t2, 0(t1)
            addi t4, t1, 4
            lw t3, 0(t4)
            add t2, t2, t3
            sw t2, 0(t1)
        endif:
        addi s1, s1, 1
        j loop
    fim:
        nop

.data
    A: .word 1,2,3,4,5,6,7,8,9,10