package logic;

import java.util.ArrayList;

import parser.Interpreter;

public class SearchEngine {
	
	private static ArrayList<Floating> searchList = new ArrayList<Floating>();

	public static void searchForKeyWords(Interpreter item, ArrayList<Floating> buffer) {
		
		
		for(int i = 0; i < buffer.size(); i++) {
			if(item.equals(buffer.get(i))) {
				searchList.add(buffer.get(i));
			}
		}

	}

}
