public class SuperMostra {

    String str = "superStr";

    public void mostra() {
        System.out.println("SuperMostra - " + str);
    }

    @Override
    public String toString() {
        return str;
    }
}
