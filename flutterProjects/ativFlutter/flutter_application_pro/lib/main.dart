import 'package:flutter/material.dart';

class RegistrationPage extends StatefulWidget {
  @override
  _RegistrationPageState createState() => _RegistrationPageState();
}

class _RegistrationPageState extends State<RegistrationPage> {
  // Controllers for TextFields
  final TextEditingController nomeController = TextEditingController();
  final TextEditingController dataNascimentoController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  final TextEditingController senhaController = TextEditingController();

  // State for password visibility
  bool _obscureText = true;

  // State for gender selection
  int? _genderValue;

  // State for notification switches
  bool _emailNotifications = false;
  bool _smsNotifications = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Create an account"),
        backgroundColor: Colors.blue,
      ),
      drawer: Drawer(
        child: ListView(
          children: <Widget>[
            const DrawerHeader(
              child: Text("Menu"),
            ),
            ListTile(
              title: const Text("Item 1"),
              onTap: () {
                print("Item 1");
              },
            ),
            ListTile(
              title: const Text("Item 2"),
              onTap: () {
                print("Item 2");
              },
            ),
            ListTile(
              title: const Text("Item 3"),
              onTap: () {
                print("Item 3");
              },
            ),
          ],
        ),
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(icon: Icon(Icons.home), label: "Home"),
          BottomNavigationBarItem(icon: Icon(Icons.person), label: "Login"),
          BottomNavigationBarItem(icon: Icon(Icons.settings), label: "Settings"),
        ],
      ),
      body: Align(
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: 300,
              child: TextField(
                maxLength: 50,
                decoration: InputDecoration(
                  labelText: 'Nome',
                ),
                style: TextStyle(
                  color: Colors.purple,
                  fontSize: 20,
                ),
                controller: nomeController,
              ),
            ),
            const SizedBox(height: 10),
            Container(
              width: 300,
              child: TextField(
                maxLength: 10,
                decoration: InputDecoration(
                  labelText: 'Data de Nascimento',
                ),
                style: TextStyle(
                  color: Colors.purple,
                  fontSize: 20,
                ),
                controller: dataNascimentoController,
              ),
            ),
            Container(
              width: 300,
              child: TextField(
                decoration: InputDecoration(
                  labelText: 'E-mail',
                ),
                style: TextStyle(
                  color: Colors.purple,
                  fontSize: 20,
                ),
                controller: emailController,
              ),
            ),
            const SizedBox(height: 20),
            Container(
              width: 300,
              child: Stack(
                alignment: Alignment.centerRight,
                children: [
                  TextField(
                    maxLength: 20,
                    keyboardType: TextInputType.visiblePassword,
                    obscureText: _obscureText,
                    decoration: InputDecoration(
                      labelText: 'Senha',
                    ),
                    style: TextStyle(
                      color: Colors.purple,
                      fontSize: 20,
                    ),
                    controller: senhaController,
                  ),
                  IconButton(
                    onPressed: () {
                      setState(() {
                        _obscureText = !_obscureText;
                      });
                    },
                    icon: Icon(
                      _obscureText ? Icons.visibility : Icons.visibility_off,
                    ),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Text(
                  'Gênero: ',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 16,
                  ),
                ),
                Row(
                  mainAxisSize: MainAxisSize.min,
                  children: [
                    Text(
                      'Masculino',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 16,
                      ),
                    ),
                    Radio<int>(
                      value: 1,
                      groupValue: _genderValue,
                      onChanged: (int? value) {
                        setState(() {
                          _genderValue = value;
                        });
                      },
                    ),
                    Text(
                      'Feminino',
                      style: TextStyle(
                        color: Colors.black,
                        fontSize: 16,
                      ),
                    ),
                    Radio<int>(
                      value: 2,
                      groupValue: _genderValue,
                      onChanged: (int? value) {
                        setState(() {
                          _genderValue = value;
                        });
                      },
                    ),
                  ],
                ),
              ],
            ),
            const SizedBox(height: 20),
            const SizedBox(height: 20),
            Container(
              child: Text(
                "Notificações:",
                style: TextStyle(color: Colors.black, fontSize: 16),
              ),
            ),
            Container(
              width: 300,
              child: Column(
                children: [
                  SwitchListTile(
                    title: const Text('E-mail'),
                    value: _emailNotifications,
                    onChanged: (bool value) {
                      setState(() {
                        _emailNotifications = value;
                      });
                    },
                  ),
                  SwitchListTile(
                    title: const Text('Celular'),
                    value: _smsNotifications,
                    onChanged: (bool value) {
                      setState(() {
                        _smsNotifications = value;
                      });
                    },
                  ),
                ],
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // Implement your logic here
              },
              child: const Text('CADASTRAR'),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  'Já possui login?',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 16,
                  ),
                ),
                TextButton(
                  onPressed: () {
                    Navigator.pop(context); // Go back to the previous page
                  },
                  child: const Text('Entrar'),
                ),
              ],
            ),
            const SizedBox(height: 20),
          ],
        ),
      ),
    );
  }

  @override
  void dispose() {
    // Dispose the controllers when the widget is removed from the widget tree
    nomeController.dispose();
    dataNascimentoController.dispose();
    emailController.dispose();
    senhaController.dispose();
    super.dispose();
  }
}
