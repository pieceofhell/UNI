import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ListaSequencial{
    public class Jogador{
        private int id;
        private String nome;
        private int altura;
        private int peso;
        private String universidade;
        private int anoNascimento;
        private String cidadeNascimento;
        private String estadoNascimento;
        static String[] allStrings;
        
        
        public Jogador(int nId, String nNome, int nAltura, int nPeso, String nUniversidade, int nAnoNascimento, String nCidadeNascimento, String nEstadoNascimento){
            this.id = nId;
            this.nome = nNome;
            this.altura = nAltura;
            this.peso = nPeso;
            this.universidade = nUniversidade;
            this.anoNascimento = nAnoNascimento;
            this.cidadeNascimento = nCidadeNascimento;
            this.estadoNascimento = nEstadoNascimento;
        }

        public Jogador(){
            this.id = -1;
            this.nome = "";
            this.altura = -1;
            this.peso = -1;
            this.universidade = "";
            this.anoNascimento = -1;
            this.cidadeNascimento = "";
            this.estadoNascimento = "";
        }

        public Jogador(int id){
            this();
            this.ler(id);
        }

        public String getNome(){
            return this.nome;
        }
        public void setNome(String nNome){
            this.nome = nNome;
        }

        public int getId(){
            return this.id;
        }
        public void setId(int nId){
            this.id = nId;
        }

        public int getAltura(){
            return this.altura;
        }
        public void setAltura(int nAltura){
            this.altura = nAltura;
        }

        public int getPeso(){
            return this.peso;
        }
        public void setPeso(int nPeso){
            this.peso = nPeso;
        }

        public String getUniversidade(){
            return this.universidade;
        }
        public void setUniversidade(String nUniversidade){
            this.universidade = nUniversidade;
        }

        public int getAnoNascimento(){
            return this.anoNascimento;
        }
        public void setAnoNascimento(int nAnoNascimento){
            this.anoNascimento = nAnoNascimento;
        }

        public String getCidadeNascimento(){
            return this.cidadeNascimento;
        }
        public void setCidadeNascimento(String nCidadeNascimento){
            this.cidadeNascimento = nCidadeNascimento;
        }

        public String getEstadoNascimento(){
            return this.estadoNascimento;
        }
        public void setEstadoNascimento(String nEstadoNascimento){
            this.estadoNascimento = nEstadoNascimento;
        }

        public Jogador clone(){
            Jogador res = new Jogador(this.id, this.nome, this.altura, this.peso, this.universidade, this.anoNascimento, this.cidadeNascimento, this.estadoNascimento);
            return res;
        }

        public void imprimir(){
            System.out.println("[" + this.id + " ## "+ this.nome + " ## " + this.altura + " ## " + this.peso + " ## " + this.anoNascimento + " ## " + this.universidade + " ## " + this.cidadeNascimento + " ## " + this.estadoNascimento + "]");
        }


        public static void getAllStrings(String nomeArq){
            try{
                RandomAccessFile raf = new RandomAccessFile(nomeArq, "r");
                String[] stringList = new String[10000];
                int i = 0;
                while(raf.getFilePointer() < raf.length()){
                    stringList[i] = raf.readLine();
                    i++;
                }
                allStrings = stringList;
                raf.close();
            }catch (IOException e){
                System.out.println("Erro ao abrir o arquivo");
            }
        }

        public void ler(int id){
            id += 1;
            if(allStrings == null){
                getAllStrings("/tmp/players.csv");
            }
            String[] propriedades = new String[8];

            String str = allStrings[id];
            int j = 0;
            propriedades[0] = "";
            for(int i=0;i<str.length();i++){
                if(str.charAt(i) == ','){
                    if(propriedades[j] == ""){
                        propriedades[j] = "nao informado";
                    }
                    j++;
                    propriedades[j] = "";
                }else{
                    propriedades[j] += str.charAt(i);
                }
            }

            if(propriedades[7] == ""){
                propriedades[7] = "nao informado";
            }

            this.id = Integer.parseInt(propriedades[0]);
            this.nome = propriedades[1];
            this.altura = Integer.parseInt(propriedades[2]);
            this.peso = Integer.parseInt(propriedades[3]);
            this.universidade = propriedades[4];
            this.anoNascimento = Integer.parseInt(propriedades[5]);
            this.cidadeNascimento = propriedades[6];
            this.estadoNascimento = propriedades[7];
        }
    }

    public class Lista{
        Jogador[] jogadores;
        int n;
        int total;

        public Lista(int total){
            this.jogadores = new Jogador[total];
            this.n = 0;
            this.total = total;
        }

        public void inserirInicio(Jogador jogador){
            if(n>=total){
                System.out.println("Erro");
                return;
            }
            this.n++;

            for(int i=n-1;i>=1;i--){
                this.jogadores[i] = this.jogadores[i-1];
            }
            this.jogadores[0] = jogador;
        }

        public void inserirFim(Jogador jogador){
            if(n>=total){
                System.out.println("Erro");
                return;
            }
            jogadores[n] = jogador;
            n++;
        }

        public void inserir(Jogador jogador, int pos){
            if(pos>n || pos<0 || n>=total){
                System.out.println("Erro");
                return;
            }
            for(int i=n-1;i>=pos;i--){
                jogadores[i+1] = jogadores[i];
            }
            jogadores[pos] = jogador;
            n++;
        }

        public Jogador removerInicio(){
            if(n==0){
                System.out.println("Erro");
                return null;
            }

            Jogador res = jogadores[0];

            for(int i=0;i<n-1;i++){
                jogadores[i] = jogadores[i+1];
            }
            n--;
            return res;
        }

        public Jogador removerFim(){
            if(n==0){
                System.out.println("Erro");
                return null;
            }
            n--;
            return jogadores[n];
        }

        public Jogador remover(int pos){
            if(pos>=n || pos<0 || n==0){
                System.out.println("Erro");
                return null;
            }

            Jogador res = jogadores[pos];
            for(int i=pos;i<n-1;i++){
                jogadores[i] = jogadores[i+1];
            }

            n--;
            return res;
        }

        public void print(){
            for(int i=0;i<n;i++){
                System.out.print("[" + i + "] ## " + jogadores[i].getNome() + " ## " + jogadores[i].getAltura() + " ## " + jogadores[i].getPeso() + " ## " + jogadores[i].getAnoNascimento() + " ## " + jogadores[i].getUniversidade() + " ## " + jogadores[i].getCidadeNascimento() + " ## " + jogadores[i].getEstadoNascimento() + " ##\n");
            }
        }

        public void command(String str){
            if(str.contains("II")){
                String[] sides = str.split(" ");
                int id = Integer.parseInt(sides[1]);
                Jogador j = new Jogador(id);
                inserirInicio(j);
            }else if(str.contains("IF")){
                String[] sides = str.split(" ");
                int id = Integer.parseInt(sides[1]);
                Jogador j = new Jogador(id);
                inserirFim(j);
            }else if(str.contains("I*")){
                String[] sides = str.split(" ");
                int pos = Integer.parseInt(sides[1]);
                int id = Integer.parseInt(sides[2]);
                Jogador j = new Jogador(id);
                inserir(j, pos);
            }else if(str.contains("RI")){
                Jogador j = removerInicio();
                System.out.println("(R) " + j.getNome());
            }else if(str.contains("RF")){
                Jogador j = removerFim();
                System.out.println("(R) " + j.getNome());
            }else if(str.contains("R*")){
                String[] sides = str.split(" ");
                int pos = Integer.parseInt(sides[1]);
                Jogador j = remover(pos);
                System.out.println("(R) " + j.getNome());
            }else{
                int id = Integer.parseInt(str);
                Jogador j = new Jogador(id);
                inserirFim(j);
            }
        }
    }

    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ListaSequencial ls = new ListaSequencial();
        Lista lista = ls.new Lista(10000);
        String in = sc.nextLine();
        while(!in.equals("FIM")){
            lista.command(in);
            in = sc.nextLine();
        }
        int qtdCom = Integer.parseInt(sc.nextLine());
        for(int i=0;i<qtdCom;i++){
            String str = sc.nextLine();
            lista.command(str);
        }

        lista.print();

        sc.close();
    }
}