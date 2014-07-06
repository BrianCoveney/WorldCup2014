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

public class Defender extends Player implements Serializable{
	
	private int tackles;
	private int fouls;
	
	public Defender(String name, int goalsScored,
						int tackles, int fouls)
	{
		super(name, goalsScored);
		this.tackles = tackles;
		this.fouls = fouls;
	}
	
	public String toString(){
		return super.toString() + " "+
				this.tackles + " " + this.fouls;
	}

}
