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

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import model.Player;
import model.Team;
import controller.WorldCupController;

public class WorldCupFrame extends JFrame{
	
	private JPanel mainPanel;
	private JButton addButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton showTeamButton;
    private final static int BUTTON_HEIGHT = 30;
    private final static int BUTTON_WIDTH = 100;

	private JTable table, teamTable;
	private PlayerTableModel tableModel;
	private TeamTableModel teamTableModel;
	
	private JLabel logoImage;
	
	public WorldCupFrame (String title){
		super(title);
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BorderLayout());
		this.getContentPane().add(this.mainPanel);	
		
		//Construct Menu
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		menuBar.add(createEditMenu());
		menuBar.add(createHelpMenu());
		

        try {
            Image bgrImg = ImageIO.read(getClass().getResource("/images/world_cup.jpg"));
            logoImage = new JLabel(new ImageIcon(bgrImg),SwingConstants.CENTER);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

		this.mainPanel.add(logoImage, BorderLayout.NORTH);
		
		this.mainPanel.add(createBottomButtonPanel(), BorderLayout.SOUTH);
		
		this.mainPanel.add(createTable(WorldCupController.getInstance().getPlayers()), 
				BorderLayout.CENTER);

        this.mainPanel.setBackground(Color.WHITE);
		
		/*this.mainPanel.add(createTeamTable(WorldCupController.getInstance().getTeam()), 
				BorderLayout.EAST);*/
	}
	
	//Table for Player
	private JScrollPane createTable(ArrayList<Player> players){
		table = new JTable();
		tableModel = new PlayerTableModel(players);
		table.setModel(tableModel);
		
		JScrollPane scroller = new JScrollPane(table);
		refreshTable();
		return scroller;
	}
	
	//Table for Team
	/*private JScrollPane createTeamTable(ArrayList<Team> team) {
		teamTable = new JTable();
		teamTableModel = new TeamTableModel(team);
		teamTable.setModel(teamTableModel);
		
		JScrollPane scroller = new JScrollPane(teamTable);
		return scroller;	
	}*/

	/** File Drop Down Menu *************/
	private JMenu createFileMenu() {
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		return menu;
	}
	
	/** Edit Drop Down Menu *************/
	private JMenu createEditMenu() {
		JMenu menu = new JMenu("Edit");
		menu.add(createDeleteItem());
		menu.add(createUpdateItem());
		return menu;
	}
	
	/** Help[ Drop Down Menu *************/
	private JMenu createHelpMenu() {
		JMenu menu = new JMenu("Help");
		menu.add(createHelpItem());
		return menu;
	}
	
	/**
	 * Creates the Help->Info menu item and sets its action listener.
	 * @return the menu item
	 */
	private JMenuItem createHelpItem() {
		JMenuItem info = new JMenuItem("Info");
		info.setMnemonic('H');
		info.setAccelerator(KeyStroke.getKeyStroke("ctrl H"));
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				JFrame outerFrame = new JFrame();
				JOptionPane.showMessageDialog(outerFrame, "This is the help section");
			}
		}
		ActionListener listener = new MenuItemListener();
		info.addActionListener(listener);
		return info;
	}
	

	/**
	 * Creates the File->Exit menu item and sets its action listener.
	 * @return the menu item
	 */
	private JMenuItem createFileExitItem() {
		JMenuItem exit = new JMenuItem("Exit", new ImageIcon("exit.gif"));
		exit.setMnemonic('E');
		exit.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {
				exitAction();
			}
		}
		ActionListener listener = new MenuItemListener();
		exit.addActionListener(listener);
		return exit;
	}
	
	/** Closes the application */
	private void exitAction(){
		System.exit(0);
	}

	/** Menu Edit->Update */
	private JMenuItem createUpdateItem(){
		JMenuItem update = new JMenuItem("Update");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				updateAction();
			}
		}
		ActionListener listener = new MenuItemListener();
		update.addActionListener(listener);
		return update;
	}
	
	/** Update Action method */
	private void updateAction(){
		int selectedIndex = table.getSelectedRow();
		if(selectedIndex >=0){
			Player selectedPlayer =
					WorldCupController.getInstance().getPlayers().get(selectedIndex);
		
			//Launch the Edit Player dialog
			JFrame outerFrame = (JFrame)getRootPane().getParent();
			
			
			AddPlayerDialog editPlayerDialog =
					new AddPlayerDialog(outerFrame, "Edit Player", selectedPlayer);
			editPlayerDialog.setSize(380,250);
			editPlayerDialog.setLocationRelativeTo(null);
			editPlayerDialog.setVisible(true);
		}
		else
		{
			JFrame outerFrame = new JFrame();
			JOptionPane.showMessageDialog(outerFrame, "Please Select a Player");
		}
	}
	
	/** Menu Edit->Delete */
	private JMenuItem createDeleteItem() {
		JMenuItem delete = new JMenuItem("Delete");
		class MenuItemListener implements ActionListener {	
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		}
			
		ActionListener listener = new MenuItemListener();
		delete.addActionListener(listener);
		return delete;
	}
	
	/** Delete Action method */
	private void deleteAction(){
		int selectedRow = table.getSelectedRow();
		if(selectedRow >=0)
		{
			WorldCupController.getInstance().deletePlayer(selectedRow);
		}
		else
		{
			JFrame outerFrame = new JFrame();
			JOptionPane.showMessageDialog(outerFrame, "Please Select a Player");
		}
	}

	
	private JPanel createBottomButtonPanel(){
		
		JPanel bottomButtonPanel = new JPanel();

        // Creating JButtons and adding text
		addButton = new JButton("Add");
		deleteButton = new JButton("Delete");
		editButton = new JButton("Edit");
		showTeamButton = new JButton("Team");

        // Checking for the correct image file path.
		try {
			Image addImg = ImageIO.read(getClass().getResource("/images/add.png"));
			addButton.setIcon(new ImageIcon(addImg));
			Image delImg = ImageIO.read(getClass().getResource("/images/delete.png"));
			deleteButton.setIcon(new ImageIcon(delImg));
			Image editImg = ImageIO.read(getClass().getResource("/images/edit.png"));
			editButton.setIcon(new ImageIcon(editImg));
			Image showTm = ImageIO.read(getClass().getResource("/images/football.png"));
			showTeamButton.setIcon(new ImageIcon(showTm));
		}catch (IOException ioe){
			ioe.printStackTrace();
		}

		// Set Button size
		addButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));


		// Listeners
		showTeamButton.addActionListener(e -> {
            int selectedIndex = table.getSelectedRow();
            if(selectedIndex >= 0)
            {
                Player selectedPlayer =
                        WorldCupController.getInstance().getPlayers().get(selectedIndex);

                        //Launch the Add Competitor dialog
                        JFrame outerFrame = (JFrame)getRootPane().getParent();
                        ShowTeamDialog showTeamDialog =
                                    new ShowTeamDialog(outerFrame, "Team Info",
                                            selectedPlayer.getName());
                showTeamDialog.setSize(600, 100);
                showTeamDialog.setLocationRelativeTo(null);
                showTeamDialog.setVisible(true);
            }
            else
            {
                JFrame outerFrame = new JFrame();
                JOptionPane.showMessageDialog(outerFrame, "Please Select a Player");
            }

        });



        // Replacing classic listener code --anonymous class is used to handle click event
        // with Java 8 Lamabda, see:
        // http://www.codejava.net/java-core/the-java-language/java-8-lambda-listener-example

		editButton.addActionListener(e -> updateAction());

        deleteButton.addActionListener(e -> deleteAction());

		
		//Creating listeners 
		AddButtonListener addButtonL = new AddButtonListener(this);
		addButton.addActionListener(addButtonL);
		
		bottomButtonPanel.add(addButton);
		bottomButtonPanel.add(Box.createHorizontalStrut(5));
		bottomButtonPanel.add(editButton);
		bottomButtonPanel.add(Box.createHorizontalStrut(5));
		bottomButtonPanel.add(deleteButton);
		bottomButtonPanel.add(Box.createHorizontalStrut(5));
		bottomButtonPanel.add(showTeamButton);
		
		
		return bottomButtonPanel;
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
			addPlayerDialog.setSize(380,250);
			addPlayerDialog.setLocationRelativeTo(null);
			addPlayerDialog.setVisible(true);
			
		}
	}
	
	
}















