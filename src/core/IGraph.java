package core;

import java.util.Set;
import java.util.List;

public interface IGraph {
    void addVertex(int vertex);
    void addEdge(int from, int to);

    boolean hasVertex(int vertex);
    boolean isEmpty();

    int findSink();
    void removeVertex(int vertex);

    Set<Integer> getVertices();
    List<Integer> getNeighbors(int vertex);

    void printGraph();
}
