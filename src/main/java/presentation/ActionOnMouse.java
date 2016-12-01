package presentation;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import logic.Sudoku;

/**
 * Handles mouse clicks and interaction with panels in grid.
 * @author KyleRingler
 *
 */
public class ActionOnMouse implements MouseListener {


  private ActionOnButton buttonAction;

  /**
   * Sets and saves the instance of the game for which the listener listens.
   */
  public ActionOnMouse(ActionOnButton buttonAction) {
    this.buttonAction = buttonAction;
  }

  @Override
  public void mousePressed(MouseEvent click) {
    Sudoku sudoku = buttonAction.getInstance();
    JPanel mouseCheck = (JPanel)click.getSource();
    Component component = mouseCheck.getComponentAt(click.getPoint());
    int xcomponent;
    int ycomponent;
    if (component instanceof Box) {
      Box box = (Box)component;
      xcomponent = box.getRowComponent();
      ycomponent = box.getColComponent();
      
      if (click.getButton() == MouseEvent.BUTTON1 
          && (sudoku.getNumberRowCol(xcomponent, ycomponent) == 0 
          || box.getForeground().equals(Color.BLUE))) {
        int number = sudoku.getNumberSimple();
        if (number == -1) {
          return;
        }
        sudoku.setNumberRowCol(xcomponent, ycomponent, number);
        box.setNumber(number, true);
        
      } else if (click.getButton() == MouseEvent.BUTTON3 
          && !box.getForeground().equals(Color.BLACK)) {
        sudoku.setNumberRowCol(xcomponent, ycomponent, 0);
        box.setNumber(0, false);
      }
      
      
    }
    
  }

  @Override
  public void mouseClicked(MouseEvent event) {
    // Do nothing because only care about mousePressed in this project
  }


  @Override
  public void mouseReleased(MouseEvent event) {
    // Do nothing because only care about mousePressed in this project

  }

  @Override
  public void mouseEntered(MouseEvent event) {
    // Do nothing because only care about mousePressed in this project

  } 
  
  @Override
  public void mouseExited(MouseEvent event) {
    // Do nothing because only care about mousePressed in this project

  }
  
}