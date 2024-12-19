import 'package:flutter/material.dart';

class ListPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: 10, // Exemplo com 10 itens
      itemBuilder: (context, index) {
        return ListTile(
          leading: Icon(Icons.label),
          title: Text('Item ${index + 1}'),
          subtitle: Text('Item description ${index + 1}'),
        );
      },
    );
  }
}