package userInterface;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.*;

import javax.swing.table.*;

import logic.Task;

public class Create extends JPanel {

	private static final int ID_WIDTH = 10;
	private static final int TASK_WIDTH = 150;
	private static final int START_WIDTH = 20;
	private static final int END_WIDTH = 20;
	private static final int REMARKS_WIDTH = 50;
	private final static String[] header = { "ID", "Task", "Start", "End",
			"Remarks" };

	protected ArrayList<String> clo = new ArrayList<String>();
	protected Update mm;

	public Create() {
		init();
	}

	public Create(ArrayList<String> al) {

		System.out.println("in create" + al);
		// clo = (ArrayList<String>) al.clone();
		// System.out.println("clone list" + this.clo);
	}

	public void setList(ArrayList<String> clo) {
		this.clo = clo;
		System.out.println("in setList" + this.clo);

	}

	public ArrayList<String> getList() {

		return this.clo;
	}

	public void init() {

		mm = new Update(getList(), header);

		JTable table = new JTable(mm);
		table.setPreferredScrollableViewportSize(new Dimension(550, 200));
		table.setFillsViewportHeight(true);

		JScrollPane scroller = new JScrollPane(table);

		table.setVisible(true);

		// setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		TableColumn taskID = table.getColumnModel().getColumn(Update.INDEX_ID);
		TableColumn taskName = table.getColumnModel().getColumn(
				Update.INDEX_TASK);
		TableColumn Start = table.getColumnModel()
				.getColumn(Update.INDEX_START);
		TableColumn End = table.getColumnModel().getColumn(Update.INDEX_END);
		TableColumn Remarks = table.getColumnModel().getColumn(
				Update.INDEX_REMARKS);

		taskID.setPreferredWidth(ID_WIDTH);
		taskName.setPreferredWidth(TASK_WIDTH);
		Start.setPreferredWidth(START_WIDTH);
		End.setPreferredWidth(END_WIDTH);
		Remarks.setPreferredWidth(REMARKS_WIDTH);

		setLayout(new BorderLayout());
		add(scroller, BorderLayout.CENTER);

		ArrayList<String> finalClone = new ArrayList<String>();
		finalClone = Filter.returnList();

		Update u = new Update(finalClone, null);
		u.updateTable(finalClone);

	}

}
