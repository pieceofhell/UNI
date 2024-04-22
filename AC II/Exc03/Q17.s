    .text
    .globl _start

_start:
    la     s0, x
    lw     s1, 0(s0)

    beq    s1, zero, case0

    addi   t0, zero, 1
    beq    s1, t0, case1

    addi   t0, zero, 2
    beq    s1, t0, case2

    addi   t0, zero, 3
    beq    s1, t0, case3

    j      default

case0:
    addi   s1, zero, 17
    j      end
case1:
    addi   s1, zero, 29
    j      end
case2:
    addi   s1, zero, 41
    j      end
case3:
    addi   s1, zero, 53
    j      end
default:
    addi   s1, zero, 71

end:
    sw     s1, 0(s0)
    ret

    .data
x:
    .word  1
