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
	
	private String name;
	private int goalsScored;
	
	private ArrayList<Team> teamInfo = new ArrayList<Team>();
	
	public Player(String name, int goalsScored){
		this.name = name;
		this.goalsScored = goalsScored;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getGoalsScored(){
		return this.goalsScored;
	}
	
	public void setGoalsScored(int goalsScored){
		this.goalsScored = goalsScored;
	}

	public ArrayList<Team> getTeamInfo() {
		return teamInfo;
	}

	public void setTeamInfo(ArrayList<Team> teamInfo) {
		this.teamInfo = teamInfo;
	}
	
	

}
