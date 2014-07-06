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
