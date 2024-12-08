import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ConsultaCEP(),
    );
  }
}

class ConsultaCEP extends StatefulWidget {
  @override
  _ConsultaCEPState createState() => _ConsultaCEPState();
}

class _ConsultaCEPState extends State<ConsultaCEP> {
  final TextEditingController _controllerCep = TextEditingController();
  String _resultado = "";

  Future<void> _recuperaCep() async {
    String cepDigitado = _controllerCep.text.trim();
    if (cepDigitado.isEmpty) {
      setState(() {
        _resultado = "Por favor, insira um CEP.";
      });
      return;
    }

    try {
      var uri = Uri.parse("https://viacep.com.br/ws/$cepDigitado/json/");
      http.Response response = await http.get(uri);

      if (response.statusCode == 200) {
        Map<String, dynamic> retorno = json.decode(response.body);
        String logradouro = retorno["logradouro"] ?? "";
        String complemento = retorno["complemento"] ?? "";
        String bairro = retorno["bairro"] ?? "";
        String localidade = retorno["localidade"] ?? "";

        setState(() {
          _resultado =
              "$logradouro, $complemento, $bairro, $localidade";
        });
      } else {
        setState(() {
          _resultado = "CEP não encontrado.";
        });
      }
    } catch (e) {
      setState(() {
        _resultado = "Erro ao buscar o CEP: $e";
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          "Consulta de CEP",
          style: TextStyle(fontFamily: 'Comic Sans MS'),
        ),
        backgroundColor: Colors.purpleAccent,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.cyan, Colors.pinkAccent],
            begin: Alignment.topLeft,
            end: Alignment.bottomRight,
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            children: [
              TextField(
                controller: _controllerCep,
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                  labelText: "Digite o CEP (ex: 30360190)",
                  labelStyle: TextStyle(fontFamily: 'Comic Sans MS'),
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10.0),
                    borderSide: BorderSide(color: Colors.purple, width: 2),
                  ),
                  filled: true,
                  fillColor: Colors.white,
                ),
                style: TextStyle(fontSize: 18),
              ),
              SizedBox(height: 16),
              ElevatedButton(
                onPressed: _recuperaCep,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.orange,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(15),
                  ),
                  padding: EdgeInsets.symmetric(horizontal: 32, vertical: 16),
                ),
                child: Text(
                  "Consultar",
                  style: TextStyle(
                    fontFamily: 'Comic Sans MS',
                    fontSize: 20,
                  ),
                ),
              ),
              SizedBox(height: 24),
              Container(
                decoration: BoxDecoration(
                  color: Colors.yellowAccent,
                  border: Border.all(color: Colors.red, width: 3),
                  borderRadius: BorderRadius.circular(10),
                ),
                padding: EdgeInsets.all(16),
                child: Text(
                  _resultado,
                  textAlign: TextAlign.center,
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                    fontFamily: 'Comic Sans MS',
                    color: Colors.black,
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
