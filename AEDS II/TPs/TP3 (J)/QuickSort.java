import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class QuickSort{
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

    public class Cell{
        Jogador jogador;
        Cell prev;
        Cell next;
        
        Cell(){
            this.jogador = null;
            this.prev = null;
            this.next = null;
        }
        Cell(Jogador jogador){
            this.jogador = jogador;
            this.prev = null;
            this.next = null;
        }
    }

    public class Lista{
        private Cell head;
        private Cell tail;
        private int length = 0;

        public Lista(){
            this.head = null;
            this.tail = null;
            length = 0;
        }

        public void inserirInicio(Jogador jogador){
            Cell tmp = new Cell(jogador);
            if(this.head == null){
                this.tail = tmp;
            }
            tmp.next = this.head;
            this.head.prev = tmp;
            this.head = tmp;
            length++;
        }

        public void inserirFim(Jogador jogador){
            Cell tmp = new Cell(jogador);
            if(this.head == null){
                this.head = tmp;
            }else{
                this.tail.next = tmp;
            }
            tmp.prev = this.tail;
            this.tail = tmp;
            length++;
        }

        public void inserir(Jogador jogador, int pos){
            Cell aux = this.head;
            if(pos == 0){
                inserirInicio(jogador);
                return;
            }else if(pos == length){
                inserirFim(jogador);
                return;
            }else if(pos > length){
                System.out.println("Erro");
                return;
            }
            for(int i=0;i<pos;i++){
                aux = aux.next;
                if(aux == null){
                    System.out.println("Erro");
                    return;
                }
            }

            length++;

            Cell tmp = new Cell(jogador);
            tmp.next = aux.next;
            aux.next = tmp;
            tmp.next.prev = tmp;
            tmp.prev = aux;
        }

        public Jogador removerInicio(){
            if(this.head == null){
                System.out.println("Erro");
                return null;
            }

            Jogador res = this.head.jogador;
            this.head.next.prev = null;
            this.head = this.head.next;

            length--;

            return res;
        }

        public Jogador removerFim(){
            if(this.tail==null){
                System.out.println("Erro");
                return null;
            }
            Jogador res = this.tail.jogador;
            this.tail.prev.next = null;
            this.tail = this.tail.prev;

            length--;
            
            return res;
        }

        public Jogador remover(int pos){
            if(pos==0){
                removerInicio();
            }else if(pos==length-1){
                removerFim();
            }else if(pos>=length){
                System.out.println("Erro");
                return null;
            }

            Jogador res;
            Cell aux = this.head;

            for(int i=0;i<pos;i++){
                aux = aux.next;
            }
            res = aux.jogador;
            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;

            return res;
        }

        public void print(){
            Cell aux = this.head;
            while(aux != null){
                aux.jogador.imprimir();
                aux = aux.next;
            }
        }

        public void print(Cell i, Cell j){
            System.out.println(i.jogador.id + " -> " + j.jogador.id);
            for(Cell aux = i; aux!=j.next; aux=aux.next){
                System.out.print(aux.jogador.id + " ");
            }
            System.out.println();
            System.out.println("All: ");
            for(Cell aux = this.head; aux!=null; aux = aux.next){
                System.out.print(aux.jogador.id + " ");
            }
            System.out.println();
            System.out.println();
            System.out.println();
        }

        public void command(String str){
            int id = Integer.parseInt(str);
            Jogador j = new Jogador(id);
            inserirFim(j);
        }

        public void swap(Cell i, Cell j){
            Jogador aux = i.jogador;

            i.jogador = j.jogador;
            j.jogador = aux;
        }

        public static int cmpStrings(String str1, String str2){
            int i = 0;
            while(i<str1.length() && i<str2.length()){
                if(str1.charAt(i) > str2.charAt(i)){
                    return 1;
                }else if(str1.charAt(i) < str2.charAt(i)){
                    return -1;
                }
                i++;
            }
            if(str1.length() < str2.length()){
                return -1;
            }else if(str1.length() > str2.length()){
                return 1;
            }else{
                return 0;
            }
        }   

        public int cmpJogadores(Jogador i, Jogador j){
            int res = cmpStrings(i.estadoNascimento, j.estadoNascimento);
            if(res != 0) return res;

            return cmpStrings(i.nome, j.nome);
        }

        public void quicksort(Cell start, Cell end){
            Cell i = start;
            Cell j = end;
            Jogador pivot = end.jogador;

            while(j!=i.prev && j!=i.prev.prev){
                while(cmpJogadores(i.jogador, pivot) == -1){
                    i = i.next;
                }
                while(cmpJogadores(pivot, j.jogador) == -1){
                    j = j.prev;
                }

                if(j!=i.prev){
                    swap(i, j);
                    i = i.next;
                    j = j.prev;
                }
            }

            if(start != j && j != start.prev){
                quicksort(start, j);
            }
            if(end != i && i != end.next){
                quicksort(i, end);
            }
        }

        public void quicksort(){
            this.head.prev = new Cell();
            this.head.prev.next = head;
            this.tail.next = new Cell();
            this.tail.next.prev = tail;
            quicksort(this.head, this.tail);
            this.head.prev = null;
            this.tail.next = null;
        }
    }


    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        QuickSort qs = new QuickSort();
        Lista lista = qs.new Lista();
        String in = sc.nextLine();
        while(!in.equals("FIM")){
            lista.command(in);
            in = sc.nextLine();
        }

        
        lista.quicksort();
        
        lista.print();

        sc.close();
    }
}