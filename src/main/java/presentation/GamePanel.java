package presentation;

import logic.ActionOnButton;
import logic.ActionOnMouse;
import logic.Sudoku;

import java.awt.BorderLayout;

import javax.swing.JFrame;




/**
 * includes main method - where everything starts.
 * @author Kyle
 *
 */

@SuppressWarnings("serial")
public class GamePanel extends JFrame {
  
  /**
   * Sets up listeners and observers - also where the program starts.
   */
  public GamePanel() {
    
    super("My Sudoku");
    
    Sudoku sudoku = new Sudoku(9);
    ActionOnButton buttonAction = new ActionOnButton(sudoku);
    GridButtons sudokuPanelButtons = new GridButtons();
    
    this.getContentPane().setLayout(new BorderLayout());
    sudokuPanelButtons.buttonSetup(buttonAction);
    this.add(sudokuPanelButtons, BorderLayout.WEST);
    
    Grid grid = new Grid();
    ActionOnMouse mouseAction = new ActionOnMouse(sudoku); 
    grid.mouseSetup(mouseAction);
    this.add(grid, BorderLayout.CENTER);
    
    sudoku.addObserver(sudokuPanelButtons);
    sudoku.addObserver(grid);

    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }
  
  /**
   * Starts the program.
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        GamePanel frame = new GamePanel();
        frame.setVisible(true);
      }
    });
  }

}
