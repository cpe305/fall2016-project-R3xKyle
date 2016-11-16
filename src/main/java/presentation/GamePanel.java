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
		
		Sudoku sudoku = new Sudoku(9);
		
		ActionOnButton buttonAction = new ActionOnButton(sudoku);
		GridButtons sudokuPanelButtons = new GridButtons();
		sudokuPanelButtons.buttonSetup(buttonAction);
		add(sudokuPanelButtons, BorderLayout.EAST);
		
		Grid grid = new Grid();
		ActionOnMouse mouseAction = new ActionOnMouse(grid, sudoku);
		grid.setHardGame(sudoku); // easy, medium, or hard???
		grid.mouseSetup(mouseAction);
		add(grid, BorderLayout.CENTER);
		
		sudoku.addObserver(sudokuPanelButtons);
		sudoku.addObserver(grid);
		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		
	}
	
	 public static void main(String[] args) {
		 java.awt.EventQueue.invokeLater(new Runnable() {
			 public void run() {
				 GamePanel frame = new GamePanel();
				 frame.setVisible(true);
			 }
		 });
		 //new Sudoku(4); 
		// new Sudoku(6);
		 //new Sudoku(9);
	 }
	

}