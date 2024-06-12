import java.util.ArrayList;
import java.util.List;

public class Pessoa {

  private String nome;
  private String endereco;
  private String telefone;
  private String cep;
  private String cidade;
  private String uf;

  public Pessoa(
    String nome,
    String endereco,
    String telefone,
    String cep,
    String cidade,
    String uf
  ) {
    this.nome = nome;
    this.endereco = endereco;
    this.telefone = telefone;
    this.cep = cep;
    this.cidade = cidade;
    this.uf = uf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }
}

class PessoaFisica extends Pessoa {

  public PessoaFisica(
    String nome,
    String endereco,
    String telefone,
    String cep,
    String cidade,
    String uf
  ) {
    super(nome, endereco, telefone, cep, cidade, uf);
  }
}

class PessoaJuridica extends Pessoa {

  public PessoaJuridica(
    String nome,
    String endereco,
    String telefone,
    String cep,
    String cidade,
    String uf
  ) {
    super(nome, endereco, telefone, cep, cidade, uf);
  }
}

class Funcionario extends PessoaFisica {

  private String cargo;
  private double salario;

  public Funcionario(
    String nome,
    String endereco,
    String telefone,
    String cep,
    String cidade,
    String uf,
    String cargo,
    double salario
  ) {
    super(nome, endereco, telefone, cep, cidade, uf);
    this.cargo = cargo;
    this.salario = salario;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  public double getSalario() {
    return salario;
  }

  public void setSalario(double salario) {
    this.salario = salario;
  }
}

class Cliente extends Pessoa {

  private double limiteCredito;

  public Cliente(
    String nome,
    String endereco,
    String telefone,
    String cep,
    String cidade,
    String uf,
    double limiteCredito
  ) {
    super(nome, endereco, telefone, cep, cidade, uf);
    this.limiteCredito = limiteCredito;
  }

  public double getLimiteCredito() {
    return limiteCredito;
  }

  public void setLimiteCredito(double limiteCredito) {
    this.limiteCredito = limiteCredito;
  }
}

class Empresa {

  private List<Cliente> clientes;
  private List<Funcionario> funcionarios;
  private Funcionario presidente;

  public Empresa() {
    clientes = new ArrayList<>();
    funcionarios = new ArrayList<>();
    presidente = null;
  }

  public Funcionario getPresidente() {
    return presidente;
  }

  public void setPresidente(Funcionario presidente) {
    this.presidente = presidente;
  }

  public void addCliente(Cliente cliente) {
    clientes.add(cliente);
  }

  public void addFuncionario(Funcionario funcionario) {
    funcionarios.add(funcionario);
  }

  public List<Cliente> getClientes() {
    return clientes;
  }

  public List<Funcionario> getFuncionarios() {
    return funcionarios;
  }
}
