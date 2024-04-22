# x=1;
# y=5*x+15;

.text
.globl _start
_start:

addi t0, zero, 1 # t0 = 0 + 1
addi t1, zero, 5 # t1 = 0 + 5
mul t2, t1, t0
addi s0, t2, 15

jr ra