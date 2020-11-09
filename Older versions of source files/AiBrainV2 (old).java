package eg.edu.guc.santorini;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DebugGraphics;

import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class AiBrainV2 {
	static boolean debugMode = true;
	
	public static void main(String[] args) {
		Player p1 = new Player("P1",1);
		Player p2 = new Player("P2",2);
		Board b = new Board(p1,p2);
		try {
			ArrayList<Board> boards = generateAllBoards(b, b.getPlayer1());
			ArrayList<Integer> values = evaluateBoards(boards);System.out.println();
			Board bestBoard = boards.get(values.indexOf(Collections.max(values)));
			Board worstBoard = boards.get(values.indexOf(Collections.min(values)));
			//System.out.println(values);
			//printBoard(bestBoard);
			//printBoard(worstBoard);
			} catch (Exception e) {
			System.out.println("o_o");
		}
	}
	public static Board getBestBoard(Board b , Player p)
	{
		ArrayList<Board> boards = generateAllBoards(b, p);
		ArrayList<Integer> values = evaluateBoards(boards);System.out.println();
		Board bestBoard = boards.get(values.indexOf(Collections.max(values)));
		Board worstBoard = boards.get(values.indexOf(Collections.min(values)));
		return bestBoard;
	}
		
	private static ArrayList<Integer> evaluateBoards(ArrayList<Board> boards) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < boards.size(); i++) {
			Board b = boards.get(i);
			result.add(EvaluateAllPieces(b.getTurn(), b.getNotTurn(), b));
		}
		return result;
	}
	public static void printBoard(Board b)
	{
		System.out.println("-----------------");
		for (int i = 0; i < 5 ; i++) {
			for (int j = 0; j < 5; j++) {
				if(b.display()[i][j].length()>1)
				System.out.print(b.display()[i][j] + " ");
				else
				System.out.print(b.display()[i][j] + "   ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}
	public static int alphabeta (Board b , int d ,int alpha , int beta , boolean maximize )
	{
		//------------------ Currunt Evaluation --------------------
		int result =0 ;
		if(maximize)
			EvaluateAllPieces(b.getPlayer1().getT1(), b.getPlayer1().getT2(), b.getPlayer2().getT1(), b.getPlayer2().getT2(), b);
		else
			EvaluateAllPieces(b.getPlayer2().getT1(), b.getPlayer2().getT2(), b.getPlayer1().getT1(), b.getPlayer1().getT2(), b);
		//---------------------- base case --------------------------
		if(d == 0)
			return result;
		
		//--------------------- recursive case ----------------------
		if(maximize)//case 1 maximize
		{
			ArrayList<Board> branches = generateAllBoards(b, b.getPlayer2());//AI is player 2
			ArrayList<Integer> results  = new ArrayList<Integer>();
			results.add(alpha);
			for (int i = 0; i < branches.size(); i++) {
				results.add(alphabeta(branches.get(i), d -1 , alpha, beta, false));
				alpha = Collections.max(results);
				if(beta <= alpha)
				break;
			}
			return alpha;
		}
		else
		{
			ArrayList<Board> branches = generateAllBoards(b, b.getPlayer1());//AI is player 2
			ArrayList<Integer> results  = new ArrayList<Integer>();
			results.add(beta);
			for (int i = 0; i < branches.size(); i++) {
				results.add(alphabeta(branches.get(i), d -1 , alpha, beta, true));
				alpha = Collections.min(results);
				if(beta <= alpha)
				break;
			}
			return beta;
		}
	}
	public static ArrayList<Board> generateAllBoards(Board b , Player P)
	{
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
	public static ArrayList<Board> generateBoardsPerPiece(Board b ,Piece p1)
	{
		ArrayList<Board> possibleBoards = new ArrayList<Board>();
		ArrayList<Location> moves = p1.possibleMoves();
		ArrayList<Location> placements ;
		for (int i = 0; i < moves.size(); i++) 
		{
			try 
			{
				Board tempBoard  = new Board(b);
				//printBoard(tempBoard);
				//printBoard(b);
				Piece p = getEqualPiece(p1, tempBoard);
				tempBoard.fakemove(p, moves.get(i));
				//printBoard(tempBoard);
				//System.out.println("*****************************************");
				placements = p.possiblePlacements();
				for (int j = 0; j < placements.size(); j++) 
				{	
					try
					{
						Board tempBoard2 = new Board(tempBoard);
						//printBoard(tempBoard2);System.out.println("^^^^^^^^^^");
						tempBoard2.fakeplace(getEqualPiece(p, tempBoard2), placements.get(j));
						possibleBoards.add(tempBoard2);
						//printBoard(tempBoard2);
						/*int x =EvaluateAllPieces(tempBoard.getPlayer1(), tempBoard.getPlayer2(), tempBoard2);
						System.out.println(x);
						printBoard(tempBoard2);*/
					}
					catch(Exception e)
					{
					}
				}
			}
			catch(Exception e)
			{
				
			}		
		}
		return possibleBoards;
	}
	private static Piece getEqualPiece(Piece p1, Board b) {
		if(b.getPlayer1().getT1().getLocation().equals(p1.getLocation()))
			return b.getPlayer1().getT1();
		if(b.getPlayer1().getT2().getLocation().equals(p1.getLocation()))
			return b.getPlayer1().getT2();
		if(b.getPlayer2().getT1().getLocation().equals(p1.getLocation()))
			return b.getPlayer2().getT1();		
		if(b.getPlayer2().getT2().getLocation().equals(p1.getLocation()))
			return b.getPlayer2().getT2();
		
		return null;
			
	}
	public static int EvaluateAllPieces(Player maxPlayer , Player minPlayer , Board b)
	{
		int result = 0;
		Location loc ; 
		loc = maxPlayer.getT1().getLocation();
		result += EvaluatePiece(2, loc, b);//if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = maxPlayer.getT2().getLocation();
		result += EvaluatePiece(2, loc, b);//if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = minPlayer.getT1().getLocation();
		result -= EvaluatePiece(2, loc, b);//if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		loc = minPlayer.getT2().getLocation();
		result -= EvaluatePiece(2, loc, b);//if(debugMode)System.out.println(EvaluatePiece(2, loc, b));
		//System.out.println("2 " + result );
		return result ;
	}
	public static int EvaluateAllPieces(Piece p11 , Piece p12 , Piece p21 , Piece p22 ,Board b)
	{
		int result = 0;
		Location loc ; 
		int level =  Integer.parseInt(b.display()[p11.getLocation().getY()][ p11.getLocation().getX()].charAt(0)+"");
		//########### fix
		loc = p11.getLocation();
		result += EvaluatePiece(2, loc, b);
		loc = p12.getLocation();
		result += EvaluatePiece(2, loc, b);
		
		loc = p21.getLocation();
		result -= EvaluatePiece(2, loc, b);
		loc = p22.getLocation();
		result -= EvaluatePiece(2, loc, b);
		return result ;
	}
	public static int EvaluatePiece(int n , Location l ,Board b)
	{
		int result  = 0;
		//if(debugMode)System.out.print(result + "#"+n+"# ");
		if(n == 1)
		return evaluateSurroundings(l, b); ;
		
		Piece p = new Cube();
		p.setLocation(new Location(l));
		ArrayList<Location> temp = p.possiblePlacements();
		for (int k = 0; k < temp.size(); k++) 
		{
			Location loc = temp.get(k);
			if(canPlace(loc, b))
			{
				int levl =  Integer.parseInt((b.display())[loc.getY()][ loc.getX()].charAt(0)+"");
				result += (levl*levl*20);
				Board tempB = new Board(b);
				int temp1 = EvaluatePiece(n-1, loc, b);
				//if(debugMode)System.out.print(temp1 + "*"+n+"* ");
				result += temp1 ;
			}
		}
		return result;	
	}	
	public static int evaluateSurroundings(Location l, Board b)	
	{
		String[][] s = b.display();
		int result = 0;
		//int level = getlevel(s[j][i]);
		Piece p = new Cube();
		p.setLocation(new Location(l));
		ArrayList<Location> temp = p.possiblePlacements();
		
		for (int k = 0; k < temp.size(); k++) 
		{
			Location loc = temp.get(k);
			int boardlevel = getlevel(s[loc.getX()][loc.getY()]);
			int level = getlevel(s[p.getLocation().getX()][p.getLocation().getY()]);
			if(s[loc.getX()][loc.getY()].length()==1)
			switch(boardlevel - level)
			{
				case 3 : result += 5 ; break;
				case 2 : result += 10 ; break;
				case 1 : result += 40 ;/*System.out.print("#")*/; break;
				case 0 : result += 10 ; break;
				case-1 : result += 5 ; break;
				case-2 : result += 2 ; break;
				case-3 : result += 1 ; break;
			}
		}
		//System.out.print(result + ""+  l + " + " );
		return result;
	}
	
	public static int getlevel(String s)
	{
		return Integer.parseInt(s.charAt(0)+"");
	}
	public static boolean canPlace(Location location,Board BoardArray) 
	{
		if((BoardArray.display())[location.getY()][location.getX()].length()==1
				&& Integer.parseInt(""+(BoardArray.display())[location.getY()][location.getX()].charAt(0))<= 3)
		{
			return true;
		}
		else
		return false;
	}
}