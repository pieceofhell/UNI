from functools import partial

def simpleSum(a, b):
    return a + b

def simpleSub(a, b):
    return a - b

x = partial(simpleSum, 2)

print(x(5))

