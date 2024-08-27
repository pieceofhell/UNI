import 'package:flutter/material.dart';

class Hello extends StatelessWidget {
  const Hello({super.key});

  @override
  Widget build(BuildContext context) {
    return const Text("Hello World!");
  }
}

void main() {
  runApp(MaterialApp(
    title: "Atividade Login",
    home: Scaffold(
      appBar: AppBar(
        title: const Text("Tela Login"),
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
      bottomNavigationBar:
          BottomNavigationBar(items: const <BottomNavigationBarItem>[
        BottomNavigationBarItem(icon: Icon(Icons.home), label: "Home"),
        BottomNavigationBarItem(icon: Icon(Icons.person), label: "Login")
      ]),
      body: Align(
        alignment: Alignment.center,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Container(
              width: 150,
              child: const TextField(
                decoration: InputDecoration(
                  labelText: 'E-mail',
                ),
                style: TextStyle(
                  color: Colors.purple,
                  fontSize: 20,
                ),
              ),
            ),
            const SizedBox(height: 10),
            Container(
              width: 150,
              child: const TextField(
                decoration: InputDecoration(
                  labelText: 'Senha',
                ),
                style: TextStyle(
                  color: Colors.purple,
                  fontSize: 20,
                ),
              ),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: () {
                // Add sign in logic here
              },
              child: const Text('ENTRAR'),
            ),
            const SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const Text(
                  'Novo?',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 16,
                  ),
                ),
                TextButton(
                  onPressed: () {
                    // Add forgot password logic here
                  },
                  child: const Text('Cadastre-se'),
                ),
              ],
            ),
          ],
        ),
      ),
    ),
  ));
}
