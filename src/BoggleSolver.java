import edu.princeton.cs.algs4.TrieSET;

public class BoggleSolver {
	 // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
	TrieSET _dictSet;
    public BoggleSolver(String[] dictionary){
    	if(dictionary == null) 
    	_dictSet = new TrieSET();
    	for(String s : dictionary){
    		_dictSet.add(s);
    	}
    }

    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board){
    	
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
