def somatorio(f, inf, sup):
    total = 0
    for x in range(inf, sup+1):
        total += f(x)
    return total

def somatorioRec(f, inf, sup):
    if inf > sup:
        return 0
    return f(inf) + somatorioRec(f, inf + 1, sup)

def somatorioTRec(f, inf, sup):
    def somatorioRecAux(f, inf, sup, total):
        if inf > sup:
            return total
        return somatorioRecAux(f, inf + 1, sup, total + f(inf))
    return somatorioRecAux(f, inf, sup, 0)

f = lambda x: x**2

print("Somatorio recursivo (funcao x^2, lim. inf. (1), lim. sup. (10)): ",format(somatorioRec(f, 1, 10)))
print("Somatorio recursivo (funcao x^2, lim. inf. (1), lim. sup. (100)): ",format(somatorioRec(f, 1, 100)))
# print("Somatorio recursivo (funcao x^2, lim. inf. (1), lim. sup. (1000)): ",format(somatorioRec(f, 1, 1000))) AQUI JA DA PAU!

print("Somatorio recursivo com cauda (funcao x^2, lim. inf. (1), lim. sup. (10)): ",format(somatorioTRec(f, 1, 10)))
print("Somatorio recursivo com cauda (funcao x^2, lim. inf. (1), lim. sup. (100)): ",format(somatorioTRec(f, 1, 100)))
# print("Somatorio recursivo com cauda (funcao x^2, lim. inf. (1), lim. sup. (1000)): ",format(somatorioRec(f, 1, 1000))) AQUI JA DA PAU!

print("Somatorio com for range (funcao x^2, lim. inf. (1), lim. sup. (10)): {0}",format(somatorio(f, 1, 10)))
print("Somatorio com for range (funcao x^2, lim. inf. (1), lim. sup. (100)): {0}",format(somatorio(f, 1, 100)))
print("Somatorio com for range (funcao x^2, lim. inf. (1), lim. sup. (1000)): {0}",format(somatorio(f, 1, 1000)))
print("Somatorio com for range (funcao x^2, lim. inf. (1), lim. sup. (10000)): {0}",format(somatorio(f, 1, 10000)))

