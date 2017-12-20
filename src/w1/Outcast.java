package w1;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private WordNet wordnet;
	public Outcast(WordNet wordnet) { // constructor takes a WordNet object
		if (wordnet == null) throw new IllegalArgumentException();
		this.wordnet = wordnet;
	}

	public String outcast(String[] nouns) { // given an array of WordNet nouns,
											// return an outcast
		if (nouns == null || nouns.length <= 0) throw new IllegalArgumentException();
		int minDistance = 0;
		String nounOutcast = nouns[0];
		for(String noun : nouns){
			int distance = distance(noun, nouns);
			if(distance > minDistance){
				minDistance = distance;
				nounOutcast = noun;
			}
		}
		return nounOutcast;
	}

	private int distance(String noun, String[] nouns) {
		int distance = 0;
		for(String n : nouns){
			distance += wordnet.distance(noun, n);
		}
		return distance;
	}

	public static void main(String[] args) { // see test client below
		WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
	    Outcast outcast = new Outcast(wordnet);
//	    for (int t = 2; t < args.length; t++) {
	        In in = new In("outcast10.txt");
	        String[] nouns = in.readAllStrings();
	        StdOut.println(outcast.outcast(nouns));
//	    }
	}
}
