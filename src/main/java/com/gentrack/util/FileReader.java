package com.gentrack.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class FileReader {
	
	//String fileLocation = "\\C:\\Users\\bbtvi\\Desktop\\Gentrack Maze Technical Test\\Samples\\input.txt";
	
	public String formatInputStringFileLocation(String userInputLocationString) {
		
		return 
			userInputLocationString.replace("\\", File.separator);
	}
	
	public List<String> readFile(String fileLocation) {
		try {
			return
				Files.readAllLines(Paths.get(fileLocation));
		} 
		catch (IOException e) {
			System.err.format("Exception occurred trying to read file from location: " + fileLocation);
		    e.printStackTrace();
		    return null;
		}
		
	}
}
