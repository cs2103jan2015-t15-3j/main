package userInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import logic.Memory;
import logic.Task;

public class Loader extends AbstractTableModel{
	
	
	Memory mem = new Memory();

	public static final int ID = 0;
    public static final int TASK = 1;
    public static final int START = 2;
    public static final int END = 3;
    public static final int REMARKS = 4;


    protected String[] columnNames;
    protected Vector dataVector;

    public Loader(String[] columnNames) {
    	
        this.columnNames = columnNames;
        dataVector = new Vector();
    }

    public String getColumnName(int column) {
        return columnNames[column];
    }



    public Class getColumnClass(int column) {
        switch (column) {
        case ID:
        case TASK:
        case START:
        case END:
        case REMARKS:
            return String.class;
        default:
            return Object.class;
        }
    }

    public Object getValueAt(int row, int column) {
    	
        RetrieveInfo info = (RetrieveInfo) dataVector.get(row);
        
        switch (column) {

        case ID:
            return info.getId();
            
        case TASK:
            return info.getTask();
            
        case START:
            return info.getStart();
            
        case END:
            return info.getEnd();
            
        case REMARKS:
            return info.getRemarks();
        default:
            return new Object();
        }
    }

    public void setValueAt(Object value, int row, int column) {
        RetrieveInfo info = (RetrieveInfo) dataVector.get(row);
        switch (column) {
        case ID:
            info.setId((int) value);
            break;
            
        case TASK:
            info.setTask((String) value);
            break;
            
        case START:
            info.setStart((String) value);
            break;
        case END:
            info.setEnd((String) value);
            break;
        case REMARKS:
            info.setRemarks((String) value);
            break;
        default:
            System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataVector.size();
    }
    
    public boolean hasEmptyRow() {
        if (dataVector.size() == 0)
            return false;
        RetrieveInfo info = (RetrieveInfo) dataVector.get(dataVector.size() - 1);
        
        if (info.getTask().trim().equals("")
                && info.getTask().trim().equals("")
                && info.getStart().trim().equals("")
                && info.getEnd().trim().equals("")
                && info.getRemarks().trim().equals(""){
            return true;
        else
            return false;
    }

    public void addEmptyRow() {
        dataVector.add(new RetrieveInfo());
        fireTableRowsInserted(dataVector.size() - 1, dataVector.size() - 1);
    }


	public static void updateTable(ArrayList<Task> printList) {
		// TODO Auto-generated method stub
		
		int row = 0;

        final LocalDate CORRECT_YEAR_FORMAT = LocalDate.of(1, 1, 1);
        DateTimeFormatter dateFormatter;

        DateTimeFormatter timeFormatter = DateTimeFormatter
                .ofPattern("hh:mm a");

        clearRows();

        if (printList.isEmpty()) {
            if (!this.hasEmptyRow()) {
                this.addEmptyRow();
            }
        }

        for (Task task : taskList) {
            if (!this.hasEmptyRow()) {
                this.addEmptyRow();
            }

            if (task.getStartDate().isAfter(CORRECT_YEAR_FORMAT)
                    && task.getEndDate().isAfter(CORRECT_YEAR_FORMAT)) {
                dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            } else {
                dateFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
            }

            
            this.setValueAt("" + (row + 1), row, INDEX_TASK);

            this.setValueAt(task.getDescription(), row, INDEX_DESCRIPTION);

            if (!task.getStartDate().equals(null) && !task.getStartDate().equals(LocalDate.MAX)) {
               
            	this.setValueAt(task.getStartDate().format(dateFormatter), row, INDEX_STARTDATE);
            }
            if (!task.getEndDate().equals(null)
                    && !task.getEndDate().equals(LocalDate.MAX)) {
            	
            	
                this.setValueAt(task.getEndDate().format(dateFormatter), row,
                        INDEX_ENDDATE);
            }
            if (!task.getStartTime().equals(null)
                    && !task.getStartTime().equals(LocalTime.MAX)) {
                this.setValueAt(task.getStartTime().format(timeFormatter), row,
                        INDEX_STARTTIME);
            }
            if (!task.getEndTime().equals(null)
                    && !task.getEndTime().equals(LocalTime.MAX)) {
                this.setValueAt(task.getEndTime().format(timeFormatter), row,
                        INDEX_ENDTIME);
            }
            if (task.getPriority() != null
                    && !task.getPriority().toString().isEmpty()) {
                this.setValueAt(task.getPriority().toString(), row,
                        INDEX_PRIORITY);
            }
            if (task.getCompleted() == true) {
                this.setValueAt(true, row, INDEX_CHECK);
            }
            row++;
        }

		
	}

}

    public void clearRows() {
        dataVector.clear();
    }
}

