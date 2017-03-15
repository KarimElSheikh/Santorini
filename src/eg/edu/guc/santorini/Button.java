package eg.edu.guc.santorini;

import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.*;


@SuppressWarnings("serial")
public class Button extends JButton {
	
	public Button (ImageIcon ico, String command, ActionListener listener){
		super(ico);
		setActionCommand(command);
		//buttonPanel.add(button1);
		setOpaque(false);
		setFocusPainted(false);
		setBorder(BorderFactory.createEmptyBorder());
		setBackground(new Color(0,0,0,0));
		addActionListener(listener);
		
	}
}
