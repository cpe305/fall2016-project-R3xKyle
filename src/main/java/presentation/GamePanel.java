package presentation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import data.Highscores;
import logic.EasySudoku;
import logic.HardSudoku;
import logic.MediumSudoku;
import logic.Sudoku;




/**
 * includes main method - where everything starts.
 * @author Kyle
 *
 */

@SuppressWarnings("serial")
public class GamePanel extends JFrame {
  
  private transient ActionOnButton buttonAction;
  GridButtons sudokuPanelButtons;
  
  /**
   * Sets up listeners and observers - also where the program starts.
   */
  public GamePanel() {
    
    super("My Sudoku");
    
    sudokuPanelButtons = new GridButtons();
    Grid grid = new Grid();
    buttonAction = new ActionOnButton(sudokuPanelButtons, grid);
    
    
    this.getContentPane().setLayout(new BorderLayout());
    sudokuPanelButtons.buttonSetup(buttonAction);
    this.add(sudokuPanelButtons, BorderLayout.WEST);

    ActionOnMouse mouseAction = new ActionOnMouse(buttonAction); // problem 
    grid.mouseSetup(mouseAction);
    this.add(grid, BorderLayout.CENTER);
    
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    
    Highscores.createFile();

  }
  
  public int getLocEasyX() {
    return (int)sudokuPanelButtons.panelLocOnScreenXEasy();
  }
  
  public int getLocEasyY() {
    return (int)sudokuPanelButtons.panelLocOnScreenYEasy();
  }
  
  public int getLocMediumX() {
    return (int)sudokuPanelButtons.panelLocOnScreenXMedium();
  }
  
  public int getLocMediumY() {
    return (int)sudokuPanelButtons.panelLocOnScreenYMedium();
  }
  
  public int getLocHardX() {
    return (int)sudokuPanelButtons.panelLocOnScreenXHard();
  }
  
  public int getLocHardY() {
    return (int)sudokuPanelButtons.panelLocOnScreenYHard();
  }
  
  public int getLocCompleteX() {
    return (int)sudokuPanelButtons.panelLocOnScreenXComplete();
  }
  
  public int getLocCompleteY() {
    return (int)sudokuPanelButtons.panelLocOnScreenYComplete();
  }
  
  public int getLocCheckX() {
    return (int)sudokuPanelButtons.panelLocOnScreenXCheck();
  }
  
  public int getLocCheckY() {
    return (int)sudokuPanelButtons.panelLocOnScreenYCheck();
  }
  
  public String getButtonActionCommand() {
    return buttonAction.returnActionPerformed();
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
