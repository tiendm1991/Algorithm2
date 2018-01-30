
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
	
	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
    public static void transform(){
    	String input = BinaryStdIn.readString();
    	int N = input.length();
    	CircularSuffixArray csa = new CircularSuffixArray(input);
    	for(int i = 0; i < N; i++){
    		if(csa.index(i) == 0) {
    			BinaryStdOut.write((int)i);
    		}
    	}
    	for(int i = 0; i < N; i++){
    		int idx = csa.index(i);
    		BinaryStdOut.write(input.charAt((idx + N - 1) % N), 8);
    	}
    	BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
    public static void inverseTransform(){
    	 int first = BinaryStdIn.readInt();
         String chars = BinaryStdIn.readString();
         char[] arr = chars.toCharArray();
         int[] next = new int[arr.length];
         boolean[] exist = new boolean[arr.length];
         Arrays.sort(arr);
         for(int i = 0; i < arr.length; i++){
        	 for(int j = 0; j < arr.length; j++) {
        		 if(chars.charAt(j) == arr[i]) {
        			 if(!exist[j]) {
        				 next[i] = j;
        				 exist[j] = true;
        				 break;
        			 }
        		 }
        	 }
         }
         for(int i = first, count = 0; count < arr.length; i = next[i]){
        	 BinaryStdOut.write(arr[i],8);
        	 count++;
         }
         BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler transform
    // if args[0] is '+', apply Burrows-Wheeler inverse transform
    public static void main(String[] args){
    	inverseTransform();
    }
}
