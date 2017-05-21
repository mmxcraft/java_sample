import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/5/18.
 */
public class EdgeWeightedDigraph
{
    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;

    public EdgeWeightedDigraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<DirectedEdge>();
    }

    public EdgeWeightedDigraph(In in)
    {
        this(in.readInt());
        int EE = in.readInt();
        for (int i = 0; i < EE; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            addEdge(new DirectedEdge(v,w,weight));
        }
    }

    public int V()
    { return V; }
    public int E()
    { return E; }
    public void addEdge(DirectedEdge e)
    {
        adj[e.from()].add(e);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v)
    {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges()
    {
        Bag<DirectedEdge> bag = new Bag<DirectedEdge>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e: adj[v])
                bag.add(e);
        return bag;
    }
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (DirectedEdge edge: edges()) {
            s += edge + "\n";
        }
        return s;
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        EdgeWeightedDigraph g1 = new EdgeWeightedDigraph(in);
        StdOut.print(g1);
    }
}
