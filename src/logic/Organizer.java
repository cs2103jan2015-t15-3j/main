package logic;

import java.util.ArrayList;
import java.util.Collections;

//@author A0112643R

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
