import java.util.Arrays;
import java.util.Collections;

public class CircularSuffixArray {
	
	private String orgString;
	private int N;
	private Integer[] index;
	
	public CircularSuffixArray(String s) { // circular suffix array of s
		if(s == null || s.equals("") ) 
	         throw new java.lang.IllegalArgumentException();
		this.orgString = s;
		this.N = s.length();
		index = new Integer[s.length()];
		char[][] orgSuffix = new char[N][N];
		for(int i = 0; i < N; i++){
			orgSuffix[0][i] = orgString.charAt(i);
			index[i] = i;
		}
		for(int i = 1; i < N; i++){
			for(int j = 0; j < N; j++){
				if(j <= N-2){
					orgSuffix[i][j] = orgSuffix[i-1][j+1]; 
				}else {
					orgSuffix[i][j] = orgSuffix[i-1][0]; 
				}
			}
		}
		System.out.println("org");
		printsuffix(orgSuffix);
		String[] sortStr = new String[N];
		for(int i = 0; i < N; i++){
			sortStr[i] = new String(orgSuffix[i]);
		}
		
		for(int i = 0; i < N-1; i++){
			for(int j = i+1; j < N; j++){
				if(sortStr[index[i]].compareTo(sortStr[index[j]]) > 0) {
					swapIdx(i, j); 
				}
			}
		}
		
		System.out.println(Arrays.asList(index));
	}
	private void swapIdx(int i, int j){
		int tmp = index[i];
		index[i] = index[j];
		index[j] = tmp;
	}

	public int length() { // length of s
		return N;
	}

	public int index(int i) { // returns index of ith sorted suffix
		return index[i];
	}
	
	private void printsuffix(char[][] suff){
		for(int i = 0; i < suff.length; i++){
			for(int j = 0; j < suff.length; j++){
				System.out.print(suff[i][j] + "\t");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) { // unit testing (required)
		CircularSuffixArray x= new CircularSuffixArray("ABRACADABRA!");
	}
}
