package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionOnButton implements ActionListener {
	private enum observerInfo {NEW_EASY_GAME, NEW_MEDIUM_GAME, NEW_HARD_GAME, CHECK, NUMS, CANDIDATES};
	private Sudoku sudoku;
	int number;
		
	public ActionOnButton (Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	public void actionPerformed(ActionEvent buttonPress) {
		if ("New Easy Game".equals(buttonPress.getActionCommand())) {
			sudoku.newEasySudoku();
		}
		else if ("New Medium Game".equals(buttonPress.getActionCommand())) {
			sudoku.newMediumSudoku();
		}
		else if ("New Hard Game".equals(buttonPress.getActionCommand())){
			sudoku.newHardSudoku();
		}
		else if ("Check Game".equals(buttonPress.getActionCommand())) {
			sudoku.checkSudoku();
		}
		else if ("Quit".equals(buttonPress.getActionCommand())) { 
			System.exit(0); //look at close window method for swing
		}
		else {
			number = Integer.parseInt(buttonPress.getActionCommand());
			sudoku.setNumb(number);
		}
	}



}
