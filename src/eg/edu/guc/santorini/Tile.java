package eg.edu.guc.santorini;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class Tile extends JLabel {

	private int layer;
	private String currentImage;
	private Location location;

	public Tile() {
	}

	public Tile(String ico, Location loc) {
		super(new ImageIcon());
		setIcon(new ImageIcon(getClass().getResource(ico)));
		currentImage = ico;
		layer = 1;
		this.location = new Location(loc.getX(), loc.getY());
	}
	public void addPiece(String p)
	{
		if(currentImage.equals(GlobalVars.getTile0Hint())||currentImage.equals(GlobalVars.getTile0()))
		{
			if(p.equals("C1"))
				currentImage = GlobalVars.getTile0player1Cube();
			if(p.equals("C2"))
				currentImage = GlobalVars.getTile0player2Cube();
			if(p.equals("P1"))
				currentImage = GlobalVars.getTile0player1Pyramid();
			if(p.equals("P2"))
				currentImage = GlobalVars.getTile0player2Pyramid();
		}
		if(currentImage.equals(GlobalVars.getTile1Hint())||currentImage.equals(GlobalVars.getTile1()))
		{
			if(p.equals("C1"))
				currentImage = GlobalVars.getTile1player1Cube();
			if(p.equals("C2"))
				currentImage = GlobalVars.getTile1player2Cube();
			if(p.equals("P1"))
				currentImage = GlobalVars.getTile1player1Pyramid();
			if(p.equals("P2"))
				currentImage = GlobalVars.getTile1player2Pyramid();
		}
		if(currentImage.equals(GlobalVars.getTile2Hint())||currentImage.equals(GlobalVars.getTile2()))
		{
			if(p.equals("C1"))
				currentImage = GlobalVars.getTile2player1Cube();
			if(p.equals("C2"))
				currentImage = GlobalVars.getTile2player2Cube();
			if(p.equals("P1"))
				currentImage = GlobalVars.getTile2player1Pyramid();
			if(p.equals("P2"))
				currentImage = GlobalVars.getTile2player2Pyramid();
		}
		if(currentImage.equals(GlobalVars.getTile3Hint())||currentImage.equals(GlobalVars.getTile3()))
		{
			if(p.equals("C1"))
				currentImage = GlobalVars.getTile3player1Cube();
			if(p.equals("C2"))
				currentImage = GlobalVars.getTile3player2Cube();
			if(p.equals("P1"))
				currentImage = GlobalVars.getTile3player1Pyramid();
			if(p.equals("P2"))
				currentImage = GlobalVars.getTile3player2Pyramid();
		
		}
		
		setIcon(new ImageIcon(getClass().getResource(currentImage)));
	}
	public void addTile(int i)
	{
		switch (i) {
		case 0:currentImage = GlobalVars.getTile0() ;break;
		case 1:currentImage = GlobalVars.getTile1() ;break;
		case 2:currentImage = GlobalVars.getTile2() ;break;
		case 3:currentImage = GlobalVars.getTile3() ;break;
		case 4:currentImage = GlobalVars.getTile4player1Dome() ;break;
		}
		setIcon(new ImageIcon(getClass().getResource(currentImage)));
	}
	public void removePiece() {
		if (currentImage.equals(GlobalVars.getTile0player1Cube())
				|| currentImage.equals(GlobalVars.getTile0player1Pyramid())
				|| currentImage.equals(GlobalVars.getTile0player2Cube())
				|| currentImage.equals(GlobalVars.getTile0player2Pyramid())) {
			currentImage = GlobalVars.getTile0();
		} else if (currentImage.equals(GlobalVars.getTile1player1Cube())
				|| currentImage.equals(GlobalVars.getTile1player1Pyramid())
				|| currentImage.equals(GlobalVars.getTile1player2Cube())
				|| currentImage.equals(GlobalVars.getTile1player2Pyramid())) {
			currentImage = GlobalVars.getTile1();
		} else if (currentImage.equals(GlobalVars.getTile2player1Cube())
				|| currentImage.equals(GlobalVars.getTile2player1Pyramid())
				|| currentImage.equals(GlobalVars.getTile2player2Cube())
				|| currentImage.equals(GlobalVars.getTile2player2Pyramid())) {
			currentImage = GlobalVars.getTile2();
		} else if (currentImage.equals(GlobalVars.getTile3player1Cube())
				|| currentImage.equals(GlobalVars.getTile3player1Pyramid())
				|| currentImage.equals(GlobalVars.getTile3player2Cube())
				|| currentImage.equals(GlobalVars.getTile3player2Pyramid())) {
			currentImage = GlobalVars.getTile3();
		}
		setIcon(new ImageIcon(getClass().getResource(currentImage)));
	}

	public void inceraseLevel(boolean flag) {
		if (currentImage.equals(GlobalVars.getTile0())
				|| currentImage.equals(GlobalVars.getTile0Hint())) {
			currentImage = GlobalVars.getTile1(); }
		else if (currentImage.equals(GlobalVars.getTile1())
				|| currentImage.equals(GlobalVars.getTile1Hint())) {
			currentImage = GlobalVars.getTile2(); }
		else if (currentImage.equals(GlobalVars.getTile2())
				|| currentImage.equals(GlobalVars.getTile2Hint())) {
			currentImage = GlobalVars.getTile3(); }
		else if (currentImage.equals(GlobalVars.getTile3())
				|| currentImage.equals(GlobalVars.getTile3Hint())) {
			if (!flag) {
				currentImage = GlobalVars.getTile4player1Dome(); }
			else {
				currentImage = GlobalVars.getTile4player2Dome();
			}
		}

		if (currentImage.equals(GlobalVars.getTile0player1Cube())) {
			currentImage = GlobalVars.getTile1player1Cube(); }
		else if (currentImage.equals(GlobalVars.getTile1player1Cube())) {
			currentImage = GlobalVars.getTile2player1Cube(); }
		else if (currentImage.equals(GlobalVars.getTile2player1Cube())) {
			currentImage = GlobalVars.getTile3player1Cube(); }

		if (currentImage.equals(GlobalVars.getTile0player1Pyramid())) {
			currentImage = GlobalVars.getTile1player1Pyramid(); }
		else if (currentImage.equals(GlobalVars.getTile1player1Pyramid())) {
			currentImage = GlobalVars.getTile2player1Pyramid(); }
		else if (currentImage.equals(GlobalVars.getTile2player1Pyramid())) {
			currentImage = GlobalVars.getTile3player1Pyramid(); }

		if (currentImage.equals(GlobalVars.getTile0player2Cube())) {
			currentImage = GlobalVars.getTile1player2Cube(); }
		else if (currentImage.equals(GlobalVars.getTile1player2Cube())) {
			currentImage = GlobalVars.getTile2player2Cube(); }
		else if (currentImage.equals(GlobalVars.getTile2player2Cube())) {
			currentImage = GlobalVars.getTile3player2Cube(); }

		if (currentImage.equals(GlobalVars.getTile0player2Pyramid())) {
			currentImage = GlobalVars.getTile1player2Pyramid(); }
		if (currentImage.equals(GlobalVars.getTile1player2Pyramid())) {
			currentImage = GlobalVars.getTile2player2Pyramid(); }
		if (currentImage.equals(GlobalVars.getTile2player2Pyramid())) {
			currentImage = GlobalVars.getTile3player2Pyramid(); }

		setIcon(new ImageIcon(getClass().getResource(currentImage)));
	}

	public void showHints() {
		// System.out.println("-_-");
		if (currentImage.equals(GlobalVars.getTile0())) {
			currentImage = GlobalVars.getTile0Hint();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile1())) {
			currentImage = GlobalVars.getTile1Hint();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile2())) {
			currentImage = GlobalVars.getTile2Hint();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile3())) {
			currentImage = GlobalVars.getTile3Hint();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
	}

	public void removeHints() {
		if (currentImage.equals(GlobalVars.getTile0Hint())) {
			currentImage = GlobalVars.getTile0();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile1Hint())) {
			currentImage = GlobalVars.getTile1();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile2Hint())) {
			currentImage = GlobalVars.getTile2();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
		if (currentImage.equals(GlobalVars.getTile3Hint())) {
			currentImage = GlobalVars.getTile3();
			setIcon(new ImageIcon(getClass().getResource(currentImage)));
		}
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getLayer() {
		return layer;
	}

	public Location getlocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
