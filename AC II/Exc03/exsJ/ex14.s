.text
.globl _start
_start:
    #int A[] = {1,3,5,7,9};
    #int B[] = {2,4,6,8,10};

    #A[0] = B[0] * 1 + A[0];
    #A[1] = B[1] * 2 + A[1];
    #A[2] = B[2] * 3 + A[2];
    #A[3] = B[3] * 4 + A[3];
    #A[4] = B[4] * 5 + A[4];

    la s0, A
    la s1, B

    lw t0, 0(s0)
    lw t1, 0(s1)
    addi t2, zero, 1
    mul t1, t1, t2
    add t0, t0, t1
    sw t0, 0(s0)

    lw t0, 4(s0)
    lw t1, 4(s1)
    addi t2, zero, 2
    mul t1, t1, t2
    add t0, t0, t1
    sw t0, 4(s0)

    lw t0, 8(s0)
    lw t1, 8(s1)
    addi t2, zero, 3
    mul t1, t1, t2
    add t0, t0, t1
    sw t0, 8(s0)

    lw t0, 12(s0)
    lw t1, 12(s1)
    addi t2, zero, 4
    mul t1, t1, t2
    add t0, t0, t1
    sw t0, 12(s0)

    lw t0, 16(s0)
    lw t1, 16(s1)
    addi t2, zero, 5
    mul t1, t1, t2
    add t0, t0, t1
    sw t0, 16(s0)
    nop
.data
A: .word 1,3,5,7,9
B: .word 2,4,6,8,10