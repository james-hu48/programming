import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();
		int[] values = precompute();
		
		for (int i = 0; i < T; i++) {
		    int N = Integer.parseInt(br.readLine());
		    if (N <= values[0]) {
		        ans.append((values[0] - N) + "" + '\n');
		        continue;
		    }
		    int diff = 0;
		    for (int j = 1; j < values.length; j++) {
		        if (values[j] == N) break;
		        if (values[j] > N) {
		            diff = Math.min(Math.abs(N - values[j - 1]), Math.abs(N - values[j]));
		            break;
		        }
		    }
            ans.append(diff + "" + '\n');
		}
		System.out.println(ans.toString());
	}
	
	public static int[] precompute() {
	    Queue<Integer> res = new PriorityQueue<> ();
	    Set<Integer> set = new HashSet<> ();
	    for (int x = 0; x <= 30; x++) {
	        for (int y = 0; y <= 30; y++) {
	            int val = (int)(Math.pow(2, x) + Math.pow(2, y));
	            if (x != y && !set.contains(val)) {
	                res.add(val);
	                set.add(val);
	            }
	        }
	    }
	    int[] temp = new int[res.size()];
	    int i = 0;
	    while (!res.isEmpty()) {
	        temp[i++] = res.poll();
	    }
	    return temp;
	}
}