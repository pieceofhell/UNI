#Usando comparação direta (melhor quando tem poucos casos)
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
la s0, x
lw s1, 0(s0)

beq s1, zero, case0

addi t0, zero, 1
beq s1, t0, case1

addi t0, zero, 2
beq s1, t0, case2

addi t0, zero, 3
beq s1, t0, case3

j default

case0:
    addi s1, zero, 7
    j end
case1:
    addi s1, zero 23
    j end
case2:
    addi s1, zero, 37
    j end
case3:
    addi s1, zero, 43
    j end
default:
    addi s1, zero, 69

end:
    sw s1, 0(s0)
    nop

.data
x: .word 1