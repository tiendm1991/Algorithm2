import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
	// apply move-to-front encoding, reading from standard input and writing to
	// standard output
	public static void encode() {
//		String input = BinaryStdIn.readString();
		String input = "ABRACADABRA!";
		int N = input.length();
		CircularSuffixArray csa = new CircularSuffixArray(input);
		for(int i = 0; i < N; i++){
			if(csa.index(i) == 0) {
				BinaryStdOut.write(i);
				break;
			}
		}
		for(int i = 0; i < N; i++){
			int idx = csa.index(i);
			BinaryStdOut.write(input.charAt((idx+N-1) % N), 8);
		}
		BinaryStdOut.close();
	}

	// apply move-to-front decoding, reading from standard input and writing to
	// standard output
	public static void decode() {

	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args) {
		encode();
	}
}
