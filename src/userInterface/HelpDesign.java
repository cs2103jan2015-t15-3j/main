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

		JPanel addPanel = new JPanel();
		ImageIcon addIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/addIcon.png")));
		JLabel addLabel = new JLabel();
		addLabel.setText(HelpInfo.addCommandGuide());
		addPanel.add(addLabel);
		tabbedPane.addTab("Add", addIcon, addPanel, null);

		JPanel deleteUndoRedoPanel = new JPanel();
		ImageIcon deleteUndoRedoIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/deleteIcon.png")));
		JLabel deleteUndoRedoLabel = new JLabel();
		deleteUndoRedoLabel.setText(HelpInfo.deleteCommandGuide());
		deleteUndoRedoPanel.add(deleteUndoRedoLabel);
		tabbedPane.addTab("Delete/Undo/Redo", deleteUndoRedoIcon,
				deleteUndoRedoPanel, null);

		JPanel editDisplayPanel = new JPanel();
		ImageIcon editDisplayIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/editIcon.png")));
		JLabel editDisplayLabel = new JLabel();
		editDisplayLabel.setText(HelpInfo.editCommandGuide());
		editDisplayPanel.add(editDisplayLabel);
		tabbedPane.addTab("Edit/Display", editDisplayIcon, editDisplayPanel,
				null);

		JPanel completeAndUncompletePanel = new JPanel();
		ImageIcon completeIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/completeIcon.png")));
		JLabel completeAndUncompleteLabel = new JLabel();
		completeAndUncompleteLabel.setText(HelpInfo
				.completeAndUncompleteCommandGuide());
		completeAndUncompletePanel.add(completeAndUncompleteLabel);
		tabbedPane.addTab("Complete/Uncomplete", completeIcon,
				completeAndUncompletePanel, null);

		JPanel searchPanel = new JPanel();
		ImageIcon searchIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/searchIcon.png")));
		JLabel searchLabel = new JLabel();
		searchLabel.setText(HelpInfo.searchCommandGuide());
		searchPanel.add(searchLabel);
		tabbedPane.addTab("Search", searchIcon, searchPanel, null);

		JPanel psearchPanel = new JPanel();
		ImageIcon psearchIcon = new ImageIcon(
				(UserInterfaceMain.class
						.getResource("/userInterface/ImageIcon/powerIcon.png")));
		JLabel psearchLabel = new JLabel();
		psearchLabel.setText(HelpInfo.psearchCommandGuide());
		psearchPanel.add(psearchLabel);
		tabbedPane.addTab("PSearch", psearchIcon, psearchPanel, null);

		return tabbedPane;
	}
}