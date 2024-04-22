.text
.globl _start
_start:
    la t0, A
    la t1, B
    la t2, C

    addi t6, zero, 4

    addi t3, zero, 0
    addi t4, zero, 10
    loop1:
        addi t5, zero, 9
        loop2:
            mul s7, t3, t6
            mul s8, t5, t6

            add s9, t0, s7
            add s10, t1, s8

            lw s0, 0(s9)
            lw s1, 0(s10)

            add s2, s0, s1
            
            mul s3, t3, t4
            add s3, s3, t5
            mul s3, s3, t6
            add s3, s3, t2

            sw s2, 0(s3)

            bge t6, s2, endif
                blt s0, s1, if  
                else:
                    sw zero, 0(s3)
                    j endif
                if:
                    addi s4, zero, 1
                    sw s4, 0(s3)

            endif:

            addi t5, t5, -1
            bge t5, zero, loop2
        addi, t3, t3, 1
        blt t3, t4, loop1
    nop
.data
A: .word 2,1,4,3,2,3,0,1,3,4
B: .word 4,3,1,0,3,2,3,4,1,2
C: .word 0