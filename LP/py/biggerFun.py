def higherDef(f, g, num):
    
    if f(num) > g(num):
        return f
    else:
        return g
    
# f = lambda x: x**2
# g = lambda x: x**3

def f(x):
    return x**2

# x = 10
# higherNumHigherDef = higherDef(f, g, x)(x)

# print(higherNumHigherDef)

y = f(10)

print(y)