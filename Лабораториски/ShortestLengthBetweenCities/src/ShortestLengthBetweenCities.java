import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.*;

class AdjacencyListGraph<T> {
    private Map<T, Map<T, Double>> adjacencyList;

    public AdjacencyListGraph() {
        this.adjacencyList = new HashMap<>();
    }

    // Add a vertex to the graph
    public void addVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList.put(vertex, new HashMap<>());
        }
    }

    // Remove a vertex from the graph
    public void removeVertex(T vertex) {
        for (Map<T, Double> neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
        adjacencyList.remove(vertex);
    }

    public void addEdge(T source, T destination, double weight) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).put(destination, weight);
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
        return adjacencyList.getOrDefault(vertex, new HashMap<>()).keySet();
    }

    public double getWeight(T source, T destination) {
        Map<T, Double> neighbors = adjacencyList.get(source);
        if (neighbors != null) {
            Double weight = neighbors.get(destination);
            return weight != null ? weight : 0;
        }
        return 0;
    }


    public Map<T, Double> shortestPath(T startVertex) {
        Map<T, Double> distances = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparing(distances::get));
        Set<T> explored = new HashSet<>();

        for (T vertex : adjacencyList.keySet()) {
            distances.put(vertex, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);

        queue.add(startVertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (Map.Entry<T, Double> neighborEntry : adjacencyList.get(current).entrySet()) {
                T neighbor = neighborEntry.getKey();
                double newDest = distances.get(current) + neighborEntry.getValue();

                if (newDest < distances.get(neighbor)) {
                    distances.put(neighbor, newDest);

                    if (!explored.contains(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return distances;
    }

    public List<T> getRoute(T start, T end, Map<T, Double> distances) {
        List<T> route = new ArrayList<>();
        Map<T, T> predecessors = new HashMap<>();
        PriorityQueue<T> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));

        Set<T> explored = new HashSet<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            explored.add(current);

            for (T neighbor : adjacencyList.getOrDefault(current, new HashMap<>()).keySet()) {
                if (!explored.contains(neighbor)) {
                    queue.add(neighbor);
                    predecessors.put(neighbor, current);
                }
            }
        }

        T current = end;
        while (current != null) {
            route.add(current);
            current = predecessors.get(current);
        }

        Collections.reverse(route);
        return route;
    }
}

public class ShortestLengthBetweenCities {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int numPaths = Integer.parseInt(sc.nextLine());

        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();

        for (int i = 0;i < numPaths;i++) {
            String line = sc.nextLine();
            String [] parts = line.split("\\s+");

            graph.addEdge(parts[1], parts[3], Double.parseDouble(parts[4]));
        }

        String start = sc.nextLine();
        String destination = sc.nextLine();

        Map<String, Double> distances = graph.shortestPath(start);
        Map<String, Double> distances2 = graph.shortestPath(destination);

        List<String> startToDestinationRoute = graph.getRoute(start, destination, distances2);
        List<String> destinationToStartRoute = graph.getRoute(destination, start, distances);

        double totalDistance = calculateTotalDistance(graph, startToDestinationRoute) +
                calculateTotalDistance(graph, destinationToStartRoute);

        printRoute(startToDestinationRoute);
        System.out.println();
        printRoute(destinationToStartRoute);
        System.out.println();
        System.out.printf("%.1f", totalDistance);
    }

    private static void printRoute(List<String> route) {
        for (int i = 0; i < route.size(); i++) {
            System.out.print(route.get(i));
            if (i != route.size() - 1) {
                System.out.print(" ");
            }
        }
    }

    private static double calculateTotalDistance(AdjacencyListGraph<String> graph, List<String> route) {
        double totalDistance = 0.0;

        for (int i = 0; i < route.size() - 1; i++) {
            String currentCity = route.get(i);
            String nextCity = route.get(i + 1);

            totalDistance += graph.getWeight(currentCity, nextCity);
        }

        return totalDistance;
    }
}