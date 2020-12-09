import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

/**
 * An implementation of DirectedGraph that uses sets of edges to indicate which vertices are
 * adjacent to any given vertex and the direction of the adjacency.
 * @param <V> vertex label type
 * @param <E> edge label type
 */
public class AdjacencySetDirectedGraph<V, E> implements DirectedGraph<V, E> {

    /**
     * Wrapper class containing two sets of edges meant to be the incoming edges for a
     * particular vertex and the outgoing edges for the same vertex
     * @param <V> vertex label type
     * @param <E> edge label type
     */
    private class adjacentEdges<V, E> {
        private Set<Edge<V, E>> outgoingEdges;
        private Set<Edge<V, E>> incomingEdges;

        public adjacentEdges() {
            this.outgoingEdges = new HashSet<>();
            this.incomingEdges = new HashSet<>();
        }
    }

    // Field
    private Map<V, adjacentEdges<V, E>> edgeMap;

    public AdjacencySetDirectedGraph() {
        this.edgeMap = new HashMap<>();
    }

    @Override
    public boolean addVertex(V v) {
        if (v == null)
            throw new IllegalArgumentException("Vertex cannot be null");

        if (edgeMap.containsKey(v))
            return false;

        this.edgeMap.put(v, new adjacentEdges<>());
        return true;
    }

    @Override
    public boolean removeVertex(V v) {
        if (!edgeMap.keySet().contains(v))
            return false;

        adjacentEdges<V, E> edgesToRemove = edgeMap.remove(v);
        for (Edge e : edgesToRemove.outgoingEdges) {
            this.edgeMap.get(e.headVertex).incomingEdges.remove(e);
        }
        for (Edge e : edgesToRemove.incomingEdges) {
            this.edgeMap.get(e.tailVertex).outgoingEdges.remove(e);
        }
        return true;
    }

    @Override
    public boolean hasVertex(V v) {
        return edgeMap.containsKey(v);
    }

    @Override
    public boolean addEdge(Edge<V, E> e) {
        if (e == null)
            throw new IllegalArgumentException("Cannot add null edge to Graph.");

        if (e.label == null)
            throw new IllegalArgumentException("Edge must have a label.");

        if (!this.edgeMap.containsKey(e.tailVertex) || !this.edgeMap.containsKey(e.headVertex))
            throw new IllegalArgumentException("Edge must connect vertices already contained by the Graph.");

        if (this.edgeMap.get(e.tailVertex).outgoingEdges.contains(e))
            return false;

        this.edgeMap.get(e.tailVertex).outgoingEdges.add(e);
        this.edgeMap.get(e.headVertex).incomingEdges.add(e);
        return true;
    }

    @Override
    public boolean removeEdge(Edge<V, E> e) {
        if (e == null || e.tailVertex == null)
            return false;

        if (!this.edgeMap.get(e.tailVertex).outgoingEdges.contains(e))
            return false;

        this.edgeMap.get(e.tailVertex).outgoingEdges.remove(e);
        this.edgeMap.get(e.headVertex).incomingEdges.remove(e);
        return true;
    }

    @Override
    public boolean hasEdge(Edge<V, E> e) {
        if (e == null || e.tailVertex == null)
            return false;

        if (!edgeMap.keySet().contains(e.tailVertex))
            return false;

        return this.edgeMap.get(e.tailVertex).outgoingEdges.contains(e);
    }

    @Override
    public Set<Edge<V, E>> getConnectingEdges(V tailVertex, V headVertex) {
        if (!edgeMap.containsKey(tailVertex) || !edgeMap.containsKey(headVertex))
            throw new IllegalArgumentException("Graph does not contain both vertices.");

        Set<Edge<V, E>> connectingEdges = new HashSet<>();
        connectingEdges.addAll(edgeMap.get(tailVertex).outgoingEdges);
        connectingEdges.retainAll(edgeMap.get(headVertex).incomingEdges);
        return Collections.unmodifiableSet(connectingEdges);
    }

    @Override
    public Set<Edge<V, E>> getOutgoingEdges(V v) {
        if (!edgeMap.containsKey(v))
            throw new IllegalArgumentException("Vertex is not in the graph.");

        return Collections.unmodifiableSet(edgeMap.get(v).outgoingEdges);
    }

    @Override
    public Set<Edge<V, E>> getIncomingEdges(V v) {
        if (!edgeMap.containsKey(v))
            throw new IllegalArgumentException("Vertex is not in the graph.");

        return Collections.unmodifiableSet(edgeMap.get(v).incomingEdges);
    }

    @Override
    public Set<V> getVertexSet() {
        return Collections.unmodifiableSet(edgeMap.keySet());
    }

    @Override
    public Set<Edge<V, E>> getEdgeSet() {
        Set<Edge<V, E>> edgeSet = new HashSet<>();
        for (V v : edgeMap.keySet()) {
            edgeSet.addAll(edgeMap.get(v).outgoingEdges);
        }
        return Collections.unmodifiableSet(edgeSet);
    }
}