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
