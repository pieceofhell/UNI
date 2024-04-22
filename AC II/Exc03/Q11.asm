# considere x mapeado em s1
.text
.globl _start
_start:
addi s1, zero, 1  # x = 1

la t0, x1
la t1, x2
la t2, x3
la t3, x4

lw s0, 0(t0)
lw s1, 0(t1)
lw s2, 0(t2)
lw s3, 0(t3)

nop
.data
x1: .word 15
x2: .word 25
x3: .word 13
x4: .word 17