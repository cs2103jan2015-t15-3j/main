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

	private static final String taskDataBase = "test.csv";
	protected static ArrayList<ProTask> allTasks;
	private static CSVReader reader;

	public static void main(String[] args) throws FileNotFoundException {
		generateCsvFile();
		addTask(1, "Complete CS2103 tut", "08 March", "13:00", "15:40",
				"Some qns not sure", false);
		addTask(2, "Go for a run", "21 Feb", "08:00", "08:15", "so tiring",
				true);
		getAllTasks();
	}

	public ProTaskStorage() throws FileNotFoundException {

		// reader = new CSVReader(new FileReader(taskDataBase), ',', '"', 1);

	}

	public static String toString(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append(id);

		return sb.toString();
	}

	public static void addTask(int id, String desc, String startDate,
			String startTime, String endTime, String remarks,
			boolean isCompleted) {

		try {
			CSVWriter writer = new CSVWriter(new FileWriter(taskDataBase, true));

			ArrayList<String> record = new ArrayList<String>();
			record.add(toString(id));
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

	public static void clearCompletedTask() {

	}

	public static void getAllTasks() throws FileNotFoundException {

		allTasks = new ArrayList<ProTask>();
		// Build reader instance
		// Read data.csv
		// Default seperator is comma
		// Default quote character is double quote
		// Start reading from line number 2 (line numbers start from zero)
		// CSVReader reader = new CSVReader(new FileReader(taskDataBase), ',' ,
		// '"' , 1);
		reader = new CSVReader(new FileReader(taskDataBase), ',', '"', 1);

		// Read CSV line by line and use the string array as you want
		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine != null) {
					// Verifying the read data here
					System.out.println(Arrays.toString(nextLine));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// return (ProTask[]) allTasks.toArray(new ProTask[0]);
	}

	public static void generateCsvFile() {
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
