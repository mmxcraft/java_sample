import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

/**
 * Created by mofma on 2017/5/22.
 */
public class CPM {
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        int N = in.readInt();
        in.readLine();
        EdgeWeightedDigraph G;

        G = new EdgeWeightedDigraph(2*N+2);
        int s = 2*N;
        int t = 2*N+1;
        for (int i = 0; i < N; i++) {
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            int num = Integer.parseInt(a[1]);

            G.addEdge(new DirectedEdge(i, i+N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i+N, t, 0.0));
            for (int j = 0; j < num; j++) {
                int successor = Integer.parseInt(a[j+2]);
                G.addEdge(new DirectedEdge(i+N, successor, 0.0));
            }
        }
        AcyclicLP lp = new AcyclicLP(G,s);
        StdOut.println("Start times:");
        for (int i = 0; i < N; i++)
            StdOut.printf("%4d: %5.1f\n", i, lp.distTo(i));
        StdOut.printf("Finish time: %5.1f\n", lp.distTo(t));
    }
}
