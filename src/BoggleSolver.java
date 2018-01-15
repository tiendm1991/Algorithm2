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
			_dictSet.add(s);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an
	// Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		int r = board.rows();
		int c = board.cols();
		Set<String> validWordsSet = new HashSet<>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				visited = new boolean[r][c];
				getValidWordAtPosition(board, i, j, "", validWordsSet);
			}
		}
		return validWordsSet;
	}

	private void getValidWordAtPosition(BoggleBoard board, int r, int c, String prefix, Set<String> validWordsSet) {
		if (visited[r][c]) return;
		char charCur = board.getLetter(r, c);
		if (charCur == 'Q') {
			prefix += "QU";
		} else {
			prefix += charCur;
		}
		if (prefix.length() >= 3 && _dictSet.contains(prefix)){
			validWordsSet.add(prefix);
		}
		visited[r][c] = true;
		if (_dictSet.hasKeysWithPrefix(prefix)) {
			for(int i = -1; i <= 1; i++){
				for(int j = -1; j <= 1; j++){
					if(i ==0 && j == 0) continue;
					if(isValid(r+i, c+j, board.rows(), board.cols())){
						getValidWordAtPosition(board, r + i, c + j, prefix, validWordsSet);
					}
				}
			}
		}
		visited[r][c] = false;
	}
	
	private boolean isValid(int r, int c, int nbRow, int nbCol){
		return r >= 0 && r < nbRow && c >= 0 && c < nbCol;
	}

//	private boolean checkContainPrefix(String prefix) {
//		Iterable<String> keyWithPrefix = _dictSet.keysWithPrefix(prefix);
//		if (keyWithPrefix == null)
//			return false;
//		for (Iterator<String> it = keyWithPrefix.iterator(); it.hasNext();) {
//			return true;
//		}
//		return false;
//	}

	// Returns the score of the given word if it is in the dictionary, zero
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
		String dictFile = "dictionary-yawl.txt";
		String boardFile = "board-qwerty.txt";
		
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
