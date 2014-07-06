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

import model.Player;

public class DatabaseFilePersistor implements IPersistor{
	
	private Connection dbConnection;
	private ArrayList<AutoCloseable> dbObjects;
	
	private static final String DB_NAME_COL = "name";
	private static final String DB_GOALS_COL = "goals";
	
	public DatabaseFilePersistor()
	{
		dbObjects = new ArrayList<AutoCloseable>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			dbConnection=
					DriverManager.getConnection("jdbc:mysql://localhost:3308/worldcup?"+
							"user=root&password="); //enter password
			
System.out.println("Database connection successful : "+dbConnection);
			
		}catch(ClassNotFoundException cfe){
			System.out.println("ERROR 0: "+cfe.getMessage());
		}catch(SQLException sqlEx){
			System.out.println("ERROR 1:"+sqlEx.getMessage());
		}
		
	}
	
	public void write(ArrayList<Player> players){
		try{
			for(Player currPlayer : players)
			{
				PreparedStatement prepStmt = 
						dbConnection.prepareStatement("INSERT into PLAYERS values (? ,?)");
						
						prepStmt.setString(1, currPlayer.getName());
						prepStmt.setInt(2, currPlayer.getGoalsScored());
						
						
						prepStmt.executeUpdate();
						dbObjects.add(prepStmt);
			}
			close();
			
		}catch(SQLException sqlEx){
			System.out.println("ERROR 2:"+sqlEx.getMessage());
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
	
	public ArrayList<Player> read()
	{
		ArrayList<Player> players = new ArrayList<Player>();
		try{
			Statement getAllPlayers = dbConnection.createStatement();
			dbObjects.add(getAllPlayers);
			ResultSet rs = getAllPlayers.executeQuery("SELECT * FROM PLAYERS");
			dbObjects.add(rs);
			
			//Iterate through the result set
			while(rs.next())
			{
				String currPlayerName = rs.getString(DB_NAME_COL);
				int currPlayerGoalsScorred = rs.getInt(DB_GOALS_COL);
				
				//Now re-create a Player instance and add it to the ArrayList
				Player recreatedPlayer = new Player(currPlayerName, currPlayerGoalsScorred);
				players.add(recreatedPlayer);
			}
			
		}catch(Exception ex){
			System.out.println("ERROR 4:"+ex.getMessage());
		}finally{
			close();
			return players;
		}
	}
	
	public void delete(String name)
	{
		try{
			PreparedStatement deletePlayerStmt =
					dbConnection.prepareStatement
					("DELETE FROM PLAYERS WHERE name =?");
					//DELETE from COMPETITOR WHERE name='john'
					//DELETE from COMPETITOR WHERE goals=3
					dbObjects.add(deletePlayerStmt);
					deletePlayerStmt.setString(1, name);
					deletePlayerStmt.executeUpdate();
			
		}catch(Exception ex){
			System.out.println("ERROR 5:"+ex.getMessage());
		}finally{
			close();
		}
	}
	
	public void update(String originalName, String newName, int newGoalsScored)
	{
		try{
		PreparedStatement prepStmt =
				dbConnection.prepareStatement("UPDATE PLAYERS SET name=?, goals=? WHERE name=?");
				dbObjects.add(prepStmt);
				prepStmt.setString(1, newName);
				prepStmt.setInt(2, newGoalsScored);
				prepStmt.setString(3, originalName);
				
				prepStmt.executeUpdate();
		}catch(Exception ex){
			System.out.println("ERROR 5:"+ex.getMessage());
		}
		finally{
			close();
		}
	}

}















