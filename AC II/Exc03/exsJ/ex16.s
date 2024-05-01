.text
.globl _start
_start:
#int a = um_valor_inteiro_qualquer;
#int b = um_valor_inteiro_qualquer;
#int x = 0;
#.if ( a >= 0 && b <= 50 ) 
#    x = 1;

    la s0, a
    la s1, b
    la s2, x

    lw s3, 0(s0)
    lw s4, 0(s1)

    blt s3, zero, end
    addi t0, zero, 50
    bgt s4, t0, end

    addi t0, zero, 1
    sw t0, 0(s2)

    end:
    nop

.data
a: .word 7
b: .word 23
x: .word 0