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

public class WorldCupModel {
	
	private ArrayList<Player> players;
	private ArrayList<Team> team;

	public WorldCupModel()
	{
		this.players = new ArrayList<Player>();
		this.team = new ArrayList<Team>();
	}
	
	public WorldCupModel(ArrayList<Player> players, ArrayList<Team> team)
	{
		this.players = players;
		this.team = team;
	}
	
	public void addPlayer(Player p)
	{
		this.players.add(p);
	}
	
	public void addTeam(Team t)
	{
		this.team.add(t);
	}
	
	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}
	
	public ArrayList<Team> getTeam()
	{
		return this.team;
	}
	
}
