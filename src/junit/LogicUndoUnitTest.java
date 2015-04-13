package junit;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Iterator;

import logic.History;
import logic.Repository;
import logic.Task;
import logic.UnitTest;
import logic.Enumerator.TaskType;

import org.junit.Test;

import parser.Interpreter;
import parser.Interpreter.CommandType;

//@author A0112643R

public class LogicUndoUnitTest {

	Repository repo = new Repository();
	Interpreter floating, deadline, appt;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	// Create a temporary buffer to replace the original contents of the buffer
	private static ArrayList<Task> createTempBuffer(Repository repo) {
		ArrayList<Task> tempBuffer = new ArrayList<Task>();
		Iterator<Task> bufferList = repo.getBuffer().iterator();
		while (bufferList.hasNext()) {
			tempBuffer.add(bufferList.next());
		}
		return tempBuffer;
	}

	@Test
	public void testUndoForAdd() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		// Adding a deadline task
		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		// Current buffer size is 1 after adding
		assertEquals("Current size", 1, repo.getBufferSize());

		// Push the add action in the stack for undo
		History addedHistory = new History();
		deadline.setCommandType(CommandType.ADD);
		deadline.setTaskID(1);
		addedHistory = UnitTest.pushAddToStack(deadline, repo);
		repo.undoActionPush(addedHistory);

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Current size is 0 after we undo an item that we added
		assertEquals("Size should be 0", 0, repo.getBufferSize());
	}

	@Test
	public void testUndoForDelete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);
		int taskID;

		// Adding a deadline task
		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		// Current buffer size is 1 after adding
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		History deletedHistory = new History();
		deadline.setTaskID(1);
		deadline.setCommandType(CommandType.DELETE);
		deletedHistory = UnitTest.pushDeleteToStack(deadline, repo);
		repo.undoActionPush(deletedHistory);

		// Retrieving the task with ID 1 and delete
		taskID = deadline.getTaskID();
		try {
			UnitTest.deleteTask(taskID, repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " does not exists!");
		}

		// Current size is 0 after deleting
		assertEquals("Current buffer size", 0, repo.getBufferSize());

		// Undo the previous action
		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Current size is 1 after we undo delete
		assertEquals("Current buffer size", 1, repo.getBufferSize());
	}

	@Test
	public void testUndoForComplete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		// Adding a deadline task
		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		deadline.setIsCompleted(false);
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		// Push the uncomplete action into the stack
		History completedHistory = new History();
		deadline.setTaskID(1);
		deadline.setCommandType(CommandType.COMPLETE);
		completedHistory = UnitTest.pushCompleteOrUncompleteToStack(deadline,
				repo);
		repo.undoActionPush(completedHistory);

		// Retrieve the task and mark it as completed
		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());
		deadline.setIsCompleted(true);
		UnitTest.setCompletion(deadline, repo);

		// Current task retrieved will be mark as completed
		assertEquals("Completed will be mark as true", true,
				tasks.getCompleted());

		// Undo the previous action for complete task
		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Current task will be mark as uncompleted after undo
		assertEquals("Completed after undo will be mark as false", false,
				tasks.getCompleted());
	}

	@Test
	public void testUndoForUnComplete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		// Adding a deadline task
		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		deadline.setIsCompleted(true);
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		// Push the complete action into the stack
		History completedHistory = new History();
		deadline.setCommandType(CommandType.UNCOMPLETE);
		deadline.setTaskID(1);
		completedHistory = UnitTest.pushCompleteOrUncompleteToStack(deadline,
				repo);
		repo.undoActionPush(completedHistory);

		// Retrieve the task and set completion to be false
		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());
		deadline.setIsCompleted(false);
		UnitTest.setCompletion(deadline, repo);

		// Current task retrieved will be marked as uncompleted
		assertEquals("Uncomplete the task", false, tasks.getCompleted());

		// Undo the previous action for uncomplete task
		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Current task will be mark as completed after undo
		assertEquals("Uncomplete task after undo will be mark as true", true,
				tasks.getCompleted());
	}

	/*
	 * @Test public void testUndoForAmend() throws ParseException { String
	 * dateInString = "09/06/2013"; Date date = formatter.parse(dateInString);
	 * 
	 * deadline = new Interpreter(); deadline.setTaskID(1);
	 * deadline.setTaskName("CS2103"); deadline.setDueDate(date);
	 * deadline.setType(TaskType.DEADLINE); deadline.setRemarks("");
	 * UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
	 * 
	 * Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
	 * deadline.getTaskID()); assertEquals("task name should be CS2103",
	 * "CS2103", tasks.getTaskName());
	 * 
	 * deadline.setKey("TASKNAME"); deadline.setTaskName("CS2103T");
	 * UnitTest.determineAmend(deadline, repo);
	 * assertEquals("task name should be CS2103T after amend", "CS2103T",
	 * tasks.getTaskName());
	 * 
	 * History amendedHistory = new History();
	 * deadline.setCommandType(CommandType.AMEND); amendedHistory =
	 * UnitTest.pushAmendToStack(deadline, repo);
	 * repo.undoActionPush(amendedHistory);
	 * 
	 * try { UnitTest.determineUndo(repo);
	 * 
	 * } catch (EmptyStackException e) { System.out.println("Stack is empty!");
	 * }
	 * 
	 * Task tasksTwo = UnitTest.retrieveTask(repo.getBuffer(),
	 * deadline.getTaskID());
	 * assertEquals("task name should be CS2103 after undo", "CS2103",
	 * tasksTwo.getTaskName()); }
	 */

	@Test
	public void testUndoForClear() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		// Adding a floating task
		floating = new Interpreter();
		floating.setTaskID(1);
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		// Adding a deadline task
		deadline = new Interpreter();
		deadline.setTaskID(2);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		// Current buffer size is 2 after adding
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		History clearedHistory = new History();
		deadline.setCommandType(CommandType.CLEAR);
		deadline.setClear("");
		deadline.setTaskID(2);

		ArrayList<Task> tempBuffer = createTempBuffer(repo);
		clearedHistory = UnitTest.pushClearToStack(deadline, tempBuffer);
		repo.undoActionPush(clearedHistory);

		// Clear the buffer
		UnitTest.determineClear(deadline, repo.getBuffer());

		// Current size is 0 after clear
		assertEquals("Current buffer size", 0, repo.getBufferSize());

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Since we delete an item, if we undo, the result should be 2.
		assertEquals("Size should be 2 after we undo clear", 2,
				repo.getBufferSize());
	}
}
