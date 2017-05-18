import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/5/14.
 */
public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G)
    {
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        mst = new Queue<Edge>();
        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(G,v);
            if (!marked[w]) visit(G,w);
        }

    }

    public void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e: G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges()
    {
        return mst;
    }

    public double weight()
    {
        double ww = 0.0;
        for (Edge e: edges())
        {
            ww += e.weight();
        }
        return ww;
    }

    public static void main(String[] args)
    {
        In in = new In (args[0]);
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);

        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e: mst.edges())
            StdOut.println(e);
        StdOut.printf("mst Weight = %.2f\n", mst.weight());

    }
}
