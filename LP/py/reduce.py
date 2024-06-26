# Função que verifica se um número é par
def eh_par(x):
    return x % 2 == 0

def eh_impar(x):
    return x % 2 != 0

# Lista de números
numeros = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

# Usando filter para obter apenas os números pares
numeros_pares = filter(eh_par, numeros)
numeros_impares = filter(eh_impar, numeros)

# Convertendo o resultado para uma lista
numeros_pares = list(numeros_pares)
numeros_impares = list(numeros_impares)

print(numeros)
print(numeros_pares)  # Saída: [2, 4, 6, 8, 10]
print(numeros_impares)