import math

tamanhoBloco = 2048
nRegistros = 81113500
nCampos = 23
memoriaRegistro = 1299 + 12 + 11 # deve ser 0 se texto abaixo for descomentado

# for i in range(nCampos):
#     memoriaRegistro += int(input(f"Espaço ocupado pelo campo {i+1}: "))

blocagem = math.floor(tamanhoBloco / memoriaRegistro)
memoriaUsadaPorBloco = blocagem * memoriaRegistro
memoriaDesperdicadaPorBloco = tamanhoBloco - memoriaUsadaPorBloco
qtdBlocos = math.ceil(nRegistros / blocagem)
memoriaGasta = qtdBlocos*tamanhoBloco

print(f"Memoria/registro: {memoriaRegistro}")
print(f"Blocagem: {blocagem}")
print(f"Tamanho do bloco: {tamanhoBloco}")
print(f"Memoria utilizada em cada bloco: {memoriaUsadaPorBloco}")
print(f"Memoria desperdiçada por bloco: {memoriaDesperdicadaPorBloco}")
print(f"Quantidade de blocos: {qtdBlocos}")
print(f"Memoria total gasta: {memoriaGasta}")