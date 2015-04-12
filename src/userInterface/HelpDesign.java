package userInterface;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

//@author A0112961L

public class HelpDesign {
	
	protected static JTabbedPane createTabbedPane() {
		 
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
			
			tabbedPane.addTab("Delete/Undo/Redo", deleteIcon, deletePanel, null);
			
			//Edit Command Guide
			JPanel editPanel = new JPanel();
			ImageIcon editIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/editIcon.png")));
			
			JLabel editLabel = new JLabel();
			editLabel.setText(HelpInfo.editCommandGuide());
			editPanel.add(editLabel);	
			
			tabbedPane.addTab("Edit/Display", editIcon, editPanel, null);
			
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
			

			//Power Search Command Guide
			JPanel psearchPanel = new JPanel();
			ImageIcon psearchIcon = new ImageIcon((UserInterfaceMain.class.getResource("/userInterface/ImageIcon/powerIcon.png")));
			
			JLabel psearchLabel = new JLabel();
			psearchLabel.setText(HelpInfo.psearchCommandGuide());
			psearchPanel.add(psearchLabel);	
			
			tabbedPane.addTab("PSearch", psearchIcon, psearchPanel, null);
			
			return tabbedPane;
	 }
}
