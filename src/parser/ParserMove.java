//@author A0111842R
package parser;

import java.io.File;

public class ParserMove {

	public static void moveDataBase(Interpreter item, String[] inputArray) {
		boolean wrongFormat = false;
		boolean expectedParameter = false;
		String dataBasePath = "";

		for (int i = 0; i < inputArray.length; i++) {
			if (expectedParameter && !inputArray[i].isEmpty()) {
				wrongFormat = true;
				item.setIsError(true);
				item.setFeedbackMsg(ParserMessage.INVALID_INPUT);
				break;
				
			} else if (i != 0 && !inputArray[i].isEmpty() && !expectedParameter) {
				expectedParameter = true;
				dataBasePath = inputArray[i];

				File tempPath = new File(dataBasePath);
				if(!tempPath.exists())
				{
					item.setIsError(true);
					item.setFeedbackMsg(ParserMessage.INVALID_FILEPATH);
				}

				
			}
		}
		if (!wrongFormat) {
			item.setDataBasePath(dataBasePath);
		} 
	}
}
