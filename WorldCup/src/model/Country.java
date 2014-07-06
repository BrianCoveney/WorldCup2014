package model;

import java.util.ArrayList;

public class Country {
	
	private String countryName;
	private ArrayList<Player> players;

	public Country(String countryName){
		this.countryName = countryName;
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player players){
		this.players.add(players);
	}
	
	public void printPlayers(){
		for(Player players : this.players){
			System.out.println(players);
		}
	}
	
}
