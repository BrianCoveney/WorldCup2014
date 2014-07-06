package model;

import java.util.ArrayList;

public class WorldCupModel {
	
	private ArrayList<Player> players;

	public WorldCupModel()
	{
		this.players = new ArrayList<Player>();
	}
	
	public WorldCupModel(ArrayList<Player> players)
	{
		this.players = players;
	}
	
	public void addPlayer(Player p)
	{
		this.players.add(p);
	}
	
	public ArrayList<Player> getPlayers()
	{
		return this.players;
	}
	
}
