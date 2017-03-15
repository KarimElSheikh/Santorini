package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.tiles.Cube;

public class AiPlayer {
	private String name;
	private Piece t1;
	private Piece t2;
	
	public AiPlayer(String name, int tileType) {
		this.name = name;
		if (tileType == 1) {
			t1 = new Cube();
			t2 = new Cube();
		} else {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}
	}

	public void setT1(Piece t1) {
		this.t1 = t1;
	}

	public Piece getT1() {
		return t1;
	}

	public void setT2(Piece t2) {
		this.t2 = t2;
	}

	public Piece getT2() {
		return t2;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
