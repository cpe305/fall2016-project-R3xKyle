package logic;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import presentation.Box;
import presentation.Grid;

public class ActionOnMouse implements MouseListener {

	private Grid grid;
	private Sudoku sudoku;
	
	public ActionOnMouse(Grid grid, Sudoku sudoku) {
		this.grid = grid;
		this.sudoku = sudoku;
	}
	
	public void MousePressed(MouseEvent click) {
		JPanel mouseCheck = (JPanel)click.getSource();
		Component component = mouseCheck.getComponentAt(click.getPoint());
		
		if (component instanceof Box) {
			Box box = (Box)component;
			int xComponent = box.getXComponent();
			int yComponent = box.getYComponent();
			
			if (click.getButton() == MouseEvent.BUTTON1 && (sudoku.getNumber(xComponent, yComponent) == 0 || box.getForeground().equals(Color.BLUE))) {
				int number = sudoku.getNumb();
				if (number < 0) {
					return;
				}
				sudoku.setNumb(xComponent, yComponent, number);
				box.setNumber(number, true);
				
			}
			else if (click.getButton() == MouseEvent.BUTTON3 && !box.getForeground().equals(Color.BLACK)) {
				sudoku.setNumb(xComponent, yComponent, 0);
				box.setNumber(0, false);
			}
			grid.update(sudoku, (Object)box);
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
