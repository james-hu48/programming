import java.util.*;
import java.io.*;

public class PROBLEMS {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] first = br.readLine().split(" ");
		int P = Integer.parseInt(first[0]);
		int S = Integer.parseInt(first[1]);
		StringBuilder ans = new StringBuilder();
		List<Pair> pairs = new ArrayList<> ();
		
		for (int p = 1; p <= P; p++) {
		    String[] scores = br.readLine().split(" ");
		    String[] solved = br.readLine().split(" ");
		    sort(scores, solved, 0, scores.length - 1);
		    int sc = 0;
		    for (int i = 0; i < solved.length - 1; i++) {
		        if (Integer.parseInt(solved[i]) > Integer.parseInt(solved[i + 1])) sc++;
		    }
		    pairs.add(new Pair(p, sc));
		}
		
		Collections.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.sc == o2.sc) return o1.level - o2.level;
                return o1.sc - o2.sc;
            }
        });
        
        for (Pair p : pairs) {
            ans.append(p.level + "" + '\n');
        }
        System.out.println(ans.toString());
	}
	
	public static void sort(String[] scores, String[] solved, int low, int high) {
	    if (low < high) {
            int pi = partition(scores, solved, low, high);
            sort(scores, solved, low, pi - 1);
            sort(scores, solved, pi + 1, high);
        }
	}
	
	public static int partition(String[] A, String[] B, int low, int high)
    {
        int pivot = Integer.parseInt(A[high]); 
        int i = low - 1;
        for (int j = low; j < high; j++)
        {
            if (Integer.parseInt(A[j]) <= pivot) {
                i++;
                String temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                temp = B[i];
                B[i] = B[j];
                B[j] = temp;
            }
        }
        String temp = A[i + 1];
        A[i + 1] = A[high];
        A[high] = temp;
        temp = B[i + 1];
        B[i + 1] = B[high];
        B[high] = temp;
        return i + 1;
    }
	
	static class Pair {
	    public int level;
	    public int sc;
	    
	    public Pair(int level, int sc) {
	        this.level = level;
	        this.sc = sc;
	    }
	}
}