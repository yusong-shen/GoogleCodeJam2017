import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author yusong
 *
 */
public class OversizedPancakeFlipper {

	/**
	 * 
	 */
	public OversizedPancakeFlipper() {
		// TODO Auto-generated constructor stub
	}

	// O(N) Greedy Approach by flipping one pancake at at time
	public static String pancakeSolver(char[] pancakes, int size) {
		// System.out.println(new String(pancakes) + ", " + size);
		int n = pancakes.length;
		int[] memo = new int[n + 1];
		// keeping a running count of the number of flips that we "owe" the current pancake.
		int numFlips = 0;
		int res = 0;
		// First, flip the leftmost N - K + 1 pancakes
		int i = 0;
		for (; i + size <= n; i++) {
			numFlips -= memo[i];
			// currently blank side
			if (pancakes[i] == '-' && numFlips % 2 == 0 ||
					pancakes[i] == '+' && numFlips % 2 == 1) {
				// can be modified to output operations
				// System.out.println("Flip! : " + i);
				numFlips += 1;
				res++;
				// when we move to (i + size)th pancake, we need to decrease those amount of flips
				memo[i + size]++; 
			}
			// else happy side just don't flip it
			
		}
		// Finally, check the last K - 1 pancakes, they must be all happy sides
		for (; i < n; i ++) {
			numFlips -= memo[i];
			if (pancakes[i] == '-' && numFlips % 2 == 0 ||
					pancakes[i] == '+' && numFlips % 2 == 1) {	
				return "IMPOSSIBLE";
			}
		}
		
		return "" + res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = Integer.parseInt(in.nextLine());  // Scanner has functions to read ints, longs, strings, chars, etc.
	    for (int i = 1; i <= t; ++i) {
	      String[] line = in.nextLine().split("\\s");
	      char[] pancakes = line[0].toCharArray();
	      int size = Integer.parseInt(line[1]);
	      System.out.println("Case #" + i + ": " + pancakeSolver(pancakes, size));
	    }
	}

}
