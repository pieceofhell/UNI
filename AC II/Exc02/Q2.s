# x=1;
# y=5-x+15;

.text
.globl _start
_start:
addi s2, zero, 1 # x
addi s3, zero, 5 # first y
addi t0, s2, 15 # expression x + 15 
sub s4, s3, t0 # full y

jr ra