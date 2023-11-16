import java.util.Scanner;

public class MatrizFlexivel {
    static Scanner sc = new Scanner(System.in);

    public class Cell{
        Cell left;
        Cell right;
        Cell up;
        Cell down;
        int value;
    
        Cell(){
            left = null;
            right = null;
            up = null;
            down = null;
            value = 0;
        }
    }
    public class Matriz{
        Cell first;
        int lines;
        int columns;
        
        Matriz(int l, int c){
            lines = l;
            columns = c;
            Cell[][] mat = new Cell[l][c];
            for(int i = 0; i < l; i++){
                for(int j = 0; j < c; j++){
                    mat[i][j] = new Cell();
                }
            }

            for(int i = 0; i < l; i++){
                for(int j = 0; j < c; j++){
                    if(j!=c-1){
                        mat[i][j].right = mat[i][j+1];
                    }
                    if(j!=0){
                        mat[i][j].left = mat[i][j-1];
                    }
                    if(i!=0){
                        mat[i][j].up = mat[i-1][j];
                    }
                    if(i!=l-1){
                        mat[i][j].down = mat[i+1][j];
                    }
                }
            }

            first = mat[0][0];
        }

        public void readLines(){
            Cell aux = first;
            Cell aux2 = first;
            for(int i=0;i<lines;i++){
                aux2 = aux;
                for(int j=0;j<columns;j++){
                    aux2.value = sc.nextInt();
                    aux2 = aux2.right;
                }
                aux = aux.down;
            }
        }

        public Matriz soma(Matriz mat){
            if(lines!=mat.lines || columns!=mat.columns){
                return null;
            }

            Matriz result = new Matriz(lines, columns);
            Cell aux11 = first;
            Cell aux12 = first;
            Cell aux21 = mat.first;
            Cell aux22 = mat.first;
            Cell aux31 = result.first;
            Cell aux32 = result.first;

            for(int i=0;i<lines;i++){
                aux12 = aux11;
                aux22 = aux21;
                aux32 = aux31;
                for(int j=0;j<columns;j++){
                    aux32.value = aux12.value + aux22.value;
                    aux12 = aux12.right;
                    aux22 = aux22.right;
                    aux32 = aux32.right;
                }
                aux11 = aux11.down;
                aux21 = aux21.down;
                aux31 = aux31.down;
            }

            return result;
        }

        public Matriz multiplicacao(Matriz mat){
            if(columns!=mat.lines){
                return null;
            }

            Matriz res = new Matriz(lines, mat.columns);

            Cell aux11 = first;
            Cell aux12 = first;
            Cell aux21 = mat.first;
            Cell aux22 = mat.first;
            Cell aux31 = res.first;
            Cell aux32 = res.first;

            for(int i=0;i<lines;i++){
                aux11 = first;
                aux32 = aux31;
                for(int j=0;j<mat.columns;j++){
                    int soma = 0;
                    aux12 = aux11;
                    aux22 = aux21;
                    for(int k=0;k<lines;k++){
                        soma+=aux12.value*aux22.value;
                        aux12 = aux12.down;
                        aux22 = aux22.right;
                    }
                    
                    aux32.value = soma;
                    aux11 = aux11.right;
                    aux32 = aux32.right;
                }
                aux21 = aux21.down;
                aux31 = aux31.down;
            }

            return res;
        }

        void mostrarDiagonalPrincipal(){
            Cell aux = first;
            for(int i=0;i<lines;i++){
                System.out.print(aux.value + " ");
                if(i!=lines-1){
                    aux = aux.down;
                    aux = aux.right;
                }
            }
            System.out.println();
        }

        void mostrarDiagonalSecundaria(){
            Cell aux = first;
            for(int i=0;i<lines-1;i++){
                aux = aux.right;
            }

            for(int i=0;i<lines;i++){
                System.out.print(aux.value + " ");
                if(i!=lines-1){
                    aux = aux.down;
                    aux = aux.left;
                }
            }
            System.out.println();
        }


        public void print(){
            Cell aux = first;
            while(aux!=null){
                Cell aux2 = aux;
                while(aux2!=null){
                    System.out.print(aux2.value + " ");
                    aux2 = aux2.right;
                }
                System.out.println();
                aux = aux.down;
            }
        }
    }

    public static void main(String[] args) {
        MatrizFlexivel mf = new MatrizFlexivel();
        MatrizFlexivel.Matriz m1;
        MatrizFlexivel.Matriz m2;

        int qtd = sc.nextInt();
        for(int i=0;i<qtd;i++){
            m1 = mf.new Matriz(sc.nextInt(), sc.nextInt());
            m1.readLines();
            m2 = mf.new Matriz(sc.nextInt(), sc.nextInt());
            m2.readLines();

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();
            m1.soma(m2).print();
            m2.multiplicacao(m1).print();
        }

        sc.close();
    }
}
