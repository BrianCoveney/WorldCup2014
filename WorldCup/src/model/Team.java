package model;


public class Team {
	
	private String teamName;
	private String playerName;
	private int gamesWon;
	
	public Team(String playerName, String teamName, int gamesWon){
		this.playerName = playerName;
		this.teamName = teamName;
		this.gamesWon = gamesWon;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(int gamesWon) {
		this.gamesWon = gamesWon;
	}

	@Override
	public String toString() {
		return "PLAYERS NAME: " +this.playerName + " | "
				+"TEAM NAME: " + this.teamName + " | "
				+"GAMES WON: " + this.gamesWon;
	}

	
	
}
