package main;

import core.FileParser;
import core.IGraph;
import core.SinkElimination;
import implementations.AdjacencyListAVL;
import implementations.AdjacencyListLL;
import implementations.AdjacencyMatrix;
import java.io.File;

public class Benchmark {

    public static void runBenchmark(String filePath, String expectedType) {
        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found: " + filePath);
            return;
        }

        System.out.println("\n======================================");
        System.out.println("Benchmark file: " + file.getName());
        System.out.println("Expected type : " + expectedType);
        System.out.println("======================================");

        testImplementation(file, "AdjacencyListLL", new AdjacencyListLL());
        testImplementation(file, "AdjacencyListAVL", new AdjacencyListAVL());
        testImplementation(file, "AdjacencyMatrix", new AdjacencyMatrix());
    }

    private static void testImplementation(File file, String structureName, IGraph graph) {
        long start = System.nanoTime();

        FileParser.parseFile(file.getAbsolutePath(), graph);
        boolean result = SinkElimination.isAcyclic(graph, false);

        long end = System.nanoTime();
        long timeMs = (end - start) / 1_000_000;

        System.out.printf("%-18s %-17s %-10s %d ms%n",
                file.getName(),
                structureName,
                result ? "Acyclic" : "Cyclic",
                timeMs);
    }
}
