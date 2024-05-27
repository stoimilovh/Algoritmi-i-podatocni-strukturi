import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Set<T>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashSet<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        // Remove the vertex from all adjacency lists
        for (Set<T> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        // Remove the vertex's own entry in the adjacency list
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source); // for undirected graph
    }

    // Remove an edge from the graph
    public void removeEdge(T source, T destination) {
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).remove(destination);
        }
        if (adjacencyList.containsKey(destination)) {
            adjacencyList.get(destination).remove(source); // for undirected graph
        }
    }

    // Get all neighbors of a vertex
    public Set<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new HashSet<>());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<T, Set<T>> entry : adjacencyList.entrySet()) {
            T vertex = entry.getKey();
            Set<T> neighbors = entry.getValue();

            if (vertex == null) {
                continue;
            }

            result.append(vertex).append(": ").append(neighbors).append("\n");
        }

        return result.toString();
    }
}

public class AdjacencyMatrixGraphTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            if (line.startsWith("ADDEDGE")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                graph.addEdge(vertex1, vertex2);
                graph.addVertex(vertex1);
                graph.addVertex(vertex2);
            } else if (line.startsWith("DELETEEDGE")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                graph.removeEdge(vertex1, vertex2);
            } else if (line.startsWith("ADJACENT")) {
                String[] parts = line.split("\\s+");
                int vertex1 = Integer.parseInt(parts[1]);
                int vertex2 = Integer.parseInt(parts[2]);

                if (graph.getNeighbors(vertex1).contains(vertex2)) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            } else if (line.startsWith("PRINTGRAPH")) {
                System.out.println(graph);
            }
        }
    }
}