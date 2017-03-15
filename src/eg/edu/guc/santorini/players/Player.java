package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.tiles.Cube;

public class Player {
	private String name;
	private Piece t1;
	private Piece t2;
	private int tiletype;
	
	public Player(String name, int tileType) {
		this.name = name;
		this.tiletype = tileType;
		if (tileType == 1) {
			t1 = new Cube();
			t2 = new Cube();
		} else {
			t1 = new Pyramid();
			t2 = new Pyramid();
		}
	}
	public Player(Player p) {
		this.name = p.getName();
		tiletype = p.getTiletype();
		if (tiletype == 1) {
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
	public int getTiletype() {
		return tiletype;
	}
	public void setTiletype(int tiletype) {
		this.tiletype = tiletype;
	}
}
