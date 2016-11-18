package presentation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import data.ObserverInfo;
import logic.ActionOnButton;

// Button class that has all buttons in game
public class GridButtons extends JPanel implements Observer {

  JButton easyGameButton;
  JButton mediumGameButton;
  JButton hardGameButton;
  JButton quitButton;
  JButton checkGameButton;
  JToggleButton[] numberChoices;
  ButtonGroup groupNumberChoices;
  // add timer and another JPanel
  public GridButtons() {
    super(new BorderLayout());

    // Panel to hold all the other panels
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    add(panel, BorderLayout.CENTER);

    // Panel to hold all the options
    JPanel optionsPanel = new JPanel();
    optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.PAGE_AXIS));
    optionsPanel.setBorder(BorderFactory.createTitledBorder(" Sudoku Options "));
    panel.add(optionsPanel);

    // Panel to hold the panel of numbers
    JPanel numListPanel = new JPanel();
    numListPanel.setLayout(new BoxLayout(numListPanel, BoxLayout.PAGE_AXIS));
    numListPanel.setBorder(BorderFactory.createTitledBorder(" Numbers "));
    panel.add(numListPanel);

    // Panel with the numbers
    JPanel numbersPanel = new JPanel();//new FlowLayout(FlowLayout.CENTER));
    numbersPanel.setLayout(new BoxLayout(numbersPanel, BoxLayout.PAGE_AXIS));
    numListPanel.add(numbersPanel);

    // button that would start a new easy game -- 4x4 grid
    easyGameButton = new JButton("New Easy Game");
    easyGameButton.setFocusable(false);
    // add to the options panel
    optionsPanel.add(easyGameButton);

    //button that would start a new medium game -- 6x6 grid
    mediumGameButton = new JButton("New Medium Game");
    mediumGameButton.setFocusable(false);
    optionsPanel.add(mediumGameButton);

    // button that would start a new hard game -- 9x9 grid
    hardGameButton = new JButton("New Hard Game");
    hardGameButton.setFocusable(false);
    optionsPanel.add(hardGameButton);

    //button that would check the game
    checkGameButton = new JButton("Check Game");
    checkGameButton.setFocusable(false);
    optionsPanel.add(checkGameButton);

    //button that would quit the game
    quitButton = new JButton("Quit");
    quitButton.setFocusable(false);
    optionsPanel.add(quitButton);

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

  // add a listener to each button 
  public void buttonSetup(ActionOnButton buttonAction) {
    easyGameButton.addActionListener(buttonAction);
    mediumGameButton.addActionListener(buttonAction);
    hardGameButton.addActionListener(buttonAction);
    quitButton.addActionListener(buttonAction);
    checkGameButton.addActionListener(buttonAction);

    for (int i = 0; i < 9; i++) {
      numberChoices[i].addActionListener(buttonAction);

    }

  }

  @Override
  public void update(Observable o, Object arg) {
    switch ((ObserverInfo)arg) {
      case NEW_HARD_GAME:
        for (int i = 0; i < 9; i++) {
          numberChoices[i].setVisible(true);
        }
        break;
      case NEW_EASY_GAME:
        for (int i = 4; i < 9; i++) {
          numberChoices[i].setVisible(false);
        }
        break;
      case NEW_MEDIUM_GAME:
        for (int i = 4; i < 6; i++) {
          numberChoices[i].setVisible(true);
        }
        for (int i = 6; i < 9; i++) {
          numberChoices[i].setVisible(false);
        }
        break;
      case CHECK:
        groupNumberChoices.clearSelection();
        break;
      default:
        break;
    }
  }

}