.text
.globl _start
_start:
    la s0, temp
    la s1, x

    lw s2 0(s0)

    li t0, 10
    beq s2, t0, case10

    li t0, 25
    beq s2, t0, case25

    j default

    case10:
        li t0, 10
        sw t0, 0(s1)
        j end
    case25:
        li t0, 25
        sw t0, 0(s1)
        j end
    default:
        sw zero, 0(s1)
    end:
        nop

.data
temp: .word 23
x: .word 0