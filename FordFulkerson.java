import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/5/23.
 */
public class FordFulkerson {
    private boolean[] marked;
    private FlowEdge[] edgeTo;
    private double value;

    public FordFulkerson(FlowNetwork G, int s, int t){
        value = 0;
        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);
            value += bottle;
        }
    }

    public boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        Queue<Integer> q = new Queue<Integer>();
        q.enqueue(s);
        marked[s] = true;
        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (FlowEdge e: G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }

        return marked[t];
    }

    public double value() { return value; }
    public boolean inCut(int v) { return marked[v]; }

    public static void main(String[] args) {
        FlowNetwork G = new FlowNetwork(new In(args[0]));
        int s = 0;
        int t = G.V()-1;
        FordFulkerson maxflow = new FordFulkerson(G, s , t);

        StdOut.println("Max flow from " + s + "to " + t);
        for (int v = 0; v < G.V(); v++)
            for (FlowEdge e:G.adj(v))
                if ( (v == e.from()) && e.flow() > 0)
                    StdOut.println(" " + e);
        StdOut.println("Max flow value = " + maxflow.value());
    }
}
