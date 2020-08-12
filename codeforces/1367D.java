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
            String s = sc.nextLine();
            int m = sc.nextInt();
            int[] b = sc.nextIntArray(m);
            char[] C = s.toCharArray();
            Arrays.sort(C);
            TreeMap<Character, Integer> map = new TreeMap<>(Collections.reverseOrder());
            for (char c : C) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            char[] ans = new char[m];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                if (b[i] == 0) {
                    list.add(i);
                    b[i] = -1;
                }
            }
            char c = findLetter(map, list.size());
            for (int idx : list) {
                ans[idx] = c;
                subtract(b, idx);
            }

            while (nextZero(b) != -1) {
                list = new ArrayList<>();
                for (int i = 0; i < m; i++) {
                    if (b[i] == 0) {
                        list.add(i);
                        b[i] = -1;
                    }
                }
                c = findLetter(map, list.size());
                for (int idx : list) {
                    ans[idx] = c;
                    subtract(b, idx);
                }
            }
            pw.println(String.valueOf(ans));
        }
    }
    
    public char findLetter(TreeMap<Character, Integer> map, int cnt) {
        char ans = ';';
        List<Character> keys = new ArrayList<>();
        for (Map.Entry<Character,Integer> entry : map.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (value >= cnt) {
                ans = key;
                break;
            }
            keys.add(key);
        }
        for (char c : keys) map.remove(c);
        map.remove(ans);
        return ans;
    }

    public void subtract(int[] b, int index) {
        int diff = 0;
        for (int i = index; i >= 0; i--) {
            b[i] -= diff;
            diff++;
        }
        diff = 0;
        for (int i = index; i < b.length; i++) {
            b[i] -= diff;
            diff++;
        }
    }

    public int nextZero(int[] b) {
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) return i;
        }
        return -1;
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