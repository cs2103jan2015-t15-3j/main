package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import logic.Appointment;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ProTaskStorage {

	private final static String taskDataBase = "test.csv";
	protected static ArrayList<Appointment> allTasks;
	private static String[] dataBaseColumns;

	public static void main(String args[]) throws FileNotFoundException {

		dataBaseColumns = new String[] { "ID", "Description", "Start",
				"End", "Remarks", "Completed" };
		if (!checkFileExist()) {

			

			createDataBase(taskDataBase);
			
		}
		/*
		 * addTask(1, "Complete CS2103 tut", "08 March", "13:00", "15:40",
		 * "Some qns not sure", false); addTask(2, "Go for a run", "21 Feb",
		 * "08:00", "08:15", "so tiring", true); addTask(3, "Silat", "9 Feb",
		 * "09:00", "12:00", "seni", false);
		 */
		getAllTasks();
		deleteTask(2);
		getAllTasks();
		// clearCompletedTasks();
	}

	public ProTaskStorage() {
		/*
		 * if (!checkFileExist()) {
		 * 
		 * dataBaseColumns = new String[] { "ID", "Description", "Start", "End",
		 * "Remarks", "Completed" };
		 * 
		 * createDataBase();
		 * 
		 * } else {getAllTasks();}
		 */
	}

	private static boolean checkFileExist() {
		boolean fileExist = false;
		File database = new File(taskDataBase);
		if (database.exists()) {
			fileExist = true;
		}
		return fileExist;
	}

	private static String intToString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	private static boolean stringToBoolean(String rowEntry) {
		boolean toReturnBoolean = false;

		if (rowEntry.equalsIgnoreCase("true")) {
			toReturnBoolean = true;
		}

		return toReturnBoolean;
	}

	private String dateToString(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dateString = df.format(date);
		return dateString;

	}

	public static void addProTask(ProTask task) {

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(taskDataBase, true));

			ArrayList<String> record = new ArrayList<String>();
			record.add(intToString(task.getID()));
			record.add(task.getTaskName());
			record.add(task.getDate());
			record.add(task.getStartTime());
			record.add(task.getEndTime());
			record.add(task.getRemarks());
			record.add(String.valueOf(task.getCompleted()));

			writer.writeNext((String[]) record.toArray(new String[0]));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addStringTask(int id, String desc, String startDate,
			String startTime, String endTime, String remarks,
			boolean isCompleted) {

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(taskDataBase, true));

			ArrayList<String> record = new ArrayList<String>();
			record.add(intToString(id));
			record.add(desc);
			record.add(startDate);
			record.add(startTime);
			record.add(endTime);
			record.add(remarks);
			record.add(String.valueOf(isCompleted));

			writer.writeNext((String[]) record.toArray(new String[0]));

			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void addAppointment(Appointment newApmt, String dataBaseName) {
		try {
			CSVWriter writer = new CSVWriter(new FileWriter(dataBaseName, true));

			ArrayList<String> record = new ArrayList<String>();

			record.add(intToString(newApmt.getTaskId()));
			record.add(newApmt.getTaskName());
			record.add(newApmt.getStartDateString());
			record.add(newApmt.getDueDateString());
			record.add(newApmt.getRemarks());
			record.add(String.valueOf(newApmt.getCompleted()));

			writer.writeNext((String[]) record.toArray(new String[0]));

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void clearCompletedTasks() {

		for (Appointment task : allTasks) {
			if (task.getCompleted() == false) {
				addAppointment(task,taskDataBase);
			}
		}
	}

	public static void deleteTask(int ID) {

		createDataBase("temp.csv");
		for (Appointment app : allTasks) {
			if (app.getTaskId() != ID) {
				addAppointment(app,"temp.csv");
			}			
		}
		
		File oldfile =new File("temp.csv");
		File newfile =new File(taskDataBase);
		
		newfile.delete();
		
		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed");
		}
		
	}

	public static boolean isCorrectColumns(String[] dataBaseCols) {
		if (dataBaseColumns == dataBaseCols) {
			return true;
		} else
			return false;

	}

	public static void getAllTasks() throws FileNotFoundException {
		// Build reader instance
		CSVReader reader = new CSVReader(new FileReader(taskDataBase));

		try {

			boolean isColumn = true;

			// Read all rows at once

			List<String[]> allRows = reader.readAll();
			if (allTasks == null)
			{
			allTasks = new ArrayList<Appointment>();
			}
			// Read CSV line by line and use the string array as you want
			for (String[] row : allRows) {
				if (isColumn) {
					if (!isCorrectColumns(row)) {

					}
					isColumn = false;

				} else {
					
					Appointment newApmt = new Appointment();
					newApmt.setTaskId(Integer.parseInt(row[0]));
					newApmt.setTaskName(row[1]);
					newApmt.setRemarks(row[4]);
					newApmt.setIsCompleted(stringToBoolean(row[5]));
					// newApmt.setStartDate(row[2]);
					allTasks.add(newApmt);
					System.out.println(Arrays.toString(row));
				}
			}

			reader.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void getAllTasks() throws FileNotFoundException {
	 * 
	 * allTasks = new ArrayList<ProTask>(); // Build reader instance // Read
	 * data.csv // Default separator is comma // Default quote character is
	 * double quote // Start reading from line number 2 (line numbers start from
	 * zero)
	 * 
	 * CSVReader reader = new CSVReader(new FileReader(taskDataBase));
	 * 
	 * // Read CSV line by line and use the string array as you want String[]
	 * nextLine; try { while ((nextLine = reader.readNext()) != null) {
	 * System.out.println(nextLine[0]); if (nextLine != null) { ProTask task =
	 * new ProTask(Integer.parseInt(nextLine[0]), nextLine[1]);
	 * task.setStartTime(nextLine[2]); task.setEndTime(nextLine[3]);
	 * task.setRemarks(nextLine[4]);
	 * task.setCompleted(returnBooleanValue(nextLine[5]));
	 * 
	 * allTasks.add(task);
	 * 
	 * System.out.println(task.getTaskName());// Arrays.toString(nextLine)); }
	 * reader.close(); } } catch (IOException e) { // TODO Auto-generated catch
	 * block e.printStackTrace(); } // return (ProTask[]) allTasks.toArray(new
	 * ProTask[0]); }
	 */
	// use this method at the start of the program or if database is not present

	public static void createDataBase(String dataBaseName) {
		try {
			FileWriter writer = new FileWriter(dataBaseName);

			for (int i = 0; i < dataBaseColumns.length; i++) {
				writer.append(dataBaseColumns[i]);

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

	public void updateTask() {

	}

}
