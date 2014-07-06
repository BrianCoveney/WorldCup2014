package model;

import java.io.Serializable;

public class Goalkeeper extends Player implements Serializable{

	private int saves;
	private int missedSaves;
	
	public Goalkeeper(String name,int goalsScored,
			int saves, int missedSaves)
	{
	super(name, goalsScored);
	this.saves = saves;
	this.missedSaves = missedSaves;
	}
	
	public String toString(){
		return super.toString() + " " + 
				this.saves + " " + this.missedSaves;
	}
}
