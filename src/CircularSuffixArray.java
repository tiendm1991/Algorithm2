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
		
		char[][] sortSuffix = new char[N][N];
		String[] sortStr = new String[N];
		for(int i = 0; i < N; i++){
			sortStr[i] = new String(orgSuffix[i]);
		}
		Arrays.sort(sortStr);
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				sortSuffix[i][j] = sortStr[i].charAt(j);
			}
		}
		System.out.println("sort");
		printsuffix(sortSuffix);
		
		for(int i = 0; i < N; i++){
			int count = 0;
			for(int j = 0; j < N; j++){
				
			}
		}
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
