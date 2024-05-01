.text
.globl _start
_start:
    la s0, x
    la s1, y
    la s2, m

    lw t0, 0(s0)
    lw t1, 0(s1)
    lw t2, 0(s2)

    bgt t0, t1, if
    else:
        sw t1, 0(s2)
        j end
    if:
        sw t0, 0(s2)
    
    end:
    nop

.data
x: .word 7
y: .word 23
m: .word 0