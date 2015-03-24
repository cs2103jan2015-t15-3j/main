package userInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import logic.Memory;
import logic.Task;
import logic.Appointment;

public class Updator extends AbstractTableModel {

	public static final int INDEX_ID = 0;
	public static final int INDEX_TASK = 1;
	public static final int INDEX_START = 2;
	public static final int INDEX_END = 3;
	public static final int INDEX_REMARKS = 4;
	//public static final int INDEX_COMPLETED = 5;
	public static final int INDEX_HIDDEN = 6;

	protected String[] columnNames;
	protected Vector data;
	protected Vector appt;
	
	public Updator(String[] columnNames) {
		this.columnNames = columnNames;
		data = new Vector();
		appt = new Vector();
	}

	public String getColumnName(int column) {
		return columnNames[column];
	}

	public boolean isCellEditable(int row, int col) {
		if (4 == col) {
			return true;
		} else {
			return false;
		}
	}

	public Class getColumnClass(int column) {
		switch (column) {
		case INDEX_ID:
		case INDEX_TASK:
		case INDEX_START:
		case INDEX_END:
		case INDEX_REMARKS:
			return String.class;
		//case INDEX_COMPLETED:
          //  return Boolean.class;
		default:
			return Object.class;
		}
	}

	public Object getValueAt(int row, int column) {
		Task tableInfo = (Task) data.get(row);
		
		Appointment info = (Appointment) appt.get(row);
		
		switch (column) {
		case INDEX_ID:
			return tableInfo.getTaskID();
		case INDEX_TASK:
			return tableInfo.getTaskName();
		case INDEX_START:
			return info.getStartDate();
		case INDEX_END:
			return tableInfo.getTaskName();
		case INDEX_REMARKS:
			return tableInfo.getRemarks();
		// case INDEX_COMPLETED:
	      //      return tableInfo.getCompleted();
		default:
			return new Object();
		}
	}

	public void setValueAt(Object value, int row, int column) {
		Task tableInfo = (Task) data.get(row);
		Appointment info = (Appointment) appt.get(row);
		
		switch (column) {
		case INDEX_ID:
			tableInfo.setTaskID((Integer) value);
			break;
		case INDEX_TASK:
			tableInfo.setTaskName((String) value);
			break;
		case INDEX_START:
			info.setStartDate((Date) value);
			break;
		case INDEX_END:
			tableInfo.setTaskName((String) value);
			break;
		case INDEX_REMARKS:
			tableInfo.setRemarks((String)value);
			break;
		//case INDEX_COMPLETED:
          //  tableInfo.setIsCompleted((Boolean) (value));
            //break;
		default:
			System.out.println("INVALID ! Please re-enter an index.");
		}
		fireTableCellUpdated(row, column);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	public boolean hasEmptyRow() {
		if (data.size() == 0)
			return false;
		else {
		Task tableInfo = (Task) data.get(data.size() - 1);
		
		Appointment info = (Appointment) appt.get(appt.size() - 1);
		
		if (tableInfo.getTaskName().trim().equals("")
				&& (info.getStartDate()).equals("")
				&& tableInfo.getTaskName().trim().equals("")
				&& tableInfo.getRemarks().trim().equals(""))
			
		{
			return true;
		} else
			return false;
		}
	}
	
	//add a new row
	public void addEmptyRow() {
		appt.add(new Appointment());
		fireTableRowsInserted(appt.size() - 1, appt.size() - 1);
		
		data.add(new Task());
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	}

	//Update table information to Jtable
	public void updateTable(ArrayList<Task> taskList) {
		
		Memory mem = new Memory();
		Appointment a = new Appointment();
		
		int row = 0;

		clearRows();
		
		if (taskList.isEmpty()) {
			if (!this.hasEmptyRow()) {
				this.addEmptyRow();
			}
		}
		 
		 // sort the table in alphabetical order
        Collections.sort(taskList, new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t1.getTaskName().compareToIgnoreCase(
                        t2.getTaskName());
            }
        });
			for (Task task : taskList) {
				if (!this.hasEmptyRow()) {
					this.addEmptyRow();
				}
				
					this.setValueAt(task.getTaskID(), row, INDEX_ID);
					this.setValueAt(task.getTaskName(), row, INDEX_TASK);
					
					this.setValueAt(a.getStartDate(), row, INDEX_START);
					this.setValueAt(task.getTaskName(), row, INDEX_END);
					this.setValueAt(task.getRemarks(), row, INDEX_REMARKS);
					
					/*
					if (!task.getStart().equals(null){
						this.setValueAt(task., row, INDEX_START);
					}
					if (!task.getEnd().equals(null){
						this.setValueAt(task., row, INDEX_END);
					}
					 */			
					/*this.setValueAt(task.getRemarks(), row, INDEX_REMARKS);
					 if (task.getCompleted() == true) {
			                this.setValueAt(true, row, INDEX_CHECK);
			            }*/
					
					//test print ID
					System.out.println(task.getTaskID());
					
					row++;
				}
			}
	
	
	public void clearRows() {
		data.clear();
		appt.clear();
		
	}
	
	//testing 
	/*public void loadTable(ArrayList<Task> list) {
		// TODO Auto-generated method stub
		
		int row = 0;
		
		Task task = new Task();
		
		Iterator<Task> itr = list.iterator();
		
		while(itr.hasNext()){
			this.addEmptyRow();
			
			this.setValueAt(task.getTaskID(), row, 0);
			this.setValueAt(task.getTaskName(), row, 1);
			this.setValueAt(task.getTaskName(), row, 2);
			this.setValueAt(task.getTaskName(), row, 3);
			this.setValueAt(task.getTaskName(), row, 4);
			row++;
			}
			
			
		}*/
}	