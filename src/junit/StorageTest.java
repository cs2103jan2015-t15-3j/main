//@author A0111842R
package junit;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import logic.Enumerator.TaskType;
import logic.Repository;
import logic.Task;

import org.junit.Test;

import com.opencsv.CSVReader;

import storage.ProTaskStorage;

public class StorageTest {

	private final String dataBaseDir = "ProTaskDatabase.csv";

	@Test
	// Testing if add, delete and clear commands reflect correctly in the database with expected output.
	public void compareDataBaseRecords() throws IOException {
		
		ProTaskStorage tempStorage = new ProTaskStorage();
		Repository repo = new Repository();
		
		String dataBasePath = tempStorage.getCurrentDataBasePath()+"/"+dataBaseDir;
		tempStorage.addStringTask(dataBasePath, 1, "One", "", "", "First test", false, "FL");
		tempStorage.addStringTask(dataBasePath, 2, "Two", "", "", "Second test", false, "FL");
		
		ArrayList<Task> tempArray = new ArrayList<Task>();
		Task taskOne = new Task();
		taskOne.setTaskID(1); taskOne.setTaskName("One"); taskOne.setRemarks("First test");taskOne.setIsCompleted(false);taskOne.setType(TaskType.FLOATING);
		Task taskTwo = new Task();
		taskTwo.setTaskID(2); taskTwo.setTaskName("Two"); taskTwo.setRemarks("Second test"); taskTwo.setIsCompleted(false); taskTwo.setType(TaskType.FLOATING);
		tempArray.add(taskOne);
		tempArray.add(taskTwo);
		repo.setBuffer(tempArray);
		
		repo = tempStorage.clearAllTasks(repo);
		
		tempStorage.addStringTask(dataBasePath, 3, "Three", "", "", "Third test", false, "FL");
		
		
		CSVReader reader = new CSVReader(new FileReader(dataBasePath));
		List<String[]> allRows = reader.readAll();
		boolean isColumn = true;

		for (String[] row : allRows) {
			if (isColumn) {
				isColumn = false;
			} 
			else
			{
				assertEquals(Integer.parseInt(row[0]),3);
				assertEquals(row[1],"Three");
				assertEquals(row[4],"Third test");
			}
		}
		reader.close();
		
		assertEquals("","");
	}
}
