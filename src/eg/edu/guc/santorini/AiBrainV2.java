package eg.edu.guc.santorini;

import java.util.ArrayList;
import java.util.Collections;

import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class AiBrainV2 {
	static boolean debugMode = true;
	static int turn = 0;

	public static Board getBestBoard(int lvl, Board b, Player p) {
		ArrayList<Board> boards = generateAllBoards(b, p);
		ArrayList<Integer> values = evaluateBoards(lvl, boards);
		Board bestBoard = boards.get(getRandombestIndex(values, Collections.max(values)));
		return bestBoard;
	}

	public static Board getRandomBoard(Board b, Player p) {
		ArrayList<Board> boards = generateAllBoards(b, p);
		Board RandomBoard = boards.get((int) (Math.random() * boards.size()));
		return RandomBoard;
	}

	public static int getRandombestIndex(ArrayList<Integer> a, int max) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) == max)
				temp.add(i);
		}
		return (temp.get((int) (Math.random() * temp.size())));
	}

	private static ArrayList<Integer> evaluateBoards(int n, ArrayList<Board> boards) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < boards.size(); i++) {
			Board b = boards.get(i);
			// result.add(EvaluateAllPieces(b.getTurn(), b.getNotTurn(), b));
			result.add(alphabeta(b, 4, -1000000, 1000000, false));
		}
		return result;
	}

	public static void printBoard(Board b) {
		System.out.println("-----------------");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (b.display()[i][j].length() > 1)
					System.out.print(b.display()[i][j] + " ");
				else
					System.out.print(b.display()[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}

	public static int alphabeta(Board b, int d, int alpha, int beta, boolean maximize) {
		// ------------------ Currunt Evaluation --------------------
		int result = 0;
		// ---------------------- base case --------------------------
		if (d == 1) {
			if (turn == 1)
				result = EvaluateAllPieces(b.getPlayer1(), b.getPlayer2(), b);
			if (turn == 2)
				result = EvaluateAllPieces(b.getPlayer2(), b.getPlayer1(), b);

			return result;
		}
		// --------------------- recursive case ----------------------
		if (maximize)// case 1 maximize
		{
			ArrayList<Board> branches = generateAllBoards(b, b.getTurn());// AI is player 2
			ArrayList<Integer> results = new ArrayList<Integer>();
			results.add(alpha);
			for (int i = 0; i < branches.size(); i++) {
				results.add(alphabeta(branches.get(i), d - 1, alpha, beta, false));
				alpha = Collections.max(results);
				if (beta <= alpha)
					break;
			}
			// System.out.println("$"+alpha+"$ " + "#"+d+"# "+results);
			// if(d ==3 )System.out.println();
			// if(d == 3)System.out.println(branches.get(results.indexOf(alpha)));
			return alpha;
		} else {
			ArrayList<Board> branches = generateAllBoards(b, b.getTurn());// AI is player 2
			ArrayList<Integer> results = new ArrayList<Integer>();
			for (int i = 0; i < branches.size(); i++) {
				results.add(alphabeta(branches.get(i), d - 1, alpha, beta, true));
				beta = Collections.min(results);
				if (beta <= alpha)
					break;
			}
			// System.out.println("$"+beta+"$ " + "#"+d+"# "+results);
			// System.out.println();
			return beta;
		}
	}

	public static ArrayList<Board> generateAllBoards(Board b, Player P) {
		ArrayList<Board> possibleBoards = new ArrayList<Board>();
		ArrayList<Board> temp = generateBoardsPerPiece(b, P.getT1());
		for (int i = 0; i < temp.size(); i++) {
			possibleBoards.add(temp.get(i));
		}
		temp = generateBoardsPerPiece(b, P.getT2());
		for (int i = 0; i < temp.size(); i++) {
			possibleBoards.add(temp.get(i));
		}
		return possibleBoards;
	}

	public static ArrayList<Board> generateBoardsPerPiece(Board b, Piece p1) {
		ArrayList<Board> possibleBoards = new ArrayList<Board>();
		ArrayList<Location> moves = p1.possibleMoves();
		ArrayList<Location> placements;
		for (int i = 0; i < moves.size(); i++) {
			try {
				Board tempBoard = new Board(b);
				// printBoard(tempBoard);
				// printBoard(b);
				Piece p = getEqualPiece(p1, tempBoard);
				tempBoard.fakemove(p, moves.get(i));
				// printBoard(tempBoard);
				// System.out.println("*****************************************");
				placements = p.possiblePlacements();
				for (int j = 0; j < placements.size(); j++) {
					try {
						Board tempBoard2 = new Board(tempBoard);
						// printBoard(tempBoard2);System.out.println("^^^^^^^^^^");
						tempBoard2.fakeplace(getEqualPiece(p, tempBoard2), placements.get(j));
						possibleBoards.add(tempBoard2);
						// printBoard(tempBoard2);
						/*
						 * int x =EvaluateAllPieces(tempBoard.getPlayer1(), tempBoard.getPlayer2(),
						 * tempBoard2); System.out.println(x); printBoard(tempBoard2);
						 */
					} catch (Exception e) {
					}
				}
			} catch (Exception e) {
			}
		}
		return possibleBoards;
	}

	private static Piece getEqualPiece(Piece p1, Board b) {
		if (b.getPlayer1().getT1().getLocation().equals(p1.getLocation()))
			return b.getPlayer1().getT1();
		if (b.getPlayer1().getT2().getLocation().equals(p1.getLocation()))
			return b.getPlayer1().getT2();
		if (b.getPlayer2().getT1().getLocation().equals(p1.getLocation()))
			return b.getPlayer2().getT1();
		if (b.getPlayer2().getT2().getLocation().equals(p1.getLocation()))
			return b.getPlayer2().getT2();
		return null;
	}

	public static int EvaluateAllPieces(Player maxPlayer, Player minPlayer, Board b) {
		int result = 0;
		Location loc;
		int x1 = 0;
		int x2 = 0;
		int x3 = 0;
		int x4 = 0;
		loc = maxPlayer.getT1().getLocation();
		x1 = EvaluatePiece(loc, b);
		result += x1;// if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = maxPlayer.getT2().getLocation();
		x2 = EvaluatePiece(loc, b);
		result += x2;// if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = minPlayer.getT1().getLocation();
		x3 = EvaluatePiece(loc, b);
		result -= 2 * x3;// if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = minPlayer.getT2().getLocation();
		x4 = EvaluatePiece(loc, b);
		result -= 2 * x4;// if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		// System.out.println(" ("+maxPlayer.getTiletype()+" ,
		// "+minPlayer.getTiletype()+") "+"{ " + + x1 +" , "+ x2 +" , "+ x3 +" , " +x4 +
		// " }");
		result -= EvaluatePositiens(maxPlayer, minPlayer);
		return result;

	}

	public static int EvaluatePositiens(Player p1, Player p2) {
		int result = 0;
		result += getDist(p1.getT1(), p2.getT1());
		result += getDist(p1.getT1(), p2.getT2());
		result += getDist(p1.getT2(), p2.getT1());
		result += getDist(p1.getT2(), p2.getT2());
		int x = getDist(p2.getT1(), p2.getT2());
		result += (25 / x) ^ 6;
		return result;

	}

	public static int getDist(Piece p1, Piece p2) {
		int difX = p1.getLocation().getX() - p2.getLocation().getX();
		int difY = p1.getLocation().getY() - p2.getLocation().getY();
		int dist = 1 + (int) Math.ceil(Math.abs(difX) * Math.abs(difY));
		return dist * dist;
	}

	public static int EvaluatePiece(Location l, Board b) {
		int result = 0;
		Piece p = b.getOccupied(l);
		ArrayList<Location> temp = p.possibleMoves();
		int level = (b.getCells())[l.getX()][l.getY()];
		if (level == 3)
			result += 250000;
		if (level == 2)
			result += 150;
		if (level == 1)
			result += 80;
		for (int k = 0; k < temp.size(); k++) {
			Location loc = temp.get(k);
			int boardlevel = (b.getCells())[loc.getX()][loc.getY()];
			switch (boardlevel - level) {
			case 3:
				result += 5;
				break;
			case 2:
				result += 10;
				break;
			case 1:
				if (boardlevel == 3) {
					result += 500;
				}
				if (boardlevel == 2)
					result += 60;
				if (boardlevel == 1)
					result += 30;
				break;
			case 0:
				result += 10;
				/*
				 * if(level == 2 )result += 50; if(level == 1 )result += 20;
				 */
				break;
			case -1:
				result += 5;
				break;
			case -2:
				result += 2;
				break;
			case -3:
				result += 1;
				break;
			}
		}
		return result;
	}
	
//	public static int evaluateSurroundings(Location l, Board b)	
//	{
//		String[][] s = b.display();
//		int result = 0;
//		//int level = getlevel(s[j][i]);
//		Piece p = new Cube();
//		p.setLocation(new Location(l));
//		ArrayList<Location> temp = p.possiblePlacements();
//		
//		for (int k = 0; k < temp.size(); k++) 
//		{
//			Location loc = temp.get(k);
//			int boardlevel = getlevel(s[loc.getX()][loc.getY()]);
//			int level = getlevel(s[p.getLocation().getX()][p.getLocation().getY()]);
//			if(s[loc.getX()][loc.getY()].length()==1)
//			switch(boardlevel - level)
//			{
//				case 3 : result += 5 ; break;
//				case 2 : result += 10 ; break;
//				case 1 : result += 40 ;/*System.out.print("#")*/; break;
//				case 0 : result += 10 ; break;
//				case-1 : result += 5 ; break;
//				case-2 : result += 2 ; break;
//				case-3 : result += 1 ; break;
//			}
//		}
//		//System.out.print(result + ""+  l + " + " );
//		return result;
//	}

	public static int getlevel(String s) {
		return Integer.parseInt(s.charAt(0) + "");
	}

	public static boolean canPlace(Location location, Board BoardArray) {
		if ((BoardArray.display())[location.getY()][location.getX()].length() == 1
				&& Integer.parseInt("" + (BoardArray.display())[location.getY()][location.getX()].charAt(0)) <= 3) {
			return true;
		} else
			return false;
	}
}