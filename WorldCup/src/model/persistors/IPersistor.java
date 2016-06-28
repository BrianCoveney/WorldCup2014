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

package model.persistors;

import java.util.ArrayList;

import model.Player;
import model.Team;

public interface IPersistor 
{
	public void write(ArrayList<Player> players);

	public void writeTeam(ArrayList<Team> team);

	public ArrayList<Player> readPlayer();

	public ArrayList<Team> readTeam();

	public void delete(String name);

	public void update(String originalName, String newName, String newPlayerPosition, int newGoalsScored, int newGoalsSaved);

	public ArrayList<Team> getTeamForPlayer(String playerName);

	public void addTeamForPlayer(Team newTeam);
	
}