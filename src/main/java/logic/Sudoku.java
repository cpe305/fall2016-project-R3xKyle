package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import data.ObserverInfo;
import presentation.Grid;
import presentation.GridButtons;

public abstract class Sudoku extends Observable {
  
  protected int[][] solution;
  protected int[][] game;
  private int toggleButton; 
  private int size;
  
  public Sudoku(int size) {
    this.size = size;
  }
  
  
  /**
   * Get function for the size of the puzzle.
   * @return Returns the size of the puzzle.
   */
  public int getSize() {
    return size;
  }

  /**
   * Set function for the size of the puzzle.
   * @param size The size of the puzzle.
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Get function for the toggle button that is selected.
   * @return The number of the toggle buttont that is selected.
   */
  public int getNumberSimple() {
    return toggleButton;
  }

  /**
   * Get function for the value at specified index.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @return The value at the specified index.
   */
  public int getNumberRowCol(int xcomponent, int ycomponent) {

    return game[ycomponent][xcomponent];
  }

  /**
   * Set function for the number of the toggle button.
   * @param number The number of the toggle button that is selected by user.
   */
  public void setNumberSimple(int number) {

    toggleButton = number;
    setChanged();
  }

  /**
   * Set function for the specified index.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param number The number to set at the specified index.
   */
  public void setNumberRowCol(int xcomponent, int ycomponent, int number) {

    game[ycomponent][xcomponent] = number;

  }
  
  protected int[][] solutionCreate(int[][] inputGame, int currIndex) {
    if (currIndex > (size * size - 1)) {
      return inputGame;
    }
    
    int xcomponent = currIndex % size;
    int ycomponent = currIndex / size;

    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      numbers.add(i);
    }
    Collections.shuffle(numbers);

    while (!numbers.isEmpty()) { 
      int number = solutionHelper(inputGame, xcomponent, ycomponent, numbers);
      if (number == -1) {
        return new int[0][0];
      }
      inputGame[ycomponent][xcomponent] = number;
      int[][] tmpGame = solutionCreate(inputGame, currIndex + 1);
      if (tmpGame.length != 0) {
        return tmpGame;
      }
      inputGame[ycomponent][xcomponent] = 0;
    }
    return new int[0][0];
  }

  /**
   * A helper function of the solution
   * @param inputGame The game to fill in with a solution.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param numbers Array List to choose random numbers between 1 and 9.
   * @return Returns the number that is valid for the index.
   */
  public abstract int solutionHelper(int[][] inputGame, int xcomponent, int ycomponent,
      List<Integer> numbers);

  public abstract void newSudoku();
  
  public abstract boolean checkBlock(int[][] inputGame, int xcomponent, int ycomponent,
      int toggleNumber);
  
  public abstract boolean checkComponent(int[][] inputGame, int index, int toggleNumber,
      int checkComponent);
  
  public abstract boolean checkSimple(int xcomponent, int ycomponent);
  
  public abstract boolean checkRowCol(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent, int xcomponent);
  
  public abstract void notifyNewGame();
  
  /**
   * Function needed when creating a solution - copies the array.
   * @param inputGame The game represented in an array to be copied.
   * @param size The size of the puzzle.
   * @return Returns a copy of the array.
   */
  protected int[][] copy(int[][] inputGame) {
    int[][] copy = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        copy[i][j] = inputGame[i][j];
      }
    }
    return copy;
  }

  /**
   * Main function for creating a game for the user to fill in.
   * @param inputGame The game that is to be modified so the user can fill in the 0's.
   * @return Returns the game that has been filled with 0's so the user can fill in.
   */
  public int[][] createGame(int[][] inputGame) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < (size * size); i++) {
      indexes.add(i);
    }
    Collections.shuffle(indexes);
    return createGame(inputGame, indexes);
  }

  /**
   * Recursive helper function that randomly fills in 0's so the user can fill in.
   * @param inputGame The game that needs to be modified with 0's.
   * @param indexes The ArrayList that randomly spits out numbers from 1 to 9.
   * @return Returns the game that has been filled with 0's so the user can fill in.
   */
  public int[][] createGame(int[][] inputGame, List<Integer> indexes) {
    while (!indexes.isEmpty()) {
      int position = indexes.remove(0);
      int xcomponent = position % size;
      int ycomponent = position / size;
      int temp = inputGame[ycomponent][xcomponent];
      inputGame[ycomponent][xcomponent] = 0;

      if (!isValid(inputGame)) {
        inputGame[ycomponent][xcomponent] = temp;
      }
    }
    return inputGame;
  }
  
  /**
   * Function to print the game.
   * @param game The game to be printed.
   * @param size The size of the puzzle.
   */
  protected void print(int[][] game) {
    System.out.println("Printing the generated puzzle for user to solve "
        + "(" + size + " x " + size + ")");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(" " + game[i][j]);
      }
      System.out.println();
    }
    System.out.println("Printing a solution with the generated puzzle "
        + "(" + size + " x " + size + ")");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(" " + solution[i][j]);
      }
      System.out.println();
    }
  }
  
  /**
   * Used in the observer pattern to notify observers that the game is complete and stop timer.
   */
  public void timerStopUpdate() {
    setChanged();
    notifyObservers(ObserverInfo.TIMERSTOP);
  }

  /**
   * Used in the observer pattern to notify observers that the user pressed the complete button.
   */
  public void completeGameCheck() {
    toggleButton = 0;
    setChanged();
    notifyObservers(ObserverInfo.COMPLETE);
  }
  
  /**
   * Used in the observer pattern to notify observers that the check game button has been clicked
   *  and clears the selection of the toggle button.
   */
  public void checkSudoku() {
    toggleButton = 0;
    setChanged();
    notifyObservers(ObserverInfo.CHECK);
  }
  
  /**
   * Function that assists in the createGame function that checks if game is valid.
   * @param inputGame The game that the user is filling in.
   * @return Returns true if valid and false otherwise.
   */
  public boolean isValid(int[][] inputGame) {
    return isValid(inputGame, 0, new int[] {0});
  }

  /**
   * Recursive helper function of isValid that fills in zeroes on random spots
   *  to generate a game for the user to fill in based on the solution.
   * @param inputGame The game that the user is filling in.
   * @param index The index of the game - goes up to size * size.
   * @param numberOfSolutions Int array that is used to check the number of solutions.
   * @return returns true if valid and false otherwise.
   */
  public boolean isValid(int[][] inputGame, int index, int[] numberOfSolutions) {
    if (index > (size * size - 1)) {
      return ++numberOfSolutions[0] == 1;
    }
    int xcomponent = index % size;
    int ycomponent = index / size;

    if (inputGame[ycomponent][xcomponent] == 0) {
      List<Integer> numbers = new ArrayList<>();
      for (int i = 1; i <= size; i++) {
        numbers.add(i);
      }
      while (!numbers.isEmpty()) {
        int number = solutionHelper(inputGame, xcomponent, ycomponent, numbers);
        if (number == -1) {
          break;
        }
        inputGame[ycomponent][xcomponent] = number;

        if (!isValid(inputGame, index + 1, numberOfSolutions)) {
          inputGame[ycomponent][xcomponent] = 0;
          return false;
        }
        inputGame[ycomponent][xcomponent] = 0;
      }
    } else if (!isValid(inputGame, index + 1, numberOfSolutions)) {
      return false;
    }
    return true;
  }
  
}
