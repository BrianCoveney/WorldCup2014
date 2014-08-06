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

package gui;

import javax.swing.JFrame;

import model.persistors.DatabaseFilePersistor;
import controller.WorldCupController;



public class MainApplication {
	
	public static void main(String[] args){
		
		   WorldCupController.getInstance().setPersistor(new DatabaseFilePersistor());
		   
		   
		   WorldCupController.getInstance().init();

//This is creating and displaying the GUI (View)
		   WorldCupFrame worldFrame = 
				new WorldCupFrame("World Cup 2014");
			worldFrame.setSize(400, 450);
			worldFrame.setLocationRelativeTo(null);
			worldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			worldFrame.setVisible(true);

			WorldCupController.getInstance().setView(worldFrame);

	}
}
