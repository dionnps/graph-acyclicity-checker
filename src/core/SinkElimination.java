package core;

public class SinkElimination {

    public static boolean isAcyclic(IGraph graph) {
        return isAcyclic(graph, true);
    }

    public static boolean isAcyclic(IGraph graph, boolean showSteps) {
        if (showSteps) {
            System.out.println("\nSink Elimination Steps:");
        }

        while (!graph.isEmpty()) {
            int sink = graph.findSink();

            if (sink == -1) {
                if (showSteps) {
                    System.out.println("No sink found.");
                    System.out.println("Graph is NOT acyclic.");
                }
                return false;
            }

            if (showSteps) {
                System.out.println("Sink found and removed: " + sink);
            }

            graph.removeVertex(sink);

            if (showSteps) {
                System.out.println("Graph after removing " + sink + ":");
                graph.printGraph();
                System.out.println();
            }
        }

        if (showSteps) {
            System.out.println("Graph is empty.");
            System.out.println("Graph IS acyclic.");
        }

        return true;
    }
}
