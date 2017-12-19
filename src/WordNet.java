import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class WordNet {
	private final SAP paths;
	private final Map<Integer, String> _mapSynset;
	private final Map<String, Bag<Integer>> _mapNounId;

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new IllegalArgumentException();
		_mapSynset = new HashMap<>();
		_mapNounId = new HashMap<>();
		buildSynset(synsets);
		Digraph g = buildHypernym(hypernyms, _mapSynset.size());
		paths = new SAP(g);
	}

	private void buildSynset(String synsets) {
		In file = new In(synsets);
		while (!file.isEmpty()) {
			String[] line = file.readLine().split(",");
			String nounId = line[0];
			int id = Integer.parseInt(nounId);
			_mapSynset.put(id, line[1]);
			for (String noun : line[1].split(" ")) {
				Bag<Integer> bag = _mapNounId.get(noun);
				if (bag == null) {
					bag = new Bag<>();
					_mapNounId.put(noun, bag);
				}
				bag.add(id);
			}
		}

	}

	private Digraph buildHypernym(String hypernyms, int numSynset) {
		In file = new In(hypernyms);
		Digraph g = new Digraph(numSynset);
		while (!file.isEmpty()) {
			String[] line = file.readLine().split(",");
			String nounId = line[0];
			int idSynset = Integer.parseInt(nounId);
			for (int i = 1; i < line.length; i++) {
				int idHyper = Integer.parseInt(line[i]);
				g.addEdge(idSynset, idHyper);
			}
		}
		return g;
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return _mapNounId.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null)
			throw new IllegalArgumentException();
		return _mapNounId.containsKey(word);
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		return paths.length(_mapNounId.get(nounA), _mapNounId.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of
	// nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		return _mapSynset.get(paths.ancestor(_mapNounId.get(nounA), _mapNounId.get(nounB)));
	}

	// do unit testing of this class
	public static void main(String[] args) {

	}
}
