package storage;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.opencsv.CSVReader;

public class KeyWordStorage {

	// Find the index of the input (2nd letter?)
	// Then find what alphabet is on the 2nd letter? (r?)

	ArrayList<KeyAlphabet> allAlphabets;
	private boolean isColumn = true;
	private int keyWordID;
	private final String taskDataBase = "test.csv";
	private final Character[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G',
			'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
			'U', 'V', 'W', 'X', 'Y', 'Z' };
	private ArrayList<KeyWord> allKeyWords;

	public KeyWordStorage() {

		allAlphabets = new ArrayList<KeyAlphabet>();
		keyWordID = 1;
		allKeyWords = new ArrayList<KeyWord>();

		for (int j = 0; j < letters.length; j++) {
			KeyAlphabet alphabet = new KeyAlphabet();
			alphabet.setAlphabet(letters[j]);
			alphabet.setIndexNumber(j);
			alphabet.setPositionNumber(0);
			allAlphabets.add(alphabet);
		}

		try {
			populateDataBase();
			Collections.sort(allKeyWords, KeyWord.WordNameComparator);
			mapKeyWords();
			System.out.println(allAlphabets);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> powerSearch(String input) {
		ArrayList<String> results = new ArrayList<String>();
		int count = 0;
		Queue<Character> queueInput = new LinkedList<Character>();

		for (int i = 0; i < input.length(); i++) {
			queueInput.add(input.charAt(i));
		}

		return results;

	}

	private int getAlphabetIndex(Character c) {
		int index = 0;
		for (int i = 0; i < letters.length; i++) {
			if (letters[i].equals(Character.toUpperCase(c))) {
				index = i;
				break;
			}
		}
		return index;
	}

	private void mapKeyWords() {
		for (KeyWord word : allKeyWords) {

			Character[] chars = word.getSplitName().toArray(new Character[0]);
			int index = 0;

			if (allAlphabets.get(getAlphabetIndex(chars[index])) != null) {
				allAlphabets.get(getAlphabetIndex(chars[index])).addWord(word);

				if (index + 1 != chars.length) {

					mapping(word, chars[index + 1], index + 1,
							allAlphabets.get(getAlphabetIndex(chars[index])));

				}
			} else {// create one
			}

		}

	}

	private void mapping(KeyWord word, Character nextChar, int index,
			KeyAlphabet rootAlphabet) {

		KeyAlphabet newAlphabet = new KeyAlphabet();
		if (rootAlphabet.checkChildExist(nextChar)) {
			newAlphabet = rootAlphabet.getChildAlphabet(nextChar);
		} else {
			newAlphabet.setAlphabet(Character.toUpperCase(nextChar));
			newAlphabet.setPositionNumber(index);
			newAlphabet.setIndexNumber(getAlphabetIndex(nextChar));
			rootAlphabet.addChildKeyAlphabet(newAlphabet);
		}
		newAlphabet.addWord(word);

		if (index + 1 != word.getWord().length()) {

			mapping(word, word.getSplitName().get(index + 1), index + 1,
					newAlphabet);
		}
	}

	public void populateDataBase() throws IOException {
		CSVReader reader = new CSVReader(new FileReader(taskDataBase));
		List<String[]> allRows = reader.readAll();

		for (String[] row : allRows) {
			if (isColumn) {
				isColumn = false;
			} else {

				int taskID = Integer.parseInt(row[0]);
				String taskName = row[1];
				ArrayList<Character> chars = new ArrayList<Character>();
				for (int i = 0; i < taskName.length(); i++) {
					chars.add(taskName.charAt(i));
				}
				KeyWord keyTaskName = new KeyWord();
				keyTaskName.setTaskID(taskID);
				keyTaskName.setWord(taskName);
				keyTaskName.setSplitName(chars);
				keyTaskName.setKeyWordID(keyWordID);
				keyWordID++;
				allKeyWords.add(keyTaskName);

				String remarks = row[4];
				// if remarks is not empty
				if (!remarks.equals("")) {
					String[] splitRemarks = remarks.split(" ");
					for (String remark : splitRemarks) {
						KeyWord keyRemarks = new KeyWord();
						ArrayList<Character> charsRemarks = new ArrayList<Character>();

						for (int j = 0; j < remark.length(); j++) {
							charsRemarks.add(remark.charAt(j));
						}
						keyRemarks.setTaskID(taskID);
						keyRemarks.setWord(remark);
						keyRemarks.setSplitName(charsRemarks);
						keyRemarks.setKeyWordID(keyWordID);
						keyWordID++;
						allKeyWords.add(keyRemarks);
					}
				}

			}
		}

	}// end of method
}
