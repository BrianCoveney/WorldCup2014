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
    private int goalsSaved;

	private ArrayList<Team> teamInfo = new ArrayList<Team>();

	public Player(String playerName, String playerPosition, int goalsScored, int goalsSaved) {
		this.playerName = playerName;
		this.playerPosition = playerPosition;
		this.goalsScored = goalsScored;
        this.goalsSaved = goalsSaved;
	}

    public static Player createFieldPlayer(String playerName, String playerPosition, int goalsScored, int goalsSaved){
        return new Player(playerName, playerPosition, goalsScored, goalsSaved);
    }

    public static Player createGoalie(String playerName, String playerPosition, int goalsSaved){
        return new Player(playerName, playerPosition, goalsSaved, 0);
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


    public int getGoalsSaved() {return goalsSaved;}

    public void setGoalsSaved(int goalsSaved) {this.goalsSaved = goalsSaved;}

    public ArrayList<Team> getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(ArrayList<Team> teamInfo) {
		this.teamInfo = teamInfo;
	}

}
