.data
arr: .word 1,2,3,4,5,6,7

.text
.globl _start
_start:
    j main

    swap:
        slli a1, a1, 2
        add a0, a0, a1
        lw t0, 0(a0)
        lw t1, 4(a0)

        sw t1, 0(a0)
        sw t0, 4(a0)

        ret
    
    main:
        la a0, arr
        li a1, 3
        jal swap
        nop