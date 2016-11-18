package logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import data.ObserverInfo;
import presentation.Box;
import presentation.Grid;

// Handles mouse clicks and interaction with grid
public class ActionOnMouse implements MouseListener {


	private Grid grid;
	private Sudoku sudoku;
	
	public ActionOnMouse(Grid grid, Sudoku sudoku) {
		this.grid = grid;
		this.sudoku = sudoku;
	}
	
	@Override
	public void mousePressed(MouseEvent click) {
		JPanel mouseCheck = (JPanel)click.getSource();
		Component component = mouseCheck.getComponentAt(click.getPoint());
		if (component instanceof Box) {
			Box box = (Box)component;
			int xComponent = box.getXComponent();
			int yComponent = box.getYComponent();
			
			if (click.getButton() == MouseEvent.BUTTON1 && (sudoku.getNumberXY(xComponent, yComponent) == 0 || box.getForeground().equals(Color.BLUE))) {
				int number = sudoku.getNumberSimple();
				if (number == -1) {
					return;
				}
				sudoku.setNumberXY(xComponent, yComponent, number);
				box.setNumber(number, true);
				
			}
			else if (click.getButton() == MouseEvent.BUTTON3 && !box.getForeground().equals(Color.BLACK)) {
				sudoku.setNumberXY(xComponent, yComponent, 0);
				box.setNumber(0, false);
			}
			
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Do nothing because only care about mousePressed in this project
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// Do nothing because only care about mousePressed in this project
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Do nothing because only care about mousePressed in this project
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Do nothing because only care about mousePressed in this project
		
	}
	
}