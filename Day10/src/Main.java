import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int joltage = 0;
        Graph g = new Graph();
        int[] joltGaps = new int[]{0, 0, 1};            // for part 1 answer
        ArrayList<Integer> mustUse = new ArrayList<>(); // for part 2 answer

        // Generate verticies
        g.add(0); // Outlet
        while (sc.hasNext()) {
            int curr = sc.nextInt();
            g.add(curr);
        }

        // Part 1
        for (int i = 1; i < 4 ; i++) {
            if (g.getVertexSet().contains(joltage + i)) {
                g.add(joltage, joltage + i);
                joltage += i ;
                joltGaps[i - 1]++;
                i = 0;
            } else if (i == 3) {
                joltage += 3; // Device
                g.add(joltage);
            }
        }
        System.out.println("Part 1: " + (joltGaps[0] * joltGaps[2])); // Part 1 answer

        // Part 2
        for (Integer i : g.getVertexSet()) {
            for (int j = 1; j < 4; j++) {
                if (g.getVertexSet().contains(i + j))
                    g.add(i, i + j);
                if (j == 3 && !g.getVertexSet().contains(i - 1) && !g.getVertexSet().contains(i - 2))
                    mustUse.add(i);
            }
        }
        long paths = g.countPaths(0, mustUse.get(0));
        for (int i = 0; i < mustUse.size() - 1; i++) {
            paths *= g.countPaths(mustUse.get(i), mustUse.get(i + 1));
        }
        paths *= g.countPaths(mustUse.get(mustUse.size() - 1), joltage);
        System.out.println("Part 2: " + paths);
    }
}
