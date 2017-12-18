import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	public Outcast(WordNet wordnet) { // constructor takes a WordNet object
		if (wordnet == null) throw new IllegalArgumentException();
	}

	public String outcast(String[] nouns) { // given an array of WordNet nouns,
											// return an outcast
		if (nouns == null) throw new IllegalArgumentException();
	}

	public static void main(String[] args) { // see test client below
		WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}
