package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import userInterface.Logging;
import logic.Enumerator.TaskType;

public class Organizer {
	
	protected static void sort(Repository repo) {
		ArrayList<Task> addToTempBuffer = new ArrayList<Task>();

		for (int count = 0; count < repo.getBuffer().size(); count++) {
			addToTempBuffer.add(repo.getBuffer().get(count));
		}
		repo.setTempBuffer(addToTempBuffer);
		Collections.sort(repo.getTempBuffer(), Compare.StringComparator);
	}
}
