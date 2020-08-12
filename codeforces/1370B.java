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
        for (int tt = 0; tt < t; tt++) {
            int n = sc.nextInt();
            int[] A = sc.nextIntArray(2*n);
            int high1 = -1;
            int high2 = -1;
            for (int i = 0; i < 2*n; i++) {
                if (high1 < A[i]) {
                    high2 = high1;
                    high1 = A[i];
                } else if (high2 < A[i]) {
                    high2 = A[i];
                }
            }
            int[] ans = new int[2*n];
            for (int factor = 2; factor <= high1 + high2; factor++) {
                Arrays.fill(ans, -1);
                Set<Integer> set = new HashSet<>();
                for (int i = 0; i < 2*n; i++) {
                    if (set.contains(i)) continue;
                    for (int j = 0; j < 2*n; j++) {
                        if (i == j) continue;
                        if (set.contains(j)) continue;
                        if ((A[i] + A[j]) % factor == 0) {
                            ans[i] = j;
                            set.add(i);
                            set.add(j);
                            break;
                        }
                    }
                }
                if (set.size() >= 2*n-2) break;
            }
            int cnt = 0;
            for (int i = 0; i < 2*n; i++) {
                if (ans[i] != -1) {
                    cnt++;
                    pw.println((i+1) + " " + (ans[i]+1));
                }
                if (cnt == n-1) break;
            }
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