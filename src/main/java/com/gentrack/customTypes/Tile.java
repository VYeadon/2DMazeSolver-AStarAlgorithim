package com.gentrack.customTypes;

import java.awt.geom.Point2D;

public class Tile {
	
	private Point2D coordiante;
	private Point2D parent;
	private int totalCost;
	
	public Tile(Point2D coordinate, int totalCost) {
		this.coordiante = coordinate;
		this.totalCost = totalCost;
	}

	public int getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	public Point2D getCoordiante() {
		return coordiante;
	}

	public void setCoordiante(int x, int y) {
		this.coordiante = new Point2D.Double(x,y);
	}

	public Point2D getParent() {
		return parent;
	}

	public void setParent(Point2D parent) {
		this.parent = parent;
	}
	
	// Uses the Manhattan distance as there are only 4 available directions in this scenario
	// a more traditional measure of distance would be used if 8 directions were available
	private int calculateGCost(Point2D coordinate, Point2D startXY) {
		int xDistance = (int) (startXY.getX() - coordinate.getX());
		int yDistance = (int) (startXY.getY() - coordinate.getY());
		
		return
			Math.abs(xDistance) + Math.abs(yDistance);
	}
	
	// Uses the Manhattan distance as there are only 4 available directions in this scenario
	// a more traditional measure of distance would be used if 8 directions were available
	private int calculateHCost(Point2D coordinate, Point2D endXY) {
		int xDistance = (int) (endXY.getX() - coordinate.getX());
		int yDistance = (int) (endXY.getY() - coordinate.getY());
		
		return
			Math.abs(xDistance) + Math.abs(yDistance);
	}

	public int calculateMazeTileCost(Point2D startXY, Point2D endXY) {
		return
			calculateGCost(coordiante, startXY) + calculateHCost(coordiante, endXY);
	}
	
	
}
