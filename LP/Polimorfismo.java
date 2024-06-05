import java.sql.Date;
import java.util.Arrays;

/**
 * ExtraPolimorfismo
 */
class ExtraPolimorfismo {

  public int func1(int x) {
    return x * 10;
  }

  public void func1() {
    System.out.println("funcao 1 - versao void executada na classe ExtraPolimorfismo");
  }

  public int funcX(int x) {
    return func1(x);
  }
}

/**
 * Polimorfismo
 */
public class Polimorfismo extends ExtraPolimorfismo {

  public int func1(int x) {
    return funcX(x);
  }  

  public void func1() {
    System.out.println("funcao 1 - versao void executada");
  }

  public int func2(int x) {
    return x / 5;
  }

  public float func3(int x) {
    return x / 5.0F;
  }

  public Date longToDate(long x) {
    return new Date(x);
  }

  public static void main(String[] args) {
    int x = 10;
    String b = "banana";
    b = b + x;
    int xy[] = new int[10];
    for (int i = 0; i < xy.length; i++) {
      xy[i] = i + 1;
    }
    System.out.println(Arrays.toString(xy));

    System.out.println(b);

    Polimorfismo p = new Polimorfismo();
    System.out.println(p.funcX(x));
    p.func1();
    System.out.println(p.func2(x));
    System.out.println(p.func3(x));
    float y = x / 2;
    System.out.println("y = " + y);
    System.out.println(p.longToDate((System.currentTimeMillis() * 2)));

    ExtraPolimorfismo ip = new ExtraPolimorfismo();
    System.out.println(ip.func1(x));
  }
}
