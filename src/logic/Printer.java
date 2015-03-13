package logic;

import java.util.ArrayList;

public class Printer {

	public static void print(ArrayList<Floating> output) {
		for (int count = 0; count < output.size(); count++) {
			System.out.print(output.toString());
		}
	}

	public static void printToUser(String output) {
		if (!output.equals("")) {
			System.out.println(output);
		}
	}
}
