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
			tabbedPane.setBounds(718, 719, -714, -722);
			
			JPanel addPanel = new JPanel();
			ImageIcon addIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/addIcon.png")));
			JLabel addLabel = new JLabel();
			
			
			addLabel.setText(HelpInfo.addGuide());
			
			addPanel.add(addLabel);
			
			tabbedPane.addTab("Add", addIcon, addPanel, null);
			
			JPanel deletePanel = new JPanel();
			ImageIcon deleteIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/deleteIcon.png")));
			tabbedPane.addTab("Delete", deleteIcon, deletePanel, null);
			
			JPanel panel = new JPanel();
			ImageIcon editIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/editIcon.png")));
			tabbedPane.addTab("Edit", editIcon, panel, null);
			
			JPanel completePanel = new JPanel();
			ImageIcon completeIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/completeIcon.png")));
			tabbedPane.addTab("Complete", completeIcon, completePanel, null);
			
			JPanel searchPanel = new JPanel();
			ImageIcon searchIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/searchIcon.png")));
			tabbedPane.addTab("Search", searchIcon, searchPanel, null);
			
			return tabbedPane;
	 }
	 
	    public static JLabel createExitLabel(){
	        JLabel exitLabel = new JLabel("Press 'F1' again to exit Help Guide");
	        exitLabel.setFont(new Font("Times New Roman", Font.BOLD, 12));
	    	exitLabel.setForeground(new Color(0, 139, 139));
	        exitLabel.setBounds(26, 386, 324, 33);
	        exitLabel.setOpaque(true);
	        
	        return exitLabel;
	    }

}
