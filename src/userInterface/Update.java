package userInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;

import userInterface.FilterInfo;

class Update extends AbstractTableModel {

	public static final int INDEX_ID = 0;
	public static final int INDEX_TASK = 1;
	public static final int INDEX_START = 2;
	public static final int INDEX_END = 3;
	public static final int INDEX_REMARKS = 4;

	private ArrayList<String> cloneList;

	protected Vector<FilterInfo> dataList;
	private String[] header;

	public Update(ArrayList<String> cloneList, String[] header) {
		super();
		this.cloneList = cloneList;

		this.header = header;
		dataList = new Vector();

	}

	public String getColumnName(int col) {
		return header[col];
	}

	public Class getColumnClass(int column) {
		switch (column) {
		case INDEX_ID:
		case INDEX_TASK:
		case INDEX_START:
		case INDEX_END:
		case INDEX_REMARKS:
			return String.class;
			// case INDEX_COMPLETED:
			// return Boolean.class;
		default:
			return Object.class;
		}
	}

	public Object getValueAt(int row, int column) {

		FilterInfo tableInfo = (FilterInfo) dataList.get(row);

		switch (column) {
		case INDEX_ID:
			return FilterInfo.getId();
		case INDEX_TASK:
			return FilterInfo.getName();
		case INDEX_START:
			return FilterInfo.getStart();
		case INDEX_END:
			return FilterInfo.getEnd();
		case INDEX_REMARKS:
			return FilterInfo.getRemarks();
			// case INDEX_COMPLETED:
			// return tableInfo.getCompleted();
		default:
			return new Object();
		}
	}

	public void setValueAt(Object value, int row, int column) {

		System.out.print("HEREEEEEEE" + row + " ");

		FilterInfo tableInfo = (FilterInfo) dataList.get(0);

		switch (column) {
		case INDEX_ID:
			FilterInfo.setId((Integer) value);
			break;
		case INDEX_TASK:
			FilterInfo.setName((String) value);
			break;
		case INDEX_START:
			tableInfo.setStart((String) value);
			break;
		case INDEX_END:
			FilterInfo.setEnd((String) value);
			break;
		case INDEX_REMARKS:
			FilterInfo.setRemarks((String) value);
			break;
		// case INDEX_COMPLETED:
		// tableInfo.setIsCompleted((Boolean) (value));
		// break;
		default:
			System.out.println("INVALID ! Please re-enter an index.");
		}
		fireTableCellUpdated(row, column);
		fireTableDataChanged();
	}

	public int getRowCount() {
		return this.cloneList.size();

	}

	public int getColumnCount() {
		return header.length;
	}

	public boolean hasEmptyRow() {
		if (dataList.size() == 0)
			return false;

		FilterInfo tableInfo = (FilterInfo) dataList.get(dataList.size() - 1);

		if (FilterInfo.getName().trim().equals("")
				&& FilterInfo.getStart().trim().equals("")
				&& FilterInfo.getEnd().trim().equals("")
				&& FilterInfo.getRemarks().trim().equals(""))

		{
			return true;
		} else
			return false;
	}

	public void addEmptyRow() {

		dataList.add(new FilterInfo());
		fireTableRowsInserted(dataList.size() - 1, dataList.size() - 1);
		// fireTableDataChanged();
	}

	public void clearRows() {
		dataList.clear();
		fireTableDataChanged();
	}

	public void updateTable(ArrayList<String> record) {

		int row = 0;
		clearRows();

		if (record.isEmpty()) {
			if (!this.hasEmptyRow()) {
				this.addEmptyRow();
			}
		}

		System.out.print("Here" + " " + FilterInfo.getId() + " "
				+ FilterInfo.getName() + " " + FilterInfo.getStart() + " "
				+ FilterInfo.getRemarks());

		for (int s = 0; s < record.size(); s++) {
			if (!this.hasEmptyRow()) {
				this.addEmptyRow();
			}

			this.setValueAt(FilterInfo.getId(), row, INDEX_ID);
			this.setValueAt(FilterInfo.getName(), row, INDEX_TASK);
			this.setValueAt(FilterInfo.getStart(), row, INDEX_START);
			this.setValueAt(FilterInfo.getEnd(), row, INDEX_END);
			this.setValueAt(FilterInfo.getRemarks(), row, INDEX_REMARKS);

			row++;

		}
	}

}
