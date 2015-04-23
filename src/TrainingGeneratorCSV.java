import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


public class TrainingGeneratorCSV {
  public static void main(String args[]){
	  
		  	String csvFile = "/home/hetti/Desktop/full-corpus.csv";
			BufferedReader br = null;
			String line = "";
			String cvsSplitBy = ",";
		 
			try {
		 
				Map<String, String> maps = new HashMap<String, String>();
		 
				br = new BufferedReader(new FileReader(csvFile));
				while ((line = br.readLine()) != null) {
		 
					// use comma as separator
					String[] country = line.split(cvsSplitBy);
					System.out.println(country[0]);
					maps.put(country[1], country[4]);
		 
				}
		 
				//loop map
				PrintWriter writer = new PrintWriter("/home/hetti/Desktop/Devops/NLP/twitter_corpus-master/output.train", "UTF-8");
				
				
				
				for (Map.Entry<String, String> entry : maps.entrySet()) {
		 
					writer.write(entry.getKey()+" "+entry.getValue()+"\n");
		 
				}
		 
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		 
			System.out.println("Done");
		  
		
	
	  
  }
}
