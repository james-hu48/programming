import java.util.*;
import java.io.*;

public class q1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            solve(i, n);
        }
        in.close();
    }

    public static void solve(int idx, int n) {
        String s = String.valueOf(n);
        char[] digits = s.toCharArray();
        char[] digitsA = new char[s.length()];
        char[] digitsB = new char[s.length()];

        for (int i = 0; i < digits.length; i++) {
            char c = digits[i];
            if (c == '4') {
                digitsA[i] = '3';
                digitsB[i] = '1';
            } else {
                digitsA[i] = c;
                digitsB[i] = '0';
            }
        }
        System.out.printf("Case #%d: %s %s\n", idx, new String(digitsA), new String(digitsB));
    }
}