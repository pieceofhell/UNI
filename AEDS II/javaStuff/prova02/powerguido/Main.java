// Interface

interface Raca {
  public void animalar();
}

interface Clock {
  public void clockar();
}

abstract class Joe {

  public void joeing() {
    System.out.println("hi i love joeing");
  }
}

interface Trump {
  public void campaign();
}

interface Bolsonaro {
  public boolean isEsfaqueado();
}

abstract class P {

  public void truco() {
    System.out.println("jogando truco");
  }
}

class Biden extends Joe implements Trump, Bolsonaro {

  public void campaign() {
    System.out.println("i love campaigning for donald trump");
  }

  public boolean isEsfaqueado() {
    boolean isEsfaqueado = false;
    return isEsfaqueado;
  }
}

interface Animal extends Raca {
  public void animalSound(); // interface method (does not have a body)

  public void sleep(); // interface method (does not have a body)

  public void snore();
}

class Figura3D {

  interface calculaVolume {
    public void calcularVolume();
  }
}

class Cubo extends Figura3D {

  void calcularVolume() {
    System.out.println("volume calculado!");
  }
}

// Pig "implements" the Animal interface
class Pig implements Animal, Clock {

  public void clockar() {
    System.out.println("i love da bigga da clocka");
  }

  public void animalar() {
    System.out.println("i love animaling");
  }

  public void animalSound() {
    // The body of animalSound() is provided here
    System.out.println("The pig says: oink oink");
  }

  public void sleep() {
    // The body of sleep() is provided here
    System.out.println("Zzz");
  }

  public void snore() {
    // The body of snore() is provided here
    System.out.println("honk shoo");
  }
}

class Main {

  public static void main(String[] args) {
    Pig myPig = new Pig(); // Create a Pig object
    Biden at = new Biden();
    at.joeing();
    at.campaign();
    myPig.animalSound();
    myPig.snore();
    myPig.sleep();
    myPig.animalar();
    myPig.clockar();
  }
}
