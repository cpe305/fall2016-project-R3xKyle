package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Handles the actions when the buttons are clicked.
 * @author KyleRingler
 *
 */
public class ActionOnButton implements ActionListener {
  private Sudoku sudoku;
  int number;
  private String actionPerformed;

  /**
   * Sets and saves the instance of the game for which the listener listens.
   * @param sudoku Instance of the game to act upon.
   */
  public ActionOnButton(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  @Override
  public void actionPerformed(ActionEvent buttonPress) {
    setActionPerformed(buttonPress.getActionCommand());
    if ("New Easy Game".equals(buttonPress.getActionCommand())) {
      sudoku.newEasySudoku();
    } else if ("New Medium Game".equals(buttonPress.getActionCommand())) {
      sudoku.newMediumSudoku(); 
    } else if ("New Hard Game".equals(buttonPress.getActionCommand())) {
      sudoku.newHardSudoku();
    } else if ("Check Game".equals(buttonPress.getActionCommand())) {
      sudoku.checkSudoku();
    } else if ("Complete".equals(buttonPress.getActionCommand())) {
      sudoku.completeGameCheck();
    } else {
      number = Integer.parseInt(buttonPress.getActionCommand());
      sudoku.setNumberSimple(number);
    }
  }
  
  public void setActionPerformed(String string) {
    actionPerformed = string;
  }

  public String returnActionPerformed() {
    return actionPerformed;
  }


}
