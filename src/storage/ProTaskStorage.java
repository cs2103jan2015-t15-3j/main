//@author A0111842R
package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import userInterface.Logging;
import logic.Appointment;
import logic.Deadline;
import logic.Repository;
import logic.Task;
import logic.Enumerator.TaskType;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class ProTaskStorage {

	private final String taskDataBase = "ProTaskDatabase.csv";
	private final String tempDataBase = "temp.csv";
	private final String dataBaseDir = "dataBaseDir.csv";
	private String currentDataBasePath = "";

	protected ArrayList<Appointment> allAppointments;
	private ArrayList<Task> allTasks;
	private ArrayList<Task> previousBuffer;
	private ArrayList<Integer> allTasksIDs;
	private final String[] dataBaseColumns;
	private final String[] dataBaseDirColumns = new String[] {
			"Current DataBase Directory", "Previous DataBase Directory" };
	private ArrayList<Task> tasks;
	private int idCounter;
	private boolean justLaunched;

	public ProTaskStorage() {

		dataBaseColumns = new String[] { "ID", "Description", "Start", "End",
				"Remarks", "Completed", "Type" };
		idCounter = 0;
		currentDataBasePath = System.getProperty("user.dir");
		retrieveDataBasesDir();
		if (!isFileExists(currentDataBasePath+"/"+taskDataBase)) {

			createDataBase(currentDataBasePath+"/"+taskDataBase);
		}

		try {
			loadAllTasks();
			justLaunched = true;
		} catch (Exception e) {
			Logging.getInputLog("Exception thrown when program attempst to load databsase into program!");
		}

	}

	private void createDataBaseDir()
	{		
		try{
		FileWriter fWriter = new FileWriter(dataBaseDir);

		for (int i = 0; i < dataBaseDirColumns.length; i++) {
			fWriter.append(dataBaseDirColumns[i]);

			if (i == dataBaseDirColumns.length - 1) {

				fWriter.append('\n');
			} else {

				fWriter.append(",");
			}
		}
		fWriter.flush();
		fWriter.close();

		CSVWriter writer = new CSVWriter(new FileWriter(dataBaseDir,
				true));

		ArrayList<String> record = new ArrayList<String>();

		record.add(currentDataBasePath);
		record.add("");

		writer.writeNext((String[])

		record.toArray(new String[0]));

		writer.close();
		}
		catch (IOException e) {
			Logging.getInputLog("Exception thrown when program attemmpts to create database!");
		}
	}
	
	private void retrieveDataBasesDir() {
		boolean isColumn = true;
		boolean firstAppLaunch = false;

		try {
			if (!isFileExists(dataBaseDir)) {
				createDataBaseDir();
				firstAppLaunch = true;
			}
			if (!firstAppLaunch) {
				CSVReader reader = new CSVReader(new FileReader(dataBaseDir),CSVParser.DEFAULT_SEPARATOR,CSVParser.DEFAULT_QUOTE_CHARACTER,'\0');
				List<String[]> allRows = reader.readAll();
				for (String[] row : allRows) {
					if (isColumn) {
						isColumn = false;
					} else {
						currentDataBasePath = row[0];
					}
				}

				reader.close();
			}
		} catch (IOException e) {
			Logging.getInputLog("Exception thrown when program attemmpts to create database!");
		}
	}

	public String getCurrentDataBasePath()
	{
		retrieveDataBasesDir();
		return this.currentDataBasePath;
	}
	public boolean moveDataBase(String newPath) {
		boolean successMove = false;
		retrieveDataBasesDir();
		File file = new File(newPath);
		// 1) 
		if (file.exists() && file.isDirectory()) {
			
			File source = new File(currentDataBasePath+"/"+taskDataBase);
			File destination = new File(newPath + "/" + taskDataBase);
			File dataBaseDirPath = new File(dataBaseDir);
			
			try {
				
				transferFileLocation(source, destination);
				source.delete();
				currentDataBasePath = newPath;
				dataBaseDirPath.delete();
				createDataBaseDir();
				
			} catch (IOException e) {
				Logging.getInputLog("IO File exceptions when attempting to move database!");
			}
			successMove = true;
		}
		// 2
		if (file.exists() && file.isFile()) {
			Logging.getInputLog("User inputs file instead of new directory to move database!");
		}
		// 3
		if (!file.exists()) {
			Logging.getInputLog("User inputs a non existent directory to move database!");
		}
		return successMove;
	}

	private void transferFileLocation(File source, File dest)
			throws IOException {
		InputStream input = null;
		OutputStream output = null;
		try {
			input = new FileInputStream(source);
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

	private boolean isFileExists(String dataBaseName) {
		boolean fileExist = false;
		File database = new File(dataBaseName);
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

	/*
	 * private String dateToString(Date date) { DateFormat df = new
	 * SimpleDateFormat("dd/MM/yy"); String dateString = df.format(date); return
	 * dateString;
	 * 
	 * }
	 */
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

	private boolean checkColumnRow(String[] dataBaseCols) {
		if (dataBaseColumns == dataBaseCols) {
			return true;
		} else
			return false;
	}


	private void replaceTempToOriginal() {
		File oldfile = new File(tempDataBase);
		File newfile = new File(currentDataBasePath+"/"+taskDataBase);

		newfile.delete();
		if (oldfile.renameTo(newfile)) {
			Logging.getInputLog("Rename of "+tempDataBase+" is successful.");
		} else {
			Logging.getInputLog("Rename of "+tempDataBase+" to "+newfile.getAbsolutePath()+" unsucessful.");
		}
	}

	private String convertToAbbreviation(String type) {
		String returnType = "NIL";

		if (type.equals("FLOATING")) {
			returnType = "FL";
		} else if (type.equals("APPOINTMENT")) {
			returnType = "AP";
		} else if (type.equals("DEADLINE")) {
			returnType = "DE";
		}
		return returnType;
	}

	/*
	 * private TaskType abbreviationToTaskType(String abb) { TaskType type =
	 * TaskType.FLOATING; if (abb.equals("AP")) { type = TaskType.APPOINTMENT; }
	 * else if (abb.equals("DE")) { type = TaskType.DEADLINE; } return type; }
	 */
	private Date stringToDate(String stringDate) {
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm",
				Locale.ENGLISH);
		Date date = new Date();

		try {
			date = format.parse(stringDate);

		} catch (ParseException e) {
			Logging.getInputLog("String unable to convert into Date");
		}
		return date; // Sat Jan 02 00:00:00 GMT 2010
	}

	// Please check with other methods if they are using addStringTask!!!!!
	public void addStringTask(String databaseName, int id, String desc,
			String startTime, String endTime, String remarks,
			boolean isCompleted, String type) {

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
			allTasksIDs.add(id);
			writer.close();

		} catch (IOException e) {
			Logging.getInputLog("Exception thrown when user wants to add new task with ID: "
					+ intToString(id) + "; Name: " + desc);
		}
	}

	public Repository writeToFile(Repository repo) {
		ArrayList<Task> obtainedTasks = repo.getBuffer();

		Task newTask = null;

		for (Task task : obtainedTasks) {
			if (!allTasksIDs.contains(task.getTaskID())) {
				newTask = task;
			}
		}
		if (newTask != null) {

			String type = newTask.getType().toString();
			repo.setCurrentID(idCounter + 1);

			if (type.equals("APPOINTMENT")) {
				Appointment item = (Appointment) newTask;
				addStringTask(currentDataBasePath+"/"+taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), item.getStartDateString(),
						item.getDueDateString(), newTask.getRemarks(), false,
						newTask.getType().toString());
			} else if (type.equals("DEADLINE")) {
				Deadline item = (Deadline) newTask;
				newTask.setTaskID(repo.getCurrentID());
				addStringTask(currentDataBasePath+"/"+taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), "", item.getDueDateString(),
						newTask.getRemarks(), false, newTask.getType()
								.toString());
			} else {
				newTask.setTaskID(repo.getCurrentID());

				addStringTask(currentDataBasePath+"/"+taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), "", "", newTask.getRemarks(),
						false, newTask.getType().toString());
			}
			idCounter++;

			if (justLaunched) {
				justLaunched = false;
				allTasks.add(newTask);
				//repo.setBuffer(allTasks);
			}
		}
		return repo;
	}

	public Repository getAllTasks() throws FileNotFoundException {
		Repository mem = new Repository();
		try {
			CSVReader reader = new CSVReader(new FileReader(currentDataBasePath+"/"+taskDataBase));
			tasks = new ArrayList<Task>();

			boolean isColumn = true;

			// Read all rows at once
			List<String[]> allRows = reader.readAll();
			for (String[] row : allRows) {
				if (isColumn) {
					if (!checkColumnRow(row)) {

						// Cannot continue because columns in database is

						// different then in system.
					}

					isColumn = false;

				} else {

					Task newTask = null;
					idCounter = Integer.parseInt(row[0]);
					if (row[6].equals("AP")) {

						newTask = new Appointment();
						newTask.setType(TaskType.APPOINTMENT);
						Appointment ap = (Appointment) newTask;
						ap.setTaskID(Integer.parseInt(row[0]));
						ap.setTaskName(row[1]);
						ap.setStartDate(stringToDate(row[2]));
						ap.setDate(stringToDate(row[3]));
						ap.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							ap.setIsCompleted(true);
						} else
							ap.setIsCompleted(false);
						ap.setType(TaskType.APPOINTMENT);
						tasks.add(ap);

					} else if (row[6].equals("DE")) {

						newTask = new Deadline();
						Deadline dl = (Deadline) newTask;
						dl.setTaskID(Integer.parseInt(row[0]));
						dl.setTaskName(row[1]);
						dl.setDate(stringToDate(row[3]));
						dl.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							dl.setIsCompleted(true);
						} else
							dl.setIsCompleted(false);
						dl.setType(TaskType.DEADLINE);
						tasks.add(dl);
					} else {
						newTask = new Task();
						newTask.setTaskID(Integer.parseInt(row[0]));
						newTask.setTaskName(row[1]);
						newTask.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							newTask.setIsCompleted(true);
						} else
							newTask.setIsCompleted(false);
						newTask.setType(TaskType.FLOATING);
						tasks.add(newTask);
					}

				}
			}
			reader.close();

		} catch (Exception e) {

			Logging.getInputLog("Exception thrown when loading tasks from database to program!");
		}
		mem.setBuffer(tasks);
		mem.setCurrentID(idCounter);
		return mem;

	}

	public Task getTask(int taskID) {
		Task toReturnTask = new Task();
		for (Task task : allTasks) {
			if (task.getTaskID() == taskID) {
				toReturnTask = task;
				break;
			}
		}
		return toReturnTask;
	}

	public void loadAllTasks() throws FileNotFoundException {
		// Build reader instance

		CSVReader reader = new CSVReader(new FileReader(currentDataBasePath+"/"+taskDataBase));

		try {

			boolean isColumn = true;

			// Read all rows at once
			List<String[]> allRows = reader.readAll();

			if (allAppointments == null) {

				allAppointments = new ArrayList<Appointment>();

			}
			if (allTasks == null) {
				allTasks = new ArrayList<Task>();
				allTasksIDs = new ArrayList<Integer>();
			}

			if (previousBuffer == null) {
				previousBuffer = new ArrayList<Task>();
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

					Task newTask = null;
					allTasksIDs.add(Integer.parseInt(row[0]));
					if (row[6].equals("AP")) {

						newTask = new Appointment();
						newTask.setType(TaskType.APPOINTMENT);
						Appointment ap = (Appointment) newTask;
						ap.setTaskID(Integer.parseInt(row[0]));
						ap.setTaskName(row[1]);
						ap.setStartDate(stringToDate(row[2]));
						ap.setDate(stringToDate(row[3]));
						ap.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							ap.setIsCompleted(true);
						} else
							ap.setIsCompleted(false);
						ap.setType(TaskType.APPOINTMENT);
						allTasks.add(ap);

					} else if (row[6].equals("DE")) {

						newTask = new Deadline();
						Deadline dl = (Deadline) newTask;
						dl.setTaskID(Integer.parseInt(row[0]));
						dl.setTaskName(row[1]);
						dl.setDate(stringToDate(row[3]));
						dl.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							dl.setIsCompleted(true);
						} else
							dl.setIsCompleted(false);
						dl.setType(TaskType.DEADLINE);
						allTasks.add(dl);
					} else {
						newTask = new Task();
						newTask.setTaskID(Integer.parseInt(row[0]));
						newTask.setTaskName(row[1]);
						newTask.setRemarks(row[4]);
						if (row[5].equalsIgnoreCase("true")) {
							newTask.setIsCompleted(true);
						} else
							newTask.setIsCompleted(false);
						newTask.setType(TaskType.FLOATING);
						allTasks.add(newTask);

					}
				}

				reader.close();

			}
		} catch (Exception e) {

			Logging.getInputLog("Exception thrown when loading tasks from database to program!");
		}
	}

	public Repository clearAllTasks(Repository repo)
			throws FileNotFoundException {
		File file = new File(currentDataBasePath+"/"+taskDataBase);
		previousBuffer = repo.getBuffer();
		repo.setBuffer(new ArrayList<Task>());
		allTasksIDs.clear();
		idCounter = 0 ;
		
		if (file.delete()) {
			Logging.getInputLog(file.getName() + " is deleted!");
		} else {
			Logging.getInputLog("Delete operation failed!");
		}
		createDataBase(currentDataBasePath+"/"+taskDataBase);
		repo.setCurrentID(idCounter);
		
		return repo;
	}

	public void updateDeleteTask(Repository repo) {

		createDataBase(tempDataBase);

		for (Task task : repo.getBuffer()) {
			String type = task.getType().toString();
			if (type.equals("APPOINTMENT")) {

				Appointment item = (Appointment) task;
				addStringTask(tempDataBase, task.getTaskID(),
						task.getTaskName(), item.getStartDateString(),
						item.getDueDateString(), task.getRemarks(),
						task.getCompleted(), task.getType().toString());

			} else if (type.equals("DEADLINE")) {

				Deadline item = (Deadline) task;
				addStringTask(tempDataBase, task.getTaskID(),
						task.getTaskName(), "", item.getDueDateString(),
						task.getRemarks(), task.getCompleted(), task.getType()
								.toString());
			} else {

				addStringTask(tempDataBase, task.getTaskID(),
						task.getTaskName(), "", "", task.getRemarks(),
						task.getCompleted(), task.getType().toString());
			}
		}
		replaceTempToOriginal();
	}
}
