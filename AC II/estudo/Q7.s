# x = 8192
# y = 1024
# z = ((12*x) + (66*y)) * 4;

la t0, a
la t1, b

lw s0, 0(t0)
lw s1, 0(t1)

nop

.data
a: .word 8192
b: .word 1024