package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logic.EasySudoku;
import logic.HardSudoku;
import logic.MediumSudoku;
import logic.Sudoku;

/**
 * Handles the actions when the buttons are clicked.
 * @author KyleRingler
 *
 */
public class ActionOnButton implements ActionListener {
  private Sudoku sudoku;
  int number;
  private String actionPerformed;
  GridButtons gridButtons;
  Grid grid;
  
  public ActionOnButton(GridButtons gridButtons, Grid grid) {
    this.gridButtons = gridButtons;
    this.grid = grid;
  }

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
  
  private void addObservers() {
    sudoku.addObserver(gridButtons);
    sudoku.addObserver(grid);
  }

  public Sudoku getInstance() {
    return sudoku;
  }

}
