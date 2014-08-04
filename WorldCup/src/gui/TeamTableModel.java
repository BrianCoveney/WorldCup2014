package gui;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Team;

public class TeamTableModel extends DefaultTableModel{
	
	private static final int NO_OF_COLS = 3;
	
	//These are the indices of the table columns
	private static final int TEAM_NAME_COL = 0;
	private static final int PLAYER_NAME_COL = 1;
	private static final int GAMES_WON_COL = 2;
	private ArrayList<Team> team;
	
	//Setting up the Contructor
	public TeamTableModel(ArrayList<Team> team)
	{
		super();
		this.team = team;
	}
	
	//We are overriding the getColumnCount method from the superclass
	public int getColumnCount(){
		return NO_OF_COLS;
	}
	
	//We are overriding the getColumnName method from the superclass
	public String getColumnName(int column){
		if(column == TEAM_NAME_COL)
		{
			return "Team Name";
		}
		else if(column == PLAYER_NAME_COL)
		{
			return "Player Name";
		}
		else if(column == GAMES_WON_COL)
		{
			return "Games Won";
		}
		else{
			return "";
		}
	}
	
	//We are overriding the getRowCount method from the superclass
	public int getRowCount(){
		if(this.team != null)
		{
			return this.team.size();
		}
		else{
			return 0;
		}	
	}
	
	//We are overriding the getValueAt method from superclass
	public Object getValueAt(int row, int col)
	{
		Team teamToGet = this.team.get(row);
		if(col == TEAM_NAME_COL)
		{
			return teamToGet.getTeamName();
		}
		else if(col == PLAYER_NAME_COL)
		{
			return teamToGet.getPlayerName();
		}
		else if(col == GAMES_WON_COL)
		{
			return teamToGet.getGamesWon();
		}
		else{
			return "";
		}
			
	}
	

}


















