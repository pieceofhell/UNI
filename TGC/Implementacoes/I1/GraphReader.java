import java.io.*;

class GraphReader {
    public static void main(String[] args) {
        String fileName = "C:/Users/henri/code/github/UNI/TGC/Implementacoes/I1/graph-test-100-1.txt";  // Replace with your file path

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // Reading the first line
            String firstLine = br.readLine();
            String[] firstLineParts = firstLine.trim().split("\\s+");
            int numVertices = Integer.parseInt(firstLineParts[0]);
            int numEdges = Integer.parseInt(firstLineParts[1]);

            // Arrays to store the values
            int[] vertex1 = new int[numEdges];
            int[] vertex2 = new int[numEdges];

            // Reading the values
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < numEdges) {
                String[] parts = line.trim().split("\\s+");
                vertex1[index] = Integer.parseInt(parts[0]);
                vertex2[index] = Integer.parseInt(parts[1]);
                index++;
            }

            // Trecho de código gerado usando ChatGPT

            System.out.println("Number of vertices: " + numVertices);
            System.out.println("Number of edges: " + numEdges);

            System.out.println("Edges:");
            for (int i = 0; i < numEdges; i++) {
                System.out.println(vertex1[i] + " " + vertex2[i]);
            }

            int target = 1;
            
            


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
