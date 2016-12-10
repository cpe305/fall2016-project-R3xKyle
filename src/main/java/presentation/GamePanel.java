package presentation;

import data.Highscores;

import java.awt.BorderLayout;
import javax.swing.JFrame;



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
  
  /**
   * Returns the row location of New Easy Game button on user's screen.
   * @return Row location of New Easy Game button on screen.
   */
  public int getLocEasyRow() {
    return (int)sudokuPanelButtons.panelLocOnScreenRowEasy();
  }
  
  /**
   * Returns the column location of New Easy Game button on user's screen.
   * @return Column location of New Easy Game button on screen.
   */
  public int getLocEasyCol() {
    return (int)sudokuPanelButtons.panelLocOnScreenColEasy();
  }
  
  /**
   * Returns the row location of New Medium Game button on user's screen.
   * @return Row location of New Medium Game button on screen.
   */
  public int getLocMediumRow() {
    return (int)sudokuPanelButtons.panelLocOnScreenRowMedium();
  }
  
  /**
   * Returns the column location of New Medium Game button on user's screen.
   * @return Column location of New Medium Game button on screen.
   */
  public int getLocMediumCol() {
    return (int)sudokuPanelButtons.panelLocOnScreenColMedium();
  }
  
  /**
   * Returns the row location of New Hard Game button on user's screen.
   * @return Row location of New Hard Game button on screen.
   */
  public int getLocHardRow() {
    return (int)sudokuPanelButtons.panelLocOnScreenRowHard();
  }
  
  /**
   * Returns the column location of New Hard Game button on user's screen.
   * @return Column location of New Hard Game button on screen.
   */
  public int getLocHardCol() {
    return (int)sudokuPanelButtons.panelLocOnScreenColHard();
  }
  
  /**
   * Returns the row location of the Complete button on user's screen.
   * @return Row location of Complete button on screen.
   */
  public int getLocCompleteRow() {
    return (int)sudokuPanelButtons.panelLocOnScreenRowComplete();
  }
  
  /**
   * Returns the column location of the Complete button on user's screen.
   * @return Column location of Complete button on screen.
   */
  public int getLocCompleteCol() {
    return (int)sudokuPanelButtons.panelLocOnScreenColComplete();
  }
  
  /**
   * Returns the row location of the Check Game button on user's screen.
   * @return Row location of Check Game button on screen.
   */
  public int getLocCheckRow() {
    return (int)sudokuPanelButtons.panelLocOnScreenRowCheck();
  }
  
  /**
   * Returns the column location of the Check Game button on user's screen.
   * @return Column location of Check Game button on screen.
   */
  public int getLocCheckCol() {
    return (int)sudokuPanelButtons.panelLocOnScreenColCheck();
  }
  
  /**
   * Returns the string associated with the most recent button pressed.
   * @return String associated with the most recent button pressed.
   */
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
