package parser;

import java.util.ArrayList;

import storage.KeyWord;
import storage.KeyWordStorage;
import logic.Task;

public class ParserPowerSearch {
	
	public static ArrayList<Task> powerSearch(String input) {
		String keyWords, checkCmd;
		KeyWordStorage storage = new KeyWordStorage(input);
		ArrayList<Task> keyWordsList = new ArrayList<Task>();
		ArrayList<KeyWord> keyWordsList2 = new ArrayList<KeyWord>();
		
		checkCmd = input.substring(0,2);
		
		switch(checkCmd) {
		case "ps ":
			keyWords = input.substring(3, input.length() - 1);
			keyWordsList2 =  storage.powerSearch(keyWords);
		case "pse":
			keyWords = input.substring(8, input.length() - 1);
			keyWordsList2 =  storage.powerSearch(keyWords);
		}
		return keyWordsList;
	}
}
