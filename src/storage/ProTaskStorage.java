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

import userInterface.Logging;
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

	public ProTaskStorage() {

		dataBaseColumns = new String[] { "ID", "Description", "Start", "End",
				"Remarks", "Completed", "Type" };
		idCounter = 1;
		if (!checkFileExist()) {

			createDataBase(taskDataBase);
		}

		try{
			loadAllTasks();
			justLaunched = true;
		}
		catch(Exception e)
		{
			Logging.getInputLog("Exception thrown when program attempst to load databsase into program!");
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
			Logging.getInputLog("Exception thrown when program attemmpts to create database!");
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
	
	private TaskType abbreviationToTaskType(String abb)
	{
		TaskType type = TaskType.FLOATING;
		if (abb.equals("AP"))
		{
			type = TaskType.APPOINTMENT;
		}
		else if (abb.equals("DL"))
		{
			type = TaskType.DEADLINE;
		}
		return type;
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

	public void addStringTask(String databaseName, int id, String desc, String startTime,
			String endTime, String remarks, boolean isCompleted, String type) {

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(databaseName, true));

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
			Logging.getInputLog("Exception thrown when user wants to add new task with ID: "+intToString(id)+"; Name: "+desc);
		}
	}

	public Repository writeToFile(Repository buffer) {
		ArrayList<Task> obtainedTasks = buffer.getBuffer();

		Task newTask = obtainedTasks.get(obtainedTasks.size() - 1);

		String type = newTask.getType().toString();
		buffer.setCurrentID(idCounter);

		if (type.equals("APPOINTMENT")) {
			Appointment item = (Appointment) newTask;
			addStringTask(taskDataBase,buffer.getCurrentID(), newTask.getTaskName(),
					item.getStartDateString(), item.getDueDateString(),
					newTask.getRemarks(), false, newTask.getType().toString());
		} else if (type.equals("DEADLINE")) {
			Deadline item = (Deadline) newTask;
			newTask.setTaskID(buffer.getCurrentID());
			addStringTask(taskDataBase,buffer.getCurrentID(), newTask.getTaskName(), "",
					item.getDueDateString(), newTask.getRemarks(), false,
					newTask.getType().toString());
		} else {
			newTask.setTaskID(buffer.getCurrentID());

			addStringTask(taskDataBase,buffer.getCurrentID(), newTask.getTaskName(), "", "",
					newTask.getRemarks(), false, newTask.getType().toString());
		}
		idCounter++;

		if (justLaunched) {
			justLaunched = false;
			allTasks.add(newTask);
			buffer.setBuffer(allTasks);
		}

		return buffer;
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
					newTask.setRemarks(row[4]);
					newTask.setIsCompleted(stringToBoolean(row[5]));
					newTask.setType(abbreviationToTaskType(row[6]));
					allTasks.add(newTask);
					
				}
			}

			idCounter = lastID + 1;

			reader.close();

		} catch (Exception e) {
			
			Logging.getInputLog("Exception thrown when loading tasks from database to program!");
		}
	}

	public void updateDeleteTask(Repository buffer) {
		
		createDataBase(tempDataBase);		
		
		for (Task task : buffer.getBuffer())
		{
			String type = task.getType().toString();
			if (type.equals("APPOINTMENT")) {
				
				Appointment item = (Appointment) task;
				addStringTask(tempDataBase,task.getTaskID(), task.getTaskName(),
						item.getStartDateString(), item.getDueDateString(),
						task.getRemarks(), task.getCompleted(), task.getType().toString());
				
			}else if (type.equals("DEADLINE")) {
				
				Deadline item = (Deadline) task;
				addStringTask(tempDataBase,task.getTaskID(), task.getTaskName(), "",
						item.getDueDateString(), task.getRemarks(), task.getCompleted(),
						task.getType().toString());
			}
			else {
			
			addStringTask(tempDataBase,task.getTaskID(), task.getTaskName(), "", "",
					task.getRemarks(), task.getCompleted(), task.getType().toString());
			}
		}
		replaceTempToOriginal();
	}
}
