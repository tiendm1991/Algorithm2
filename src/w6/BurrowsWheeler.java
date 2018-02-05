package w6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;

public class BurrowsWheeler {

	// apply Burrows-Wheeler transform, reading from standard input and writing
	// to standard output
	public static void transform() {
		String input = BinaryStdIn.readString();
		int N = input.length();
		CircularSuffixArray csa = new CircularSuffixArray(input);
		for (int i = 0; i < N; i++) {
			if (csa.index(i) == 0) {
				BinaryStdOut.write(i);
			}
		}
		for (int i = 0; i < N; i++) {
			int idx = csa.index(i);
			BinaryStdOut.write(input.charAt((idx + N - 1) % N), 8);
		}
		BinaryStdOut.close();
	}

	// apply Burrows-Wheeler inverse transform, reading from standard input and
	// writing to standard output
	public static void inverseTransform() {
		int first = BinaryStdIn.readInt();
		String chars = BinaryStdIn.readString();
		char[] arr = chars.toCharArray();
		int[] next = new int[arr.length];
		Map<Character, Integer> positions = new HashMap<Character, Integer>();
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			Integer p = positions.get(arr[i]);
			if (p == null)
				p = 0;
			for (int j = p; j < arr.length; j++) {
				if (chars.charAt(j) == arr[i]) {
					next[i] = j;
					positions.put(arr[i], j + 1);
					break;
				}
			}
		}

		for (int i = first, count = 0; count < arr.length; i = next[i]) {
			BinaryStdOut.write(arr[i], 8);
			count++;
		}
		BinaryStdOut.close();
	}

	// if args[0] is '-', apply Burrows-Wheeler transform
	// if args[0] is '+', apply Burrows-Wheeler inverse transform
	public static void main(String[] args) {
		inverseTransform();
	}
}
