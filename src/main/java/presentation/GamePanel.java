package presentation;

import logic.ActionOnButton;
import logic.ActionOnMouse;
import logic.Sudoku;

import java.awt.BorderLayout;
import javax.swing.JFrame;



// includes main method - where everything starts
/**
 * .
 * @author Kyle
 *
 */

public class GamePanel extends JFrame {
  
  /**
   * .
   */
  
  public GamePanel() {
    super("My Sudoku");

    getContentPane().setLayout(new BorderLayout());

    Sudoku sudoku = new Sudoku(9);

    ActionOnButton buttonAction = new ActionOnButton(sudoku);
    GridButtons sudokuPanelButtons = new GridButtons();
    sudokuPanelButtons.buttonSetup(buttonAction);
    add(sudokuPanelButtons, BorderLayout.EAST);

    Grid grid = new Grid();
    ActionOnMouse mouseAction = new ActionOnMouse(sudoku);
    grid.setHardGame(sudoku); 
    grid.mouseSetup(mouseAction);
    add(grid, BorderLayout.CENTER);

    sudoku.addObserver(sudokuPanelButtons);
    sudoku.addObserver(grid);

    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);


  }
  /**
   * .
   * @param args The command line arguments.
   */
  
  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        GamePanel frame = new GamePanel();
        frame.setVisible(true);
      }
    });
  }


}