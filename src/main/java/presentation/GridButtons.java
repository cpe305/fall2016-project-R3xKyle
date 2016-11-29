package presentation;

import data.Highscores;
import data.ObserverInfo;
import logic.ActionOnButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 * Button class that has all the buttons in game.
 * @author KyleRingler
 *
 */
@SuppressWarnings("serial")
public class GridButtons extends JPanel implements Observer {

  JPanel panel;
  JButton easyGameButton;
  JButton mediumGameButton;
  JButton hardGameButton;
  JButton completeButton;
  JButton checkGameButton;
  JToggleButton[] numberChoices;
  ButtonGroup groupNumberChoices;
  JLabel bestTime;
  private transient Highscores highscores;
  private long startTime;
  private long time;
  private long highscore;
  private String mode;
  private String bestTimeNotSet = "Best Time: --";
  private String bestTimeString = "Best Time: ";
  
  /**
   * Creates the UI layout of all the buttons (on the left side of the grid).
   */
  public GridButtons() {
    super(new BorderLayout());
    
    highscores = new Highscores();
    
    // Panel to hold all the other panels
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    add(panel, BorderLayout.NORTH);

    // Panel to hold all the options
    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
    optionsPanel.setBorder(BorderFactory.createTitledBorder(" Sudoku Options "));
    panel.add(optionsPanel);

    // Panel to hold the panel of numbers
    JPanel numListPanel = new JPanel();
    numListPanel.setLayout(new BoxLayout(numListPanel, BoxLayout.Y_AXIS));
    numListPanel.setAlignmentX(CENTER_ALIGNMENT);
    panel.add(numListPanel);
    
    // Panel with the numbers
    JPanel numbersPanel = new JPanel();
    numbersPanel.setLayout(new BoxLayout(numbersPanel, BoxLayout.Y_AXIS));
    numListPanel.add(numbersPanel);

    // button that would start a new easy game -- 4x4 grid
    easyGameButton = new JButton("New Easy Game");
    easyGameButton.setFocusable(false);
    easyGameButton.setAlignmentX(CENTER_ALIGNMENT);
    
    // add to the options panel
    optionsPanel.add(easyGameButton);

    //button that would start a new medium game -- 6x6 grid
    mediumGameButton = new JButton("New Medium Game");
    mediumGameButton.setFocusable(false);
    mediumGameButton.setAlignmentX(CENTER_ALIGNMENT);
    optionsPanel.add(mediumGameButton);

    // button that would start a new hard game -- 9x9 grid
    hardGameButton = new JButton("New Hard Game");
    hardGameButton.setFocusable(false);
    hardGameButton.setAlignmentX(CENTER_ALIGNMENT);
    optionsPanel.add(hardGameButton);

    //button that would check the game
    checkGameButton = new JButton("Check Game");
    checkGameButton.setFocusable(false);
    checkGameButton.setAlignmentX(CENTER_ALIGNMENT);
    optionsPanel.add(checkGameButton);

    //button that would quit the game
    completeButton = new JButton("Complete");
    completeButton.setFocusable(false);
    completeButton.setAlignmentX(CENTER_ALIGNMENT);
    optionsPanel.add(completeButton);
    
    bestTime = new JLabel("Best Time:", JLabel.CENTER);
    bestTime.setAlignmentX(CENTER_ALIGNMENT);
    bestTime.setText("Best Time:");
    panel.add(bestTime);

    groupNumberChoices = new ButtonGroup();
    numberChoices = new JToggleButton[9];

    for (int i = 0; i < 9; i++) {
      numberChoices[i] = new JToggleButton(Integer.toString(i + 1));
      numberChoices[i].setPreferredSize(new Dimension(50, 50));
      numberChoices[i].setFocusable(false);
      groupNumberChoices.add(numberChoices[i]);
      numbersPanel.add(numberChoices[i]);

    }

    
  }

  /**
   * Sets up all the listeners for the buttons - for the observer pattern.
   * @param buttonAction The listener for the buttons.
   */
  public void buttonSetup(ActionOnButton buttonAction) {
    easyGameButton.addActionListener(buttonAction);
    mediumGameButton.addActionListener(buttonAction);
    hardGameButton.addActionListener(buttonAction);
    completeButton.addActionListener(buttonAction);
    checkGameButton.addActionListener(buttonAction);

    for (int i = 0; i < 9; i++) {
      numberChoices[i].addActionListener(buttonAction);

    }

  }

  @Override
  public void update(Observable observable, Object arg) {
    switch ((ObserverInfo)arg) {
      case NEW_HARD_GAME:
        mode = "Hard";
        for (int i = 0; i < 9; i++) {
          numberChoices[i].setVisible(true);
        }
        groupNumberChoices.clearSelection();
        highscore = highscores.getHighScore(mode);
        if (highscore > 0) {
          bestTime.setText(bestTimeString + (highscore / 60)
              + " mins " + (highscore % 60) + " secs");
        } else {
          bestTime.setText(bestTimeNotSet);
        }
        startTime = System.currentTimeMillis();
        break;
      case NEW_EASY_GAME:
        mode = "Easy";
        for (int i = 4; i < 9; i++) {
          numberChoices[i].setVisible(false);
        }
        groupNumberChoices.clearSelection();
        highscore = highscores.getHighScore(mode);
        if (highscore > 0) {
          bestTime.setText(bestTimeString + (highscore / 60)
              + " mins " + (highscore % 60) + " secs");
        } else {
          bestTime.setText(bestTimeNotSet);
        }
        startTime = System.currentTimeMillis();
        break;
      case NEW_MEDIUM_GAME:
        mode = "Medium";
        for (int i = 4; i < 6; i++) {
          numberChoices[i].setVisible(true);
        }
        for (int i = 6; i < 9; i++) {
          numberChoices[i].setVisible(false);
        }
        groupNumberChoices.clearSelection();
        highscore = highscores.getHighScore(mode);
        if (highscore > 0) {
          bestTime.setText(bestTimeString + (highscore / 60)
              + " mins " + (highscore % 60) + " secs");
        } else {
          bestTime.setText(bestTimeNotSet);
        }
        startTime = System.currentTimeMillis();
        break;
      case COMPLETE:
      case CHECK:
        groupNumberChoices.clearSelection();
        break;
      case TIMERSTOP:
        int check;
        time = (System.currentTimeMillis() - startTime) / 1000;
        highscore = highscores.getHighScore(mode);
        check = highscores.checkScore(mode, time);
        if (check > 0) {
          bestTime.setText(bestTimeString + (time / 60) + " mins " + (time % 60) + " secs");
        }
        break;
      default:
        break;
    }
  }
  
  public double panelLocOnScreenXEasy() {
    return panel.getLocationOnScreen().getX() + easyGameButton.getX() + 2 ;
  }
  
  public double panelLocOnScreenYEasy() {
    return panel.getLocationOnScreen().getY() + easyGameButton.getY() + 2;
  }
  
  public double panelLocOnScreenXMedium() {
    return panel.getLocationOnScreen().getX() + mediumGameButton.getX() + 2;
  }
  
  public double panelLocOnScreenYMedium() {
    return panel.getLocationOnScreen().getY() + mediumGameButton.getY() + 2;
  }
  
  public double panelLocOnScreenXHard() {
    return panel.getLocationOnScreen().getX() + hardGameButton.getX() + 2;
  }
  
  public double panelLocOnScreenYHard() {
    return panel.getLocationOnScreen().getY() + hardGameButton.getY() + 2;
  }
  
  public double panelLocOnScreenXComplete() {
    return panel.getLocationOnScreen().getX() + completeButton.getX() + 2;
  }
  
  public double panelLocOnScreenYComplete() {
    return panel.getLocationOnScreen().getY() + completeButton.getY() + 2;
  }
  
  public double panelLocOnScreenXCheck() {
    return panel.getLocationOnScreen().getX() + checkGameButton.getX() + 2;
  }
  
  public double panelLocOnScreenYCheck() {
    return panel.getLocationOnScreen().getY() + checkGameButton.getY() + 2;
  }
  

}