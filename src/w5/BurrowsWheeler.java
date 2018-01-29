package w5;
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
         char[] orgChar = chars.toCharArray();
         char[] sortChar = chars.toCharArray();
         int[] next = new int[orgChar.length];
         Set<Integer> exist = new HashSet<>();
         Arrays.sort(sortChar);
         for(int i = 0; i < sortChar.length; i++){
        	 for(int j = 0; j < orgChar.length; j++){
        		 if(orgChar[j] == sortChar[i]) {
        			 if(!exist.contains(j)){
        				 exist.add(j);
        				 next[i] = j;
        				 break;
        			 }
        		 }
        	 }
         }
         System.out.println(Arrays.asList(next));
         for(int i = first, count = 0; count < next.length; i = next[i]){
        	 BinaryStdOut.write(sortChar[i],8);
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
