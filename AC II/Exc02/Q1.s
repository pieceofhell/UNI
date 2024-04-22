# a = 2;
# b = 3;
# c = 4;
# d = 5;
# x = (a+b)-(c+d);
# y = a-b+x;
# b = x-y;

.text
.globl _start
_start:
addi s2, zero, 2 # a
addi s3, zero, 3 # b
addi s4, zero, 4 # c
addi s5, zero, 5 # d

add t0, s2, s3 # a + b
add t1, s4, s5 # c + d
sub s8, s6, s7 # x = (a+b)-(c+d)

sub t2, s2, s3 # a - b;
add s9, t0, s8 # y = a - b + x;
sub s10, s8, s9 # b = x - y;

jr ra