package w5;

import java.util.Iterator;
import java.util.LinkedList;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	
	private static final int ALPHABET_SIZE = 256;
	
	// apply move-to-front encoding, reading from standard input and writing to
	// standard output
	public static void encode() {
		LinkedList<Character> moveToFront = buildAnsci();
		 while (!BinaryStdIn.isEmpty()) {
			 char c = BinaryStdIn.readChar();
			 int count = 0;
			 for(Iterator<Character> it = moveToFront.iterator();it.hasNext(); ){
				 Character curChar = it.next();
				 if(curChar == c){
					 BinaryStdOut.write(count,8);
					 it.remove();
					 moveToFront.add(0, curChar);
					 break;
				 }
				 count++;
			 }
		 }
		 BinaryStdOut.close();
	}

	// apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() {
		LinkedList<Character> moveToFront = buildAnsci();
		 while (!BinaryStdIn.isEmpty()) {
			 int idx = BinaryStdIn.readChar();
			 char c = moveToFront.get(idx);
			 BinaryStdOut.write(c,8);
			 moveToFront.remove(idx);
			 moveToFront.add(0, c);
		 }
		 BinaryStdOut.close();
	}
	
	private static LinkedList<Character> buildAnsci(){
		LinkedList<Character> list = new LinkedList<>();
		for(int i = 0; i < ALPHABET_SIZE; i++){
			list.add((char) i);
		}
		return list;
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		encode();
	}
}
