
package presentation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


// Each individual box for the grid which holds a position (xcomponent, ycomponent) 
@SuppressWarnings("serial")
public class Box extends JLabel {


  private int xcomponent;
  private int ycomponent;

  /**
   * A box that holds a number and a position of (xcomponent, ycomponent).
   * @param xcomponent Row position of the box.
   * @param ycomponent Column position of the box.
   */
  public Box(int xcomponent, int ycomponent) {
    super("", CENTER);
    this.xcomponent = xcomponent;
    this.ycomponent = ycomponent;

    setPreferredSize(new Dimension(60, 60));
    setBorder(BorderFactory.createLineBorder(Color.GRAY));
    setFont(new Font(Font.DIALOG, Font.BOLD, 28));
    setOpaque(true);
  }

  /**
   * Sets the number in the box.
   * @param number The number to be placed in the box.
   * @param userInput If true, the number is represented blue for the user input and 
   *     false, the number is represented black for generated input.
   */
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

  /**
   * Get function to return the row position of the box.
   * @return The row position of the box.
   */
  public int getRowComponent() {
    return xcomponent;
  }
  
  public int getColComponent() {
    return ycomponent;
  }
}