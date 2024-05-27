.data
str: .string "abacate"

.text
.globl _start
_start:
    j main
    busca_caractere:
        lb t0, 0(a0)
        li t1, 0

        loop:
            beq t0, a1, endLoop 
            beq t0, zero, endOfString
            
            addi a0, a0, 1
            lb t0, 0(a0)
            
            j loop
        endOfString:
            li a0, 0
        endLoop:
            ret

    main:
    la a0, str
    addi a1, zero, 'c'

    jal busca_caractere

    nop

    