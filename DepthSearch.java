import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/4/23.
 */
public class DepthSearch {
    private boolean[] marked;
    private int count;
    Graph g1;

    public DepthSearch(Graph G, int s){
             count = 0;
        marked = new boolean[G.V()];

        /*for (int w: G.adj(s)) {
            if (!marked[w])
                marked[w] = true;
            else
                dfs(G,w);
        }*/
    }

    public boolean marked (int v) {
        return marked[v];
    }

    public int count() {
        return 1;
    }

    public static void main(String[] args)
    {
        Graph g1 = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        DepthSearch search = new DepthSearch(g1, s);
        for (int v = 0; v < g1.V(); v++)
            if (search.marked(v))
                StdOut.print( v + " ");
        StdOut.println();

        if (search.count() != g1.V())
            StdOut.print("NOT ");
        StdOut.print("connected");
    }
}
