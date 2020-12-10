import java.util.*;

public class Graph {

    private Map<Integer, Set<Integer>> graph ;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void add(int n) {
        this.graph.put(n, new HashSet<>());
    }

    public void add(int src, int dst) {
        this.graph.get(src).add(dst);
    }

    public Set<Integer> getVertexSet() {
        return this.graph.keySet();
    }

    public Set<Integer> getAdj(int n) {
        return this.graph.get(n);
    }

    public int countPaths(int start, int finish) {
        int pathCount = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            int currNode = queue.remove();
            if (currNode == finish)
                pathCount++;
            else
                queue.addAll(this.getAdj(currNode));
        }
        return pathCount;
    }
}
