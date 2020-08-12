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
        int n = sc.nextInt();
        int[] cnts = new int[5];
        for (int i = 0; i < n; i++) {
            int s = sc.nextInt();
            cnts[s]++;
        }
        int ans = cnts[4];
        if (cnts[3] != 0) {
            if (cnts[3] >= cnts[1]) {
                ans += cnts[3];
                cnts[1] = 0;
            } else {
                ans += cnts[3];
                cnts[1] = cnts[1] - cnts[3];
            }
        }
        if (cnts[2] != 0) {
            ans += cnts[2] / 2;
            if (cnts[2] % 2 == 1) {
                ans++;
                cnts[1] = cnts[1] - 2;
            }
        }
        if (cnts[1] > 0) {
            ans += cnts[1] / 4;
            if (cnts[1] % 4 != 0) ans++;
        }
        pw.println(ans);
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