package eg.edu.guc.santorini;


import java.awt.*;

import javax.swing.*;

import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Tile[][] labels;
	private JPanel gridPanel;
	private JPanel buttonPanel;
	private Adapter myAdapter;
	private StartAdapter startAdapter;
	private int p1Type = 1;
	private int p2Type = 1;
	private JTextField jt;
	private JButton info;
	private JTextField jt2;
	private JToggleButton p1Cube;
	private JToggleButton p1Pyramid;
	private JToggleButton p2Cube;
	private JToggleButton p2Pyramid;
	private JButton playNow;
	private JButton player1Type;
	private JButton player2Type;
	
	public Window(int rows, int columns)
	{
		buttonPanel = new JPanel(null);
		buttonPanel.setBackground(Color.ORANGE);
		
		JLabel x = new JLabel(new ImageIcon(getClass().getResource(GlobalVars.getTitle())));
		buttonPanel.add(x);
		Dimension size = x.getPreferredSize();
		x.setBounds(300, 25, size.width, size.height);
		
		JLabel choosePiece = new JLabel(new ImageIcon(getClass().getResource(GlobalVars.getChoosePiece())));
		buttonPanel.add(choosePiece);
		size = choosePiece.getPreferredSize();
		choosePiece.setBounds(50, 150, size.width, size.height);
		
		JLabel chooseName = new JLabel(new ImageIcon(getClass().getResource(GlobalVars.getChooseName())));
		buttonPanel.add(chooseName);
		size = chooseName.getPreferredSize();
		chooseName.setBounds(550, 150, size.width, size.height);
		
		JLabel player1 = new JLabel(new ImageIcon(getClass().getResource(GlobalVars.getPlayer1())));
		buttonPanel.add(player1);
		size = player1.getPreferredSize();
		player1.setBounds(50, 250, size.width, size.height);
		JLabel player2 = new JLabel(new ImageIcon(getClass().getResource(GlobalVars.getPlayer2())));
		buttonPanel.add(player2);
		size = player2.getPreferredSize();
		player2.setBounds(50, 350, size.width, size.height);
		
		addButtonsAndTextFields();
		
		add(buttonPanel);
		setTitle("Santorini");
		setLocation(0,0);
        setSize(1000, 700);
		setVisible(true);
		startAdapter = new StartAdapter(this);
		p1Cube.addMouseListener(startAdapter);
		p2Cube.addMouseListener(startAdapter);
		p1Pyramid.addMouseListener(startAdapter);
		p2Pyramid.addMouseListener(startAdapter);
		playNow.addMouseListener(startAdapter);
		player1Type.addMouseListener(startAdapter);
		player2Type.addMouseListener(startAdapter);
		
		WindowDestroyer wd = new WindowDestroyer();
		addWindowListener(wd);
	}
	
	public void addButtonsAndTextFields() {		
		p1Cube = new JToggleButton("Cube");
		Dimension size = p1Cube.getPreferredSize();
		p1Cube.setBounds(400, 265, size.width, size.height);
		buttonPanel.add(p1Cube);
		
		p1Pyramid = new JToggleButton("Pyramid");
		size = p1Pyramid.getPreferredSize();
		p1Pyramid.setBounds(460, 265, size.width, size.height);
		buttonPanel.add(p1Pyramid);
		
		p2Cube = new JToggleButton("Cube");
		size = p2Cube.getPreferredSize();
		p2Cube.setBounds(400, 365, size.width, size.height);
		buttonPanel.add(p2Cube);
		
		p2Pyramid = new JToggleButton("Pyramid");
		size = p2Pyramid.getPreferredSize();
		p2Pyramid.setBounds(460, 365, size.width, size.height);
		buttonPanel.add(p2Pyramid);
		
		player1Type = new JButton("Human Player      ");
		player1Type.setActionCommand("p1");
		buttonPanel.add(player1Type);
		size = player1Type.getPreferredSize();
		player1Type.setBounds(250, 265, size.width, size.height);
		
		player2Type = new JButton("Human Player      ");
		player2Type.setActionCommand("p2");
		buttonPanel.add(player2Type);
		size = player2Type.getPreferredSize();
		player2Type.setBounds(250, 365, size.width, size.height);
		
		jt = new JTextField();
		jt.setSize(250, 32);
		jt.setBounds(600, 260, 250, 32);
		buttonPanel.add(jt);
		
		jt2 = new JTextField();
		jt2.setSize(250, 32);
		jt2.setBounds(600, 360, 250, 32);
		buttonPanel.add(jt2);
		
		playNow = new JButton(new ImageIcon(getClass().getResource(GlobalVars.getPlayNow())));
		size = playNow.getPreferredSize();
		playNow.setActionCommand("play");
		playNow.setBounds(350, 450, size.width, size.height);
		playNow.setBackground(Color.orange);
		playNow.setOpaque(true);
		playNow.setBorder(null);
		buttonPanel.add(playNow);
	}
	
	public void setPieceType(int player, int type) {
		if (player == 1) {
			p1Type = type;
		}
		else {
			p2Type = type;
		}
	}
	public void startPlay(int rows, int columns , int plMode , int p2Mode)
	{
		buttonPanel.setVisible(false);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		info = new JButton("Player 1: " + jt.getText() + "    Player 2: "
				+ jt2.getText() + "                    Player 1's turn to move");
		info.setBorder(BorderFactory.createLineBorder(Color.white));
		add(info, BorderLayout.NORTH);
		labels = new Tile[rows][columns];
		System.out.println();
		
		myAdapter = new Adapter(jt.getText(), jt2.getText() , this , plMode , p2Mode);
		
		initPanels(rows, columns);
		initGridTiles();
	}
	public JTextField getJt() {
		return jt;
	}

	public JTextField getJt2() {
		return jt2;
	}

	public void initPanels(int row, int column)
	{
		//Create Panel for the tiles grid
		gridPanel = new JPanel(new GridLayout(row, column));
		gridPanel.setBackground(Color.orange);
		add(gridPanel, BorderLayout.CENTER);
	}
	
	public void initGridTiles()
	{
		String icon = GlobalVars.getTile0();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) 
			{
				labels[i][j] = new Tile(icon, new Location(i, j));
				if (i == 0 && j == 0) {
					if (p1Type == 1) {
				labels[i][j] = new Tile(GlobalVars.getTile0player1Cube(), new Location(i, j)); }
					else {
				labels[i][j] = new Tile(GlobalVars.getTile0player1Pyramid(), new Location(i, j));
					}
				}
					
				if (i == 4 && j == 1) {
					if (p1Type == 1) {
				labels[i][j] = new Tile(GlobalVars.getTile0player1Cube(), new Location(i, j)); }
					else {
				labels[i][j] = new Tile(GlobalVars.getTile0player1Pyramid(), new Location(i, j));
						}
				}
				if (i == 0 && j == 3) {
					if (p2Type == 1) {
				labels[i][j] = new Tile(GlobalVars.getTile0player2Cube(), new Location(i, j)); }
					else {
				labels[i][j] = new Tile(GlobalVars.getTile0player2Pyramid(), new Location(i, j));
					}
				}
				if (i == 4 && j == 4) {
					if (p2Type == 1) {
				labels[i][j] = new Tile(GlobalVars.getTile0player2Cube(), new Location(i, j)); }
					else {
				labels[i][j] = new Tile(GlobalVars.getTile0player2Pyramid(), new Location(i, j));
					}
				}
				
				labels[i][j].addMouseListener(myAdapter);
				gridPanel.add(labels[i][j]);
			}
		}
	}
	
	public Tile [][] getLabels() {
		return labels;
	}
	
	public void setInfo(String s) {
		info.setText(s);
	}
	
	public int getP1Type() {
		return p1Type;
	}

	public int getP2Type() {
		return p2Type;
	}

	public JToggleButton getP1Cube() {
		return p1Cube;
	}

	public JToggleButton getP1Pyramid() {
		return p1Pyramid;
	}

	public JToggleButton getP2Cube() {
		return p2Cube;
	}

	public JToggleButton getP2Pyramid() {
		return p2Pyramid;
	}

	public JButton getPlayNow() {
		return playNow;
	}

	public static void main(String[] args) {
		Window x = new Window(5, 5);
	}
	
}
