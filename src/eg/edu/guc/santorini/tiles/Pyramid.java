package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;
import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {

	@Override
	public ArrayList<Location> possibleMoves() {
		ArrayList<Location> moves = new ArrayList<Location>();
		
		Location l = this.getLocation().addLocation(new Location(-1, 1));
		if (withinBoundaries(l)) {
			moves.add(l);
		}
		
		l = this.getLocation().addLocation(new Location(-1, -1));
		if (withinBoundaries(l)) {
			moves.add(l);
		}
		
		l = this.getLocation().addLocation(new Location(1, -1));
		if (withinBoundaries(l)) {
			moves.add(l);
		}
		
		l = this.getLocation().addLocation(new Location(1, 1));
		if (withinBoundaries(l)) {
			moves.add(l);
		}		
		return moves;
	}
	
	public String toString() {
		return "P";
	}
}
