# while(i == 8){
# x = i++;
# }
    .text
    .globl _start
_start:
    li     s0, 8
    li     s1, 0

    li     t0, 8

loop:
    bne    s0, t0, end
    mv     s1, s0
    addi   s0, s0, 1
    j      loop
end:
    ret