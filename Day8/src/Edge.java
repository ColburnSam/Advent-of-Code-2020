import java.util.Objects;

/**
 * This class provides the Edge objects used in the DirectedGraph Interface.
 * Edge objects are defined by their tail-end vertex, their head-end vertex and their label.
 * Two Edges are considered equal if and only if each of their fields are equal under the Objects.equals() method.
 * @param <V> The vertex type.
 * @param <E> The edge label type.
 *
 * Sam Colburn
 */
public class Edge<V, E> {
    public final V tailVertex;
    public final V headVertex;
    public final E label;

    public Edge(V tailVertex, V headVertex, E label) {
        this.tailVertex = tailVertex;
        this.headVertex = headVertex;
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (o == null)
            return false;

        if (this.getClass() != o.getClass())
            return false;
        Edge e = (Edge) o;

        return Objects.equals(this.label, e.label)
                && Objects.equals(this.headVertex, e.headVertex)
                && Objects.equals(this.tailVertex, e.tailVertex);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (label == null ? 0 : label.hashCode());
        hash = 31 * hash + (headVertex == null ? 0 : headVertex.hashCode());
        hash = 31 * hash + (tailVertex == null ? 0 : tailVertex.hashCode());
        return hash;
    }
}