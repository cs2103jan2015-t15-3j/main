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

public class LogicUndoUnitTest {

	Repository repo = new Repository();
	Interpreter floating, deadline, appt;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	@Test
	public void testUndoForAdd() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);

		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Size should be 1", 1, repo.getBufferSize());

		History addedHistory = new History();
		deadline.setRemarks("");
		deadline.setCommandType(CommandType.ADD);
		addedHistory = UnitTest.pushAddToStack(deadline, repo);
		repo.undoActionPush(addedHistory);

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}
		// Since we add an item, if we undo, the result should be 0.
		assertEquals("Size should be 0", 0, repo.getBufferSize());

	}

	@Test
	public void testUndoForDelete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Size should be 1", 1, repo.getBufferSize());

		History deletedHistory = new History();
		deadline.setCommandType(CommandType.DELETE);
		deletedHistory = UnitTest.pushDeleteToStack(deadline, repo);
		repo.undoActionPush(deletedHistory);

		int taskID = deadline.getTaskID();
		try {
			UnitTest.deleteTask(taskID, repo);
		} catch (IndexOutOfBoundsException e) {
			System.out.println(taskID + " does not exists!");
		}
		assertEquals("Size should be 0 after deleting", 0, repo.getBufferSize());

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Since we delete an item, if we undo, the result should be 1.
		assertEquals("Size should be 1 after undo", 1, repo.getBufferSize());
	}

	@Test
	public void testUndoForComplete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskID(1);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		deadline.setIsCompleted(false);
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());

		History completedHistory = new History();
		deadline.setCommandType(CommandType.COMPLETE);
		completedHistory = UnitTest.pushCompleteOrUncompleteToStack(deadline,
				repo);
		repo.undoActionPush(completedHistory);

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		deadline.setIsCompleted(true);
		UnitTest.setCompletion(deadline, repo);
		assertEquals("Result should be marked as completed", true,
				tasks.getCompleted());

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Since we undo the completed item, the result should be false.
		assertEquals("Result should be false after undo", false,
				tasks.getCompleted());
	}

	public void testUndoForUnComplete() throws ParseException {
		String dateInString = "09/06/2013";
		Date date = formatter.parse(dateInString);

		deadline = new Interpreter();
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
		
		History completedHistory = new History();
		deadline.setCommandType(CommandType.UNCOMPLETE);
		completedHistory = UnitTest.pushCompleteOrUncompleteToStack(deadline,
				repo);
		repo.undoActionPush(completedHistory);

		Task tasks = UnitTest.retrieveTask(repo.getBuffer(),
				deadline.getTaskID());

		deadline.setIsCompleted(false);
		UnitTest.setCompletion(deadline, repo);
		assertEquals("Result should be marked as completed", true,
				tasks.getCompleted());

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Since we undo the uncompleted item, the result should be true.
		assertEquals("Result should be true after undo", false,
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

		floating = new Interpreter();
		floating.setTaskID(1);
		floating.setTaskName("CS2103");
		floating.setType(TaskType.FLOATING);
		floating.setRemarks("Question 1 is important.");
		UnitTest.addTask(floating, repo.getBuffer(), repo.numberGenerator());

		deadline = new Interpreter();
		deadline.setTaskID(2);
		deadline.setTaskName("CS2103");
		deadline.setDueDate(date);
		deadline.setType(TaskType.DEADLINE);
		deadline.setRemarks("");
		UnitTest.addTask(deadline, repo.getBuffer(), repo.numberGenerator());
		assertEquals("Size should be 2", 2, repo.getBufferSize());

		History clearedHistory = new History();
		deadline.setCommandType(CommandType.CLEAR);
		deadline.setClear("");
		ArrayList<Task> tempBuffer = new ArrayList<Task>();
		Iterator<Task> bufferList = repo.getBuffer().iterator();
		while (bufferList.hasNext()) {
			tempBuffer.add(bufferList.next());
		}
		clearedHistory = UnitTest.pushClearToStack(deadline, tempBuffer);
		repo.undoActionPush(clearedHistory);

		UnitTest.determineClear(deadline, repo.getBuffer());
		assertEquals("Size should be 0 after clearing", 0, repo.getBufferSize());

		try {
			UnitTest.determineUndo(repo);
		} catch (EmptyStackException e) {
			System.out.println("Stack is empty!");
		}

		// Since we delete an item, if we undo, the result should be 1.
		assertEquals("Size should be 2 after we undo clear", 2,
				repo.getBufferSize());
	}

}
