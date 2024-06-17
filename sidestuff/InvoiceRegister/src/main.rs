use chrono::prelude::*;
use regex::Regex;
use std::error::Error;
use std::fs::{self, File};
use std::io::{self, BufRead, BufReader, BufWriter, Write};
use std::path::Path;

#[derive(Debug)]
struct InvoiceRegister {
    data: Option<DateTime<Utc>>,
    data_num: i64,
    data_string: String,
    metodo_pagamento: String,
    historico: String,
    valor: f32,
    is_uber: bool,
    is_comida: bool,
}

impl InvoiceRegister {
    fn is_uber(historico: &str) -> bool {
        historico.to_lowercase().contains("uber")
    }

    fn is_food(historico: &str) -> bool {
        let lower_case_historico = historico.to_lowercase();
        let food_keywords = [
            "pizzaria", "trigopane", "hamburguer", "sorvete", "acai", "subway",
            "batata", "mcdonalds", "burger", "burguer", "burgueria", "attelier", "sucos",
        ];
        food_keywords.iter().any(|&keyword| lower_case_historico.contains(keyword))
    }

    fn remove_double_spaces(str: &str) -> String {
        let re = Regex::new(r"\s+").unwrap();
        re.replace_all(str, " ").to_string()
    }

    fn remove_garbage(str: &str) -> String {
        let mut result = str.to_string();
        let replacements = [
            ("\\s+", " "), ("[éêè]", "e"), ("[óôò]", "o"), ("[áãâà]", "a"), ("[í]", "i"), ("[ú]", "u"), ("[ç]", "c"),
            ("[Ã]", ""), ("©", "é"), ("¢", "â"),
        ];
        for &(pattern, replacement) in &replacements {
            let re = Regex::new(pattern).unwrap();
            result = re.replace_all(&result, replacement).to_string();
        }
        if let Some(pos) = result.find('*') {
            result = result[pos + 1..].to_string();
        }
        result = result.replace("Belo Horizont Bra", "");
        if result.chars().next().map_or(false, |c| c.is_lowercase()) {
            let mut chars = result.chars();
            let first = chars.next().unwrap().to_uppercase().to_string();
            result = first + chars.as_str();
        }
        result
    }

    fn find_csv_files(folder: &Path) -> io::Result<Vec<String>> {
        let mut csv_files = Vec::new();
        if folder.is_dir() {
            for entry in fs::read_dir(folder)? {
                let entry = entry?;
                let path = entry.path();
                if path.is_file() && path.extension().map_or(false, |ext| ext == "csv") {
                    csv_files.push(path.to_string_lossy().to_string());
                }
            }
        }
        Ok(csv_files)
    }

    fn remove_dots_replace_comma(valor: &str) -> String {
        valor.replace(".", "").replace(',', '.')
    }

    fn replace_dot_to_comma(value: f32) -> String {
        format!("{:.2}", value).replace('.', ",")
    }

    fn date_to_long(date: DateTime<Utc>) -> i64 {
        let base_date = Utc.ymd(1900, 1, 1).and_hms(0, 0, 0);
        (date - base_date).num_days()
    }

    fn remove_negative(num: f32) -> f32 {
        if num < 0.0 {
            0.0
        } else {
            num
        }
    }

    fn remove_positive(num: f32) -> f32 {
        if num > 0.0 {
            0.0
        } else {
            num
        }
    }

    fn string_to_date(date: &str) -> Option<DateTime<Utc>> {
        Utc.datetime_from_str(date, "%d/%m/%Y").ok()
    }

    fn read(nome_arquivo: &str) -> Result<Vec<InvoiceRegister>, Box<dyn Error>> {
        let file = File::open(nome_arquivo)?;
        let reader = BufReader::new(file);
        let mut all_transactions = Vec::new();
        for (line_count, line) in reader.lines().enumerate() {
            let line = line?;
            if line_count <= 5 {
                continue;
            }
            let fields: Vec<&str> = line.split(';').collect();
            let data_string = fields.get(0).unwrap_or(&"null").to_string();
            let metodo_pagamento = Self::remove_garbage(fields.get(1).unwrap_or(&"null"));
            let historico = Self::remove_garbage(fields.get(2).unwrap_or(&"Poupanca"));
            let valor = fields
                .get(3)
                .map_or(-1.0, |v| Self::remove_dots_replace_comma(v).parse().unwrap_or(-1.0));

            let data = Self::string_to_date(&data_string);
            let data_num = data.map_or(0, |d| Self::date_to_long(d));
            let is_uber = Self::is_uber(&historico);
            let is_comida = Self::is_food(&historico);

            all_transactions.push(InvoiceRegister {
                data,
                data_num,
                data_string,
                metodo_pagamento,
                historico,
                valor,
                is_uber,
                is_comida,
            });
        }
        Ok(all_transactions)
    }

    fn write_to_csv(file_path: &str, transactions: &[InvoiceRegister]) -> io::Result<()> {
        let file = File::create(file_path)?;
        let mut writer = BufWriter::new(file);
        for transaction in transactions.iter().rev() {
            let negative_value = if Self::remove_negative(transaction.valor) == 0.0 {
                String::new()
            } else {
                Self::replace_dot_to_comma(Self::remove_negative(transaction.valor))
            };
            let positive_value = if Self::remove_positive(transaction.valor) == 0.0 {
                String::new()
            } else {
                Self::replace_dot_to_comma(Self::remove_positive(transaction.valor) * -1.0)
            };

            let line = if transaction.is_uber {
                format!(
                    "{};;{};{};;;;;;\n",
                    transaction.data_num,
                    Self::remove_garbage(&transaction.historico),
                    positive_value,
                )
            } else if transaction.is_comida {
                format!(
                    "{};;{};;;{};;;;\n",
                    transaction.data_num,
                    Self::remove_garbage(&transaction.historico),
                    positive_value,
                )
            } else {
                format!(
                    "{};;{};;;;;;;{}\n",
                    transaction.data_num,
                    Self::remove_garbage(&transaction.historico),
                    positive_value,
                )
            };

            writer.write_all(line.as_bytes())?;
        }
        writer.flush()
    }

    fn print(&self) {
        println!("{:?}", self);
    }
}

fn main() -> Result<(), Box<dyn Error>> {
    let mut input = String::new();
    println!("Enter the path to the CSV file:");
    io::stdin().read_line(&mut input)?;
    let input_path = input.trim();

    let transactions = InvoiceRegister::read(input_path)?;
    let count_registers = transactions.len();
    for transaction in &transactions {
        transaction.print();
    }

    println!("\nTotal de registros: {}", count_registers);
    println!("Digite o nome do arquivo de saída (com ou sem extensao):");

    input.clear();
    io::stdin().read_line(&mut input)?;
    let mut output_path = input.trim().to_string();

    if !output_path.ends_with(".csv") {
        output_path.push_str(".csv");
    }

    InvoiceRegister::write_to_csv(&output_path, &transactions)?;

    Ok(())
}
