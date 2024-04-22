# int i;
# int A[10];
# for (i=0; i<10; i++) {
# A[i]=A[i]+1;
# }
    .text
    .globl _start
_start:
    la     s0, A

    li     s1, 0

    li     t2, 10
loop:
    beq    s1, t2, end

    slli   t0, s1, 2
    add    t0, t0, s0

    lw     t1, 0(t0)
    addi   t1, t1, 1
    sw     t1, 0(t0)

    addi   s1, s1, 1
    j      loop
end:
    ret

    .data
A:
    .word  0