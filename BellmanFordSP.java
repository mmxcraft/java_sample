import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;

/**
 * Created by mofma on 2017/5/22.
 */
public class BellmanFordSP{
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s)
    {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new Queue<Integer>();

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        queue.enqueue(s);
        while (!queue.isEmpty() && !this.hasNegativeCycle())
        {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v)
    {
        for (DirectedEdge e: G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }

            }
            if (cost++ % G.V() == 0)
                findNegatvieCycle();
        }
    }

    public double distTo(int v) { return distTo[v]; }
    public boolean hasPathTo(int v) { return distTo[v] < Double.POSITIVE_INFINITY; }
    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private void findNegatvieCycle()
    {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt;
        spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++)
            if (edgeTo[v] != null)
                spt.addEdge(edgeTo[v]);
        EdgeWeightedCycle cf;
        cf = new EdgeWeightedCycle(spt);
        cycle = cf.cycle();
    }

    public boolean hasNegativeCycle()
    {
        return cycle != null;
    }
    public Iterable<DirectedEdge> negativeCycle()
    {
        return cycle;
    }
}
