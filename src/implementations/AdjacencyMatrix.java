package implementations;

import core.IGraph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyMatrix implements IGraph {

    private final List<Integer> vertices;
    private final Map<Integer, Integer> indexMap;
    private boolean[][] matrix;

    public AdjacencyMatrix() {
        vertices = new ArrayList<>();
        indexMap = new HashMap<>();
        matrix = new boolean[0][0];
    }

    @Override
    public void addVertex(int vertex) {
        if (indexMap.containsKey(vertex)) {
            return;
        }

        int newSize = vertices.size() + 1;
        vertices.add(vertex);
        indexMap.put(vertex, newSize - 1);

        boolean[][] newMatrix = new boolean[newSize][newSize];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, matrix[i].length);
        }

        matrix = newMatrix;
    }

    @Override
    public void addEdge(int from, int to) {
        addVertex(from);
        addVertex(to);

        int i = indexMap.get(from);
        int j = indexMap.get(to);
        matrix[i][j] = true;
    }

    @Override
    public boolean hasVertex(int vertex) {
        return indexMap.containsKey(vertex);
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int findSink() {
        for (int i = 0; i < vertices.size(); i++) {
            boolean hasOutgoing = false;

            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]) {
                    hasOutgoing = true;
                    break;
                }
            }

            if (!hasOutgoing) {
                return vertices.get(i);
            }
        }

        return -1;
    }

    @Override
    public void removeVertex(int vertex) {
        if (!indexMap.containsKey(vertex)) {
            return;
        }

        int removeIndex = indexMap.get(vertex);
        int newSize = vertices.size() - 1;
        boolean[][] newMatrix = new boolean[newSize][newSize];
        List<Integer> newVertices = new ArrayList<>();

        int newRow = 0;
        for (int i = 0; i < vertices.size(); i++) {
            if (i == removeIndex) {
                continue;
            }

            newVertices.add(vertices.get(i));

            int newCol = 0;
            for (int j = 0; j < vertices.size(); j++) {
                if (j == removeIndex) {
                    continue;
                }

                newMatrix[newRow][newCol] = matrix[i][j];
                newCol++;
            }
            newRow++;
        }

        vertices.clear();
        vertices.addAll(newVertices);

        indexMap.clear();
        for (int i = 0; i < vertices.size(); i++) {
            indexMap.put(vertices.get(i), i);
        }

        matrix = newMatrix;
    }

    @Override
    public Set<Integer> getVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        List<Integer> neighbors = new ArrayList<>();

        if (!indexMap.containsKey(vertex)) {
            return neighbors;
        }

        int row = indexMap.get(vertex);
        for (int j = 0; j < vertices.size(); j++) {
            if (matrix[row][j]) {
                neighbors.add(vertices.get(j));
            }
        }

        return neighbors;
    }

    @Override
    public void printGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            List<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < vertices.size(); j++) {
                if (matrix[i][j]) {
                    neighbors.add(vertices.get(j));
                }
            }
            System.out.println(vertices.get(i) + " -> " + neighbors);
        }
    }
}
