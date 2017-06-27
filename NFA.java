import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/6/11.
 *
 *
 *  % java NFA "(A*B|AC)D" AAAABD
 *  true
 *
 *  % java NFA "(A*B|AC)D" AAAAC
 *  false
 *
 *  % java NFA "(a|(bc)*d)*" abcbcd
 *  true
 *
 *  % java NFA "(a|(bc)*d)*" abcbcbcdaaaabcbcdaaaddd
 *  true
 *
 *  Remarks
 *
 **********************************************************************
 */
public class NFA {
    private char[] re;
    private Digraph G;
    private int M;

    public NFA(String regexp){
        M = regexp.length();
        re = regexp.toCharArray();
        G = buildDigraph();
    }

    public boolean recognizes(String txt){
        Bag<Integer> pc = new Bag<Integer>();
        DirectedDFS dfs = new DirectedDFS(G,0);

        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v)) pc.add(v);

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();
            for (int v: pc) {
                if (v == M) continue;
                if ((re[v] == txt.charAt(i)) || re[v] == '.')
                    match.add(v+1);
            }

            dfs = new DirectedDFS(G, match);
            pc = new Bag<Integer>();

            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v)) pc.add(v);
            if (pc.size() == 0) return false;
        }

        for (int v: pc)
            if (v == M) return true;

        return false;

    }

    public Digraph buildDigraph() {
        Digraph G = new Digraph(M+1);
        Stack<Integer> ops = new Stack<Integer>();
        for (int i = 0; i < M; i++) {
            int lp = i;
            if (re[i] == '(' || re[i] == '|') ops.push(i);
            else if (re[i] == ')') {
                int or = ops.pop();
                if (re[or] == '|') {
                    lp = ops.pop();
                    G.addEdge(lp, or+1);
                    G.addEdge(or, i);
               }else
                   lp = or;
            }

            if (i < M-1 && re[i+1] == '*') {
                G.addEdge(lp, i+1);
                G.addEdge(i+1, lp);
            }

            if (re[i] == '('|| re[i] == '*' || re[i] == ')' )
                G.addEdge(i, i+1);
        }

        return G;
    }

    public static void main(String[] args) {
        String regexp = "(" + args[0] + ")";
        String txt = args[1];
        NFA nfa = new NFA(regexp);
        StdOut.println(nfa.recognizes(txt));
    }

}
