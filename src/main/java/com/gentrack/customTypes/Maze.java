package com.gentrack.customTypes;

import java.awt.geom.Point2D;
import java.util.List;

public class Maze {
	
	public int width;
	public int height;
	
	public Point2D startXY;
	public Point2D endXY;
	
	public List<String> maze;
	
	public Maze(List<String> fileData) 
	{
		width = Integer.parseInt(fileData.get(0).split(" ")[0]);
		height = Integer.parseInt(fileData.get(0).split(" ")[1]);
		
		int startX = Integer.parseInt(fileData.get(1).split(" ")[0]);
		int startY = Integer.parseInt(fileData.get(1).split(" ")[1]);
		startXY = new Point2D.Double(startX, startY);
				
		int endX = Integer.parseInt(fileData.get(2).split(" ")[0]);
		int endY = Integer.parseInt(fileData.get(2).split(" ")[1]);
		endXY = new Point2D.Double(endX, endY);
		
		maze = fileData.subList(3, fileData.size());
		
		for(int i=0; i<maze.size(); i++) {
			maze.set(i, maze.get(i).replaceAll(" ", ""));
		}
	}
	
	public String getMazeTileFromXYCoordiante(double x, double y) {
		return
			maze.get((int)y)
				.substring((int)x, (int)x+1);
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Point2D getStartXY() {
		return startXY;
	}
	public Point2D getEndXY() {
		return endXY;
	}
	public List<String> getMaze() {
		return maze;
	}

}
