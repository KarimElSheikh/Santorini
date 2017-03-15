package eg.edu.guc.santorini;

import java.util.ArrayList;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {

	private int [][] cells;
	private Player player1;
	public int[][] getCells() {
		return cells;
	}

	public void setCells(int[][] cells) {
		this.cells = cells;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	private Player player2;
	public int turn;
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	

	private Piece lastMoved;
	
	public Board(Player player1, Player player2)
	{
		cells = new int[SIDE][SIDE];
		this.player1 = player1;
		this.player2 = player2;
		this.player1.getT1().setLocation(new Location(0, 0));
		this.player1.getT2().setLocation(new Location(4, 1));
		this.player2.getT1().setLocation(new Location(0, 3));
		this.player2.getT2().setLocation(new Location(4, 4));
		turn = 0;
	}
	public Board(Board b)
	{
		cells = new int[SIDE][SIDE];
		int sum = 0 ;
		for (int i = 0; i < SIDE; i++) {
			for (int j = 0; j < SIDE; j++) {
				cells[i][j] = b.getCells()[i][j];
				sum += cells[i][j];
			}
		}
		this.player1 = new Player(b.getPlayer1());
		this.player2 = new Player(b.getPlayer2());
		this.player1.getT1().setLocation(b.getPlayer1().getT1().getLocation());
		this.player1.getT2().setLocation(b.getPlayer1().getT2().getLocation());
		this.player2.getT1().setLocation(b.getPlayer2().getT1().getLocation());
		this.player2.getT2().setLocation(b.getPlayer2().getT2().getLocation());
		turn = sum%2;
	}
	@Override
	public void move(Piece piece, Location newLocation) throws InvalidMoveException {
		if (canMove(piece, newLocation) && getTurn() == getPiecePlayer(piece) 
				&& !isGameOver() && lastMoved == null) {
			piece.setLocation(newLocation);
			lastMoved = piece;
		}
		else {
			throw new InvalidMoveException("This move is not allowed");
		}
	}
	public void fakemove(Piece piece, Location newLocation) throws InvalidMoveException {
		if (canMove(piece, newLocation) 
				 && lastMoved == null) {
			piece.setLocation(newLocation);
			lastMoved = piece;
		}
		else {
			throw new InvalidMoveException("This move is not allowed");
		}
	}
	public void fakeplace(Piece piece, Location newLocation) throws InvalidPlacementException {
		if (canPlace(piece, newLocation)) {
			cells[newLocation.getX()][newLocation.getY()]++;
			turn++;
			lastMoved = null;
		}
		else {
			throw new InvalidPlacementException("This placement is not allowed");
		}
	}
	@Override
	public void place(Piece piece, Location newLocation) throws InvalidPlacementException {
		if (canPlace(piece, newLocation) && getTurn() == getPiecePlayer(piece) 
				&& lastMoved == piece && !isGameOver()) {
			cells[newLocation.getX()][newLocation.getY()]++;
			turn++;
			lastMoved = null;
		}
		else {
			throw new InvalidPlacementException("This placement is not allowed");
		}
	}

	@Override
	public boolean isGameOver() {
		return isWinner(player1) || isWinner(player2);
	}

	@Override
	public boolean isWinner(Player player) {
		if (player == player1) {
			return isLevelThree(player1.getT1()) || isLevelThree(player1.getT2()) 
				|| (hasNoMoves(player2) && getTurn() == player2);
		} else if (player == player2) {
			return isLevelThree(player2.getT1()) || isLevelThree(player2.getT2()) 
				|| (hasNoMoves(player1) && getTurn() == player1);			
		}
		return false;
	}
	
	@Override
	public boolean hasNoMoves(Player player) {
		if (player == player1) {
			return hasNoMoves(player1.getT1()) && hasNoMoves(player1.getT2());
		}
		else if (player == player2) {
			return hasNoMoves(player2.getT1()) && hasNoMoves(player2.getT2()); 
		}
		return false;
	}
	
	/**
	 * @param piece The piece to be checked
	 * @return true if piece has no location to move, otherwise returns false
	 */
	private boolean hasNoMoves(Piece piece) {
		boolean hasNoMoves = true;
		
		ArrayList<Location> possibleMoves = piece.possibleMoves();
		
		for (int i = 0; i < possibleMoves.size(); i++) {
			if (canMove(piece, possibleMoves.get(i))) {
				hasNoMoves = false;
			}
		}
		return hasNoMoves;
	}

	@Override
	public Player getWinner() {
		if (isWinner(player1)) {
			return player1;
		}
		
		if (isWinner(player2)) {
			return player2;
		}
		
		return null;
	}

	@Override
	public boolean canMove(Piece piece, Location location) {
		return cells[piece.getLocation().getX()][piece.getLocation().getY()] 
		       - cells[location.getX()][location.getY()] >= -1 
			   && validPlay(piece, location, "Move");
	}

	@Override
	public boolean canPlace(Piece piece, Location location) {		
		return validPlay(piece, location, "Place");
	}
	
	@Override
	public Player getTurn() {
		if (turn % 2 == 0) {
			return player1;
		}
		else {
			return player2;
		}
	}
	public Player getNotTurn() {
		if (turn % 2 == 1) {
			return player1;
		}
		else {
			return player2;
		}
	}
	@Override
	public String[][] display() {
		String [][] result = new String[SIDE][SIDE];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = "" + cells[i][j];
			}
		}
		
		result[player1.getT1().getLocation().getX()][player1.getT1().getLocation().getY()] 
		                                            += player1.getT1().toString() + "1";
		
		result[player1.getT2().getLocation().getX()][player1.getT2().getLocation().getY()] 
			                                            += player1.getT2().toString() + "1";

		result[player2.getT1().getLocation().getX()][player2.getT1().getLocation().getY()] 
			                                            += player2.getT1().toString() + "2";
		
		result[player2.getT2().getLocation().getX()][player2.getT2().getLocation().getY()] 
			                                            += player2.getT2().toString() + "2";
		
		return result;
	}
	
	
	/**
	 * @param piece The piece to be checked
	 * @return true if piece is on a tile of level three, otherwise returns false
	 */
	private boolean isLevelThree(Piece piece) {
		return cells[piece.getLocation().getX()][piece.getLocation().getY()] == 3;
	}
	
	
	/**
	 * @param piece The piece to be checked
	 * @param location The new location
	 * @param type The type of the play, whether it is move or place
	 * @return true if the play is valid according 
	 * to the game rules and play type, otherwise returns false
	 */
	private boolean validPlay(Piece piece, Location location, String type)
	{
		if (!withinBoundaries(location)) {
			return false;
		}
		
		if (type.equals("Place") && !exists(piece.possiblePlacements(), location)) {
			return false;
		}
			
		if (type.equals("Move") && !exists(piece.possibleMoves(), location)) {
			return false;
		}
		
		if (isOccupied(location)) {
			return false;
		}
		
		if (isDome(location)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * @param location The location to be checked
	 * @return true if location is not outside the board, otherwise returns false
	 */
	private boolean withinBoundaries(Location location) {
		return location.getX() >= 0 && location.getX() < SIDE 
			&& location.getY() >= 0 && location.getY() < SIDE;
	}
	
	
	/**
	 * @param places Arraylist of Location
	 * @param location The location to be checked
	 * @return true if location exists in Arraylist places, otherwise returns false
	 */
	private boolean exists(ArrayList<Location> places, Location location)
	{
		boolean found = false;
		
		for (int i = 0; i < places.size(); i++) {
			if (location.equals(places.get(i))) {
				found = true;
			}
		}
		
		return found;
	}
	
	
	/**
	 * @param location The location to be checked
	 * @return true if location is occupied with a piece, otherwise returns false
	 */
	private boolean isOccupied(Location location) {
		return (player1.getT1().getLocation().equals(location) 
				|| player1.getT2().getLocation().equals(location) 
				|| player2.getT1().getLocation().equals(location) 
				|| player2.getT2().getLocation().equals(location));
	}
	public Piece getOccupied(Location location) {
		Piece temp = null ;
		if(player1.getT1().getLocation().equals(location))
			temp = player1.getT1();
		if(player1.getT2().getLocation().equals(location))
			temp = player1.getT2();
		if(player2.getT1().getLocation().equals(location))
			temp = player2.getT1();
		if(player2.getT2().getLocation().equals(location))
			temp = player2.getT2();
		return temp ;
	}
	
	/**
	 * @param location The location to be checked
	 * @return true if there is a dome(level 4) at location
	 */
	private boolean isDome(Location location) {
		return cells[location.getX()][location.getY()] > 3;
	}
	
	/**
	 * @param piece The piece to be checked
	 * @return the player who owns piece
	 */
	private Player getPiecePlayer(Piece piece) {
		if (piece == player1.getT1() || piece == player1.getT2()) {
			return player1;
		}
		else {
			if (piece == player2.getT1() || piece == player2.getT2()) {
				return player2;
			}
		}
			return null;
	}
	public Piece getLastMoved() {
		return lastMoved;
	}

	public void setLastMoved(Piece lastMoved) {
		this.lastMoved = lastMoved;
	}
	@Override
	public String toString() {
		String s = "";
		s += "-----------------\n";
		for (int i = 0; i < 5 ; i++) {
			for (int j = 0; j < 5; j++) {
				if(display()[i][j].length()>1)
				s += display()[i][j] + " ";
				else
				s += display()[i][j] + "   ";
			}
			s += "\n";
		}
		s += "-----------------\n";
		return s;
	}
}
