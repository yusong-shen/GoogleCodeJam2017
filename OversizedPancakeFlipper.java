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
Raw
 TidyNumber.java
/**
 * 
 */

import java.util.*;
import java.io.*;

/**
 * @author yusong
 *
 */
public class TidyNumber {

	/**
	 * 
	 */
	public TidyNumber() {

	
	}
	
	private static long exp10(int x) {
		long res = 1;
		for (int i = 0; i < x; i++) {
			res *= 10;
		}
		return res;
	}
	
	private static long digits2number(int[] digits) {
		int n = digits.length;
		char[] chars = new char[n];
		for (int i = 0; i < n; i++) {
			chars[i] = (char) (digits[i] + '0');
		}	
		String res = new String(chars);
		return Long.parseLong(res);
	}
	
	public static long lastTidyNumber(long num) {
		if (num < 10) return num;
		String s = "" + num;
		int nDigit = s.length();

		char[] chars = s.toCharArray();
		int[] digits = new int[nDigit];
		for (int i = 0; i < nDigit; i++) {
			digits[i] = chars[i] - '0';
		}
		
		// nDigit >= 2
		int[] res = computeTidyNumber(digits);

		return digits2number(res);
	}
	
	public static int[] computeTidyNumber(int[] digits) {
		int n = digits.length;
		int[] res = Arrays.copyOf(digits, n);
		while (!isTidyNumber(res)) {
			res = Arrays.copyOf(digits, n);
			for (int i = 1; i < n; i++) {
				if (digits[i - 1] > digits[i]) {
					res[i - 1] = digits[i - 1] - 1;
					// fill the rest least significant bits to 9
					Arrays.fill(res, i, n, 9);
					break;
				}
			}	
			digits = res;
		}
		return res;
	}

	public static boolean isTidyNumber(int[] digits) {
		int prevDigit = -1;
		int i = 0;
		// skip leading 0
		while (digits[i] == 0) {
			i++;
		}
		
		for (; i + 1 < digits.length; i++) {
			if (digits[i] > digits[i + 1]) {
				return false;
			}
			
		}
		
		return true;
	}	
	
	public static boolean isTidyNumber(long num) {
		long prevDigit = 11;
		while (num != 0) {
			long digit = num % 10;
			if (prevDigit < digit) {
				return false;
			}
			prevDigit = digit;
			num = num / 10;
		}
		
		
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	    Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
	    int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
	    for (int i = 1; i <= t; ++i) {
	      long n = in.nextLong();
	      System.out.println("Case #" + i + ": " + lastTidyNumber(n));
	    }
	}

}
