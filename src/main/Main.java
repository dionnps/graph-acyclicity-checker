package main;

import core.FileParser;
import core.IGraph;
import implementations.AdjacencyListLL;
import core.SinkElimination;
import core.DFS;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // TASK 1
        System.out.println("Directed Graph Acyclicity Checker");

        // TASK 2 + TASK 3
        IGraph graph = new AdjacencyListLL();

        FileParser.parseFile("graph.txt", graph);

        System.out.println("Graph loaded from file:");
        graph.printGraph();

        int sink = graph.findSink();
        System.out.println("Sink found: " + sink);

        if (sink != -1) {
            graph.removeVertex(sink);
            System.out.println("Graph after removing sink:");
            graph.printGraph();
        } else {
            System.out.println("No sink found in the graph.");
        }

        // TASK 4
        boolean acyclic = SinkElimination.isAcyclic(graph);

        System.out.println("\nFinal Answer: " + (acyclic ? "yes" : "no"));

        // TASK 5
        if (!acyclic) {
            IGraph originalGraph = new AdjacencyListLL();
            FileParser.parseFile("graph.txt", originalGraph);

            List<Integer> cycle = DFS.findCycle(originalGraph);
            DFS.printCycle(cycle);
        }

        // TASK 6
        System.out.println();
        System.out.println("===== TASK 6 BENCHMARK =====");

        String acyclicPath1 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/acyclic/a_40_0.txt";
        String cyclicPath1 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/cyclic/c_40_0.txt";
        Benchmark.runBenchmark(acyclicPath1, "ACYCLIC");
        Benchmark.runBenchmark(cyclicPath1, "CYCLIC");

        String acyclicPath2 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/acyclic/a_80_0.txt";
        String cyclicPath2 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/cyclic/c_80_0.txt";
        Benchmark.runBenchmark(acyclicPath2, "ACYCLIC");
        Benchmark.runBenchmark(cyclicPath2, "CYCLIC");

        String acyclicPath3 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/acyclic/a_160_0.txt";
        String cyclicPath3 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/cyclic/c_160_0.txt";
        Benchmark.runBenchmark(acyclicPath3, "ACYCLIC");
        Benchmark.runBenchmark(cyclicPath3, "CYCLIC");

        String acyclicPath4 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/acyclic/a_320_0.txt";
        String cyclicPath4 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/cyclic/c_320_0.txt";
        Benchmark.runBenchmark(acyclicPath4, "ACYCLIC");
        Benchmark.runBenchmark(cyclicPath4, "CYCLIC");

        String acyclicPath5 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/acyclic/a_640_0.txt";
        String cyclicPath5 = "C:/Users/User/OneDrive/Desktop/Algo/CW/benchmarks/benchmarks/cyclic/c_640_0.txt";
        Benchmark.runBenchmark(acyclicPath5, "ACYCLIC");
        Benchmark.runBenchmark(cyclicPath5, "CYCLIC");
    }
}
