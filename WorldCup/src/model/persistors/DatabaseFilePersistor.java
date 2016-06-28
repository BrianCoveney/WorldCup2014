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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import model.Player;
import model.Team;

public class DatabaseFilePersistor implements IPersistor{
	
	private Connection dbConnection;
	private ArrayList<AutoCloseable> dbObjects;

	// Database column names set as constants
	private static final String DB_PLAYER_NAME_COL = "PlayerName";
	private static final String DB_PLAYER_POSITION_COL = "PlayerPosition";
	private static final String DB_GOALS_COL = "goals";
	private static final String DB_GOALS_SAVED_COL = "goals_saved";
	
	private static final String DB_TEAM_COL = "TeamName";
	private static final String DB_GAMES_WON_COL = "GamesWon";
	
	public DatabaseFilePersistor()
	{
		dbObjects = new ArrayList<AutoCloseable>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			dbConnection=
					DriverManager.getConnection("jdbc:mysql://localhost:3306/worldcup?"+
							"user=root&password=bossdog12"); //enter password
			
			System.out.println("Database connection successful : "+dbConnection);
			
		}catch(ClassNotFoundException cfe){
			System.out.println("ERROR 0: "+cfe.getMessage());
		}catch(SQLException sqlEx){
			System.out.println("ERROR 1:"+sqlEx.getMessage());
		}
		
	}
	
	//Write Player method
	public void write(ArrayList<Player> players){
		try{
			for(Player currPlayer : players)
			{
				PreparedStatement prepStmt = 
						dbConnection.prepareStatement("INSERT into players values (? ,?, ?, ?)");
						
						prepStmt.setString(1, currPlayer.getName());
						prepStmt.setString(2, currPlayer.getPlayerPosition());
						prepStmt.setInt(3, currPlayer.getGoalsScored());
						prepStmt.setInt(4, currPlayer.getGoalsSaved());


				prepStmt.executeUpdate();
						dbObjects.add(prepStmt);
			}
			close();
			
		}catch(SQLException sqlEx){
			System.out.println("ERROR 2:"+sqlEx.getMessage());
		}
	}
	
	//Write Team method
		public void writeTeam(ArrayList<Team> team)
		{
			try{
				for(Team currTeam : team)
				{
					PreparedStatement prepStmt =
					dbConnection.prepareStatement("INSERT into team_info values (? ,?, ?)");
						
					prepStmt.setString(1, currTeam.getPlayerName());
					prepStmt.setString(2, currTeam.getTeamName());
					prepStmt.setInt(3, currTeam.getGamesWon());
						
					prepStmt.executeUpdate();
					dbObjects.add(prepStmt);
				}
				close();
					
			}catch(Exception ex)
			{
				System.out.println(ex.getMessage());
			}
		}
	
	public void close()
	{
		try{
			for(AutoCloseable curr : dbObjects)
			{
				curr.close();
			}
			
		}catch(Exception ex){
			System.out.println("ERROR 3:"+ex.getMessage());
		}
	}
	
	/** Read Player **********/
	public ArrayList<Player> readPlayer()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		try{
			Statement getAllPlayers = dbConnection.createStatement();
			dbObjects.add(getAllPlayers);
			ResultSet rs = getAllPlayers.executeQuery("SELECT * FROM players");
			dbObjects.add(rs);
			
			//Iterate through the result set
			while(rs.next())
			{
				String currPlayerName = rs.getString(DB_PLAYER_NAME_COL);
				String currPlayerPosition = rs.getString(DB_PLAYER_POSITION_COL);
				int currPlayerGoalsScorred = rs.getInt(DB_GOALS_COL);
				int currPlayerGoalsSaved = rs.getInt(DB_GOALS_SAVED_COL);
				
				//Now re-create a Player instance and add it to the ArrayList
				Player recreatedPlayer = Player.createFieldPlayer(
						currPlayerName, currPlayerPosition, currPlayerGoalsScorred, currPlayerGoalsSaved);
				players.add(recreatedPlayer);
			}
			
		}catch(Exception ex){
			System.out.println("ERROR 4:"+ex.getMessage());
		}finally{
			close();
			return players;
		}
	}
	
	/** Read Team **********/
	public ArrayList<Team> readTeam()
	{
		ArrayList<Team> team = new ArrayList<Team>();
		try{
			Statement getAllTeams = dbConnection.createStatement();
			dbObjects.add(getAllTeams);
			ResultSet rs = getAllTeams.executeQuery("SELECT * FROM team_info");
			dbObjects.add(rs);
			
			//Iterate through the result set
			while(rs.next())
			{
				String currPlayerName = rs.getString(DB_PLAYER_NAME_COL);
				String currTeamName  = rs.getString(DB_TEAM_COL);
				int currGamesWon = rs.getInt(DB_GAMES_WON_COL);
				
				Team recreatedTeam = new Team(currPlayerName, currTeamName, currGamesWon);
			}
			
		}catch(Exception e){
			e.getMessage();
		}finally{
			close();
			return team;
		}
	}
	
	
	//Get Call for Team method	
		public ArrayList<Team>getTeamForPlayer(String playerName)
		{
			ArrayList<Team> team = new ArrayList<Team>();
			try{
				PreparedStatement getTeam
					= dbConnection.prepareStatement("SELECT * FROM team_info WHERE PlayerName = ?");
				getTeam.setString(1, playerName);
				ResultSet rs = getTeam.executeQuery();
					
				while(rs.next())
				{
					String currPlayerName = rs.getString(DB_PLAYER_NAME_COL);
					String currTeamName = rs.getString(DB_TEAM_COL);
					int currGamesWon = rs.getInt(DB_GAMES_WON_COL);

					Team tm = new Team(currPlayerName, currTeamName, currGamesWon);
					team.add(tm);
				}
				getTeam.close();
					rs.close();
					
			}catch(Exception ex){
					System.out.println(ex.getMessage());
			}finally{
				return team;
			}	
		}
		
		
		//Add Team for Player method
		public void addTeamForPlayer(Team newTeam)
		{
			try{
				PreparedStatement prepStmt =
					dbConnection.prepareStatement("INSERT into team_info values (?, ?, ?)" );

				
				prepStmt.setString(1, newTeam.getPlayerName());
				prepStmt.setString(2, newTeam.getTeamName());
				prepStmt.setInt(3, newTeam.getGamesWon());
						
				prepStmt.executeUpdate();
				dbObjects.add(prepStmt);
				
			}catch(Exception ex){
				System.out.println(ex.getMessage());
			}
			finally{
				close();
			}
		}

	
	public void delete(String playername)
	{
		try{
			PreparedStatement deletePlayerStmt =
					dbConnection.prepareStatement
					("DELETE FROM players WHERE PlayerName =?");
					dbObjects.add(deletePlayerStmt);
					deletePlayerStmt.setString(1, playername);
					deletePlayerStmt.executeUpdate();
					
			PreparedStatement deleteTeamStmt =
					dbConnection.prepareStatement("DELETE FROM team_info where PlayerName=?");
			dbObjects.add(deleteTeamStmt);
			deleteTeamStmt.setString(1, playername);
			deleteTeamStmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("ERROR 5:"+ex.getMessage());
		}finally{
			close();
		}
	}
	
	public void update(String originalName, String newName, String newPlayerPosition, int newGoalsScored, int newGoalsSaved)
	{
		try{
		PreparedStatement prepStmt =
				dbConnection.prepareStatement("UPDATE players SET PlayerName=?, PlayerPosition=?, goals=?, goals_saved=? WHERE PlayerName=?");
				dbObjects.add(prepStmt);
				prepStmt.setString(1, newName);
				prepStmt.setString(2, newPlayerPosition);
				prepStmt.setInt(3, newGoalsScored);
				prepStmt.setInt(4, newGoalsSaved);
				prepStmt.setString(5, originalName);
				
				prepStmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("ERROR 6:"+ex.getMessage());
		}
		finally{
			close();
		}
	}


}















