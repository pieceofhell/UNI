.text
.globl _start
_start:
    lw s1, x1
    lw s2, x2
    lw s3, x3
    lw s4, x4

    add t0, s1, s2
    add t1, s3, s4
    add t0, t0, t1

    la t1, soma

    sw t0, 0(t1)
    nop

.data
x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17
soma: .word -1