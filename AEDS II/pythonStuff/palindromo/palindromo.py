from unidecode import unidecode

while True:
    ogString = input()

    if ogString == "FIM":
        break
    
    cmpString = ogString.replace(" ", "")
    cmpString = unidecode(cmpString)
    cmpString = cmpString.lower()
    cmpString = cmpString.replace("!", "")
    cmpString = cmpString.replace("?", "")
    cmpString = cmpString.replace(".", "")
    cmpString = cmpString.replace(",", "")
    cmpString = cmpString.replace(":", "")
    cmpString = cmpString.replace(";", "")
    cmpString = cmpString.replace("á", "a")
    cmpString = cmpString.replace("ã", "a")
    cmpString = cmpString.replace("à", "a")
    cmpString = cmpString.replace("é", "e")
    cmpString = cmpString.replace("ê", "e")
    cmpString = cmpString.replace("í", "i")
    cmpString = cmpString.replace("ó", "o")
    cmpString = cmpString.replace("õ", "o")
    cmpString = cmpString.replace("ô", "o")
    cmpString = cmpString.replace("ú", "u")
    cmpString = cmpString.replace("ç", "c")
    
    invertedString = cmpString[::-1]

    if cmpString == invertedString:
        print("SIM SIM SIM SIM")
    else:
        print("NAO NAO NAO NAO")