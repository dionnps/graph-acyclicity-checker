package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser {

    public static void parseFile(String fileName, IGraph graph) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (lineNumber == 1 && parts.length == 1) {
                    continue;
                }

                if (parts.length != 2) {
                    System.out.println("Invalid format at line " + lineNumber + ": " + line);
                    continue;
                }

                try {
                    int from = Integer.parseInt(parts[0]);
                    int to = Integer.parseInt(parts[1]);
                    graph.addEdge(from, to);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid numbers at line " + lineNumber + ": " + line);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }
    }
}
