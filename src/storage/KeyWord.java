package storage;

import java.util.ArrayList;
import java.util.Comparator;

import logic.Task;

public class KeyWord implements Comparable<KeyWord> {

	private int keyWordID;
	private String word;
	private ArrayList<Character> splitName;
	private int taskID;

	public KeyWord() {

	}

	public int getKeyWordID() {
		return keyWordID;
	}

	public void setKeyWordID(int keyWordID) {
		this.keyWordID = keyWordID;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<Character> getSplitName() {
		return splitName;
	}

	public void setSplitName(ArrayList<Character> splitName) {
		this.splitName = splitName;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	@Override
	public String toString() {
		return "[" + this.getKeyWordID() + " " + this.getWord() + " "
				+ this.getSplitName() + "]";
	}

	public static Comparator<KeyWord> WordNameComparator = new Comparator<KeyWord>() {

		public int compare(KeyWord word1, KeyWord word2) {

			String wordName1 = word1.getWord().toUpperCase();
			String wordName2 = word2.getWord().toUpperCase();

			// ascending order
			return wordName1.compareTo(wordName2);

			// descending order
			// return wordName2.compareTo(wordName1);
		}

	};

	@Override
	public int compareTo(KeyWord o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
