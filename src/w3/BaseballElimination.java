package w3;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
	private final int N;
	private int[] wins;
	private int[] losses;
	private int[] remains;
	private int[][] games;
	private Map<String, Integer> mapTeam;
	
	public BaseballElimination(String filename) {
		if (filename == null)
			throw new IllegalArgumentException();
		In file = new In(filename);
		N = file.readInt();
		mapTeam = new HashMap<>();
		wins = new int[N];
		losses = new int[N];
		remains = new int[N];
		games = new int[N][N];
		for (int i = 0; i < N; i++) {
			String teamName = file.readString();
			mapTeam.put(teamName, i);
			wins[i] = file.readInt();
			losses[i] = file.readInt();
			remains[i] = file.readInt();
			for (int j = 0; j < N; j++) {
				games[i][j] = file.readInt();
			}
		}
	}

	public int numberOfTeams() {
		return N;
	}

	public Iterable<String> teams() {
		return mapTeam.keySet();
	}

	public int wins(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		return wins[mapTeam.get(team)];
	}

	public int losses(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		return losses[mapTeam.get(team)];
	}

	public int remaining(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		return remains[mapTeam.get(team)];
	}

	public int against(String team1, String team2) {
		if (!mapTeam.containsKey(team1) || !mapTeam.containsKey(team2)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		return games[mapTeam.get(team1)][mapTeam.get(team2)];
	}

	public boolean isEliminated(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		int id = mapTeam.get(team);
		if(!getTrivialElimination(team).trim().equals("")){
			return true;
		}
		FlowNetwork fn = createNetwork(id);
		int s = N;
		int t = N+1;
		FordFulkerson ffk = new FordFulkerson(fn, s, t);
		for(FlowEdge edge : fn.adj(s)){
			if(edge.flow() < edge.capacity()) return true;
		}
		return false;
	}

	private String getTrivialElimination(String team) {
		int idTeam = mapTeam.get(team);
		for(String strTeam : teams()){
			Integer i = mapTeam.get(strTeam);
			if(i == idTeam) continue;
			if(wins[idTeam] + remains[idTeam] < wins[i]){
				return strTeam;
			}
		}
		return "";
	}
	
	private FlowNetwork createNetwork(int id) {
		int s = N;
		int t = N+1;
		int newNode = N+2;
		Set<FlowEdge> edges = new HashSet<>();
		int maxWin = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++){
			if(wins[i] > maxWin) maxWin = wins[i];
		}
		for(int i = 0; i < N-1; i++){
			if(i == id || wins[i] + remains[i] < maxWin || wins[id] + remains[id] < wins[i]) continue;
			for(int j = i+1; j < N; j++){
				if(j == id || games[i][j] == 0 || wins[j] + remains[j] < maxWin) continue;
				edges.add(new FlowEdge(s, newNode, games[i][j]));
				edges.add(new FlowEdge(newNode, i, Double.POSITIVE_INFINITY));
				edges.add(new FlowEdge(newNode, j, Double.POSITIVE_INFINITY));
				newNode++;
			}
			edges.add(new FlowEdge(i, t, wins[id] + remains[id] - wins[i]));
		}
		FlowNetwork fn = new FlowNetwork(newNode);
		for(FlowEdge e : edges){
			fn.addEdge(e);
		}
		return fn;
	}

	public Iterable<String> certificateOfElimination(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not exist");
		}
		int v = mapTeam.get(team);
		Set<String> eliminations = new HashSet<>();
		String trivial = getTrivialElimination(team);
		if(!trivial.equals("")){
			eliminations.add(trivial);
			return eliminations;
		}
		FlowNetwork fn = createNetwork(v);
		int s = N, t = N+1;
		FordFulkerson ffk = new FordFulkerson(fn, s, t);
		for(FlowEdge e : fn.adj(s)){
			if(e.flow() < e.capacity()){
				for(String teamCheck : teams()){
					int id = mapTeam.get(teamCheck);
					if(ffk.inCut(id)){
						eliminations.add(teamCheck);
					}
				}
			}
		}
		if(eliminations.isEmpty()){
			return null;
		}
		return eliminations;
	}
	
	public static void main(String[] args) {
	    BaseballElimination division = new BaseballElimination("teams4.txt");
	    for (String team : division.teams()) {
	        if (division.isEliminated(team)) {
	            StdOut.print(team + " is eliminated by the subset R = { ");
	            for (String t : division.certificateOfElimination(team)) {
	                StdOut.print(t + " ");
	            }
	            StdOut.println("}");
	        }
	        else {
	            StdOut.println(team + " is not eliminated");
	        }
	    }
	}
}
