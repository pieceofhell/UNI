def construtor_taxador(taxa):
    def taxador(valor):
        resu = valor * (taxa/100)
        return resu
    return taxador

taxa_25 = construtor_taxador(25)

x = 10

print(taxa_25(x))

taxa_22 = construtor_taxador(22)

y  = 100

print(taxa_22(y))