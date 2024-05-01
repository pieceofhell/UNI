.text
.globl _start
_start:
    la s1, x1
    la s2, x2
    la s3, x3
    la s4, x4

    lw s5, 0(s1)
    lw s6, 0(s2)
    lw s7, 0(s3)
    lw s8, 0(s4)
.data
x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17