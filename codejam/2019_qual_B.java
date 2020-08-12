import java.util.*;
import java.io.*;

public class q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();

        for (int i = 1; i <= t; i++) {
            int n = in.nextInt();
            in.nextLine();
            String moves = in.nextLine();
            solve(i, moves);
        }
        in.close();
    }

    public static void solve(int idx, String moves) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'E') {
                sb.append('S');
            } else {
                sb.append('E');
            }
        }
        System.out.printf("Case #%d: %s\n", idx, sb.toString());
    }
}