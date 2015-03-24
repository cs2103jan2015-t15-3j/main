package userInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import logic.Deadline;
import logic.Repository;
import logic.Task;
import logic.Appointment;

public class Updator extends AbstractTableModel {

	public static final int INDEX_ID = 0;
	public static final int INDEX_TASK = 1;
	public static final int INDEX_START = 2;
	public static final int INDEX_END = 3;
	public static final int INDEX_REMARKS = 4;
	//public static final int INDEX_COMPLETED = 5;

	protected String[] columnNames;
	protected Vector data;
	protected Vector appt;
	protected Vector due;
	
	public Updator(String[] columnNames) {
		this.columnNames = columnNames;
		data = new Vector();
		appt = new Vector();
		due = new Vector();
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
		Appointment dd = (Appointment) due.get(row);
		
		switch (column) {
		case INDEX_ID:
			return tableInfo.getTaskID();
		case INDEX_TASK:
			return tableInfo.getTaskName();
		case INDEX_START:
			//return info.getStartDateString();
		case INDEX_END:
			//return tableInfo.getTaskName();
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
		Appointment dd = (Appointment) due.get(row);
		
		switch (column) {
		case INDEX_ID:
			tableInfo.setTaskID((Integer) value);
			break;
		case INDEX_TASK:
			tableInfo.setTaskName((String) value);
			break;
		case INDEX_START:
			//info.setStartDate(null);
			break;
		case INDEX_END:
			//dd.setDate(null); 
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
		//fireTableDataChanged();
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
		
		Task tableInfo = (Task) data.get(data.size() - 1);
		Appointment info = (Appointment) appt.get(appt.size() - 1);
		Appointment dd = (Appointment) due.get(due.size() - 1);
		
		if (tableInfo.getTaskName().trim().equals("")
				&& info.getStartDateString().equals("")
				//&& tableInfo.getTaskName().trim().equals("")
				&& tableInfo.getRemarks().trim().equals(""))
		
		{
			return true;
		} 
		else
			return false;
	}
	
	public void addEmptyRow() {
		due.add(new Appointment());
		fireTableRowsInserted(due.size() - 1, due.size() - 1);
		//fireTableDataChanged();
		
		appt.add(new Appointment());
		fireTableRowsInserted(appt.size() - 1, appt.size() - 1);
		//fireTableDataChanged();

		data.add(new Task());
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
		//fireTableDataChanged();
	}
	
	public void clearRows() {
		data.clear();
		appt.clear();
		due.clear();
		fireTableDataChanged();
	}

	public void updateTable(ArrayList<Task> taskList) {
		
		Appointment a = new Appointment();
		Deadline d = new Deadline();
		
		int row = 0;

		clearRows();
		//fireTableDataChanged();
		
		   if (taskList.isEmpty()) {
	            if (!this.hasEmptyRow()) {
	                this.addEmptyRow();
	            }
	        }

	        // sort the table in alphabetical order
	      /*  Collections.sort(taskList, new Comparator<Task>() {
	            public int compare(Task t1, Task t2) {
	                return t1.getTaskName().compareToIgnoreCase(
	                        t2.getTaskName());
	            }
	        });*/

			for (Task task : taskList) {
				if (!this.hasEmptyRow()) {
					this.addEmptyRow();
				}
				
					this.setValueAt(task.getTaskID(), row, INDEX_ID);
					this.setValueAt(task.getTaskName(), row, INDEX_TASK);
					
					//if(a.getStartDateString().equals("NIL"))
					//{
					//this.setValueAt(task.getTaskName(), row, INDEX_START);
					//}
					//else
					//{
					this.setValueAt(a.getStartDateString(), row, INDEX_START);
					//}
					this.setValueAt(d.getDueDateString(), row, INDEX_END);
					this.setValueAt(task.getRemarks(), row, INDEX_REMARKS);	

					//test date
					System.out.println(a.getStartDateString());
					System.out.println(d.getDueDateString());
					System.out.println(data.size());
					
					row++;
				}
			}


}	