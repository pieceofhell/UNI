.text
.globl _start
_start:
addi s1, zero, 2
addi s2, zero, 3
addi s3, zero, 4
addi s4, zero, 5

add t0, s1, s2
add t1, s3, s4
sub s5, t0, t1
sub t0, s1, s2
add s6, t0, s5
sub s2, s5, s6