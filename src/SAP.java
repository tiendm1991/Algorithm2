import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
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
		checkBound(v);
		checkBound(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		int minDistance = -1;
		for(int i = 0; i < digraph.V(); i++){
			if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)){
				int distance = bfsV.distTo(i) + bfsW.distTo(i);
				if(distance < minDistance || (minDistance == -1 && distance >= 0)){
					minDistance = distance;
				}
			}
		}
		return minDistance;
	}

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w){
		checkBound(v);
		checkBound(w);
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		int minDistance = Integer.MAX_VALUE;
		int minAncestor = -1;
		for(int i = 0; i < digraph.V(); i++){
			if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)){
				int distance = bfsV.distTo(i) + bfsW.distTo(i);
				if(distance < minDistance){
					minDistance = distance;
					minAncestor = i;
				}
			}
		}
		return minAncestor;
	}

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w){
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		int minDistance = -1;
		for(int i = 0; i < digraph.V(); i++){
			if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)){
				int distance = bfsV.distTo(i) + bfsW.distTo(i);
				if(distance < minDistance || (minDistance == -1 && distance >= 0)){
					minDistance = distance;
				}
			}
		}
		return minDistance;
	}

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(digraph, w);
		int minDistance = Integer.MAX_VALUE;
		int minAncestor = -1;
		for(int i = 0; i < digraph.V(); i++){
			if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)){
				int distance = bfsV.distTo(i) + bfsW.distTo(i);
				if(distance < minDistance){
					minDistance = distance;
					minAncestor = i;
				}
			}
		}
		return minAncestor;
	}
	
	private final void checkBound(int v) {
        if (v < 0 || v > digraph.V() - 1) {
            throw new IllegalArgumentException();
        }
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
