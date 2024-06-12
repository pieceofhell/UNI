public class Aplicacao {
    EstendeMostra est = new EstendeMostra();
    
    SuperMostra sup = new SuperMostra();

    float x = 3.33f;
    double a = 1.0;
    double z = x/a;

    public void executa() {
        sup = est;
        est.mostra();
        sup.mostra();
    }

    public static void main(String[] args) {
        Aplicacao app = new Aplicacao();
        app.executa();
    }
}
