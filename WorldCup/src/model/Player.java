/********************************************
 * Title: World Cup 2014
 * 
 * Date: 06/07/2014
 *
 * Author: 
 * Brian Coveney  
 * CS Student at Cork Institute of Technology
 *
 *******************************************/

package model;

import java.util.ArrayList;

public class Player {

	private String playerName;
	private String playerPosition;
	private int goalsScored;

	private ArrayList<Team> teamInfo = new ArrayList<Team>();

	public Player(String playerName, String playerPosition, int goalsScored) {
		this.playerName = playerName;
		this.playerPosition = playerPosition;
		this.goalsScored = goalsScored;
	}

	public String getName() {
		return this.playerName;
	}

	public void setName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public int getGoalsScored() {
		return this.goalsScored;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}

	public ArrayList<Team> getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(ArrayList<Team> teamInfo) {
		this.teamInfo = teamInfo;
	}

}
