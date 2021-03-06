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

package controller;

import gui.WorldCupFrame;

import java.util.ArrayList;
import java.util.Date;
import model.Player;
import model.Team;
import model.WorldCupModel;
import model.persistors.IPersistor;

public class WorldCupController {

	private static WorldCupController instance;
	private WorldCupModel dataModel;
	private WorldCupFrame view;
	
	private PersistanceMode persistanceMode;
	
	private IPersistor persistor;
	
	
	public static WorldCupController getInstance(){
		if(instance == null)
		{
			instance = new WorldCupController();
		}
		return instance;
	}
	
	public void init()
	{
		try{
			
			ArrayList<Player> players = this.persistor.readPlayer();
			ArrayList<Team> team = this.persistor.readTeam();
			
			if(players != null)
			{
				WorldCupModel dataModel = new WorldCupModel(players, team);
				this.setModel(dataModel);
			}
			else
			{
				this.setModel((new WorldCupModel()));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//Setters for Model, Persistor, PersistorMode and View
	private void setModel(WorldCupModel dataModel){
		this.dataModel = dataModel;
	}
	
	public void setPersistor(IPersistor persistor){
		this.persistor = persistor;
	}
	
	public void setView(WorldCupFrame view){
		this.view = view;
	}
	
	public void setPersistanceMode(PersistanceMode persistanceMode){
		this.persistanceMode = persistanceMode;
	}
	
	//Getters for Player and Team
	
	public ArrayList<Player> getPlayers()
	{
		return this.dataModel.getPlayers();
	}
	
	public ArrayList<Team> getTeam()
	{
		return this.dataModel.getTeam();
	}
		
	public ArrayList<Team> getTeamForPlayer(String playerName)
	{
		return this.persistor.getTeamForPlayer(playerName);
	}
	
	
	//Create New Player method
	public void createNewFieldPlayer(String playerName, String playerPosistion, int goalsScored, int goalsSaved){

		Player newPlayer1 = null;
		newPlayer1 = Player.createFieldPlayer(playerName, playerPosistion, goalsScored, goalsSaved);



		this.dataModel.addPlayer(newPlayer1);
		
				//For a database just insert the new player
				ArrayList<Player> playerWrapper = new ArrayList<Player>();
				playerWrapper.add(newPlayer1);

		this.persistor.write(playerWrapper);


		this.view.refreshTable();
	}


    //Create New Player method
    public void createNewGoaliePlayer(String playerName, String playerPosistion, int goalsSaved){

        Player newPlayer2 = null;
        newPlayer2 = Player.createGoalie(playerName, playerPosistion, goalsSaved);

        this.dataModel.addPlayer(newPlayer2);

        //For a database just insert the new player
        ArrayList<Player> playerWrapper = new ArrayList<Player>();
        playerWrapper.add(newPlayer2);

        this.persistor.write(playerWrapper);


        this.view.refreshTable();
    }


	//Create New Team method****************
	public void createNewTeam(String name, String teamName, int gamesWon)
	{
		Team newTeam = null;
		newTeam = new Team(name, teamName, gamesWon);
		
		this.dataModel.addTeam(newTeam);
		
				//For a database just insert the new player
				ArrayList<Team> teamWrapper = new ArrayList<Team>();
				teamWrapper.add(newTeam);
				this.persistor.writeTeam(teamWrapper);
	
		this.view.refreshTable();
	}		
	
	//Add 
	public void addTeamForPlayer(String playerName, String teamName, int gamesWon)
	{
		Team addTeam = 
				new Team(playerName, teamName, gamesWon);
			
		this.persistor.addTeamForPlayer(addTeam);
			
		this.view.refreshTable();	
	}
	
	
	
	
	public void deletePlayer(int selectedIndex)
	{
		Player removedPlayer =
				this.dataModel.getPlayers().remove(selectedIndex);
		
		this.persistor.delete(removedPlayer.getName());
		
		this.view.refreshTable();
	}
	
	public void updatePlayer(String originalName, String newName, String newPlayerPosistion, int newGoalsScorred, int newGoalsSaved)
	{
		//Search model for someone who has the originalName
		for(Player currPlayer : this.dataModel.getPlayers())
		{
			if(currPlayer.getName().equals(originalName))
			{
				currPlayer.setName(newName);
				currPlayer.setPlayerPosition(newPlayerPosistion);
				currPlayer.setGoalsScored(newGoalsScorred);
                currPlayer.setGoalsSaved(newGoalsSaved);
			}
		}
		this.persistor.update(originalName, newName, newPlayerPosistion, newGoalsScorred, newGoalsSaved);
		this.view.refreshTable();
	}



}
















