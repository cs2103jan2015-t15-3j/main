package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import logic.Appointment;
import logic.Deadline;
import logic.Repository;
import logic.Task;
import logic.Enumerator.TaskType;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ProTaskStorage {

	private final String taskDataBase = "test.csv";
	private final String tempDataBase = "temp.csv";

	protected ArrayList<Appointment> allAppointments;
	private ArrayList<Task> allTasks;
	private String[] dataBaseColumns;
	private int idCounter;
	private boolean justLaunched; 

	/*
	 * public static void main(String args[]) throws
	 * 
	 * FileNotFoundException {
	 * 
	 * dataBaseColumns = new String[] { "ID",
	 * 
	 * "Description", "Start", "End", "Remarks", "Completed" }; if (!
	 * 
	 * checkFileExist()) {
	 * 
	 * createDataBase(taskDataBase);
	 * 
	 * addStringTask(1, "Complete CS2103 tut", "08
	 * 
	 * March 13:00", "09 March 15:40", "Some qns not sure",
	 * 
	 * true); addStringTask(2, "Go for a run", "21 Feb 08:00", " 21 Feb
	 * 
	 * 08:15", "so tiring", false); addStringTask(3, "Silat", " 9 Feb 09:00",
	 * 
	 * "12:00", "seni", false);
	 * 
	 * } else { loadAllTasks(); } loadAllTasks();
	 * 
	 * //deleteTask(2); //getAllTasks();
	 * 
	 * //clearCompletedTasks(); }
	 */

	public ProTaskStorage() {

		dataBaseColumns = new String[] { "ID", "Description", "Start", "End",
				"Remarks", "Completed", "Type" };
		idCounter = 1;
		if (!checkFileExist()) {

			createDataBase(taskDataBase);
			// addString is just for testing purposes!
			/*
			 * addStringTask(1, "Complete CS2103 tut", "08 March 13:00",
			 * "09 March 15:40", "Some qns not sure", false); addStringTask(2,
			 * "Go for a run", "21 Feb 08:00", " 21 Feb 08:15", "so tiring",
			 * true); addStringTask(3, "Silat", " 9 Feb 09:00", "12:00", "seni",
			 * false);
			 */
		}
		try {
			loadAllTasks();
			justLaunched = true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}

	}

	private boolean checkFileExist() {
		boolean fileExist = false;
		File database = new File(taskDataBase);
		if (database.exists()) {
			fileExist = true;
		}
		return fileExist;
	}

	private void createDataBase(String dataBaseName) {
		try {
			FileWriter writer = new FileWriter(dataBaseName);

			for (int i = 0; i < dataBaseColumns.length; i++) {
				writer.append

				(dataBaseColumns[i]);

				if (i == dataBaseColumns.length - 1) {

					writer.append('\n');
				} else {

					writer.append(",");
				}
			}

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = df.format(date);
		return dateString;

	}

	private String intToString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	private boolean isCorrectColumns(String[] dataBaseCols) {

		if (dataBaseColumns == dataBaseCols) {
			return true;
		} else
			return false;

	}

	private void replaceTempToOriginal() {
		File oldfile = new File(tempDataBase);
		File newfile = new File(taskDataBase);

		newfile.delete();

		if (oldfile.renameTo(newfile)) {
			System.out.println("Rename succesful");
		} else {
			System.out.println("Rename failed");
		}
	}

	private boolean stringToBoolean(String rowEntry) {

		boolean toReturnBoolean = false;

		if (rowEntry.equalsIgnoreCase("true")) {
			toReturnBoolean = true;
		}

		return toReturnBoolean;
	}

	private String convertToAbbreviation(String type) {
		String returnType = "NIL";

		if (type.equals("FLOATING")) {
			returnType = "FL";
		} else if (type.equals("APPOINTMENT")) {
			returnType = "AP";
		} else if (type.equals("DEADLINE")) {
			returnType = "DL";
		}
		return returnType;
	}

	private Date stringToDate(String stringDate) {
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm a",
				Locale.ENGLISH);
		Date date = new Date();

		try {
			date = format.parse(stringDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date; // Sat Jan 02 00:00:00 GMT 2010
	}

	private void addAppointment(Appointment newApmt, String dataBaseName) {

		try {
			CSVWriter writer = new

			CSVWriter(new FileWriter(dataBaseName, true));

			ArrayList<String> record = new ArrayList<String>();

			record.add(intToString(newApmt.getTaskID()));
			record.add(newApmt.getTaskName());
			record.add(newApmt.getStartDateString());
			record.add(newApmt.getDueDateString());
			record.add(newApmt.getRemarks());
			record.add(String.valueOf(newApmt.getCompleted()));

			writer.writeNext((String[])

			record.toArray(new String[0]));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// for testing purposes
	public void addStringTask(int id, String desc, String startTime,
			String endTime, String remarks, boolean isCompleted, String type) {

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(taskDataBase, true));

			ArrayList<String> record = new ArrayList<String>();

			record.add(intToString(id));
			record.add(desc);
			record.add(startTime);
			record.add(endTime);
			record.add(remarks);
			record.add(String.valueOf(isCompleted));
			record.add(convertToAbbreviation(type));

			writer.writeNext((String[])

			record.toArray(new String[0]));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void addAppointment(Appointment newApmt) {

		try {
			CSVWriter writer = new

			CSVWriter(new FileWriter(taskDataBase, true));

			ArrayList<String> record = new ArrayList<String>();

			record.add(intToString(newApmt.getTaskID()));
			record.add(newApmt.getTaskName());
			record.add(newApmt.getStartDateString());
			record.add(newApmt.getDueDateString());
			record.add(newApmt.getRemarks());
			record.add(String.valueOf(newApmt.getCompleted()));

			writer.writeNext((String[])

			record.toArray(new String[0]));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Repository writeToFile(Repository buffer) {
		ArrayList<Task> obtainedTasks = buffer.getBuffer();
		ArrayList<Task> temp = new ArrayList<Task>();

		
		temp.add(obtainedTasks.get(obtainedTasks.size()-1));
		
		for (Task task : temp) {

			String type = task.getType().toString();
			buffer.setCurrentID(idCounter);
			
			if (type.equals("APPOINTMENT")) {
				Appointment item = (Appointment) task;
				addStringTask(idCounter, task.getTaskName(),
						item.getStartDateString(), item.getDueDateString(),
						task.getRemarks(), false, task.getType().toString());
			} else if (type.equals("DEADLINE")) {
				Deadline item = (Deadline) task;
				task.setTaskID(idCounter);
				addStringTask(idCounter, task.getTaskName(), "",
						item.getDueDateString(), task.getRemarks(), false, task
								.getType().toString());
			} else {
				task.setTaskID(idCounter);
				
				addStringTask(idCounter, task.getTaskName(), "", "",
						task.getRemarks(), false, task.getType().toString());
			}
			idCounter++;
			//allTasks.add(task);
		}
		
		if (justLaunched)
		{
			justLaunched = false;
			for (Task task : temp)
			{
				allTasks.add(task);
			}
			buffer.setBuffer(allTasks);
		}
		
		return buffer;
	}

	public void clearCompletedTasks() {

		createDataBase(tempDataBase);
		for (Appointment task : allAppointments) {
			if (task.getCompleted() == false) {
				addAppointment(task, tempDataBase);
			}
		}
		replaceTempToOriginal();
	}

	public void deleteTask(int ID) {

		createDataBase(tempDataBase);
		for (Appointment app : allAppointments) {
			if (app.getTaskID() != ID) {
				addAppointment(app, tempDataBase);
			}
		}
		replaceTempToOriginal();
	}

	public void deleteTask(Repository buffer) {

	}

	public void updateTask(Appointment updatedApp) {

		createDataBase(tempDataBase);

		for (Appointment app : allAppointments) {

			if (app.getTaskID() != updatedApp.getTaskID()) {
				addAppointment(app, tempDataBase);

			}
		}
		replaceTempToOriginal();

	}

	public void loadAllTasks() throws FileNotFoundException {
		// Build reader instance

		CSVReader reader = new CSVReader(new FileReader(taskDataBase));
		int lastID = 0;

		try {

			boolean isColumn = true;

			// Read all rows at once

			List<String[]> allRows = reader.readAll();

			if (allAppointments == null) {

				allAppointments = new ArrayList<Appointment>();

			}
			if (allTasks == null) {
				allTasks = new ArrayList<Task>();
			}

			// Read CSV line by line and use the string array as you want
			for (String[] row : allRows) {
				if (isColumn) {
					if (!isCorrectColumns(row)) {

						// Cannot continue because columns in database is

						// different then in system.
					}

					isColumn = false;

				} else {

					Task newTask = new Task();
					newTask.setTaskID(Integer.parseInt(row[0]));
					lastID = Integer.parseInt(row[0]);
					newTask.setTaskName(row[1]);
					
					allTasks.add(newTask);
					/*
					 * Appointment newApmt = new Appointment();
					 * 
					 * newApmt.setTaskId(Integer.parseInt(row[0]));
					 * newApmt.setTaskName(row[1]);
					 * newApmt.setStartDate(stringToDate(row[2]));
					 * newApmt.setDate(stringToDate(row[3]));
					 * newApmt.setRemarks(row[4]);
					 * newApmt.setIsCompleted(stringToBoolean(row[5]));
					 * 
					 * allAppointments.add(newApmt);
					 */
					// System.out.println(Arrays.toString(row));
				}
			}
			
			idCounter = lastID + 1;

			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
