    .data
string:
    .string "Phosphoribosylaminoimidazolesuccinocarboxamide"

    .text
    .globl  _start
_start:
    j       main
buscaChar:
    lb      t0, 0(a0)
    li      t1, 0

loop:
    beq     t0, a1, fimLoop
    beq     t0, zero, fimstring

    addi    a0, a0, 1
    lb      t0, 0(a0)

    j       loop
fimstring:
    li      a0, 0
fimLoop:
    ret

main:
    la      a0, string
    addi    a1, zero, 'c'

    jal     buscaChar

    nop

