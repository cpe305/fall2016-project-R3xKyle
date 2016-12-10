package presentation;

import logic.EasySudoku;
import logic.HardSudoku;
import logic.MediumSudoku;
import logic.Sudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Handles the actions when the buttons are clicked.
 * @author KyleRingler
 *
 */
public class ActionOnButton implements ActionListener {
  private Sudoku sudoku = null;
  int number;
  private String actionPerformed;
  GridButtons gridButtons;
  Grid grid;
  
  /**
   * Takes in the instance of GridButtons and Grid to hold to add observers to
   * a new sudoku instance.
   * @param gridButtons The instance of GridButtons to pass to add as an observer for sudoku.
   * @param grid The instance of Grid to add as an observer for sudoku.
   */
  public ActionOnButton(GridButtons gridButtons, Grid grid) {
    this.gridButtons = gridButtons;
    this.grid = grid;
  }

  /**
   * Determines what method to execute when buttoms are pressed. Allows sudoku to add observers.
   */
  @Override
  public void actionPerformed(ActionEvent buttonPress) {
    setActionPerformed(buttonPress.getActionCommand());
    if ("New Easy Game".equals(buttonPress.getActionCommand())) {
      sudoku = new EasySudoku();
      addObservers();
      sudoku.notifyNewGame();
    } else if ("New Medium Game".equals(buttonPress.getActionCommand())) {
      sudoku = new MediumSudoku();
      addObservers();
      sudoku.notifyNewGame();
    } else if ("New Hard Game".equals(buttonPress.getActionCommand())) {
      sudoku = new HardSudoku();
      addObservers();
      sudoku.notifyNewGame();
    } else if ("Check Game".equals(buttonPress.getActionCommand())) {
      if (sudoku != null) {
        sudoku.checkSudoku();
      }
    } else if ("Complete".equals(buttonPress.getActionCommand())) {
      if (sudoku != null) {
        sudoku.completeGameCheck();
      }
    } else {
      number = Integer.parseInt(buttonPress.getActionCommand());
      sudoku.setNumberSimple(number);
    }
  }
  
  /**
   * Set the string of the button that was pressed.
   * @param string The string associated with the button.
   */
  public void setActionPerformed(String string) {
    actionPerformed = string;
  }

  /**
   * Returns the string of the button that was most recently pressed.
   * @return The string associated with the button that was most recently pressed.
   */
  public String returnActionPerformed() {
    return actionPerformed;
  }
  
  /**
   * Calls the addObserver() method for the sudoku instance.
   */
  private void addObservers() {
    sudoku.addObserver(gridButtons);
    sudoku.addObserver(grid);
  }

  /**
   * Returns the instance of sudoku.
   * @return The current instance of Sudoku.
   */
  public Sudoku getInstance() {
    return sudoku;
  }

}
