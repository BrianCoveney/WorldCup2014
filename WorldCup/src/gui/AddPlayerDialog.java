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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Player;
import controller.WorldCupController;

public class AddPlayerDialog extends JDialog{
	
	private enum Mode {ADD, EDIT};
	private JPanel mainPanel;
	
	private JLabel nameLabel;
	private JTextField nameField;
	
	private JLabel playerPositionLabel;
	private JTextField playerPositionField;
	
	private JLabel goalLabel;
	private JTextField goalField;
	
	private JButton okButton;
	private JButton cancelButton;
	private Mode dialogMode;
	private Player playerBeingEdited;
	
	
	public AddPlayerDialog(JFrame parent, String title, Player p)
	{
		this(parent, title);
		this.playerBeingEdited = p;
		this.nameField.setText(p.getName());
		this.playerPositionField.setText(p.getPlayerPosition());
		this.goalField.setText(Integer.toString(p.getGoalsScored()));
		dialogMode = Mode.EDIT;
	}
	
	
	public AddPlayerDialog(JFrame parent, String title)
	{
		super(parent, title);
		this.mainPanel = new JPanel();
		BoxLayout boxL = new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS);
		this.mainPanel.setLayout(boxL);
		this.getContentPane().add(this.mainPanel);
		
		this.mainPanel.add(createNamePanel());
		this.mainPanel.add(createPlayerPositionPanel());
		this.mainPanel.add(createGoalsPanel());
		this.mainPanel.add(createButtonPanel());
		dialogMode = Mode.ADD;
	}
	
	
	private JPanel createPlayerPositionPanel() {
		JPanel playerPosPanel = new JPanel();
		playerPositionLabel = new JLabel("Player Position: ");

		playerPositionLabel.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 20), new EtchedBorder()));
		
		playerPositionField = new JTextField();
		playerPositionField.setPreferredSize(new Dimension( 115, 20 ));
		
		playerPosPanel.add(playerPositionLabel);
		playerPosPanel.add(playerPositionField);

		return playerPosPanel;
	}


	private JPanel createNamePanel(){
		JPanel namePanel = new JPanel();
		nameLabel = new JLabel("Name: ");
		nameLabel.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 85), new EtchedBorder()));
		nameField = new JTextField(10);
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		return namePanel;
	}
	
	
	private JPanel createGoalsPanel(){
		JPanel goalPanel = new JPanel();
		goalLabel = new JLabel("Goals Scored: ");
		goalLabel.setBorder(BorderFactory.createCompoundBorder(
				new EmptyBorder(10, 10, 10, 33), new EtchedBorder()));
		goalField = new JTextField(10);
		goalPanel.add(goalLabel);
		goalPanel.add(goalField);
		return goalPanel;
	}
	
	
	private JPanel createButtonPanel(){
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				
				if(dialogMode == Mode.ADD){
					WorldCupController.getInstance().
						createNewPlayer(nameField.getText(), playerPositionField.getText(),	
								Integer.parseInt(goalField.getText()));
					
				}else if(dialogMode == Mode.EDIT){
					WorldCupController.getInstance().updatePlayer(
							playerBeingEdited.getName(),
							nameField.getText(), playerPositionField.getText(),
							Integer.parseInt(goalField.getText()));
				}
				dispose();
			}
		});
		cancelButton = new JButton("Cancel");
		buttonPanel.add(okButton);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(cancelButton);
		
		cancelButton.addActionListener(new ActionListener(){ 
			
			public void actionPerformed(ActionEvent e){
				dispose();
			}
			
		});
		
		return buttonPanel;
		
		
	}

}















