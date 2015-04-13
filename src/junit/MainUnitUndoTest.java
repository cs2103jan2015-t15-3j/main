package junit;

import static org.junit.Assert.*;
import logic.LogicMain;
import logic.Repository;
import logic.Task;
import logic.UnitTest;

import org.junit.Test;

//@author A0112643R

public class MainUnitUndoTest {

	/*
	 * This main unit undo testing class will test the possible output for the
	 * undo command for the previous action.
	 */

	@Test
	public void testUndoAdd() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);
		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("undo", repo);

		// Current buffer size should be 1 after we undo add the second item
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		LogicMain.parseString("u", repo);

		// Current buffer size should be 0 after we undo add the first time
		assertEquals("Current buffer size", 0, repo.getBufferSize());

		LogicMain.parseString("redo", repo);

		// Current buffer size should be 1 after we redo the undo action
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		LogicMain.parseString("redo", repo);

		// Current buffer size should be 2 after we redo the undo action
		assertEquals("Current buffer size", 2, repo.getBufferSize());
	}

	@Test
	public void testUndoDelete() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);
		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("d 2", repo);

		// Current buffer size should be 1 after we delete the second item
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		LogicMain.parseString("d 1", repo);

		// Current buffer size should be 0 after we delete the first item
		assertEquals("Current buffer size", 0, repo.getBufferSize());

		LogicMain.parseString("undo", repo);

		// Current buffer size should be 1 after we undo add the first item
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		LogicMain.parseString("u", repo);
		// Current buffer size should be 0 after we undo add the second item
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("redo", repo);
		// Current buffer size should be 1 after we redo the undo action
		assertEquals("Current buffer size", 1, repo.getBufferSize());

		LogicMain.parseString("r", repo);

		// Current buffer size should be 2 after we redo the undo action
		assertEquals("Current buffer size", 0, repo.getBufferSize());
	}

	@Test
	public void testUndoClear() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);
		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("clear", repo);

		// Current buffer size is 0 after clearing
		assertEquals("Current buffer size", 0, repo.getBufferSize());

		LogicMain.parseString("u", repo);

		// Current buffer size is 2 after undo clear command
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		LogicMain.parseString("r", repo);

		// Current buffer size is 0 after we redo the undo action
		assertEquals("Current buffer size", 0, repo.getBufferSize());
	}

	@Test
	public void testUndoComplete() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);
		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding.
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		// Complete both tasks in the buffer
		LogicMain.parseString("cp 1", repo);
		LogicMain.parseString("cp 2", repo);

		Task taskOne = UnitTest.retrieveTask(repo.getBuffer(), 1);
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);

		// Result should be true after completing the two tasks
		assertEquals("Completed for ID 1", true, taskOne.getCompleted());
		assertEquals("Completed for ID 2", true, taskTwo.getCompleted());

		LogicMain.parseString("u", repo);
		LogicMain.parseString("u", repo);

		// Result should be false after we undo
		assertEquals("Completed for ID 1", false, taskOne.getCompleted());
		assertEquals("Completed for ID 2", false, taskTwo.getCompleted());
	}

	@Test
	public void testUndoUncomplete() {
		Repository repo = new Repository();

		// Adding a deadline task with task name and remarks
		LogicMain.parseString("add CS2103 [12/05/15] <Finish report>", repo);
		// Adding a appointment task with task name
		LogicMain.parseString("add EE2020 [13/06/15 14/06/15]", repo);

		// Current buffer size is 2 after adding
		assertEquals("Current buffer size", 2, repo.getBufferSize());

		Task taskOne = UnitTest.retrieveTask(repo.getBuffer(), 1);
		Task taskTwo = UnitTest.retrieveTask(repo.getBuffer(), 2);

		// Complete both tasks in the buffer
		LogicMain.parseString("cp 1", repo);
		LogicMain.parseString("cp 2", repo);

		// Uncomplete both tasks in the buffer
		LogicMain.parseString("ucp 1", repo);
		LogicMain.parseString("ucp 2", repo);

		// Result should be false after uncompleting the two tasks.
		assertEquals("Completed for ID 1", false, taskOne.getCompleted());
		assertEquals("Completed for ID 2", false, taskTwo.getCompleted());

		LogicMain.parseString("u", repo);
		LogicMain.parseString("u", repo);

		// Result should be true after we undo
		assertEquals("Completed for ID 1", true, taskOne.getCompleted());
		assertEquals("Completed for ID 2", true, taskTwo.getCompleted());
	}
}
