/**
 * Created by Mofeng on 2017/5/18.
 */
public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;
    public DirectedEdge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    double weight()
    { return weight;}
    public int from()
    {
        return v;
    }
    public int to()
    {
        return w;
    }
    public String toString()
    {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
