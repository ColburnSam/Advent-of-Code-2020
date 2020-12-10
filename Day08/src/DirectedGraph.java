import java.util.Set;

/**
 * A Graph is a collection of objects called "vertices" which are generally linked together by paths called "edges."
 * A Directed Graph is a Graph whose edges have orientation, directing away from one vertex (tail) to another (head).
 * More formally, a Graph is an ordered pair G = (V,E) where V is a vertex set, and E is an edge set.
 * On a Directed Graph, an edge is an ordered pair of vertices, e.g.: e = (u,v) with u,v in V.
 *
 * This interface enables interaction with a labeled, directed multi-graph.
 * - Vertices must be uniquely labeled.
 * - Edges must labeled and their labels must be unique if they represent the same "path."
 *      i.e.: Edges e, f = (u,v) implies e and f must be labeled differently.
 *
 * @param <V> The vertex label type
 * @param <E> The edge label type
 *
 * Sam Colburn
 */
public interface DirectedGraph<V, E> {

    /**
     * Adds the given (non-null) vertex to the Graph if the Graph does not already contain that vertex.
     * i.e.: Adds v != null, if and only if there exists no vertex u in G such that v.equals(u).
     * Throws IllegalArgumentException if v == null.
     * @param v The vertex to be added.
     * @return true if and only if the vertex is added to the Graph.
     * @throws IllegalArgumentException if v is null.
     */
    boolean addVertex(V v);

    /**
     * Removes the given vertex from the Graph. If no such vertex is on the Graph, does nothing.
     * i.e.: For each vertex u in G, removes u from G if and only if u.equals(v).
     * Note at most one vertex will be removed because redundant vertices cannot be added to G.
     * Removes any Edges that had the vertex as an end point, i.e: any e such that e = (u,v) or e = (v,u) for some u.
     * @param v The vertex to be added.
     * @return True if and only if the vertex is removed from the Graph.
     */
    boolean removeVertex(V v);

    /**
     * Returns true if and only if the Graph contains the given (non-null) vertex.
     * i.e.: if the graph contains a vertex u such that u.equals(v).
     * @param v The vertex whose inclusion in the Graph is in question.
     * @return True if and only if the Graph contains v.
     */
    boolean hasVertex(V v);

    /**
     * Adds the given Edge object to the Graph, G, as long as its head and tail vertices exist on G
     * and the Graph does not already contain that edge, i.e.: There is no Edge f such that f.equals(e).
     * Throws an IllegalArgumentException if the Edge is null or has no label.
     * Throws an IllegalArgumentException if the head or tail vertex of the Edge is not in the Graph.
     * i.e.: if there are not vertices u, v in G such that u.equals(e.tailVertex) and v.equals(e.headVertex).
     * (Note that u and v need not be distinct).
     * @param e An Edge object to be inserted into G
     * @return True if and only if the Edge is added to G.
     * @throws IllegalArgumentException if the given Edge is null, has a null label, or if its endpoints are not in G.
     * Because null vertices are not admitted to the Graph, either vertex contained by the Edge being null will
     * necessarily throw this exception.
     */
    boolean addEdge(Edge<V, E> e);

    /**
     * Removes the given Edge if the Graph contains such an Edge,
     * i.e.: if the Graph contains an Edge f such that f.equals(e).
     * Otherwise does nothing and returns false.
     * @param e The Edge to be removed.
     * @return True if and only if the Edge is removed.
     */
    boolean removeEdge(Edge<V, E> e);

    /**
     * Returns true if and only if the Graph, G, contains an Edge equal to the given Edge
     * i.e.: if there an edge f in G such that f.equals(e).
     * @param e The Edge whose inclusion in the Graph is in question.
     * @return True if and only if the Graph contains the Edge.
     */
    boolean hasEdge(Edge<V, E> e);

    /**
     * Returns the Set of Edges directing from a given tailVertex to a given headVertex.
     * If the Graph does not contain each given vertex throws an IllegalArgumentException,
     * i.e.: if there are not vertices u, v in G such that u.equals(tailVertex) and v.equals(headVertex).
     * (Note that u and v need not be distinct).
     * If no such Edges exists, returns an empty set.
     * @param tailVertex The vertex away from which the edge is directed.
     * @param headVertex The vertex into which the edge is directed.
     * @return Set of Edges directed from tail to head if it exists.
     * @throws IllegalArgumentException if either vertex is not on the Graph. Because null vertices are not
     * admitted to the Graph, either vertex being null will necessarily throw this exception.
     */
    Set<Edge<V,E>> getConnectingEdges(V tailVertex, V headVertex);

    /**
     * Returns the set of Edges directing away from the given vertex.
     * If the graph does not contain such a vertex throws IllegalArgumentException,
     * i.e.: if there is not a vertex u in G such that u.equals(v).
     * If no such Edges exists, returns an empty set.
     * @param v the vertex that is the tail of the Edges we want.
     * @return Set of Edges directing away from v.
     * @throws IllegalArgumentException if the vertex is not in the Graph. Because null vertices are not
     * admitted to the Graph, a null vertex will necessarily throw this exception.
     */
    Set<Edge<V,E>> getOutgoingEdges(V v);

    /**
     * Returns the set of Edges directing into the given vertex.
     * If the graph does not contain such a vertex throws IllegalArgumentException,
     * i.e.: if there is not a vertex u in G such that u.equals(v).
     * If no such Edges exists, returns an empty set.
     * @param v the vertex that is the head of the Edges we want.
     * @return Set of Edges directing into v.
     * @throws IllegalArgumentException if the vertex is not in the Graph. Because null vertices are not
     * admitted to the Graph, a null vertex will necessarily throw this exception.
     */
    Set<Edge<V,E>> getIncomingEdges(V v);

    /**
     * Returns all vertices, i.e. the vertex set V of the Graph.
     * @return Set containing all labels of the vertices in the Graph.
     */
    Set<V> getVertexSet();

    /**
     * Returns all Edges, i.e. the edge set E of the Graph.
     * @return Set containing all Edges in the graph.
     */
    Set<Edge<V,E>> getEdgeSet();
}