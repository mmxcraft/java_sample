import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mofeng on 2017/6/7.
 */
public class BoyerMoore {
    private final int R;
    private int[] right;
    private char[] pattern;
    private String pat;

    public BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        right = new int[R];
        for (int i = 0; i < R; i++)
            right[i] = -1;

        for (int i = 0; i < pat.length(); i++)
            right[pat.charAt(i)] = i;
    }

    public BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = pattern;

        for (int i = 0; i < pattern.length; i++)
            this.pattern[i] = pattern[i];

        right = new int[R];
        for (int i = 0; i < R; i++)
            right[i] = -1;

        for (int i = 0; i < pattern.length; i++)
            right[pattern[i]] = i;
    }

    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();

        int skip;

        for (int i = 0; i <= n-m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--){
                if(pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0)  return i;
        }

        return n;
    }

    public int search(char[] text) {
        int m = pattern.length;
        int n = text.length;

        int skip;

        for (int i = 0; i <= n-m; i += skip){
            skip = 0;

            for (int j = m -1 ; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;
        }
        return n;
    }

    public static void main(String[] args){
        String pat = args[0];
        String txt = args[1];

        char[] pattern = pat.toCharArray();
        char[] text = txt.toCharArray();

        BoyerMoore b1 = new BoyerMoore(pat);
        BoyerMoore b2 = new BoyerMoore(pattern, 256);

        int offset1 = b1.search(txt);
        int offset2 = b2.search(text);

        StdOut.println("text:     "+txt);
        StdOut.print("pattern:  ");
        for (int i = 0; i < offset1;i++)
            StdOut.print(" ");
        StdOut.println(pat);

        StdOut.print("pattern:  ");
        for (int i = 0; i < offset2;i++)
            StdOut.print(" ");
        StdOut.println(pat);


    }
}
