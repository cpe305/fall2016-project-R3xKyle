

package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import data.ObserverInfo;

public class Sudoku extends Observable {

	
	private int[][] solution;
	private int[][] game;
	private int selectedNumber; 
	private int size;
	
	public Sudoku() {
		newHardSudoku();
		size = 9;
	}
	
	public Sudoku(int size) {
		this.size = size;
		if (size == 4) {
			newEasySudoku();
		}
		else if (size == 6) {
			newMediumSudoku();
		}
		else {
			newHardSudoku();
		}
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}


	public int getNumberSimple() {
		return selectedNumber;
	}
	
	public int getNumberXY(int xComponent, int yComponent) {

		return game[yComponent][xComponent];
	}
	
	public void setNumberSimple(int number) {

		selectedNumber = number;
		setChanged();
		notifyObservers(ObserverInfo.NUMS);
		
	}
	
	public void setNumberXY(int xComponent, int yComponent, int i) {
		
		game[yComponent][xComponent] = i;
		
	}
	
	public boolean isCheckEasy(int x, int y) {
		return isPossibleCheckCandidate(game, y, game[y][x], 1, x, size) && isPossibleCheckCandidate(game, x, game[y][x], 0, y, size) && isPossibleCheckEasyBlock(game, x, y, game[y][x]);
	}
	
	public boolean isCheckMedium(int x, int y) {
		return isPossibleCheckCandidate(game, y, game[y][x], 1, x, size) && isPossibleCheckCandidate(game, x, game[y][x], 0, y, size) && isPossibleCheckMediumBlock(game, x, y, game[y][x]);
	}
	
	public boolean isCheckHard(int x, int y) {
		System.out.println("Size is + " + size);
		System.out.println(isPossibleCheckCandidate(game, y, game[y][x], 1, x, size));
		System.out.println(" " + isPossibleCheckCandidate(game, x, game[y][x], 0, y, size));
		System.out.println(" " + isPossibleCheckHardBlock(game, x, y, game[y][x]));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(game[i][j] + " ");
			}
			System.out.println();
		}
		System.out.print("X is " + x + " Y IS" + y + " game[y][x] is " + game[y][x]);
		
		return isPossibleCheckCandidate(game, y, game[y][x], 1, x, size) && isPossibleCheckCandidate(game, x, game[y][x], 0, y, size) && isPossibleCheckHardBlock(game, x, y, game[y][x]);
	}
	
	private boolean isPossibleCheckCandidate(int[][] game2, int y, int selectedNumber2, int checkComponent, int x, int size) {

		if (checkComponent > 0) {
			for (int i = 0; i < size; i++) {
				if (game2[y][i] == selectedNumber2 && i != x) {
					System.out.println("\n this is checkComponent > 0, game2[y][i] = " + game2[y][i] + " i is " + i + " y is "  + y + " x is " + x + "selectedNumber2 = " + selectedNumber2);
							
					return false;
				}
			}
			return true;
		}
		else {
			for (int i = 0; i < size; i++) {
				if (game2[i][y] == selectedNumber2 && i != x) {
					System.out.println("\n this is checkComponent, game[i][y] = " + game2[i][y] + " i is " + i + " y is " + y + " x is " + x + " selectedNumber2 = " + selectedNumber2);
					return false;
				}
			}
			return true;
		}
	}
	
	private boolean isPossibleCheckEasyBlock(int[][] game2, int x, int y, int selectedNumber2) {

		int xBlock;
		int yBlock;
		if (x < 2) 
			xBlock = 0;
		else 
			xBlock = 2;
		if (y < 2) 
			yBlock = 0;
		else 
			yBlock = 2;
		for (int i = yBlock; i < yBlock + 2; i++) {
			for (int j = xBlock; j < xBlock + 2; j++) {
				if (game2[i][j] == selectedNumber2 && i != y && j != x) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isPossibleCheckMediumBlock(int[][] game2, int x, int y, int selectedNumber2) {

		int xBlock;
		int yBlock;
		if (x < 3) 
			xBlock = 0;
		else
			xBlock = 3;
		if (y < 2) 
			yBlock = 0;
		else if (y < 4) 
			yBlock = 2;
		else 
			yBlock = 4;
		for (int i = yBlock; i < yBlock + 2; i++) {
			for (int j = xBlock; j < xBlock + 3; j++) {
				if (game2[i][j] == selectedNumber2 && i != y && j != x) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isPossibleCheckHardBlock(int[][] game2, int x, int y, int selectedNumber2) {

		int xBlock;
		int yBlock;
		if (x < 3) 
			xBlock = 0;
		else if (x < 6)
			xBlock = 3;
		else 
			xBlock = 6;
		if (y < 3) 
			yBlock = 0;
		else if (y < 6) 
			yBlock = 3;
		else 
			yBlock = 6;
		for (int i = yBlock; i < yBlock + 3; i++) {
			for (int j = xBlock; j < xBlock + 3; j++) {
				if (game2[i][j] == selectedNumber2 && i != y && j != x) {
					System.out.println("x is " + x + " Y is " + y + "i is " + i + " j is " + j + " game2[i][j] is " + game2[i][j]);
				
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isCandidateEasy(int x, int y) {
		return game[y][x] == 0 && isPossibleComponentEasy(game, y, selectedNumber, 1) && isPossibleComponentEasy(game, x, selectedNumber, 0) && isPossibleBlockEasy(game, x, y, selectedNumber);
	}
	
	public boolean isCandidateMedium(int x, int y) {
		return game[y][x] == 0 && isPossibleComponentMedium(game, y, selectedNumber, 1) && isPossibleComponentMedium(game, x, selectedNumber, 0) && isPossibleBlockMedium(game, x, y, selectedNumber);
	}
	
	public boolean isCandidateHard(int x, int y) {
		return game[y][x] == 0 && isPossibleComponentHard(game, y, selectedNumber, 1) && isPossibleComponentHard(game, x, selectedNumber, 0) && isPossibleBlockHard(game, x, y, selectedNumber);
	}
	
	private boolean isPossibleComponentEasy(int[][] game2, int y, int selectedNumber2, int checkComponent) {

		if (checkComponent > 0) {
			for (int i = 0; i < 4; i++) {
				if (game2[y][i] == selectedNumber2) {
					return false;
				}
			}
			return true;
		}
		else {
			for (int i = 0; i < 4; i++) {
				if (game2[i][y] == selectedNumber2) {
					return false;
				}
			}
			return true;
		}
	}
	
	private boolean isPossibleComponentMedium(int[][] game2, int y, int selectedNumber2, int checkComponent) {

		if (checkComponent > 0) {
			for (int i = 0; i < 6; i++) {
				if (game2[y][i] == selectedNumber2) {
					return false;
				}
			}
			return true;
		}
		else {
			for (int i = 0; i < 6; i++) {
				if (game2[i][y] == selectedNumber2) {
					return false;
				}
			}
			return true;
		}
	}

	private boolean isPossibleComponentHard(int[][] game2, int y, int selectedNumber2, int checkComponent) {

		if (checkComponent > 0) {
			for (int i = 0; i < 9; i++) {
				if (game2[y][i] == selectedNumber2) {
					//System.out.println("this is checkComponent > 0, game[y][i] = " + game2[y][i] + "i is " + i + "selectedNumber2 = " + selectedNumber2);
					return false;
				}
			}
			return true;
		}
		else {
			for (int i = 0; i < 9; i++) {
				if (game2[i][y] == selectedNumber2) {
					//System.out.println("this is checkComponent, game[y][i] = " + game2[y][i] + "i is " + i + "selectedNumber2 = " + selectedNumber2);
					return false;
				}
			}
			return true;
		}
	}	
	
	private boolean isPossibleBlockEasy(int[][] game2, int x, int y, int selectedNumber2) {

		int xBlock;
		int yBlock;
		if (x < 2) 
			xBlock = 0;
		else 
			xBlock = 2;
		if (y < 2) 
			yBlock = 0;
		else 
			yBlock = 2;
		for (int i = yBlock; i < yBlock + 2; i++) {
			for (int j = xBlock; j < xBlock + 2; j++) {
				if (game2[i][j] == selectedNumber2) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isPossibleBlockMedium(int[][] game2, int x, int y, int selectedNumber2) {
	
		int xBlock;
		int yBlock;
		if (x < 3) 
			xBlock = 0;
		else
			xBlock = 3;
		if (y < 2) 
			yBlock = 0;
		else if (y < 4) 
			yBlock = 2;
		else 
			yBlock = 4;
		for (int i = yBlock; i < yBlock + 2; i++) {
			for (int j = xBlock; j < xBlock + 3; j++) {
				if (game2[i][j] == selectedNumber2) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private boolean isPossibleBlockHard(int[][] game2, int x, int y, int selectedNumber2) {

		int xBlock;
		int yBlock;
		if (x < 3) 
			xBlock = 0;
		else if (x < 6)
			xBlock = 3;
		else 
			xBlock = 6;
		if (y < 3) 
			yBlock = 0;
		else if (y < 6) 
			yBlock = 3;
		else 
			yBlock = 6;
		for (int i = yBlock; i < yBlock + 3; i++) {
			for (int j = xBlock; j < xBlock + 3; j++) {
				if (game2[i][j] == selectedNumber2) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean isValid(int[][] game, int size) {
		return isValid(game, 0, new int[] {0}, size);
	}
	
	public boolean isValid(int[][] game, int index, int[] numberOfSolutions, int size) {
		if (index > (size * size - 1)) {
			return ++numberOfSolutions[0] == 1;
		}
		int x = index % size;
		int y = index / size;
		
		if (game[y][x] == 0) {
			List<Integer> numbers = new ArrayList<Integer>();
			for (int i = 1; i <= size; i++) {
				numbers.add(i);
			}
			while (!numbers.isEmpty()) {
				int number = solutionHelper(game, x, y, numbers, size);
				if (number == -1) {
					break;
				}
				game[y][x] = number;
				
				if (!isValid(game, index + 1, numberOfSolutions, size)) {
					game[y][x] = 0;
					return false;
				}
				game[y][x] = 0;
			}
		}
		else if (!isValid(game, index + 1, numberOfSolutions, size)) {
			return false;
		}
		return true;
	}




	public void newEasySudoku() {

		solution = solutionCreate(new int[4][4], 0, 4);
		game = createGame(copy(solution, 4), 4);
		setChanged();
		notifyObservers(ObserverInfo.NEW_EASY_GAME);
		print(game, 4);
	}

	public void newMediumSudoku() {
	
		solution = solutionCreate(new int[6][6], 0, 6);
		game = createGame(copy(solution, 6), 6);
		setChanged();
		notifyObservers(ObserverInfo.NEW_MEDIUM_GAME);
		print(game, 6);
	}
	

	public void newHardSudoku() {
	
		solution = solutionCreate(new int[9][9], 0, 9);
		game = createGame(copy(solution, 9), 9);
		setChanged();
		notifyObservers(ObserverInfo.NEW_HARD_GAME);
		print(game, 9);
	}
	
	private int[][] solutionCreate(int[][] game, int currIndex, int size) {
		if (currIndex > (size * size - 1)) {
			return game;
			
		}
		int x = currIndex % size;
		int y = currIndex / size;
		
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= size; i++) {
			numbers.add(i);
		}
		Collections.shuffle(numbers);
		
		while (!numbers.isEmpty()) { 
			int number = solutionHelper(game, x, y, numbers, size);
			if (number == -1) {
				return null;
			}
			game[y][x] = number;
			int[][] tmpGame = solutionCreate(game, currIndex + 1, size);
			if (tmpGame != null) {
				return tmpGame;
			}
			game[y][x] = 0;
		}
		return null;
	}
	
	private int solutionHelper(int[][] game, int i, int j, List<Integer> numbers, int size) {
		while (!numbers.isEmpty()) {
			int number = numbers.remove(0);
			if (size == 4) {
				if (isPossibleComponentEasy(game, j, number, 1) && isPossibleComponentEasy(game, i, number, 0) && isPossibleBlockEasy(game, i, j, number)) {
					return number;
				}
			}
			else if (size == 6) {
				if (isPossibleComponentMedium(game, j, number, 1) && isPossibleComponentMedium(game, i, number, 0) && isPossibleBlockMedium(game, i, j, number)) {
					return number;
				}
			}
			else {
				if (isPossibleComponentHard(game, j, number, 1) && isPossibleComponentHard(game, i, number, 0) && isPossibleBlockHard(game, i, j, number)) {
					return number;
				}
			}
		}
		return -1;
	}
	

	public void checkSudoku() {
		selectedNumber = 0;
		setChanged();
		notifyObservers(ObserverInfo.CHECK);
	}

	
	public int[][] createGame(int[][] game, int size) {
		List<Integer> positions = new ArrayList<Integer>();
		for (int i = 0; i < (size * size); i++) {
			positions.add(i);
		}
		Collections.shuffle(positions);
		return createGame(game, positions, size);
	}
	
	public int[][] createGame(int[][] game, List<Integer> positions, int size) {
		while (!positions.isEmpty()) {
			int position = positions.remove(0);
			int x = position % size;
			int y = position / size;
			int temp = game[y][x];
			game[y][x] = 0;
			
			if (!isValid(game, size)) {
				game[y][x] = temp;
			}
		}
		return game;
	}
	
	private void print(int[][] game, int size) {
		System.out.println("Printing the generated puzzle for user to solve (" + size + " x " + size + ")");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + game[i][j]);
			}
			System.out.println();
		}
		System.out.println("Printing a solution with the generated puzzle (" + size + " x " + size + ")");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(" " + solution[i][j]);
			}
			System.out.println();
		}
	}
	
	private int[][] copy (int[][] game , int size) {
		int[][] copy = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				copy[i][j] = game[i][j];
			}
		}
		return copy;
	}

}