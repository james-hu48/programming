import java.io.*;
import java.util.*;

public class Main {
    FasterScanner sc = new FasterScanner();
    PrintWriter pw = new PrintWriter(System.out);
    static int MOD = 1000000007;

    public static void main(String[] args) {
        Main m = new Main();
        m.solve();
        m.done();
    }

    public void solve() {
        int t = sc.nextInt();
        for (int tt = 1; tt <= t; tt++) {
            int n = sc.nextInt();
            int[] P = new int[n];
            int[] H = new int[n];
            for (int i = 0; i < n; i++) {
                P[i] = sc.nextInt();
                H[i] = sc.nextInt();
            }
            Tree[] T = new Tree[n];
            for (int i = 0; i < n; i++) T[i] = new Tree(P[i], H[i]);
            Arrays.sort(T, (t1, t2) -> t1.pos - t2.pos);
            TreeMap<Integer, Integer> left = new TreeMap<>();
            TreeMap<Integer, Integer> right = new TreeMap<>();
            left.put(T[0].pos, T[0].h);
            right.put(T[0].pos+T[0].h, T[0].h);
            for (int i = 1; i < n; i++) {
                Tree tree = T[i];
                int lDiff = tree.pos-tree.h;
                int rDiff = tree.pos+tree.h;

                left.put(tree.pos, tree.h);
                // left + left  <--A<--B
                if (left.containsKey(lDiff)) {
                    left.put(tree.pos, left.get(lDiff)+tree.h);
                }
                // right + left  A--><--B
                if (right.containsKey(lDiff)) {
                    left.put(tree.pos, Math.max(left.get(tree.pos), right.get(lDiff)+tree.h));
                }
                if (!right.containsKey(rDiff)) right.put(rDiff, tree.h);
                // right + right  A-->B-->
                if (right.containsKey(tree.pos)) {
                    right.put(rDiff, Math.max(right.get(rDiff), right.get(tree.pos)+tree.h));
                }
            }
            int best = 0;
            for (Map.Entry<Integer, Integer> e : left.entrySet()) best = Math.max(best, e.getValue());
            for (Map.Entry<Integer, Integer> e : right.entrySet()) best = Math.max(best, e.getValue());
            pw.println("Case #" + tt + ": " + best);
        }
    }

    class Tree {
        int pos;
        int h;

        public Tree(int pos, int h) {
            this.pos = pos;
            this.h = h;
        }
    }

    public void done() {
        pw.flush();
        pw.close();
    }
}

class FasterScanner {
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = System.in.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public String nextLine() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }

    public String nextString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
        
    public int[] nextIntArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }
        return arr;
    }
    
    public long[] nextLongArray(int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextLong();
        }
        return arr;
    }

    private boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    private boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    }
}