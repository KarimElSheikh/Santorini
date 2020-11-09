package eg.edu.guc.santorini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import eg.edu.guc.santorini.players.AiPlayer;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Adapter implements ActionListener, MouseListener {
	
	private Board myBoard;
	private Tile selectedTile;
	private Piece selectedPiece;
	private Window myWindow;
	private int exceptions;
	private int player1type = 0;
	private int player2type = 0;
	public Adapter(String p1Name, String p2Name, Window w , int p1Mode , int p2Mode) {
		myWindow = w;
		Player fplayer;
		Player splayer;
		player1type = p1Mode;
		player2type = p2Mode;
		
		fplayer = new Player(p1Name, w.getP1Type());
		splayer = new Player(p2Name, w.getP2Type());
		myBoard = new Board(fplayer, splayer);

	}

	public boolean isOccubied(Location l) {
		String s = myBoard.display()[l.getX()][l.getY()];
		if (s.length() > 1) {
			return true;
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		selectedTile = (Tile) e.getSource();
		if(myBoard.getTurn() == myBoard.getPlayer1())
		{
			AiBrainV2.turn = 1;
			switch (player1type) {
			
			case 0:
				if (myBoard.getLastMoved() == null) { // Move Time
					moveTime();
				} else {
					placeTime();
				}
				break;
			case 1:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(1,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 2's turn to move");
				break;
			case 2:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(3,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 2's turn to move");
				break;
			case 3:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(4,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 2's turn to move");
				break;
			}
		}
		else
		{
			AiBrainV2.turn = 2;
			switch (player2type) {
			case 0:
				if (myBoard.getLastMoved() == null) { // Move Time
					moveTime();
				} else {
					placeTime();
				}
				break;
			case 1:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(1,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 1's turn to move");
				break;
			case 2:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(3,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 1's turn to move");
				break;
			case 3:
				selectedTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
				myBoard = AiBrainV2.getBestBoard(4,myBoard, myBoard.getTurn());		
				drawBoard();
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 1's turn to move");
				break;
			}
		}
	}
	public void moveTime() {
		try {
			if (isOccubied(selectedTile.getlocation())) {
				selectedPiece = getPieceByTile();
				showPossibleMoves();
			}
			else {
				Location temp = selectedPiece.getLocation();
				myBoard.move(selectedPiece, selectedTile.getlocation());
				if (myBoard.getTurn() == myBoard.getPlayer1()) { // change in GUI
					selectedTile.addPiece(selectedPiece.toString() + 1);
					myWindow.setInfo("Player 1: "
							+ myBoard.getPlayer1().getName() + "    Player 2: "
							+ myBoard.getPlayer2().getName()
							+ "                    Player 1's turn to place");
				}
				else {
					selectedTile.addPiece(selectedPiece.toString() + 2);
					myWindow.setInfo("Player 1: "
							+ myBoard.getPlayer1().getName() + "    Player 2: "
							+ myBoard.getPlayer2().getName()
							+ "                    Player 2's turn to place");
				}
				getTileByLocation(temp).removePiece();
					showPossiblePlaces();
				if (myBoard.isGameOver()) {
					removeAllHints();
				}
			}
		} catch (Exception e) {
				exceptions++;
			}
	}
	public void printBestMove(int lvl)
	{
		AiBrainV2.printBoard(AiBrainV2.getBestBoard(lvl ,myBoard, myBoard.getTurn()));	
	}
	public void drawBoard()
	{
		Tile [][] labels = myWindow.getLabels();
		int  [][] levels = myBoard.getCells();
		Location loc11 = myBoard.getPlayer1().getT1().getLocation();
		Location loc12 = myBoard.getPlayer1().getT2().getLocation();
		Location loc21 = myBoard.getPlayer2().getT1().getLocation();
		Location loc22 = myBoard.getPlayer2().getT2().getLocation();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				labels [i][j].addTile(levels[i][j]);
				if(i == loc11.getX() && j == loc11.getY())
					labels[i][j].addPiece(myBoard.getPlayer1().getT1().toString()+1);
				if(i == loc12.getX() && j == loc12.getY())
					labels[i][j].addPiece(myBoard.getPlayer1().getT2().toString()+1);
				if(i == loc21.getX() && j == loc21.getY())
					labels[i][j].addPiece(myBoard.getPlayer2().getT1().toString()+2);
				if(i == loc22.getX() && j == loc22.getY())
					labels[i][j].addPiece(myBoard.getPlayer2().getT2().toString()+2);
			}
		}
	}
	public void placeTime() {
		try {
			if (selectedPiece == myBoard.getLastMoved()) {
				myBoard.place(selectedPiece, selectedTile.getlocation());
				if (myBoard.getTurn() == myBoard.getPlayer1()) {
					myWindow.setInfo("Player 1: "
									+ myBoard.getPlayer1().getName() + "    Player 2: "
									+ myBoard.getPlayer2().getName()
									+ "                    Player 1's turn to move");
				} else {
					myWindow.setInfo("Player 1: "
									+ myBoard.getPlayer1().getName() + "    Player 2: "
									+ myBoard.getPlayer2().getName()
									+ "                    Player 2's turn to move");
				}
				selectedTile.inceraseLevel(myBoard.getTurn() == myBoard.getPlayer1());
				removeAllHints();
			}
		} catch (Exception e) {
			exceptions++;
		}
	}

	private void showPossiblePlaces() {
		removeAllHints();
		for (int i = 0; i < selectedPiece.possiblePlacements().size(); i++) {
			if (myBoard.canPlace(selectedPiece, selectedPiece
					.possiblePlacements().get(i))) {
				getTileByLocation(selectedPiece.possiblePlacements().get(i))
						.showHints();
			}
		}
	}

	private void showPossibleMoves() {
		removeAllHints();
		for (int i = 0; i < selectedPiece.possibleMoves().size(); i++) {
			// System.out.println(selectedPiece.possibleMoves().get(i));
			if (myBoard.canMove(selectedPiece, selectedPiece.possibleMoves()
					.get(i))) {
				getTileByLocation(selectedPiece.possibleMoves().get(i))
						.showHints();
			}
		}
	}

	private Tile getTileByLocation(Location l) {
		return myWindow.getLabels()[l.getX()][l.getY()];
	}

	private void removeAllHints() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				((Tile) (myWindow.getLabels()[i][j])).removeHints();
			}
		}
	}

	private Piece getPieceByTile() {

		if (myBoard.getPlayer1().getT1().getLocation()
				.equals(selectedTile.getlocation())) {
			return myBoard.getPlayer1().getT1();
		}
		if (myBoard.getPlayer1().getT2().getLocation()
				.equals(selectedTile.getlocation())) {
			return myBoard.getPlayer1().getT2();
		}
		if (myBoard.getPlayer2().getT1().getLocation()
				.equals(selectedTile.getlocation())) {
			return myBoard.getPlayer2().getT1();
		}
		if (myBoard.getPlayer2().getT2().getLocation()
				.equals(selectedTile.getlocation())) {
			return myBoard.getPlayer2().getT2();
		}
		return null;
	}

	public void mouseReleased(MouseEvent arg0) {
		selectedTile.setBorder(BorderFactory.createEmptyBorder());
		if (myBoard.isGameOver()) {
			if (myBoard.isWinner(myBoard.getPlayer1())) {
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 1 "
						+ myBoard.getPlayer1().getName() + " is the winner!!!");
				JOptionPane.showMessageDialog(null, "Congratulations!\n" + "Player 1 "
						+ myBoard.getPlayer1().getName() + " wins!");
				myWindow.getContentPane().removeAll();
				myWindow.setVisible(false);
				new Window(5, 5);
			} else {
				myWindow.setInfo("Player 1: "
						+ myBoard.getPlayer1().getName() + "    Player 2: "
						+ myBoard.getPlayer2().getName()
						+ "                    Player 2 "
						+ myBoard.getPlayer2().getName() + " is the winner!!!");
				JOptionPane.showMessageDialog(null, "Congratulations!\n" + "Player 2 "
						+ myBoard.getPlayer2().getName() + " wins!");
			}
		}
	}
	public int getExceptions() {
		return exceptions;
	}
	
}