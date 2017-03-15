package eg.edu.guc.santorini.tiles;
import java.util.ArrayList;
import eg.edu.guc.santorini.utilities.Location;
public interface PieceInterface {
	
	
	/**
	 * @return ArrayList of Location with the possible moves of the piece
	 */
	ArrayList<Location> possibleMoves();
	
	/**
	 * @return ArrayList of Location with the possible placements of the piece
	 */
	ArrayList<Location> possiblePlacements();
}