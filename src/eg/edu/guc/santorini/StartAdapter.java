package eg.edu.guc.santorini;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.JButton;

public class StartAdapter implements ActionListener, MouseListener {
	private Window myWindow;
	private JToggleButton p1Cube;
	private JToggleButton p1Pyramid;
	private JToggleButton p2Cube;
	private JToggleButton p2Pyramid;
	public int player1Mode = 0;
	public int player2Mode = 0;
	public String[] modeArrays ;
	public StartAdapter(Window w) {
		p1Cube = w.getP1Cube();
		p1Pyramid = w.getP1Pyramid();
		p2Cube = w.getP2Cube();
		p2Pyramid = w.getP2Pyramid();
		myWindow = w;
		modeArrays = new String[4];
		modeArrays[0] = "Human Player";
		modeArrays[1] = "AI Player level 1";
		modeArrays[2] = "AI Player level 2";
		modeArrays[3] = "AI Player level 3";
	}
	public void actionPerformed(ActionEvent e) {
		if(((JButton)e.getSource()).getActionCommand() == "p1")
		{	
			if(player1Mode == 3)player1Mode = 0;
			((JButton)e.getSource()).setLabel(modeArrays[++player1Mode]);
		}
		if(((JButton)e.getSource()).getActionCommand() == "p2")
		{
			if(player2Mode == 3)player2Mode = 0;
			((JButton)e.getSource()).setLabel(modeArrays[++player2Mode]);
		}
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent e) {
		Object o = e.getSource();
		if(o instanceof JButton)
		{
			if(((JButton)e.getSource()).getActionCommand() == "p1")
			{	
				if(player1Mode == 3)player1Mode = -1;
				((JButton)e.getSource()).setLabel(modeArrays[++player1Mode]);
			}
			if(((JButton)e.getSource()).getActionCommand() == "p2")
			{
				if(player2Mode == 3)player2Mode = -1;
				((JButton)e.getSource()).setLabel(modeArrays[++player2Mode]);
			}
		}
		if (o instanceof JButton && ((JButton)e.getSource()).getActionCommand() == "play") {
			if (p1Cube.isSelected()) {
				if (p2Cube.isSelected() || p2Pyramid.isSelected()) {
					myWindow.startPlay(5, 5,player1Mode,player2Mode);
				}
				else {
					JOptionPane.showMessageDialog(null, "You must select the Piece types");
				}
			}
			else if (p1Pyramid.isSelected()) {
				if (p2Cube.isSelected() || p2Pyramid.isSelected()) {
					myWindow.startPlay(5, 5,player1Mode,player2Mode);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "You must select the Piece types");
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "You must select the Piece types");
			}
		} else {
			if (o == p1Cube) {
				if (p1Pyramid.isSelected()) {
					p1Pyramid.doClick();
				}
				myWindow.setPieceType(1, 1);
			} else if (o == p1Pyramid) {
				if (p1Cube.isSelected()) {
						p1Cube.doClick(); }
					myWindow.setPieceType(1, 2);
			} else if (o == p2Cube) {
				if (p2Pyramid.isSelected()) {
					p2Pyramid.doClick(); }
				myWindow.setPieceType(2, 1);
			} else {
				if (p2Cube.isSelected()) {
					p2Cube.doClick(); }
				myWindow.setPieceType(2, 2);
			}
		}
	}

	public void unDo(JToggleButton z) {
		z.doClick();
	}

	public void mouseReleased(MouseEvent arg0) {

	}
}