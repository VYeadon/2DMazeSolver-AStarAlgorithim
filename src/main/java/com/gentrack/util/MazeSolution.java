package com.gentrack.util;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.gentrack.customTypes.Maze;
import com.gentrack.customTypes.Tile;

public class MazeSolution {
	
	private List<String> mazeSolution;
	private Maze maze;
	private HashSet<Tile> closedSet;
	
	public MazeSolution(Maze maze, HashSet<Tile> closedSet) {
		this.maze = maze;
		this.closedSet = closedSet;
	}
	
	public List<String> getMazeSolution() {
		mazeSolution = new ArrayList<String>();
		
		formatMaze();
		writeMazeSolutionPath();
		addStartAndEnd();
		
		for(int i=0; i<mazeSolution.size(); i++) {
			System.out.println(mazeSolution.get(i));
		}
		
		return 
			mazeSolution;
	}

	private List<String> formatMaze() {
		
		for(int y=0; y<maze.getMaze().size();y++) {
			String currentMazeSolutionLine = "";
			for(int x=0; x<maze.getMaze().get(y).length();x++) {
				
				if(maze.getMazeTileFromXYCoordiante(x, y).equals("1")) {
					currentMazeSolutionLine = currentMazeSolutionLine.concat("#");
				}
				else if(maze.getMazeTileFromXYCoordiante(x, y).equals("0")) {
					currentMazeSolutionLine = currentMazeSolutionLine.concat(" ");
				}
			}
			mazeSolution.add(currentMazeSolutionLine);
		}
		
		return 
			mazeSolution;
		
	}
	
	private List<String> addStartAndEnd() {
		
		StringBuilder builder = new StringBuilder(mazeSolution.get((int) maze.getStartXY().getY()));
		builder.replace((int) maze.getStartXY().getX(), (int) maze.getStartXY().getX() + 1, "S");
		mazeSolution.set((int) maze.getStartXY().getY(), builder.toString());
		
		builder = new StringBuilder(mazeSolution.get((int) maze.getEndXY().getY()));
		builder.replace((int) maze.getEndXY().getX(), (int) maze.getEndXY().getX() + 1, "E");
		mazeSolution.set((int) maze.getEndXY().getY(), builder.toString());

		return
			mazeSolution;
	}
	
	private List<String> writeMazeSolutionPath() {
		Tile pathTileFromFinishToStart = closedSet
											.stream()
											.filter(tile -> 
														tile
														.getCoordiante()
														.equals(maze.getEndXY()))
														.findFirst()
														.get();

		while (!pathTileFromFinishToStart.getCoordiante().equals(maze.getStartXY())) {
			
			Point2D parentXY = pathTileFromFinishToStart.getParent();
			
			StringBuilder builder = new StringBuilder(mazeSolution.get((int) parentXY.getY()));
			builder.replace((int) parentXY.getX(), (int) parentXY.getX() + 1, "X");
			mazeSolution.set((int) parentXY.getY(), builder.toString());
			
			pathTileFromFinishToStart = closedSet.stream().filter(tile -> tile.getCoordiante().equals(parentXY)).findFirst().get();
		};
		
		return mazeSolution;
	}
}
