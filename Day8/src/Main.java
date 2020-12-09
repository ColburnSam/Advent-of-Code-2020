import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        Program program = new Program(sc);

        // Part 1
        System.out.println("Part 1: " + program.runCode());

        // Part 2
        AdjacencySetDirectedGraph<Integer, String> codeGraph = new AdjacencySetDirectedGraph<>();
        for (int i = 0; i <= program.getLength(); i++) {    // Populate Vertex Set
            codeGraph.addVertex(i);
        }
        for (int i = 0; i < program.getLength(); i++) {     // Populate Edge Set
            String[] s = program.getLine(i).split(" ");
            int move = 1;
            if (s[0].equals("jmp"))
                move = parseInt(s[1]);

            String label = "(" + i + ", " + (i + move) + ")";
            if (0 < i + move && i + move <= program.getLength())
                codeGraph.addEdge(new Edge(i, i + move, label));
        }

        // Create set of nodes (tree) that feed into the file end
        Set<Integer> targets = new HashSet<>();
        Queue<Edge<Integer, String>> queue = new ArrayDeque<>();
        queue.add(new Edge<>(program.getLength(), -1, "fake")); // False edge to "end" node
        while (!queue.isEmpty()) {
            int currentNode = queue.remove().tailVertex;
            targets.add(currentNode);
            queue.addAll(codeGraph.getIncomingEdges(currentNode));
        }

        // Find and fix bad line
        if (program.fixCode(targets))
            System.out.println("Part 2: " + program.runCode());
    }
}
