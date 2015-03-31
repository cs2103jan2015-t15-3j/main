package parser;

public class ParserPowerSearch {
	public static void powerSearchTask(Interpreter item, String[] inputArray) {
		String searchKey = "";
		if(inputArray.length == 1) {
			item.setKey(null);
		} else {
			int lengthSearchKey = inputArray.length-1;
			for(int i=1; i<=lengthSearchKey; i++){
				searchKey = searchKey.concat(inputArray[i]);
			}
			item.setKey(searchKey);
		}
	}
}
