package  eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	private Location location;

	public void setLocation(Location locaction) {
		this.location = locaction;
	}

	public Location getLocation() {
		return location;
	}
	
	public ArrayList<Location> possiblePlacements() {
		ArrayList<Location> placements = new ArrayList<Location>();
		
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Location location = new Location(i, j);
				location = location.addLocation(this.getLocation());
				if (withinBoundaries(location)) {
					placements.add(location);
				}
			}
		}
		return placements;
	}
	
	/**
	 * @param location The location to be checked
	 * @return true if location is not outside the board, otherwise returns false
	 */
	boolean withinBoundaries(Location location) {
		return location.getX() >= 0 && location.getX() < Board.SIDE 
				&& location.getY() >= 0 && location.getY() < Board.SIDE;
	}
}
