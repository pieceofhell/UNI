.text
.globl _start
_start:
    #y = 127x - 65z + 1
    la t0, x
    la t1, z
    la t2, y

    lw s0, 0(t0)
    lw s1, 0(t1)

    addi t3, zero, 127
    mul s0, s0, t3

    addi t3, zero, -65
    mul s1, s1, t3

    add s0, s0, s1
    addi s0, s0, 1

    sw s0, 0(t2)

.data
x: .word 5
z: .word 7
y: .word 0  # esse valor deverá ser sobrescrito após a execução do programa