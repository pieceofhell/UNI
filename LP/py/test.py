def construtor_de_taxador(taxa):
    def taxador(valor):
        return valor * (float(taxa) / 100)
    return taxador

def construtor2(taxa):
    return lambda valor: valor * (float(taxa) / 100)

def bagulhador(val):
    def shulambador(valor):
        print("val recebido e sendo mexido em shulambador: " + str(valor))
        return valor * valor
    return lambda x: shulambador(val) * 2

vat1 = bagulhador(2)

print("Resultado de bagulhador(2):")
print("Referência vat1: {0}".format(vat1))

print("Resultado vat1(100): {0}".format(vat1(100)))
