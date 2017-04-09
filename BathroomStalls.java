import java.io.*;
import java.util.*;

public class BathroomStalls {

	public BathroomStalls() {
		// TODO Auto-generated constructor stub
	}
	
	// O(k * n * logn)
	public static int[] getLastResult(int n, int k) {
		int[] res = new int[2];
		
		TreeSet<Integer> stalls = new TreeSet<>();
		// positions of two guards
		stalls.add(0);
		stalls.add(n + 1);

		
		// for each person
		int best_ls = 0, best_rs = 0;		
		for (int i = 1; i <= k; i++) {
			int ls = 0, rs = 0;
			// try every position from right to left
			int bestPos = 0;
			int max_of_min = Integer.MIN_VALUE, max_of_max = Integer.MIN_VALUE;
			for (int j = n; j >= 1; j--) {
				// if this position has been occupied, skip
				if (stalls.contains(j)) {
					continue;
				}
				ls = j - stalls.lower(j) - 1;
				rs = stalls.higher(j) - j - 1;
				if (Math.min(ls, rs) > max_of_min) {
					max_of_min = Math.min(ls, rs);
					bestPos = j;
				} else if (Math.min(ls, rs) == max_of_min) {
					if (Math.max(ls, rs) >= max_of_max) {
						max_of_max = Math.max(ls, rs);
						bestPos = j;
					}
				}
				
			}
			// System.out.println(i + " : " + bestPos);
			best_ls = bestPos - stalls.lower(bestPos) - 1;
			best_rs = stalls.higher(bestPos) - bestPos - 1;
			stalls.add(bestPos);
			
		}
		res[0] = Math.max(best_ls, best_rs);
		res[1] = Math.min(best_ls, best_rs);
		return res;
	}

	// O(logn)
	public static long[] bathroomSolver(long n, long k) {
		TreeSet<Long> set = new TreeSet<>();
		// keep track the number of appearance of x
		HashMap<Long, Long> count = new HashMap<>();
		long numPerson = 0;
		// x0 : the number of right longest consecutive empty stalls
		// x1 : the number of left longest consecutive empty stalls
		long x0 = 0, x1 = 0;
		set.add(n);
		count.put(n, (long) 1);
		while (numPerson < k) {
			// take the current longest consecutive empty stalls
			long x = set.last();
			// divide them into two halves
			x0 = (long) Math.ceil((x - 1.0) / 2.0);
			x1 = (long) Math.floor((x - 1.0) / 2.0);
			numPerson += count.get(x);
			set.remove(x);
			set.add(x0);
			set.add(x1);	
			// idea : a same x will yield the same pair of x0 and x1
			// so we process them at the same time by increase count by C(X)
			count.put(x0, count.getOrDefault(x0, (long) 0) + count.get(x));
			count.put(x1, count.getOrDefault(x1, (long) 0) + count.get(x));
		}
		
		
		return new long[] {x0, x1};
		
	}


	public static void main(String[] args) {
	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
	    for (int i = 1; i <= t; ++i) {
	      long n = in.nextLong();
	      long k = in.nextLong();
//	      int[] res = getLastResult(n, k);
	      long[] res = bathroomSolver(n, k);
	      System.out.println("Case #" + i + ": " + res[0] + " " + res[1]);
	    }
		
	}

}
