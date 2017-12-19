import java.util.Iterator;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	// constructor takes a digraph (not necessarily a DAG)
	private Digraph digraph;
	public SAP(Digraph G){
		if(G == null) throw new IllegalArgumentException(); 
		this.digraph= new Digraph(G);
	}

   // length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w){
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(digraph, v);
		Iterable<Integer> path = bfs.pathTo(w);
		int count = 0;
		if(path == null) return 0;
		for (Iterator<Integer> it = path.iterator(); it.hasNext();) {
			count++;
		}
		return count;
	}

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w){
		if(v == null || w == null) throw new IllegalArgumentException(); 
	}

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		if(v == null || w == null) throw new IllegalArgumentException(); 
	}

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		if(v == null || w == null) throw new IllegalArgumentException(); 
	}

   // do unit testing of this class
	public static void main(String[] args){
		In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}
}
