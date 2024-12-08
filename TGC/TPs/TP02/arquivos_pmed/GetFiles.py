import requests
import os

# Diretório onde os arquivos serão salvos
output_dir = "arquivos_pmed"
os.makedirs(output_dir, exist_ok=True)

# URL base
base_url = "https://people.brunel.ac.uk/~mastjjb/jeb/orlib/files/"

# Loop para baixar os arquivos
for i in range(1, 41):
    file_name = f"pmed{i}.txt"
    file_url = base_url + file_name
    
    try:
        # Baixa o conteúdo do arquivo
        response = requests.get(file_url)
        response.raise_for_status()  # Levanta um erro se o download falhar
        
        # Salva o conteúdo no diretório
        with open(os.path.join(output_dir, file_name), 'w', encoding='utf-8') as file:
            file.write(response.text)
        
        print(f"{file_name} baixado com sucesso.")
    except requests.RequestException as e:
        print(f"Erro ao baixar {file_name}: {e}")
