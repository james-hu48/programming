import java.io.*;
import java.util.*;

class TTUPLE {
    FasterScanner sc = new FasterScanner();
    PrintWriter pw = new PrintWriter(System.out);
    static int MOD = 1000000007;

    public static void main(String[] args) {
        TTUPLE m = new TTUPLE();
        m.solve();
        m.done();
    }

    public void solve() {
        int t = sc.nextInt();
        for (int tt = 0; tt < t; tt++) {
            int p1 = sc.nextInt();
            int q1 = sc.nextInt();
            int r1 = sc.nextInt();
            int a1 = sc.nextInt();
            int b1 = sc.nextInt();
            int c1 = sc.nextInt();
            List<Pair> list = new ArrayList<>();
            list.add(new Pair(new int[]{p1,q1,r1}, new int[]{a1,b1,c1}));
            list.add(new Pair(new int[]{p1,r1,q1}, new int[]{a1,c1,b1}));
            list.add(new Pair(new int[]{q1,p1,r1}, new int[]{b1,a1,c1}));
            list.add(new Pair(new int[]{q1,r1,p1}, new int[]{b1,c1,a1}));
            list.add(new Pair(new int[]{r1,q1,p1}, new int[]{c1,b1,a1}));
            list.add(new Pair(new int[]{r1,p1,q1}, new int[]{c1,a1,b1}));
            int best = 3;
            long p = 0, q = 0, r = 0, a = 0, b = 0,c = 0;
            for (Pair pair : list) {
                int[] start = pair.start; // {p, q, r}
                int[] end = pair.end; // {a, b, c}
                p = start[0];
                q = start[1];
                r = start[2];
                a = end[0];
                b = end[1];
                c = end[2];
                // System.out.printf("(%d, %d, %d) -> (%d, %d, %d)\n", p, q, r, a, b, c);
                long d = a-p;
                if (a == p) { // one elem correct
                    best = Math.min(best, 2);
                    if (b == q && c == r) best = 0; // all elems are correct
                    if (b == q) best = Math.min(best, 1); // two elems are correct
                    if (b-q == c-r) best = Math.min(best, 1); // one elem correct, other two are same difference
                    else best = Math.min(best, 2); // one elem correct, other two are not same difference
                    if (q != 0 && r != 0 && b % q == 0 && c % r == 0)
                        if (b/q == c/r) best = Math.min(best, 1); // one elem correct, other two are same factor
                }
                if (b-q == d && c-r == d) best = Math.min(best, 1); // all elems are same difference
                
                // ADD ADD
                if (b-q == c-r) best = Math.min(best, 2); // add (or mult) p -> add q,r
                if (b-(q+d) == c-r) best = Math.min(best, 2); // add p,q -> add q,r
                // System.out.println("ADD ADD " + best);
                // ADD MULT
                if (q != 0 && r != 0 && b % q == 0 && c % r == 0)
                    if (b/q == c/r) best = Math.min(best, 2); // add (or mult) p -> mult q,r
                if ((q+d) != 0 && r != 0 && b % (q+d) == 0 && c % r == 0)
                    if (b/(q+d) == c/r) best = Math.min(best, 2); // add p,q -> mult q,r
                if ((b-a) != 0 && (a*q-b*p) % (b-a) == 0) {
                    d = (a*q-b*p)/(b-a);
                    if ((q+d) != 0 && (p+d) != 0) {
                        if (r != 0 && a % (p+d) == 0 && b % (q+d) == 0 && c % r == 0)
                            if (a/(p+d) == b/(q+d) && a/(p+d) == c/r && b/(q+d) == c/r) best = Math.min(best, 2); // add p,q -> mult p,q,r
                        if ((r+d) != 0 && a % (p+d) == 0 && b % (q+d) == 0 && c % (r+d) == 0)
                            if (a/(p+d) == b/(q+d) && a/(p+d) == c/(r+d) && b/(q+d) == c/(r+d)) best = Math.min(best, 2); // add p,q,r -> mult p,q,r
                    }
                }
                d = c-r;
                if ((p+d) != 0 && (q+d) != 0 && a % (p+d) == 0 && b % (q+d) == 0)
                    if (a/(p+d) == b/(q+d)) best = Math.min(best, 2); // add p,q,r -> mult p,q
                // System.out.println("ADD MULT " + best);
                if (p != 0 && q != 0 && r != 0) { // if p,q,r are 0, then cannot be MULT MULT
                    if (a % p == 0 && b % q == 0 && c % r == 0) 
                        if (a/p == b/q && a/p == c/r && b/q == c/r) best = Math.min(best, 1); // all elems are same factor
                    // MULT MULT
                    if (a % p == 0) {
                        d = a/p;
                        if (d != 0 && b % (q*d) == 0 && c % r == 0)
                            if (b/(q*d) == c/r) best = Math.min(best, 2); // mult p,q -> mult q,r
                    }
                    if (c != 0 && (b*r) % (c*q) == 0 && a % p == 0 && b % q == 0 && c % r == 0) {
                        d = (b*r)/(c*q);
                        if (d != 0 && a % (p*d) == 0 && b % (q*d) == 0)
                            if (a/p == b/q && a/(p*d) == c/r && b/(q*d) == c/r) best = Math.min(best, 2); // mult p,q -> mult p,q,r
                    }
                }
                // System.out.println("MULT MULT " + best);
                // MULT ADD
                if (p != 0 && a % p == 0) {
                    d = a/p;
                    if (b-(q*d) == c-r) best = Math.min(best, 2); // mult p,q -> add q,r
                }
                if (q != 0 && (b-(c-r)) % q == 0) {
                    d = (b-(c-r))/q;
                    if (a-p*d == b-q*d && a-p*d == c-r && b-q*d == c-r) best = Math.min(best, 2); // mult p,q -> add p,q,r
                }
                if ((p-q) != 0 && (a-b) % (p-q) == 0) {
                    d = (a-b)/(p-q);
                    if (a-p*d == b-q*d && a-p*d == c-r*d && b-q*d == c-r*d) best = Math.min(best, 2); // mult p,q,r -> add p,q,r
                }
                if (r != 0 && c % r == 0) {
                    d = c/r;
                    if (a-p*d == b-q*d) best = Math.min(best, 2); // mult p,q,r -> add p,q
                }
                // System.out.println("MULT ADD " + best);
            }
            pw.println(best);
        }
    }

    class Pair {
        int[] start;
        int[] end;

        public Pair(int[] start, int[] end) {
            this.start = start;
            this.end = end;
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