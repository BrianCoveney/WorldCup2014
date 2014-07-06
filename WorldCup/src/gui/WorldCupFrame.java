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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.Player;
import controller.WorldCupController;

public class WorldCupFrame extends JFrame{
	
	private JPanel mainPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton okButton;
	private JButton cancelButton;
	private JTable table;
	private PlayerTableModel tableModel;
	
	public WorldCupFrame (String title){
		super(title);
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(this.mainPanel);
		
		this.mainPanel.add(createSideButtonPanel(), BorderLayout.EAST);
		this.mainPanel.add(createBottomPanel(), BorderLayout.SOUTH);
		this.mainPanel.add(createTable(WorldCupController.getInstance().getPlayers()), 
				BorderLayout.CENTER);	
	}
	
	private JScrollPane createTable(ArrayList<Player> players){
		table = new JTable();
		tableModel = new PlayerTableModel(players);
		table.setModel(tableModel);
		
		JScrollPane scroller = new JScrollPane(table);
		return scroller;
	}
	
	private JPanel createSideButtonPanel(){
		JPanel sideButtonPanel = new JPanel();
		BoxLayout boxL = new BoxLayout(sideButtonPanel, BoxLayout.Y_AXIS);
		sideButtonPanel.setLayout(boxL);
		
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
				int selectedIndex = table.getSelectedRow();
				if(selectedIndex >=0){
					Player selectedPlayer =
							WorldCupController.getInstance().getPlayers().get(selectedIndex);
				
					//Launch the Edit Player dialog
					JFrame outerFrame = (JFrame)getRootPane().getParent();
					AddPlayerDialog editPlayerDialog =
							new AddPlayerDialog(outerFrame, "Edit Player", selectedPlayer);
					editPlayerDialog.setSize(400,250);
					editPlayerDialog.setLocationRelativeTo(null);
					editPlayerDialog.setVisible(true);
					
				}
				else
				{
					JFrame outerFrame = new JFrame();
					JOptionPane.showMessageDialog(outerFrame, "Please Select a Player");
				}
			}
		});
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow >=0)
				{
					WorldCupController.getInstance().deletePlayer(selectedRow);
				}
				else
				{
					JFrame outerFrame = new JFrame();
					JOptionPane.showMessageDialog(outerFrame, "Please Select a Contact");
				}
				
			}
		});
		
		//Creating listeners 
		AddButtonListener addButtonL = new AddButtonListener(this);
		addButton.addActionListener(addButtonL);
		
		sideButtonPanel.add(addButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(editButton);
		sideButtonPanel.add(Box.createVerticalStrut(5));
		sideButtonPanel.add(deleteButton);
		
		return sideButtonPanel;
	}
	
	private JPanel createBottomPanel()
	{
		JPanel bottomPanel = new JPanel();
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		bottomPanel.add(okButton);
		bottomPanel.add(cancelButton);
		return bottomPanel;
	}
	
	public void refreshTable()
	{
		this.tableModel.fireTableDataChanged();
	}
	
	public class AddButtonListener implements ActionListener{
		private JFrame outerFrame;
		
		public AddButtonListener(JFrame outerFrame){
			this.outerFrame = outerFrame;
		}
		
		public void actionPerformed(ActionEvent e){
			//Launch the Add Player dialog
			AddPlayerDialog addPlayerDialog =
					new AddPlayerDialog(outerFrame, "Add Player");
			addPlayerDialog.setSize(400,250);
			addPlayerDialog.setLocationRelativeTo(null);
			addPlayerDialog.setVisible(true);
			
		}
	}
	
	
}















