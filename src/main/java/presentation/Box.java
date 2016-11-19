
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


// Each individual box for the grid which holds a position (x, y) 
public class Box extends JLabel {


  private int x;
  private int y;

  public Box(int x, int y) {
    super("", CENTER);
    this.x = x;
    this.y = y;

    setPreferredSize(new Dimension(60, 60));
    setBorder(BorderFactory.createLineBorder(Color.GRAY));
    setFont(new Font(Font.DIALOG, Font.BOLD, 28));
    setOpaque(true);
  }

  //Set the number in the box 
  public void setNumber(int number, boolean userInput) {
    if (userInput == true) {
      setForeground(Color.BLUE);
    } else {
      setForeground(Color.BLACK);
    }
    if (number > 0) {
      setText(Integer.toString(number));
    } else {
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