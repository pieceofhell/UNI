def gen():
    i = 1
    while True:
        yield i
        i += 1

from itertools import islice

def take(n, iterable):
    "Retorna os n primeiros elementos de um iteravel com uma lista"
    return list(islice(iterable, n))

# print(take(10, gen())) # [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

def primos():
   for n in gen():
     if not any(i>1 and i!=n and n%i == 0
         for i in islice(gen(), n)):
             yield n
 
print(take(10, primos()))
# [1, 2, 3, 5, 7, 11, 13, 17, 19, 23]
