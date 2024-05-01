#Usando jump table (bom para quando tem muitos casos)
#
#int x = 3;
#switch(x){
#   case 0:
#       x = 7;
#       break;
#    case 1:
#        x = 23;
#        break;
#    case 2:
#        x = 37;
#        break;
#    case 3:
#        x = 43;
#        break;
#    default:
#        x = 69;
#}

.text
.globl _start
_start:

la s1, x
lw s0, 0(s1)
add s2, zero, s0

addi t0, zero, 3
bgt s2, t0, default

slli s2, s2, 2
la t0, jump_table
add s2, s2, t0
lw t0, 0(s2)
jr t0

case0:
    addi s0, zero, 7
    j end
case1:
    addi s0, zero, 23
    j end
case2:
    addi s0, zero, 37
    j end
case3:
    addi s0, zero, 43
    j end
default:
    addi s0, zero, 69

end:
    sw s0, 0(s1)
    nop
    

.data
x: .word 1
jump_table: .word case0, case1, case2, case3
