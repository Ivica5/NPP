package io.ivan.NPP.factoryPattern;

public class Team {
	
	private int position, playedGames, points, goalsFor, goalsAgainst, goalDifference;
	private TeamName team;
	
	private static class TeamName{
		private String name;
	}

	public int getRank() {
		return position;
	}

	public String getTeam() {
		return team.name;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public int getPoints() {
		return points;
	}

	public int getGoals() {
		return goalsFor;
	}

	public int getGoalsAgainst() {
		return goalsAgainst;
	}

	public int getGoalDifference() {
		return goalDifference;
	}

	public String toString() {
        return String.format("%-3d %-25s %-5d %-6d %-5d %-5d %d",
        		getRank(), getTeam(), getPlayedGames(), getPoints(), getGoals(), getGoalsAgainst(), getGoalDifference());
    }
}
