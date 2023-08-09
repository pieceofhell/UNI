// Armazenamento das informações de usuários no localStorage
if (!localStorage.usuarios) {
  localStorage.setItem(
    "usuarios",
    JSON.stringify({
      clientes: [],
      prestadores: [],
    })
  );
}

// Função para cadastrar um usuário
function cadastrar() {
  var nome = document.getElementById("nome").value;
  var email = document.getElementById("email").value;
  var senha = document.getElementById("senha").value;
  var tipo = document.getElementById("tipo").value;

  var usuario = {
    nome: nome,
    email: email,
    senha: senha,
  };

  var usuarios = JSON.parse(localStorage.getItem("usuarios"));
  if (tipo == "cliente") {
    usuarios.clientes.push(usuario);
  } else {
    usuarios.prestadores.push(usuario);
  }

  localStorage.setItem("usuarios", JSON.stringify(usuarios));
  alert("Usuário cadastrado com sucesso!");
}

// Função para realizar o login
function login() {
  var email = document.getElementById("loginEmail").value;
  var senha = document.getElementById("loginSenha").value;

  var usuarios = JSON.parse(localStorage.getItem("usuarios"));
  var usuarioEncontrado = null;

  // Verificação do usuário cadastrado
  for (var i = 0; i < usuarios.clientes.length; i++) {
    if (
      usuarios.clientes[i].email == email &&
      usuarios.clientes[i].senha == senha
    ) {
      usuarioEncontrado = usuarios.clientes[i];
      break;
    }
  }

  if (!usuarioEncontrado) {
    for (var i = 0; i < usuarios.prestadores.length; i++) {
      if (
        usuarios.prestadores[i].email == email &&
        usuarios.prestadores[i].senha == senha
      ) {
        usuarioEncontrado = usuarios.prestadores[i];
        break;
      }
    }
  }

  if (usuarioEncontrado) {
    alert("Bem-vindo, " + usuarioEncontrado.nome + "!");
  } else {
    alert("Usuário não encontrado ou senha incorreta!");
  }
}
