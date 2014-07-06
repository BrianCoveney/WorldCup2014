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

import model.Player;
import model.WorldCupModel;
import model.persistors.IPersistor;

public class WorldCupController {

	private static WorldCupController instance;
	private WorldCupModel dataModel;
	private WorldCupFrame view;
	
	private PersistanceMode persistanceMode;
	
	//THe persistor 
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
			
			ArrayList<Player> players = this.persistor.read();
			//Check that what was read out of the file was not null
			if(players != null){
				WorldCupModel dataModel = new WorldCupModel(players);
				this.setModel(dataModel);
			}
			else{
				this.setModel((new WorldCupModel()));
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			//Defaulting the model to blank if an exception happens
			this.setModel(new WorldCupModel());
		}
	}
	
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
	
	public void createNewPlayer(String name, int goalsScored){
		Player newPlayer = null;
		newPlayer = new Player(name, goalsScored);
		
		this.dataModel.addPlayer(newPlayer);
		switch(this.persistanceMode)
		{
			//For a serialize file get the extire list from the model and
			//write out a fresh copy
			case FILE :
			{
				this.persistor.write(getPlayers()); break;
			}
			case DATABASE :
			{
				//For a database just insert the new player
				ArrayList<Player> playerWrapper = new ArrayList<Player>();
				playerWrapper.add(newPlayer);
				this.persistor.write(playerWrapper);
				break;
			}
		}
		this.view.refreshTable();
	}
	
	public ArrayList<Player> getPlayers()
	{
		return this.dataModel.getPlayers();
	}
	
	public void deletePlayer(int selectedIndex)
	{
		Player removedPlayer =
				this.dataModel.getPlayers().remove(selectedIndex);
		
		this.persistor.delete(removedPlayer.getName());
		
		this.view.refreshTable();
	}
	
	public void updatePlayer(String originalName, String newName, int newGoalsScorred)
	{
		//Search model for someone who has the originalName
		for(Player currPlayer : this.dataModel.getPlayers())
		{
			if(currPlayer.getName().equals(originalName))
			{
				currPlayer.setName(newName);
				currPlayer.setGoalsScored(newGoalsScorred);
			}
		}
		this.persistor.update(originalName, newName, newGoalsScorred);
		this.view.refreshTable();
	}
	
}
















