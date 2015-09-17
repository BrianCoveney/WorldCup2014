/*********************************************
 * Title: World Cup 2014
 * 
 * Date: Summer 2014
 *
 * Author: 
 * Brian Coveney  
 * CS Student at Cork Institute of Technology
 *
 ********************************************/ 

package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import controller.WorldCupController;
import model.Team;


public class ShowTeamDialog extends JDialog {
	
	private JComboBox<Team> teamCombo;
	private JButton addTeamButton;
	private JPanel mainPanel;
	private String selectedPlayerName;
	private int callDuration;
	
	public ShowTeamDialog(JFrame parent, String title, String selectedPlayerName)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		this.selectedPlayerName = selectedPlayerName;
		BoxLayout boxL = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
		this.mainPanel.setLayout(boxL);
		this.getContentPane().add(this.mainPanel); 
		this.mainPanel.add(createTeamComboPanel());

	}


	private JPanel createTeamComboPanel()
	{
		JPanel teamComboPanel = new JPanel();
		
		teamCombo = new JComboBox<Team>();
//		Dimension dim = teamCombo.getPreferredSize(); // adjusting comboBox size.
//		teamCombo.setPreferredSize(new Dimension(400, dim.height)); 
		
		populateTeamCombo();
		teamComboPanel.add(teamCombo);
		
		addTeamButton = new JButton("Add Team..");
		addTeamButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ev)
			{

				try{
					String teamName = (JOptionPane.showInputDialog("Enter Team"));
					int gamesWon = 
							Integer.parseInt(JOptionPane.showInputDialog("Input Games Won :"));
					
					WorldCupController.getInstance().
						addTeamForPlayer(selectedPlayerName, teamName, gamesWon);
					
				}catch(Exception ex){
					JFrame outerFrame = new JFrame();
					JOptionPane.showConfirmDialog(outerFrame, "Error");			
				}
				dispose();
			}
		});
		
		teamComboPanel.add(addTeamButton);
		return teamComboPanel;
	};
		
	
	private void populateTeamCombo() 
	{
		teamCombo.removeAll();
		ArrayList<Team> teamForPlayer =
				WorldCupController.getInstance().getTeamForPlayer(selectedPlayerName);
		
		for(Team currTeam : teamForPlayer)
		{
			teamCombo.addItem(currTeam);
		}
	}		
}//end ShowCallsDialog class



















