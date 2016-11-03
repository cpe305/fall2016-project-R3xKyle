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

import logic.ActionOnButton;

// Button class that has all buttons in game
public class GridButtons extends JPanel implements Observer {
	JButton easyGameButton, mediumGameButton, hardGameButton, quitButton, checkGameButton;
	JToggleButton[] numberChoices;
	ButtonGroup groupNumberChoices;
	
	public GridButtons() {
		super(new BorderLayout());
		
		// Panel to hold all the other panels
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		add(panel, BorderLayout.CENTER);
		
		// Panel to hold all the options
		JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		optionsPanel.setBorder(BorderFactory.createTitledBorder(" Sudoku Options "));
		panel.add(optionsPanel);
		
		// Panel to hold the panel of numbers
		JPanel numListPanel = new JPanel();
		numListPanel.setLayout(new BoxLayout(numListPanel, BoxLayout.LINE_AXIS));
		numListPanel.setBorder(BorderFactory.createTitledBorder(" Numbers "));
		panel.add(numListPanel);
		
		// Panel with the numbers
		JPanel numbersPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
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
		
		//button that would quit the game
		quitButton = new JButton("Quit");
		quitButton.setFocusable(false);
		optionsPanel.add(quitButton);
		
		//button that would check the game
		checkGameButton = new JButton("Check Game");
		checkGameButton.setFocusable(false);
		optionsPanel.add(checkGameButton);
		
		groupNumberChoices = new ButtonGroup();
		numberChoices = new JToggleButton[9];
		
		for (int i = 0; i < 9; i++) {
			numberChoices[i] = new JToggleButton("" + (i + 1));
			numberChoices[i].setPreferredSize(new Dimension(30, 30));
			numberChoices[i].setFocusable(false);
			groupNumberChoices.add(numberChoices[i]);
			numbersPanel.add(numberChoices[i]);
			
		}
		
	}
	
	// add a listener to each button 
	public void controllerSetup(ActionOnButton bController) {
		easyGameButton.addActionListener(bController);
		mediumGameButton.addActionListener(bController);
		hardGameButton.addActionListener(bController);
		quitButton.addActionListener(bController);
		checkGameButton.addActionListener(bController);
		
		for (int i = 0; i < 9; i++) {
			numberChoices[i].addActionListener(bController);
			
		}
		
	}

	@Override
	// possibly add stuff
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
