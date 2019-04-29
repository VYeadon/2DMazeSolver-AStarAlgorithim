package com.gentrack.logic;

import java.awt.geom.Point2D;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import com.gentrack.customTypes.Maze;
import com.gentrack.customTypes.Tile;
import com.gentrack.enums.Directions;
import com.gentrack.util.MazeSolution;

public class MazeSolver {

	public Maze maze;

	public Tile currentTile;
	public Tile neighbourTile;

	public HashSet<Tile> closedSet = new HashSet<Tile>();
	public HashSet<Tile> openSet = new HashSet<Tile>();

	public boolean solveMaze(List<String> fileData) {

		maze = new Maze(fileData);

		int neighbourTileCost;

		openSet.add(new Tile(maze.getStartXY(), 0));

		do {
			currentTile = findTileWithLowestCostInOpenGroup();

			openSet.remove(currentTile);
			closedSet.add(currentTile);

			for (Directions direction : Directions.values()) {
				
				setNeighbourTileCoordinate(direction, currentTile.getCoordiante());
				
				if (!checkIfTileIsMovable(neighbourTile.getCoordiante()) || closedSet.stream()
																				.anyMatch(tile -> 
																						tile
																						.getCoordiante()
																						.equals(neighbourTile.getCoordiante()))) {

					continue;
				}


				neighbourTileCost = neighbourTile.calculateMazeTileCost(maze.getStartXY(), maze.getEndXY());

				if (isNewTileCostLessThanPrevious(neighbourTile, neighbourTileCost) || !openSet.stream()
																							.anyMatch(tile -> 
																									tile
																									.getCoordiante()
																									.equals(neighbourTile.getCoordiante()))) {

					neighbourTile.setTotalCost(neighbourTileCost);
					neighbourTile.setParent(currentTile.getCoordiante());
					openSet.add(neighbourTile);
				}
			}
			if (openSet.isEmpty()) {
				break;
			}
		} while (!currentTile.getCoordiante().equals(maze.getEndXY()));

		if (currentTile.getCoordiante().equals(maze.getEndXY())) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNewTileCostLessThanPrevious(Tile tileToCheck, int newCost) {
		if (openSet
				.stream()
				.anyMatch(tile -> 
					tile
					.getCoordiante()
					.equals(tileToCheck.getCoordiante()))) {

			if (openSet
					.stream()
					.filter(tile -> 
						tile
						.getCoordiante()
						.equals(tileToCheck.getCoordiante()))
					.findFirst()
					.get()
					.getTotalCost() > newCost) {
				return true;
			}
		}
		return false;
	}

	public Tile setNeighbourTileCoordinate(Directions direction, Point2D parentCoordinate) {
		
		switch (direction) {
		case NORTH: {
			if (parentCoordinate.getY() != 0) {
				return 
					neighbourTile = new Tile(new Point2D.Double(parentCoordinate.getX(), parentCoordinate.getY() - 1), 0);
			} 
			else {
				return 
					neighbourTile = new Tile(getWrappableTileCoordinate(direction, parentCoordinate), 0);
			}
		}
		case EAST: {
			if (parentCoordinate.getX() != maze.getWidth() - 1) {
				return 
					neighbourTile = new Tile(new Point2D.Double(parentCoordinate.getX() + 1, parentCoordinate.getY()), 0);
			} 
			else {
				return 
					neighbourTile = new Tile(getWrappableTileCoordinate(direction, parentCoordinate), 0);
			}
		}
		case SOUTH: {
			if (parentCoordinate.getY() != maze.getHeight() - 1) {
				return 
					neighbourTile = new Tile(new Point2D.Double(parentCoordinate.getX(), parentCoordinate.getY() + 1), 0);
			} 
			else {
				return 
					neighbourTile = new Tile(getWrappableTileCoordinate(direction, parentCoordinate), 0);
			}
		}
		case WEST: {
			if (parentCoordinate.getX() != 0) {
				return 
					neighbourTile = new Tile(new Point2D.Double(parentCoordinate.getX() - 1, parentCoordinate.getY()), 0);
			}
			else {
				return 
					neighbourTile = new Tile(getWrappableTileCoordinate(direction, parentCoordinate), 0);
			}
		}
		}
		return neighbourTile;
	}

	public boolean checkIfTileIsMovable(Point2D coordinate) {
		if (maze.getMazeTileFromXYCoordiante(coordinate.getX(), coordinate.getY()).equals("0")) {
			return true;
		} 
		else {
			return false;
		}
	}

	public Point2D getWrappableTileCoordinate(Directions direction, Point2D coordinate) {

		switch (direction) {
		case NORTH: {
			return new Point2D.Double(coordinate.getX(), maze.getHeight() - 1);
		}
		case EAST: {
			return new Point2D.Double(0, coordinate.getY());
		}
		case SOUTH: {
			return new Point2D.Double(coordinate.getX(), 0);
		}
		case WEST: {
			return new Point2D.Double(maze.getWidth() - 1, coordinate.getY());
		}
		}
		return coordinate;
	}

	public Tile findTileWithLowestCostInOpenGroup() {
		return openSet.stream().min(Comparator.comparing(Tile::getTotalCost)).get();
	}

	public List<String> getMazeSolution() {
		MazeSolution solution = new MazeSolution(maze, closedSet);

		return solution.getMazeSolution();
	}
}
