package IO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	private BufferedReader bufferedReader;
	public Reader(String path){
		try{
			this.bufferedReader = new BufferedReader(new FileReader(path));
		}
		catch(IOException ex){
			System.err.println("Error reading the file " + ex);
		}
	}
	public String readLine(){
		String line = "";
		try{
			while((line = bufferedReader.readLine()) != null){
				return line;
			}
		}
		catch(IOException ex){
			System.err.println("Error reading line");
		}
		if(line == null){
			try{
				bufferedReader.close();				
			}
			catch(IOException ex){
				System.err.println("Error closing file");
			}
		}
		return null;
	}
}
