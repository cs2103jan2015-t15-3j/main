package logic;

import java.util.ArrayList;
import java.util.Iterator;

//@author A0112643R

public class Printer {

	public static void executePrint(ArrayList<Task> output) {

		Iterator<Task> list = output.iterator();
		while (list.hasNext()) {
			System.out.println(list.next());
		}
	}

	public static void printToUser(String output) {
		if (!output.equals("")) {
			System.out.println(output);
		}
	}
}
