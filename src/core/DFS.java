package core;

import java.util.*;

public class DFS {

    public static List<Integer> findCycle(IGraph graph) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> inStack = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();

        for (int vertex : graph.getVertices()) {
            if (!visited.contains(vertex)) {
                List<Integer> cycle = dfsVisit(graph, vertex, visited, inStack, parent);
                if (cycle != null) {
                    return cycle;
                }
            }
        }

        return null;
    }

    private static List<Integer> dfsVisit(IGraph graph, int current,
                                          Set<Integer> visited,
                                          Set<Integer> inStack,
                                          Map<Integer, Integer> parent) {
        visited.add(current);
        inStack.add(current);

        for (int neighbor : graph.getNeighbors(current)) {
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, current);

                List<Integer> cycle = dfsVisit(graph, neighbor, visited, inStack, parent);
                if (cycle != null) {
                    return cycle;
                }
            } else if (inStack.contains(neighbor)) {
                return buildCycle(current, neighbor, parent);
            }
        }

        inStack.remove(current);
        return null;
    }

    private static List<Integer> buildCycle(int current, int neighbor, Map<Integer, Integer> parent) {
        List<Integer> cycle = new ArrayList<>();
        cycle.add(neighbor);

        int temp = current;
        while (temp != neighbor) {
            cycle.add(temp);
            temp = parent.get(temp);
        }

        cycle.add(neighbor);
        Collections.reverse(cycle);

        return cycle;
    }

    public static void printCycle(List<Integer> cycle) {
        if (cycle == null || cycle.isEmpty()) {
            System.out.println("No cycle found.");
            return;
        }

        System.out.print("Cycle found: ");
        for (int i = 0; i < cycle.size(); i++) {
            System.out.print(cycle.get(i));
            if (i < cycle.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
