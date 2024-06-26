def serie_rec(n, i=1, result=0):
    if i > n:
        return result
    else:
        return serie_rec(n, i + 1, result + ((1 + i**2) / i))

def serie(n):
    return serie_rec(n)

n = 2 # valor arbitrario para teste
print(serie(n))