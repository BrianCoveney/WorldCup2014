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

public class Forward extends Player implements Serializable{
	
	private int goalFromHeadder;
	private int goalFromPenalty;
	
	public Forward(String name, int goalsScored,
						int goalFromHeadder, int goalFromPenalty){
		super(name, goalsScored);
		this.goalFromHeadder = goalFromHeadder;
		this.goalFromPenalty = goalFromPenalty;
	}
	
	public String toString(){
		return super.toString() + " " +
				this.goalFromHeadder + " " + this.goalFromPenalty;
	}

}
