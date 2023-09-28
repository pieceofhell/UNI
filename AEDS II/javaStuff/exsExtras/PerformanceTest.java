import java.util.ArrayList;
import java.util.List;

class Person {

  private String firstName;
  private String lastName;

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void print() {
    System.out.println("First name is: " + firstName);
    System.out.println("Last name is: " + lastName);
  }
  // Add getters and setters if needed
}

public class PerformanceTest {

  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();

    List<Person> list = new ArrayList<Person>();
    for (int i = 0; i <= 10000; i++) {
      list.add(new Person("John", "Doe"));
    }

    // Get the Java runtime
    Runtime runtime = Runtime.getRuntime();

    // Run the garbage collector
    runtime.gc();

    // Calculate the used memory
    double memory = runtime.totalMemory() - runtime.freeMemory();

    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    System.out.println("Elapsed Time (in ms): " + elapsedTime);
    System.out.println("Used memory (in mb): " + memory / 1000000);
  }
}
