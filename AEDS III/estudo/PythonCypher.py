def encrypt(text, key):
    encrypted_text = ""
    for char in text:
        if char.isalpha():
            ascii_offset = ord('a') if char.islower() else ord('A')
            print("ascii offset is: " + str(ascii_offset) + str("\n"))
            encrypted_char = chr((ord(char) - ascii_offset + key) % 26 + ascii_offset)
            print("the following calculations were made: " + str(ord(char)) + " - " + str(ascii_offset) + " + " + str(key) + " % 26 + " + str(ascii_offset) + str("\n"))
            print("encrypted char is: " + encrypted_char + str("\n"))
            encrypted_text += encrypted_char
        else:
            encrypted_text += char
    return encrypted_text

def decrypt(text, key):
    return encrypt(text, -key)

# Get input from the user
text = input("Enter the text to encrypt: ")
key = int(input("Enter the encryption key: "))

# Encrypt the text
encrypted_text = encrypt(text, key)

# Print the encrypted text
print("Encrypted text:", encrypted_text)