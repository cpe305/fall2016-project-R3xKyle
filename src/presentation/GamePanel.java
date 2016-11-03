package presentation;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import logic.ActionOnButton;
import logic.ActionOnMouse;
import logic.Sudoku;

public class GamePanel extends JFrame {

	public GamePanel() {
		super("My Sudoku");
		
		getContentPane().setLayout(new BorderLayout());
		
		Sudoku sudoku = new Sudoku();
		
		ActionOnButton controllerButton = new ActionOnButton(sudoku);
		GridButtons sudokuPanelButtons = new GridButtons();
		sudokuPanelButtons.controllerSetup(controllerButton);
		add(sudokuPanelButtons, BorderLayout.WEST);
		
		Grid grid = new Grid();
		ActionOnMouse controllerSudoku = new ActionOnMouse(grid, sudoku);
		grid.setGame(sudoku); // easy, medium, or hard???
		grid.controllerSetup(controllerSudoku);
		add(grid, BorderLayout.EAST);
		
		sudoku.addObserver(sudokuPanelButtons);
		sudoku.addObserver(grid);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}
	

}
