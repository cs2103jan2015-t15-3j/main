import java.io.FileWriter;
import java.io.IOException;


public class ProTaskStorage {

	private static final String taskDataBase = "test.csv";
	public static void main (String args[])
	{
		generateCsvFile();		
		
	}
	public ProTaskStorage()
	{
		
	}
	
	public void addTask()
	{
		
	}
	
    public static void generateCsvFile()
    {
      try
      {
         FileWriter writer = new FileWriter(taskDataBase);
      
         writer.append("ID");
         writer.append(',');
         writer.append("Task Name");
         writer.append(',');
         writer.append("Start");
         writer.append(',');
         writer.append("End");
         writer.append('\n');
      
      
      //generate whatever data you want
      
         writer.flush();
         writer.close();
      }
          catch(IOException e)
         {
            e.printStackTrace();
         } 
   }

}
