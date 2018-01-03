import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

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
			throw new IllegalArgumentException("The team is not known! Please specify a valid team name!");
		}
		return wins[mapTeam.get(team)];
	}

	public int losses(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not known! Please specify a valid team name!");
		}
		return losses[mapTeam.get(team)];
	}

	public int remaining(String team) {
		if (!mapTeam.containsKey(team)) {
			throw new IllegalArgumentException("The team is not known! Please specify a valid team name!");
		}
		return remains[mapTeam.get(team)];
	}

	public int against(String team1, String team2) {
		if (!mapTeam.containsKey(team1) || !mapTeam.containsKey(team2)) {
			throw new IllegalArgumentException("The team is not known! Please specify a valid team name!");
		}
		return games[mapTeam.get(team1)][mapTeam.get(team2)];
	}

	public boolean isEliminated(String team) {

	}

	public Iterable<String> certificateOfElimination(String team) {

	}
}
