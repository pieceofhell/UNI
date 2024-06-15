def maxFun(f, g):
   def funcaoMax(x):
       return maxVal(f(x), g(x))
   return funcaoMax 

def maxVal(a, b):
    if a > b:
        return a
    return b

f = lambda x: x**2
g = lambda x: x**3

print(maxFun(f, g)(3))