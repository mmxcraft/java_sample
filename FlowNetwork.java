import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

/**
 * Created by Mofeng on 2017/5/23.
 */
public class FlowNetwork {
    private final int V;
    private int E;
    private Bag<FlowEdge>[] adj;

    public FlowNetwork(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<FlowEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<FlowEdge>();
    }

    public FlowNetwork(In in) {
        this(in.readInt());
        int EE = in.readInt();
        for (int i = 0; i < EE; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double capacity = in.readDouble();
            addEdge(new FlowEdge(v,w,capacity));
        }
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
        E += 2;
    }
    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<FlowEdge> edges() {
        Bag bag = new Bag<FlowEdge>();
        for (int v = 0; v < V; v++)
            for (FlowEdge e: adj[v])
                bag.add(e);
        return bag;
    }
    public int V() {
        return V;
    }
    public int E() { return E;}
    public String toString() {
        String s = V + " vertices, " + E + " edges\n";
        for (FlowEdge edge: edges()) {
            s += edge + "\n";
        }
        return s;
    }


}
