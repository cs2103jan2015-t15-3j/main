package userInterface;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class HelpDesign {
	
	 public static JTabbedPane createTabbedPane() {
		 
		 	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(100, 100, 447, 470);
		
			//Add Command Guide
			JPanel addPanel = new JPanel();
			ImageIcon addIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/addIcon.png")));
			
			JLabel addLabel = new JLabel();
			addLabel.setText(HelpInfo.addCommandGuide());
			addPanel.add(addLabel);
			
			tabbedPane.addTab("Add", addIcon, addPanel, null);
			
			//Delete Command Guide
			JPanel deletePanel = new JPanel();
			ImageIcon deleteIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/deleteIcon.png")));
			
			
			JLabel deleteLabel = new JLabel();
			deleteLabel.setText(HelpInfo.deleteCommandGuide());
			deletePanel.add(deleteLabel);			
			
			tabbedPane.addTab("Delete", deleteIcon, deletePanel, null);
			
			//Edit Command Guide
			JPanel editPanel = new JPanel();
			ImageIcon editIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/editIcon.png")));
			
			JLabel editLabel = new JLabel();
			editLabel.setText(HelpInfo.editCommandGuide());
			editPanel.add(editLabel);	
			
			tabbedPane.addTab("Edit", editIcon, editPanel, null);
			
			//Complete/Uncomplete Command Guide
			JPanel completeAndUncompletePanel = new JPanel();
			ImageIcon completeIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/completeIcon.png")));
			
			JLabel completeAndUncompleteLabel = new JLabel();
			completeAndUncompleteLabel.setText(HelpInfo.completeAndUncompleteCommandGuide());
			completeAndUncompletePanel.add(completeAndUncompleteLabel);	
			
			tabbedPane.addTab("Complete/Uncomplete", completeIcon, completeAndUncompletePanel, null);
			
			//Search Command Guide
			JPanel searchPanel = new JPanel();
			ImageIcon searchIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/searchIcon.png")));
			
			JLabel searchLabel = new JLabel();
			searchLabel.setText(HelpInfo.searchCommandGuide());
			searchPanel.add(searchLabel);	
			
			tabbedPane.addTab("Search", searchIcon, searchPanel, null);
			
			return tabbedPane;
	 }
	 
	    public static JLabel createExitLabel(){
	        JLabel exitLabel = new JLabel("Press 'F1' again to exit Help Guide");
	        exitLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    	exitLabel.setForeground(new Color(0, 139, 139));
	        exitLabel.setBounds(26, 362, 193, 33);
	        exitLabel.setOpaque(true);
	        
	        return exitLabel;
	    }
}
