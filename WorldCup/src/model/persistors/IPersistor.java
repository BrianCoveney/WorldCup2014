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

public interface IPersistor 
{
	public void write(ArrayList<Player> players);
	public ArrayList<Player> read();
	
	public void delete(String name);
	
	public void update(String originalName, String newName, int newGoalsScorred);
	
}