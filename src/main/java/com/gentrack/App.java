package com.gentrack;

import java.util.List;

import com.gentrack.logic.MazeSolver;
import com.gentrack.util.FileReader;

public class App {

	public static void main(String[] args) {

		FileReader fr = new FileReader();
		
		List<String> fileData = fr.readFile(fr.formatInputStringFileLocation(args[0]));
		
		MazeSolver ms = new MazeSolver();
		
		if(ms.solveMaze(fileData)) {
			ms.getMazeSolution();
		}
		else {
			System.out.println("fail");
		}
	}

}
