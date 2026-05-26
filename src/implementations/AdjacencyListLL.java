package implementations;

import core.IGraph;
import core.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyListLL implements IGraph {

    private final Map<Integer, LinkedList> adjacencyList;

    public AdjacencyListLL() {
        adjacencyList = new HashMap<>();
    }

    @Override
    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new LinkedList());
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);
        adjacencyList.get(from).add(to);
    }

    @Override
    public boolean hasVertex(int vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public boolean isEmpty() {
        return adjacencyList.isEmpty();
    }

    @Override
    public int findSink() {
        for (int vertex : adjacencyList.keySet()) {
            if (adjacencyList.get(vertex).isEmpty()) {
                return vertex;
            }
        }
        return -1;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return;
        }

        adjacencyList.remove(vertex);

        for (LinkedList neighbors : adjacencyList.values()) {
            neighbors.remove(vertex);
        }
    }

    @Override
    public Set<Integer> getVertices() {
        return new HashSet<>(adjacencyList.keySet());
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return new ArrayList<>();
        }
        return adjacencyList.get(vertex).toList();
    }

    @Override
    public void printGraph() {
        for (int vertex : adjacencyList.keySet()) {
            System.out.println(vertex + " -> " + adjacencyList.get(vertex));
        }
    }
}
