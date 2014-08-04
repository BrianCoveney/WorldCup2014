 /***************************************************************************
 * Title: Phone Information Application 
 * 
 * Java Packages & Files: 
 * 
 * controller 		 - PersistanceMode & PhoneInfoController. 
 * gui   			 - AddContact, ContactTable Model, MainAppicationDB, 
 * 					   PhoneInfoFrame & ShowTeamsDialog. 
 * model  			 - Team, Contact & PhoneInfoModel.
 * model.persistors  - DatabaseFilePersistor, DBPersistorTester & IPersistor.
 *
 * Date: 08/05/2014
 *
 * Author: Brian Coveney  Student Id: R00105727
 *
 * About this:
 * -----------
 * 
 * ShowTeamsDialog - creating Dialog Box for adding and displaying Team(s)
 *
  **************************************************************************/

package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Team;
import controller.WorldCupController;

public class ShowTeamDialog extends JDialog {
	
	private JComboBox<Team> TeamCombo;
	private JButton addTeamButton;
	private JPanel mainPanel;
	private String selectedPlayertName;
	private String teamName;
	private int gamesWon;
	
	public ShowTeamDialog(JFrame parent, String title, String selectedPlayertName)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		this.selectedPlayertName = selectedPlayertName;
		this.teamName = teamName;
		this.gamesWon = gamesWon;
		BoxLayout boxL = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
		this.mainPanel.setLayout(boxL);
		this.getContentPane().add(this.mainPanel); 
		this.mainPanel.add(createTeamComboPanel());

	}


	private JPanel createTeamComboPanel()
	{
		JPanel TeamComboPanel = new JPanel();
		
		TeamCombo = new JComboBox<Team>();
		Dimension dim = TeamCombo.getPreferredSize(); // adjusting comboBox size.
		TeamCombo.setPreferredSize(new Dimension(275, dim.height)); 
		
		populateTeamCombo();
		TeamComboPanel.add(TeamCombo);
		
		addTeamButton = new JButton("Add Team..");
		addTeamButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{

				try{
					String selectedPlayertName = (JOptionPane.showInputDialog("Input Players Name :"));
					String teamName = (JOptionPane.showInputDialog("Input Team Name :"));
					int gamesWon = Integer.parseInt(JOptionPane.showInputDialog("Input Games Won :"));
					
					WorldCupController.getInstance().
						addTeamForPlayer(selectedPlayertName, teamName, gamesWon);
					
					
					
				}catch(NumberFormatException ex){
					JFrame outerFrame = new JFrame();
					JOptionPane.showConfirmDialog(outerFrame, "Please enter a Number");			
				}
				dispose();
			}
		});
		
		TeamComboPanel.add(addTeamButton);
		return TeamComboPanel;
	};
		
	
	private void populateTeamCombo() 
	{
		TeamCombo.removeAll();
		ArrayList<Team> TeamsForContact =
				WorldCupController.getInstance().getTeamForPlayer(selectedPlayertName);
		
		for(Team currTeam : TeamsForContact)
		{
			TeamCombo.addItem(currTeam);
		}
	}		
}//end ShowTeamsDialog class