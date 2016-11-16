
package presentation;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import data.ObserverInfo;
import logic.ActionOnMouse;
import logic.Sudoku;

public class Grid extends JPanel implements Observer {
	
	private Box[][] boxesHard;
	private JPanel[][] panelsHard;
	
	private Box[][] boxesMedium;
	private JPanel[][] panelsMedium;
	
	private Box[][] boxesEasy;
	private JPanel[][] panelsEasy;
	
	private String currentMode = "hard";
	
	public Grid(int size) {
		super(new GridLayout(3, 3));
		
		panelsHard = new JPanel[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				panelsHard[i][j] = new JPanel(new GridLayout(3, 3));
				panelsHard[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				panelsHard[i][j].setVisible(true);
				add(panelsHard[i][j]);
			}
		}
		
		boxesHard = new Box[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boxesHard[i][j] = new Box(j, i);
				panelsHard[i / 3][j / 3].add(boxesHard[i][j]);
			}
		}
		
		panelsEasy = new JPanel[2][2];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				panelsEasy[i][j] = new JPanel(new GridLayout(2,2));
				panelsEasy[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				panelsEasy[i][j].setVisible(true);
			}
		}
		
		boxesEasy = new Box[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boxesEasy[i][j] = new Box(j, i);
				panelsEasy[i / 2][j / 2].add(boxesEasy[i][j]);
			}
		}
		
		panelsMedium = new JPanel[3][2];
		for (int i= 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				panelsMedium[i][j] = new JPanel(new GridLayout(2,3));
				panelsMedium[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				panelsMedium[i][j].setVisible(true);
			}
		}
		
		boxesMedium = new Box[6][6];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				boxesMedium[i][j] = new Box(j, i);
				panelsMedium[i / 2][j / 3].add(boxesMedium[i][j]);
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		switch ((ObserverInfo)arg) {
		case NEW_EASY_GAME:
			setEasyGame((Sudoku)o);
			currentMode = "Easy";
			break;
		case NEW_MEDIUM_GAME:
			setMediumGame((Sudoku)o);
			currentMode = "Medium";
			break;
		case NEW_HARD_GAME:
			setHardGame((Sudoku)o);
			currentMode = "Hard";
			break;
		case CHECK:
			setGameCheck((Sudoku)o);
			break;
		}
		
	}


	public void setEasyGame(Sudoku sudoku) {
		sudoku.setSize(4);
		removeMedium();
		removeHard();
		
		for (int i= 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				add(panelsEasy[i][j]);
			}
		}
		validate();
		repaint();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				boxesEasy[i][j].setBackground(Color.WHITE);
				boxesEasy[i][j].setNumber(sudoku.getNumberXY(j, i), false);
			}
		}

	}
	
	public void setMediumGame(Sudoku sudoku) {
	
		sudoku.setSize(6);
		removeEasy();
		removeHard();
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				add(panelsMedium[i][j]);
			}
		}
		
		validate();
		repaint();
		
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				boxesMedium[i][j].setBackground(Color.WHITE);
				boxesMedium[i][j].setNumber(sudoku.getNumberXY(j, i), false);
			}
		}
	}	


	public void setHardGame(Sudoku sudoku) {
		
		sudoku.setSize(9);
		removeEasy();
		removeMedium();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				add(panelsHard[i][j]);
			}
		}
		
		validate();
		repaint();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				boxesHard[i][j].setBackground(Color.WHITE);
				boxesHard[i][j].setNumber(sudoku.getNumberXY(j, i), false);
			}
		}
	}


	private void setGameCheck(Sudoku sudoku) {
		if ("Easy".equals(currentMode)) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					boxesEasy[i][j].setBackground(Color.WHITE);
					if (boxesEasy[i][j].getForeground().equals(Color.BLUE)) {
						boxesEasy[i][j].setBackground(sudoku.isCheckEasy(j, i) ? Color.GREEN : Color.RED);
					}
				}
			}
		}
		
		else if ("Medium".equals(currentMode)) {
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 6; j++) {
					boxesMedium[i][j].setBackground(Color.WHITE);
					if (boxesMedium[i][j].getForeground().equals(Color.BLUE)) {
						boxesMedium[i][j].setBackground(sudoku.isCheckMedium(j, i) ? Color.GREEN: Color.RED);
					}
				}
			}
		}
		
		else  {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					boxesHard[i][j].setBackground(Color.WHITE);
					if (boxesHard[i][j].getForeground().equals(Color.BLUE)) {
						boxesHard[i][j].setBackground(sudoku.isCheckHard(j, i) ? Color.GREEN : Color.RED);
					}
				}
			}
		}
	}
	
	public void mouseSetup(ActionOnMouse mouseAction) {
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				panelsEasy[i][j].addMouseListener(mouseAction);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				panelsMedium[i][j].addMouseListener(mouseAction);
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				panelsHard[i][j].addMouseListener(mouseAction);
			}
		}

	}
	
	public void removeEasy() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				remove(panelsEasy[i][j]);
			}
		}
	}

	public void removeMedium() {
		for (int i= 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				remove(panelsMedium[i][j]);
			}
		}
	}
	
	public void removeHard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				remove(panelsHard[i][j]);
			}
		}
	}
	
}