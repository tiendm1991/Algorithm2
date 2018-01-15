import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.princeton.cs.algs4.TrieSET;

public class BoggleSolver {
	 // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	private TrieSET _dictSet;
	private boolean visited[][];
    public BoggleSolver(String[] dictionary){
    	if(dictionary == null) 
    	_dictSet = new TrieSET();
    	for(String s : dictionary){
    		_dictSet.add(s);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	int r = board.rows();
    	int c = board.cols();
    	visited = new boolean[r][c];
    	Set<String> validWordsSet = new HashSet<>();
    	for(int i = 0; i < r; i++){
    		for(int j = 0; j < c; j++){
    			getValidWordAtPosition(board, i, j, "", validWordsSet);
    		}
    	}
    	return validWordsSet;
    }

    private void getValidWordAtPosition(BoggleBoard board, int r, int c, String prefix, Set<String> validWordsSet) {
		if(r < 0 || r >= board.rows() || c < 0|| c >= board.cols() || visited[r][c]) return;
		char charCur = board.getLetter(r, c);
		if(charCur == 'Q'){
			prefix += "Qu";
		}else {
			prefix += charCur;
		}
		if(_dictSet.contains(prefix)) validWordsSet.add(prefix);
		if(checkContainPrefix(prefix)){
			getValidWordAtPosition(board, r-1, c-1, prefix, validWordsSet);
			getValidWordAtPosition(board, r-1, c, prefix, validWordsSet);
			getValidWordAtPosition(board, r-1, c+1, prefix, validWordsSet);
			getValidWordAtPosition(board, r, c+1, prefix, validWordsSet);
			getValidWordAtPosition(board, r+1, c+1, prefix, validWordsSet);
			getValidWordAtPosition(board, r+1, c, prefix, validWordsSet);
			getValidWordAtPosition(board, r+1, c-1, prefix, validWordsSet);
			getValidWordAtPosition(board, r, c-1, prefix, validWordsSet);
		}
	}

	private boolean checkContainPrefix(String prefix) {
		Iterable<String> keyWithPrefix = _dictSet.keysWithPrefix(prefix);
		if(keyWithPrefix == null) return false;
		for(Iterator<String> it = keyWithPrefix.iterator(); it.hasNext();){
			return true;
		}
		return false;
	}

	// Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word){
    	if(word == null) throw new IllegalArgumentException();
    	int length = word.length();
    	if(!_dictSet.contains(word) || length < 3) return 0;
    	if(length == 3 || length == 4) return 1;
    	if(length > 4 && length < 7) return length - 3;
    	if(length == 7) return 5;
    	return 11;
    }
}
