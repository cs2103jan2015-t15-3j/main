package storage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ProTaskStorage {

	private final String taskDataBase = "test.csv";
	protected ArrayList<ProTask> allTasks;

	/*
	 * public static void main(String[] args) throws FileNotFoundException {
	 * createDataBase(); addTask(1, "Complete CS2103 tut", "08 March", "13:00",
	 * "15:40", "Some qns not sure", false); addTask(2, "Go for a run",
	 * "21 Feb", "08:00", "08:15", "so tiring", true); getAllTasks(); }
	 */
	
	public ProTaskStorage() {

		createDataBase();
		// reader = new CSVReader(new FileReader(taskDataBase), ',', '"', 1);

	}

	private String intToString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	private boolean returnBooleanValue(String rowEntry) {
		boolean toReturnBoolean = false;

		if (rowEntry.equalsIgnoreCase("true")) {
			toReturnBoolean = true;
		}

		return toReturnBoolean;
	}

	public void addTask(int id, String desc, String startDate,
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

	public void clearCompletedTask() {

	}

	public ProTask[] getAllTasks() throws FileNotFoundException {

		allTasks = new ArrayList<ProTask>();
		// Build reader instance
		// Read data.csv
		// Default separator is comma
		// Default quote character is double quote
		// Start reading from line number 2 (line numbers start from zero)

		CSVReader reader = new CSVReader(new FileReader(taskDataBase), ',',
				'"', 1);

		// Read CSV line by line and use the string array as you want
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {

					ProTask task = new ProTask(Integer.parseInt(nextLine[0]),
							nextLine[1]);
					task.setDate(nextLine[2]);
					task.setStartTime(nextLine[3]);
					task.setEndTime(nextLine[4]);
					task.setRemarks(nextLine[5]);
					task.setCompleted(returnBooleanValue(nextLine[6]));

					allTasks.add(task);

					System.out.println(task.getTaskName());// Arrays.toString(nextLine));
				}
				reader.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (ProTask[]) allTasks.toArray(new ProTask[0]);
	}

	// use this method at the start of the program or if database is not present
	public void createDataBase() {
		try {
			FileWriter writer = new FileWriter(taskDataBase);

			writer.append("ID");
			writer.append(',');
			writer.append("Description");
			writer.append(',');
			writer.append("Date");
			writer.append(',');
			writer.append("Start Time");
			writer.append(',');
			writer.append("End Time");
			writer.append(",");
			writer.append("Remarks");
			writer.append(",");
			writer.append("Completed");
			writer.append('\n');

			// generate whatever data you want

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
