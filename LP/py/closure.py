# closure exemple:

def outer_func(x):
    def inner_func(y):
        return x + y
    return inner_func

f = outer_func(10)
print(f(20))