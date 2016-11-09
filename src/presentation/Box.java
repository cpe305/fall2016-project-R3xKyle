package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Box extends JLabel {

	private int xComponent;
	private int yComponent;
	
	public Box(int xComponent, int yComponent) {
		super("", CENTER);
		this.xComponent = xComponent;
		this.yComponent = yComponent;
		
		setPreferredSize(new Dimension(40, 40)); // New dimension???
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		setFont(new Font(Font.DIALOG, Font.PLAIN, 20));
		setOpaque(true);
	}
	
	//Set the number in the box 
	public void setNumber(int number, boolean userInput) {
		if (userInput == true) {
			setForeground(Color.BLUE);
		}
		else {
			setForeground(Color.BLACK);
		}
		if (number > 0) {
			setText(number + "");
		}
		else {
			setText("");
		}
	}
	
	public int getXComponent() {
		return xComponent;
	}
	public int getYComponent() {
		return yComponent;
	}
}