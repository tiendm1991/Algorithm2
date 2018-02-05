package w6;
import java.util.Arrays;
import java.util.Comparator;

public class CircularSuffixArray {
	
	private int N;
	private Integer[] index;
	
	public CircularSuffixArray(String s) { // circular suffix array of s
		if(s == null) throw new java.lang.IllegalArgumentException();
		this.N = s.length();
		index = new Integer[s.length()];
		char[] orgChar = s.toCharArray();
		for(int i = 0; i < N; i++){
			index[i] = i;
		}
		Arrays.sort(index, new Comparator<Integer>() {
			@Override
			public int compare(Integer idx1, Integer idx2) {
				for(int i = 0; i < N; i++){
					int compare = orgChar[(idx1+i)%N] - orgChar[(idx2+i)%N];
					if(compare != 0){
						return compare;
					}
				}
				return 0;
			}
		});
	}
	
	public int length() { // length of s
		return N;
	}

	public int index(int i) { // returns index of ith sorted suffix
		if(i < 0 || i >= N) throw new java.lang.IllegalArgumentException();
		return index[i];
	}
	
	public static void main(String[] args) { // unit testing (required)
		CircularSuffixArray x = new CircularSuffixArray("ABRACADABRA!");
	}
}
