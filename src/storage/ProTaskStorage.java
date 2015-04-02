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
	private ArrayList<Integer> allTasksIDs;
	private String[] dataBaseColumns;
	private ArrayList<Task> tasks;
	private int idCounter;
	private boolean justLaunched;

	public ProTaskStorage() {

		dataBaseColumns = new String[] { "ID", "Description", "Start", "End",
				"Remarks", "Completed", "Type" };
		idCounter = 0;
		if (!checkFileExist()) {

			createDataBase(taskDataBase);
		}

		try {
			loadAllTasks();
			justLaunched = true;
		} catch (Exception e) {
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
		DateFormat df = new SimpleDateFormat("dd/MM/yy");
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

	private boolean checkColumnRow(String[] dataBaseCols) {
		if (dataBaseColumns == dataBaseCols) {
			return true;
		} else
			return false;
	}

	public String getReasonForFileDeletionFailureInPlainEnglish(File file) {
		try {
			if (!file.exists())
				return "It doesn't exist in the first place.";
			else if (file.isDirectory() && file.list().length > 0)
				return "It's a directory and it's not empty.";
			else
				return "Somebody else has it open, we don't have write permissions, or somebody stole my disk.";
		} catch (SecurityException e) {
			return "We're sandboxed and don't have filesystem access.";
		}
	}

	private void replaceTempToOriginal() {
		File oldfile = new File(tempDataBase);
		File newfile = new File(taskDataBase);

		newfile.delete();
		System.out
				.println(getReasonForFileDeletionFailureInPlainEnglish(newfile));
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
			returnType = "DE";
		}
		return returnType;
	}

	private TaskType abbreviationToTaskType(String abb) {
		TaskType type = TaskType.FLOATING;
		if (abb.equals("AP")) {
			type = TaskType.APPOINTMENT;
		} else if (abb.equals("DE")) {
			type = TaskType.DEADLINE;
		}
		return type;
	}

	private Date stringToDate(String stringDate) {
		DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm",
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
			repo.setCurrentID(idCounter+1);

			if (type.equals("APPOINTMENT")) {
				Appointment item = (Appointment) newTask;
				addStringTask(taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), item.getStartDateString(),
						item.getDueDateString(), newTask.getRemarks(), false,
						newTask.getType().toString());
			} else if (type.equals("DEADLINE")) {
				Deadline item = (Deadline) newTask;
				newTask.setTaskID(repo.getCurrentID());
				addStringTask(taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), "", item.getDueDateString(),
						newTask.getRemarks(), false, newTask.getType()
								.toString());
			} else {
				newTask.setTaskID(repo.getCurrentID());

				addStringTask(taskDataBase, repo.getCurrentID(),
						newTask.getTaskName(), "", "", newTask.getRemarks(),
						false, newTask.getType().toString());
			}
			idCounter++;

			if (justLaunched) {
				justLaunched = false;
				allTasks.add(newTask);
				repo.setBuffer(allTasks);
			}
		}
		return repo;
	}

	public Repository getAllTasks(Repository mem) throws FileNotFoundException {
		try {
			CSVReader reader = new CSVReader(new FileReader(taskDataBase));
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

	public void loadAllTasks() throws FileNotFoundException {
		// Build reader instance

		CSVReader reader = new CSVReader(new FileReader(taskDataBase));

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

	public void clearAllTasks() throws FileNotFoundException {
		File file = new File(taskDataBase);

		if (file.delete()) {
			System.out.println(file.getName() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed.");
		}
		createDataBase(taskDataBase);
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
