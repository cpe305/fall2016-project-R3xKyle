package View;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Controller.ControllerButton;
import Controller.ControllerSudoku;
import Model.Sudoku;

public class GamePanel extends JFrame {

	public GamePanel() {
		super("My Sudoku");
		
		getContentPane().setLayout(new BorderLayout());
		
		Sudoku sudoku = new Sudoku();
		
		ControllerButton controllerButton = new ControllerButton(sudoku);
		GridButtons sudokuPanelButtons = new GridButtons();
		sudokuPanelButtons.controllerSetup(controllerButton);
		add(sudokuPanelButtons, BorderLayout.WEST);
		
		Grid grid = new Grid();
		ControllerSudoku controllerSudoku = new ControllerSudoku(grid, sudoku);
		grid.setGame(sudoku); // easy, medium, or hard???
		grid.controllerSetup(controllerSudoku);
		add(grid, BorderLayout.EAST);
		
		sudoku.addObserver(sudokuPanelButtons);
		sudoku.addObserver(grid);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new Sudoku();
	}

}
