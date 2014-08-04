package model;

import java.util.ArrayList;

public class Team {

	private ArrayList<Team> teamInfo = new ArrayList<Team>();
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

	public ArrayList<Team> getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(ArrayList<Team> teamInfo) {
		this.teamInfo = teamInfo;
	}
	
}
