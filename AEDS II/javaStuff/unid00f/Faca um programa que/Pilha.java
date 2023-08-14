import java.io.*;
import java.util.*;

public class Pilha {
    private Stack<String> stack;

    public Pilha() {
        stack = new Stack<>();
    }

    public void push(String element) {
        stack.push(element);
    }

    public String pop() {
        if (!isEmpty()) {
            return stack.pop();
        }
        return null;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public List<String> getList() {
        return new ArrayList<>(stack);
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (String element : stack) {
                writer.println(element);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        stack.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stack.push(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
