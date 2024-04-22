# x=3;
# y=4;
# z=(15-x)+(67-y)+4;
.text
.globl _start
_start:

addi t0, zero, 3
addi t1, zero, 4

addi t2, zero, 15
addi t3, zero, 67
addi t4, zero, 4

sub s0, t2, t0   # s0 = 15 - x
sub s1, t3, t1   # s1 = 67 - y

add s2, s0, s1   # s2 = (15 - x) + (67 - y)
addi s3, s2, 4   # s3 = (15 - x) + (67 - y) + 4

jr ra