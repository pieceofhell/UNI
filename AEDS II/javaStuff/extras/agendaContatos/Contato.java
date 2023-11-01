public class Contato {
    String nome;
    String telefone;
    String email;
    String cpf;

    Contato(){
        this.nome = "";
        this.telefone = "";
        this.email = "";
        this.cpf = "";
    }

    Contato(String nome, String telefone, String email, String cpf){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
}
