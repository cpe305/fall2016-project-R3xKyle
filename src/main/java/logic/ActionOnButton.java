package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// handles the actions on buttons clicked
public class ActionOnButton implements ActionListener {
  private Sudoku sudoku;
  int number;

  public ActionOnButton(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  @Override
  public void actionPerformed(ActionEvent buttonPress) {
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



}
