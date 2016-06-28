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

package gui;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Player;

public class PlayerTableModel extends DefaultTableModel{
	
	private static final int NO_OF_COLS  = 4;
	
	//These are the indices of the table columns
	private static final int NAME_COL = 0;
	private static final int PLAYER_POS_COL = 1;
	private static final int GOALS_COL = 2;
	private static final int GOALS_SAVED_COL = 3;

	private ArrayList<Player> players;
	
	public PlayerTableModel(ArrayList<Player> players)
	{
		super();
		this.players = players;
	}
	
	//We are overriding the getColumn() method from the superclass
	public int getColumnCount(){
		return NO_OF_COLS;
	}
	
	public String getColumnName(int column){
		if(column == NAME_COL)
		{
			return "Player Name";
		}
		
		else if(column == PLAYER_POS_COL)
		{
			return "Player Position";
		}
		else if(column == GOALS_SAVED_COL)
		{
			return "Goals Saved";
		}
		else if(column == GOALS_COL)
		{
			return "Goals";
		}
		else
		{
			return "";
		}
	}
		
		public int getRowCount()
		{
			if(this.players != null)
			{
				return this.players.size();
			}
			else
			{	
				return 0;
			}
		}
		
		public Object getValueAt(int row, int col)
		{
			
			Player playerToGet = this.players.get(row);
			if(col == NAME_COL)
			{
				return playerToGet.getName();
			}
			else if(col == PLAYER_POS_COL)
			{
				return playerToGet.getPlayerPosition();
			}
			else if(col == GOALS_SAVED_COL)
			{
				return playerToGet.getGoalsSaved();
			}
			else if(col == GOALS_COL)
			{
				return playerToGet.getGoalsScored();
			}
			else
			{
				return "";
			}
		}
	
}

















