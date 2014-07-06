package gui;

import javax.swing.JFrame;

import model.persistors.DatabaseFilePersistor;
import controller.PersistanceMode;
import controller.WorldCupController;



public class MainApplication {
	
	public static void main(String[] args){
		
		   WorldCupController.getInstance().setPersistor(new DatabaseFilePersistor());
		   WorldCupController.getInstance().setPersistanceMode(PersistanceMode.DATABASE);
		   WorldCupController.getInstance().init();

//This is creating and displaying the GUI (View)
		   WorldCupFrame worldFrame = 
				new WorldCupFrame("World Cup 2014");
			worldFrame.setSize(400, 300);
			worldFrame.setLocationRelativeTo(null);
			worldFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			worldFrame.setVisible(true);

			WorldCupController.getInstance().setView(worldFrame);

	}
}
