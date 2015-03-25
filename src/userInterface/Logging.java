package userInterface;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
//import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Logging {

	private static ArrayList<String> myList = new ArrayList<String>();
	private static final String ERROR_MESSAGE = "Error adding to textfile\n";

	public static void getInputLog(String input) {

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");//dd/MM/yyyy
		
		String myTextFile = "loggingText.txt";
		

		checkFileExist(myTextFile);
		String time = dateFormat.format(date).toString() + ": " + input;
		myList.add(time);
		saveFile(myTextFile);
	}

	// Checks whether file exist else create a new file
		public static Boolean checkFileExist(String myTextFile) {
			//if (!storeFileToArraylist(myTextFile, myList)) {
				File myFile = new File(myTextFile);
				
				try {
					myFile.createNewFile();
				} catch (IOException e) {
					System.out.println(ERROR_MESSAGE);
					System.exit(0);
				}
				return false;
			//} else {
				//return true;
			//}
		}
		
		// This method will try to read the file and store the contents into the
		// list.
		/*public static boolean storeFileToArraylist(String file,
				ArrayList<String> myList) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line = null;
				while ((line = reader.readLine()) != null) {
					myList.add(line);
				}
				reader.close();
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	*/

	private static void saveFile(String myTextFile) {
		File file = new File(myTextFile);
		//file.delete();
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (int i = 0; i < myList.size(); i++) {
				bw.write(myList.get(i));
				
				if (i < myList.size() - 1) {
					bw.newLine();
				}
			}
			bw.close();
			
		} catch (IOException e) {
			System.out.println(ERROR_MESSAGE);
			System.exit(0);
		}
	}

}