package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.Sudoku;

public class ControllerButton implements ActionListener {
	private Sudoku sudoku;
	int number;
		
	public ControllerButton (Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	public void actionOnSudokuPanelButtonsPress(ActionEvent buttonPress) {
		if (buttonPress.getActionCommand().equals("New Easy Game")) {
			sudoku.newEasySudoku();
		}
		else if (buttonPress.getActionCommand().equals("New Medium Game")) {
			sudoku.newMediumSudoku();
		}
		else if (buttonPress.getActionCommand().equals("New Hard Game")){
			sudoku.newHardSudoku();
		}
		else if (buttonPress.getActionCommand().equals("Check Game")) {
			sudoku.checkSudoku();
		}
		else if (buttonPress.getActionCommand().equals("Quit")) { 
			System.exit(0);
		}
		else {
			number = Integer.parseInt(buttonPress.getActionCommand());
			sudoku.setNumb(number);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
