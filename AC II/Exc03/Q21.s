# int i;
# int A[10];
# for (i=0; i<10; i++) {
# if (i%2==0)
# A[i]=A[i]+A[i+1];
# else
# A[i]=A[i]*2;
# }
    .text
    .globl _start
_start:
    la     s0, A
    li     s1, 0

    li     t5, 10

loop:
    beq    s1, t5, end

    andi   t0, s1, 1
    beqz   t0, even
odd:
    slli   t1, s1, 2
    add    t1, t1, s0
    lw     t2, 0(t1)
    slli   t2, t2, 1
    sw     t2, 0(t1)
    j      endif
even:
    slli   t1, s1, 2
    add    t1, t1, s0
    lw     t2, 0(t1)
    addi   t4, t1, 4
    lw     t3, 0(t4)
    add    t2, t2, t3
    sw     t2, 0(t1)
endif:
    addi   s1, s1, 1
    j      loop
end:
    nop

    .data
A:
    .word  1, 2, 3, 4, 5, 6, 7, 8, 9, 10