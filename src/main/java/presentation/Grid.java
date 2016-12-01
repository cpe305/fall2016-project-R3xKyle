
package presentation;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import data.ObserverInfo;
import logic.Sudoku;

/**
 * Controls the Grid - Sudoku - with UI updates on user interaction.
 * @author KyleRingler
 *
 */
@SuppressWarnings("serial")
public class Grid extends JPanel implements Observer {

  private Box[][] boxesHard;
  private JPanel[][] panelsHard;

  private Box[][] boxesMedium;
  private JPanel[][] panelsMedium;

  private Box[][] boxesEasy;
  private JPanel[][] panelsEasy;

  private String currentMode = "hard";

  /**
   * The grid section of the game which initializes all the panels and boxes
   *  for all difficulty levels.
   */
  public Grid() {
    super(new GridLayout(3, 3));

    panelsHard = new JPanel[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        panelsHard[i][j] = new JPanel(new GridLayout(3, 3));
        panelsHard[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panelsHard[i][j].setVisible(true);
        add(panelsHard[i][j]);
      }
    }

    boxesHard = new Box[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        boxesHard[i][j] = new Box(j, i);
        panelsHard[i / 3][j / 3].add(boxesHard[i][j]);
      }
    }

    panelsEasy = new JPanel[2][2];
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        panelsEasy[i][j] = new JPanel(new GridLayout(2,2));
        panelsEasy[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panelsEasy[i][j].setVisible(true);
      }
    }

    boxesEasy = new Box[4][4];
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        boxesEasy[i][j] = new Box(j, i);
        panelsEasy[i / 2][j / 2].add(boxesEasy[i][j]);
      }
    }

    panelsMedium = new JPanel[3][2];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        panelsMedium[i][j] = new JPanel(new GridLayout(2,3));
        panelsMedium[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        panelsMedium[i][j].setVisible(true);
      }
    }

    boxesMedium = new Box[6][6];
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        boxesMedium[i][j] = new Box(j, i);
        panelsMedium[i / 2][j / 3].add(boxesMedium[i][j]);
      }
    }
  }

  @Override
  // For the observer pattern
  public void update(Observable observable, Object arg) {

    switch ((ObserverInfo)arg) {
      case NEW_EASY_GAME:
        setEasyGame((Sudoku)observable);
        currentMode = "Easy";
        break;
      case NEW_MEDIUM_GAME:
        setMediumGame((Sudoku)observable);
        currentMode = "Medium";
        break;
      case NEW_HARD_GAME:
        setHardGame((Sudoku)observable);
        currentMode = "Hard";
        break;
      case CHECK:
        setGameCheck((Sudoku)observable);
        break;
      case COMPLETE: // fill this in and stop time
        int failureCount = setGameCheck((Sudoku)observable);
        if (failureCount == 0) {
          ((Sudoku)observable).timerStopUpdate();
        }
        System.out.println("Failure count is " + failureCount);
        break;
      default:
        break;
    }

  }

  /**
   * Sets up the panels and boxes for the easy game.
   * @param sudoku The instance of the game to act upon.
   */
  public void setEasyGame(Sudoku sudoku) {

    removeMedium();
    removeHard();

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        add(panelsEasy[i][j]);
      }
    }
    validate();
    repaint();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        boxesEasy[i][j].setBackground(Color.WHITE);
        boxesEasy[i][j].setNumber(sudoku.getNumberRowCol(j, i), false);
      }
    }

  }

  /**
   * Sets up the panels and boxes for the medium game.
   * @param sudoku The instance of the game to act upon.
   */
  public void setMediumGame(Sudoku sudoku) {

    removeEasy();
    removeHard();

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        add(panelsMedium[i][j]);
      }
    }

    validate();
    repaint();

    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        boxesMedium[i][j].setBackground(Color.WHITE);
        boxesMedium[i][j].setNumber(sudoku.getNumberRowCol(j, i), false);
      }
    }
  }

  /**
   * Sets up the panels and boxes for the hard game.
   * @param sudoku The instance of the game to act upon.
   */
  public void setHardGame(Sudoku sudoku) {
    System.out.println("Setting hard game");
    removeEasy();
    removeMedium();
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        add(panelsHard[i][j]);
      }
    }

    validate();
    repaint();

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        boxesHard[i][j].setBackground(Color.WHITE);
        boxesHard[i][j].setNumber(sudoku.getNumberRowCol(j, i), false);
      }
    }
  }

  /**
   * Checks all of the boxes in the game for validity and fills
   *  the background in green if valid and red if not.
   * @param observable The instance of the game to act upon.
   * @return Returns the amount of failures of validity in the game.
   */
  private int setGameCheck(Sudoku observable) {
    int failureCount = 0;
    if ("Easy".equals(currentMode)) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
          boxesEasy[i][j].setBackground(Color.WHITE);
          if ("".equals(boxesEasy[i][j].getText())) {
            failureCount++;
          }
          if (boxesEasy[i][j].getForeground().equals(Color.BLUE)) {
            if (!("".equals(boxesEasy[i][j].getText()))) {
              if (observable.checkSimple(j, i)) {
                boxesEasy[i][j].setBackground(Color.GREEN);
              } else {
                boxesEasy[i][j].setBackground(Color.RED);
                failureCount++;
              }
            }
          }
        } 
      }
    } else if ("Medium".equals(currentMode)) {
      for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 6; j++) {
          boxesMedium[i][j].setBackground(Color.WHITE);
          if ("".equals(boxesMedium[i][j].getText())) {
            failureCount++;
          }
          if (boxesMedium[i][j].getForeground().equals(Color.BLUE)) {
            if (!("".equals(boxesMedium[i][j].getText()))) {
              if (observable.checkSimple(j, i)) {
                boxesMedium[i][j].setBackground(Color.GREEN);
              } else {
                boxesMedium[i][j].setBackground(Color.RED);
                failureCount++;
              }
            }
          }
        }
      }
    } else  {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          boxesHard[i][j].setBackground(Color.WHITE);
          if ("".equals(boxesHard[i][j].getText())) {
            failureCount++;
          }
          if (boxesHard[i][j].getForeground().equals(Color.BLUE)) {
            if (!("".equals(boxesHard[i][j].getText()))) {
              if (observable.checkSimple(j, i)) {
                boxesHard[i][j].setBackground(Color.GREEN);
              } else {
                boxesHard[i][j].setBackground(Color.RED);
                failureCount++;
              }
            }
          }
        }
      }
    }
    return failureCount;
  }

  /**
   * Sets up all the listeners in the panels for mouse actions.
   * @param mouseAction The listener for mouse actions.
   */
  public void mouseSetup(ActionOnMouse mouseAction) {

    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        panelsEasy[i][j].addMouseListener(mouseAction);
      }
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        panelsMedium[i][j].addMouseListener(mouseAction);
      }
    }
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        panelsHard[i][j].addMouseListener(mouseAction);
      }
    }

  }

  /**
   * Removes all the easy panels on the grid.
   */
  public void removeEasy() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        remove(panelsEasy[i][j]);
      }
    }
  }

  /**
   * Removes all the medium panels on the grid.
   */
  public void removeMedium() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 2; j++) {
        remove(panelsMedium[i][j]);
      }
    }
  }

  /**
   * Removes all the hard panels on the grid.
   */
  public void removeHard() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        remove(panelsHard[i][j]);
      }
    }
  }

}