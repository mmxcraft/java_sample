/**
 * Created by mofma on 2016/12/16.
 */
public class MaxPQ<Key extends Comparable<Key>>
{
    private Key[] pq;
    private int N;
    public MaxPQ(int capacity)
    {
        pq = (Key[]) new Comparable[capacity+1];
        N =0;
    }
    public boolean isEmpty()
    { return N == 0; }
    public int getSize()
    {
        return N;
    }
    public Key getMax()
    { return pq[1]; }
    public void insert(Key key)
    {
        pq[++N] = key;
        swim(N);
    }
    public Key delMax()
    {
        Key max = pq[1];
        exch(1,N--);
        sink(1);
        pq[N+1] = null;
        return max;
    }
    private void swim(int k)
    {
        while(k>1 && less(k/2,k)) {
            exch(k,k/2);
            k = k/2;
        }
    }
    private void sink(int k)
    {
        while(2*k <= N) {
            int j = 2*k;
            if(j<N && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }
    private boolean less(int i, int j)
    {
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j)
    {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    public static void main(String[] args)
    {
        MaxPQ<Character> testMaxPQ = new MaxPQ<Character> (128);
        testMaxPQ.insert('P');
        testMaxPQ.insert('A');
        testMaxPQ.insert('D');
        testMaxPQ.insert('S');
        testMaxPQ.insert('Z');
        testMaxPQ.insert('R');
        testMaxPQ.insert('M');
        testMaxPQ.insert('N');
        System.out.printf("N=%d, max=%c\n",testMaxPQ.getSize(),testMaxPQ.getMax());

        testMaxPQ.delMax();
        testMaxPQ.delMax();
        testMaxPQ.insert('H');
        System.out.printf("N=%d, max=%c\n",testMaxPQ.getSize(),testMaxPQ.getMax());



    }
}
