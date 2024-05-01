.text
.globl _start
_start:
    li s0, 1
    li s1, 1
    li s2, 0

    li t0, 7

    loop:
        beq s2, t0, end

        add s0, s0, s1
        
        addi s2, s2, 1
        beq s2, t0, end

        add s1, s0, s1

        addi s2, s2, 1
        j loop
    end:
        nop
