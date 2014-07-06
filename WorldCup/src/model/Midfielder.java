package model;

import java.io.Serializable;

public class Midfielder extends Player implements Serializable {
	
	private int assists;
	private int distanceCovered;
	
	public Midfielder(String name, int goalsScored, 
							int passes, int distanceCovered){
		
		super(name, goalsScored);
		this.assists = passes;
		this.distanceCovered = distanceCovered;
		
	}
	
	public String toString(){
		return super.toString() + " " +
				this.assists + " " + this.distanceCovered;
	}
	

}
