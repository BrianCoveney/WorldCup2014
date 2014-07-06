package model;

import java.io.Serializable;

public class Player {
	
	private String name;
	private int goalsScored;
	
	public Player(String name, int goalsScored){
		this.name = name;
		this.goalsScored = goalsScored;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getGoalsScored(){
		return this.goalsScored;
	}
	
	public void setGoalsScored(int goalsScored){
		this.goalsScored = goalsScored;
	}
	
	public String toString(){
		return this.name + " " + this.goalsScored;
	}

}
