import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BoggleSolver {
	// Initializes the data structure using the given array of strings as the
	// dictionary.
	// (You can assume each word in the dictionary contains only the uppercase
	// letters A through Z.)
	private MyTrieSet _dictSet;
	private boolean visited[][];

	public BoggleSolver(String[] dictionary) {
		if (dictionary == null)
			throw new IllegalArgumentException();
		_dictSet = new MyTrieSet();
		
		for (String s : dictionary) {
			if(s.length() >= 3){
				_dictSet.add(s);
			}
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		int r = board.rows();
		int c = board.cols();
		Set<String> validWordsSet = new HashSet<>();
		visited = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				getValidWordAtPosition(board, i, j, "", validWordsSet);
			}
		}
		return validWordsSet;
	}

	private void getValidWordAtPosition(BoggleBoard board, int r, int c, String prefix, Set<String> validWordsSet) {
		if (visited[r][c]) return;
		char charCur = board.getLetter(r, c);
		String word = prefix;
		if (charCur == 'Q') {
			word += "QU";
		} else {
			word += charCur;
		}
		if (word.length() >= 3 && _dictSet.contains(word)){
			validWordsSet.add(word);
		}
		visited[r][c] = true;
		if (_dictSet.hasKeysWithPrefix(word)) {
			for(int i = r-1; i <= r+1; i++){
				if(i < 0 || i >= board.rows()) continue;
				for(int j = c-1; j <= c+1; j++){
					if(j < 0 || j >= board.cols() || (i == r && j == c))continue;
					getValidWordAtPosition(board, i, j, word, validWordsSet);
				}
			}
		}
		visited[r][c] = false;
	}
	

	// Returns the score of the given word if it  is in the dictionary, zero
	// otherwise.
	// (You can assume the word contains only the uppercase letters A through
	// Z.)
	public int scoreOf(String word) {
		if (word == null)
			throw new IllegalArgumentException();
		int length = word.length();
		if (!_dictSet.contains(word) || length < 3)
			return 0;
		if (length == 3 || length == 4)
			return 1;
		if (length > 4 && length < 7)
			return length - 3;
		if (length == 7)
			return 5;
		return 11;
	}

	public static void main(String[] args) {
		String dictFile = "dictionary-common.txt";
		String boardFile = "board-test.txt";
		
		In in = new In(dictFile);
		String[] dictionary = in.readAllStrings();
		BoggleSolver solver = new BoggleSolver(dictionary);
		BoggleBoard board = new BoggleBoard(boardFile);
		int score = 0;
		int count = 0;
		for (String word : solver.getAllValidWords(board)) {
			StdOut.println((++count) + ". " + word);
			score += solver.scoreOf(word);
		}
		StdOut.println("Score = " + score);
	}
}
