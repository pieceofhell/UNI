## 0, 1, 1, 2, 3, 5, 8, 13, 21, ...

    .text
    .globl _start
_start:

    li     s0, 1
# Inicializa s0 com 1 (primeiro termo da sequência)
    li     s1, 1
# Inicializa s1 com 1 (segundo termo da sequência)

    li     s2, 0
# Inicializa s2 com 0 (contador para controlar a iteração)

    li     t0, 7
# Carrega o valor 7 para t0 (representando o índice do último termo desejado)

loop:
# Compara s2 (contador) com t0 (índice do último termo desejado)
# Se forem iguais, o loop termina
    beq    s2, t0, end

# Calcula o próximo termo da sequência de Fibonacci
# somando os dois últimos termos (s0 e s1)
    add    s0, s0, s1

# Incrementa o contador s2
    addi   s2, s2, 1

# Verifica se o contador s2 é igual ao índice do último
# termo desejado (t0) Se for, o loop termina
    beq    s2, t0, end

# Calcula o próximo termo da sequência de Fibonacci
# somando o último termo calculado (s0) com o termo anterior (s1)
    add    s1, s0, s1

# Incrementa o contador s2
    addi   s2, s2, 1

    j      loop        # Pula de volta para o início do loop

end:
    ret                # Retorna (termina a execução)