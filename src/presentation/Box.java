
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Box extends JLabel {


	private int x;
	private int y;
	
	public Box(int x, int y) {
		super("", CENTER);
		this.x = x;
		this.y = y;
		
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
		return x;
	}
	public int getYComponent() {
		return y;
	}
}