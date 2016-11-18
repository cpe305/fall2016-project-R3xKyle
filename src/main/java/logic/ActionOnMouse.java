package logic;

import presentation.Box;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;




// Handles mouse clicks and interaction with grid
public class ActionOnMouse implements MouseListener {


  private Sudoku sudoku;

  public ActionOnMouse(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  @Override
  public void mousePressed(MouseEvent click) {
    JPanel mouseCheck = (JPanel)click.getSource();
    Component component = mouseCheck.getComponentAt(click.getPoint());
    int xcomponent;
    int ycomponent;
    if (component instanceof Box) {
      Box box = (Box)component;
      xcomponent = box.getXComponent();
      ycomponent = box.getYComponent();
      
      if (click.getButton() == MouseEvent.BUTTON1 
          && (sudoku.getNumberXY(xcomponent, ycomponent) == 0 
          || box.getForeground().equals(Color.BLUE))) {
        int number = sudoku.getNumberSimple();
        if (number == -1) {
          return;
        }
        sudoku.setNumberXY(xcomponent, ycomponent, number);
        box.setNumber(number, true);
        
      } else if (click.getButton() == MouseEvent.BUTTON3 
          && !box.getForeground().equals(Color.BLACK)) {
        sudoku.setNumberXY(xcomponent, ycomponent, 0);
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