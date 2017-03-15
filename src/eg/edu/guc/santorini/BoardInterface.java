package eg.edu.guc.santorini;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public interface BoardInterface {
	
	int SIDE = 5;

	/**
	 * 	If the game rules allow the move, the piece is moved to new location. 
	 * @param piece	The piece to be moved
	 * @param newLocation The new location of the piece
	 * @throws InvalidMoveException is thrown if the move is not allowed according to the game rules
	 * 
	 */
	void move(Piece piece, Location newLocation) throws InvalidMoveException;

	/**
	 * If the game rules allow the placement, a tile is placed at new location. 
	 * @param piece	The piece that places the tile
	 * @param newLocation The location to place the tile
	 * @throws InvalidPlacementException is thrown if the 
	 * placement is not allowed according to the game rules
	 */
	void place(Piece piece, Location newLocation) throws InvalidPlacementException;
	
	/**
	 * @return true if the game is over, otherwise returns false
	 */
	boolean isGameOver();

	/**
	 * @param player
	 * @return true if player is winner, otherwise returns false
	 */
	boolean isWinner(Player player);
	
	/**
	 * @param player
	 * @return true if player has no moves, otherwise returns false
	 */
	boolean hasNoMoves(Player player);
	
	/**
	 * @return the player who won the game, if no winner, null is returned
	 */
	Player getWinner();
	
	/**
	 * @param piece The piece to be moved
	 * @param location The new location of the piece
	 * @return true if piece can move to location, otherwise returns false
	 */
	boolean canMove(Piece piece, Location location);
	
	/**
	 * @param piece The piece that places the tile
	 * @param location The location to place the tile
	 * @return true if a Piece piece can place a tile at location, otherwise returns false
	 */
	boolean canPlace(Piece piece, Location location);
	
	/**
	 * @return the player whose turn is the current turn
	 */
	Player getTurn();
	
	
	/**
	 * @return 2D String array that displays the board
	 */
	String [][] display();
}
