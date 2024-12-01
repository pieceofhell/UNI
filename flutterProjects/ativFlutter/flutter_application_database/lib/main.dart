import 'package:flutter/material.dart';
import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

void main() {
  runApp(const App());
}

class App extends StatelessWidget {
  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      home: Home(),
    );
  }
}

class Home extends StatefulWidget {
  const Home({super.key});

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  // Método para recuperar ou abrir o banco de dados
  _recuperarBD() async {
    final caminho = await getDatabasesPath();
    final local = join(caminho, "bancodados.db");

    var retorno = await openDatabase(
      local,
      version: 1,
      onCreate: (db, dbVersaoRecente) {
        String sql = """
          CREATE TABLE alunos (
            matricula TEXT PRIMARY KEY,
            nome VARCHAR,
            idade INTEGER,
            curso VARCHAR
          )
        """;
        db.execute(sql);
      },
    );

    print("Banco aberto: ${retorno.isOpen}");
    return retorno;
  }

  // Método para inserir um novo aluno
  _salvarDados(BuildContext context, String matricula, String nome, int idade,
      String curso) async {
    Database db = await _recuperarBD();

    Map<String, dynamic> dadosAluno = {
      "matricula": matricula,
      "nome": nome,
      "idade": idade,
      "curso": curso,
    };

    try {
      await db.insert("alunos", dadosAluno);
      _mostrarDialogo(context, "Aluno salvo com sucesso!");
    } catch (e) {
      _mostrarDialogo(context, "Erro ao salvar: Matrícula já cadastrada.");
    }
  }

  // Método para listar todos os alunos
  _listarAlunos() async {
    Database db = await _recuperarBD();
    List alunos = await db.query("alunos");

    for (var aluno in alunos) {
      print(
          "Matrícula: ${aluno['matricula']}, Nome: ${aluno['nome']}, Idade: ${aluno['idade']}, Curso: ${aluno['curso']}");
    }
  }

  // Método para listar um aluno pela matrícula
  _listarUmAluno(BuildContext context, String matricula) async {
    Database db = await _recuperarBD();
    List alunos = await db.query(
      "alunos",
      where: "matricula = ?",
      whereArgs: [matricula],
    );

    if (alunos.isNotEmpty) {
      var aluno = alunos.first;
      _mostrarDialogo(
        context,
        "Matrícula: ${aluno['matricula']}\n"
        "Nome: ${aluno['nome']}\n"
        "Idade: ${aluno['idade']}\n"
        "Curso: ${aluno['curso']}",
      );
    } else {
      _mostrarDialogo(
          context, "Aluno com matrícula $matricula não encontrado.");
    }
  }

  // Método para excluir um aluno pela matrícula
  _excluirAluno(BuildContext context, String matricula) async {
    Database db = await _recuperarBD();
    int retorno = await db.delete(
      "alunos",
      where: "matricula = ?",
      whereArgs: [matricula],
    );

    _mostrarDialogo(
        context,
        retorno > 0
            ? "Aluno excluído com sucesso."
            : "Nenhum aluno encontrado com essa matrícula.");
  }

  // Método para atualizar um aluno pela matrícula
  _atualizarAluno(BuildContext context, String matricula, String? nome,
      int? idade, String? curso) async {
    Database db = await _recuperarBD();

    Map<String, dynamic> dadosAtualizados = {};
    if (nome != null && nome.isNotEmpty) dadosAtualizados["nome"] = nome;
    if (idade != null) dadosAtualizados["idade"] = idade;
    if (curso != null && curso.isNotEmpty) dadosAtualizados["curso"] = curso;

    if (dadosAtualizados.isNotEmpty) {
      int retorno = await db.update(
        "alunos",
        dadosAtualizados,
        where: "matricula = ?",
        whereArgs: [matricula],
      );

      _mostrarDialogo(
          context,
          retorno > 0
              ? "Aluno atualizado com sucesso."
              : "Nenhum aluno encontrado com essa matrícula.");
    } else {
      _mostrarDialogo(context, "Nenhuma informação para atualizar.");
    }
  }

  // Método para exibir diálogos
  _mostrarDialogo(BuildContext context, String mensagem) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text("Resultado"),
          content: Text(mensagem),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              child: const Text("OK"),
            ),
          ],
        );
      },
    );
  }

  final TextEditingController _matriculaController = TextEditingController();
  final TextEditingController _nomeController = TextEditingController();
  final TextEditingController _idadeController = TextEditingController();
  final TextEditingController _cursoController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        alignment: Alignment.center,
        child: SingleChildScrollView(
          child: Column(
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              _buildTextField(_matriculaController, "Digite a matrícula:"),
              _buildTextField(_nomeController, "Digite o nome:"),
              _buildTextField(_idadeController, "Digite a idade:",
                  isNumber: true),
              _buildTextField(_cursoController, "Digite o curso:"),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  _salvarDados(
                    context,
                    _matriculaController.text,
                    _nomeController.text,
                    int.tryParse(_idadeController.text) ?? 0,
                    _cursoController.text,
                  );
                },
                child: const Text("Salvar aluno"),
              ),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: _listarAlunos,
                child: const Text("Listar todos alunos"),
              ),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  _listarUmAluno(context, _matriculaController.text);
                },
                child: const Text("Listar um aluno"),
              ),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  _excluirAluno(context, _matriculaController.text);
                },
                child: const Text("Excluir aluno"),
              ),
              const SizedBox(height: 10),
              ElevatedButton(
                onPressed: () {
                  _atualizarAluno(
                    context,
                    _matriculaController.text,
                    _nomeController.text.isNotEmpty
                        ? _nomeController.text
                        : null,
                    int.tryParse(_idadeController.text),
                    _cursoController.text.isNotEmpty
                        ? _cursoController.text
                        : null,
                  );
                },
                child: const Text("Atualizar aluno"),
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildTextField(TextEditingController controller, String label,
      {bool isNumber = false}) {
    return Container(
      margin: const EdgeInsets.all(5),
      width: 300,
      child: TextField(
        controller: controller,
        decoration: InputDecoration(label: Text(label)),
        keyboardType: isNumber ? TextInputType.number : TextInputType.text,
      ),
    );
  }
}
