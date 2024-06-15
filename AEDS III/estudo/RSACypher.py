import random

def generate_keypair(p, q):
    n = p * q
    phi = (p - 1) * (q - 1)

    # Choose e such that 1 < e < phi and gcd(e, phi) = 1
    e = random.randrange(1, phi)
    while gcd(e, phi) != 1:
        e = random.randrange(1, phi)

    # Compute d such that d*e ≡ 1 (mod phi)
    d = mod_inverse(e, phi)

    return ((e, n), (d, n))

def gcd(a, b):
    while b != 0:
        a, b = b, a % b
    return a

def mod_inverse(a, m):
    if gcd(a, m) != 1:
        return None

    # Extended Euclidean Algorithm
    x, y, old_x, old_y = 0, 1, 1, 0
    while m != 0:
        quotient = a // m
        a, m = m, a % m
        x, old_x = old_x - quotient * x, x
        y, old_y = old_y - quotient * y, y

    return old_x % m

def encrypt(public_key, message):
    e, n = public_key
    encrypted_message = [pow(ord(char), e, n) for char in message]
    return encrypted_message

def decrypt(private_key, encrypted_message):
    d, n = private_key
    decrypted_message = [chr(pow(char, d, n)) for char in encrypted_message]
    return ''.join(decrypted_message)

# Example usage
p = 61
q = 53
public_key, private_key = generate_keypair(p, q)

message = "Hello, World!"
encrypted_message = encrypt(public_key, message)
decrypted_message = decrypt(private_key, encrypted_message)

print("Original message:", message)
print("Encrypted message:", encrypted_message)
print("Decrypted message:", decrypted_message)