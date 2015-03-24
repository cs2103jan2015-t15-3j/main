package userInterface;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import logic.Task;


//create table connect to main
public class Creator extends JPanel{
	
	public static final String[] columnNames = { "ID", "Task", "Start", "End", "Remarks"};

	private static final int ID_WIDTH = 10;
	private static final int TASK_WIDTH = 150;
	private static final int START_WIDTH = 20;
	private static final int END_WIDTH = 20;
	private static final int REMARKS_WIDTH = 50;

	public static JTable table;
	public static JScrollPane scroller;
	
	//update table class
	protected Updator tableModel;
	
	public void updateTable(ArrayList<Task> taskList) {
		tableModel.updateTable(taskList);
	}
	
	public void tempTable(ArrayList<Task> tempList) {
		tableModel.tempTable(tempList);
		
	}
	
	public Creator() {
		initComponent();
	}
			
	public void initComponent(){
	
		tableModel = new Updator(columnNames);
		//tableModel.addTableModelListener(new InteractiveForm.InteractiveTableModelListener());
		
		table = new JTable();
		table.setModel(tableModel);
		
		//set table width scroll
		table.setPreferredScrollableViewportSize(new Dimension(550, 200));
		table.setFillsViewportHeight(true);
		
		table.setSurrendersFocusOnKeystroke(true);
		if (!tableModel.hasEmptyRow()) {
			tableModel.addEmptyRow();
		}
		scroller = new JScrollPane(table);
		
		TableColumn taskID = table.getColumnModel().getColumn(Updator.INDEX_ID);
		TableColumn taskName = table.getColumnModel().getColumn(Updator.INDEX_TASK);
		TableColumn Start = table.getColumnModel().getColumn(Updator.INDEX_START);
		TableColumn End = table.getColumnModel().getColumn(Updator.INDEX_END);
		TableColumn Remarks = table.getColumnModel().getColumn(Updator.INDEX_REMARKS);
		
		taskID.setPreferredWidth(ID_WIDTH);
		taskName.setPreferredWidth(TASK_WIDTH);
		Start.setPreferredWidth(START_WIDTH);
		End.setPreferredWidth(END_WIDTH);
		Remarks.setPreferredWidth(REMARKS_WIDTH);
			
		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);
	}

	
	
}
