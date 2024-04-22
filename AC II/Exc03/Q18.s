# Definições dos valores dos cases
    .equ   CASE_10, 10
    .equ   CASE_25, 25

# temp deve ser armazenado em s0 e x em s1
    .text
    .globl _start
_start:

# Carrega o valor de temp para s0 (registrador temporário)
# Suponha que temp já esteja carregado em s0
# (Isso pode ser feito anteriormente no código)
# Exemplo: addi s0, zero, <valor de temp>

# Início do switch-case
    li     s1, 0                # Inicializa x com 0
    nop

# Verificação do case 10
    li     t0, CASE_10          # Carrega o valor do case 10
    bne    s0, t0, check_25     # Verifica se temp é igual a 10
    li     s1, CASE_10          # Se sim, x = 10
    j      end_switch           # Pula para o final do switch-case

check_25:
# Verificação do case 25
    li     t0, CASE_25          # Carrega o valor do case 25
    bne    s0, t0, default_case # Verifica se temp é igual a 25
    li     s1, CASE_25          # Se sim, x = 25
    j      end_switch           # Pula para o final do switch-case

default_case:
# Caso padrão
    li     s1, 0                # Se não houver correspondência, x = 0

end_switch:
    ret