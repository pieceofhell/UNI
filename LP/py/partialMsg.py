from functools import partial
from random import randint

def log(nivel, texto):
    print("[{nivel}]: {msg}"
           .format(nivel=nivel, msg=texto))	

debug = partial(log, "debug")

# debug("Início da tarefa.")
# debug("Continuação da tarefa")
# debug("Tarefa encerrada. Resultado.")

# testCase = partial(log, "testCase")
# testCase("Teste 1")
# testCase("Teste 2")
# testCase("Teste 3")

def randPow(ceil):
    # generating a random number between 0 and ceil
    exp = randint(0, ceil)
    print("Expoente gerado: ", exp)
    def potencia(num):
        return num ** exp
    return potencia

randPotencia = randPow(3)
print("[2^?]: {randPotencia}".format(randPotencia=randPotencia(2)))


def pow(exp, num):
    return num ** exp

square = partial(pow, 2)
cube = partial(pow, 3)

print(square(3))