package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class UnitTest {
	public static void addTask(Interpreter item, ArrayList<Task> buffer,
			int index) {
		Affix.addTask(item, buffer, index);
	}
	
	public static void deleteTask(Interpreter item, ArrayList<Task> buffer) {
		Obliterator.deleteTask(item, buffer);
	}
	
	public static void clearTask(Interpreter item, ArrayList<Task> buffer) {
		Obliterator.clearTask(item, buffer);
	}
}
