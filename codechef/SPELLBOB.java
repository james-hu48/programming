import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder ans = new StringBuilder();

		for (int i = 0; i < T; i++) {
			char[] A = br.readLine().toCharArray();
			char[] B = br.readLine().toCharArray();
			
			List<String> temp = new ArrayList<> ();
			permute(temp, "", A, B, new boolean[3]);
			if (check(temp)) 
				ans.append("yes" + '\n');
			else
				ans.append("no" + '\n');
		}
		System.out.println(ans.toString());
	}

	public static boolean check(List<String> ans) {
		for (String s : ans) {
			if (s.equals("bob")) return true;
		}
		return false;
	}

	public static void permute(List<String> ans, String s, char[] A, char[] B, boolean[] used) {
		if (s.length() == 3) {
			ans.add(s);
			return;
		}
		
		for (int i = 0; i < 3; i++) {
		    if (used[i]) continue;
		    used[i] = true;
		    permute(ans, s + A[i], A, B, used);
            permute(ans, s + B[i], A, B, used);
            used[i] = false;
		}
	}
}