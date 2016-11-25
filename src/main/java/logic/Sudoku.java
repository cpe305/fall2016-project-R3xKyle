package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import data.ObserverInfo;

/**
 * Contains all the game logic.
 * @author KyleRingler
 *
 */
public class Sudoku extends Observable {

  private int[][] solution;
  private int[][] game;
  private int toggleButton; 
  private int size;
  
  /**
   * Class constructor specifying the size of the puzzle.
   * @param size The size of the puzzle.
   */
  public Sudoku(int size) {
    this.size = size;
    if (size == 4) {
      newEasySudoku();
    } else if (size == 6) {
      newMediumSudoku();
    } else {
      newHardSudoku();
    }
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

  /**
   * Function for the easy puzzle that checks whether the value
   *  at the specified index is a valid number.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @return Returns true if the number is valid given the sudoku rules and false otherwise.
   */
  public boolean checkEasySimple(int xcomponent, int ycomponent) {
    return checkRowCol(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size)
        && checkRowCol(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size)
        && checkBlockEasy(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  /**
   * Function for the medium puzzle that checks whether the value
   *  at the specified index is a valid number.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @return Returns true if the number is valid given the sudoku rules and false otherwise.
   */
  public boolean checkMediumSimple(int xcomponent, int ycomponent) {
    return checkRowCol(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size)
        && checkRowCol(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size)
        && checkBlockMedium(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  /**
   * Function for the easy puzzle that checks whether the value
   *  at the specified index is a valid number.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @return Returns true if the number is valid give the sudoku rules and false otherwise.
   */
  public boolean checkHardSimple(int xcomponent, int ycomponent) {
    return checkRowCol(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size) 
        && checkRowCol(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size) 
        && checkBlockHard(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  /**
   * Function to check if the row or column is valid.
   * @param inputGame The game that the user is filling in.
   * @param ycomponent The column index of the puzzle.
   * @param toggleNumber The number that is to be checked for validity at the specified index.
   * @param checkComponent If 1 - check row validity - If 2 - check column validity.
   * @param xcomponent The row index of the puzzle.
   * @param size The size of the puzzle.
   * @return Returns true if number is valid and false otherwise.
   */
  private boolean checkRowCol(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent, int xcomponent, int size) {

    if (checkComponent > 0) {
      for (int i = 0; i < size; i++) {
        if (inputGame[ycomponent][i] == toggleNumber && i != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < size; i++) {
        if (inputGame[i][ycomponent] == toggleNumber && i != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Function for the easy game that checks if the block is valid.
   * @param inputGame The game that the user is filling in.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param toggleNumber The number that is to be checked for validity at the specified index.
   * @return Returns true if number is valid and false otherwise.
   */
  private boolean checkBlockEasy(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock = 2;
    int colblock = 2;
    if (xcomponent < 2) {
      rowblock = 0;
    }
    if (ycomponent < 2) {
      colblock = 0;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 2; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent
            && j != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Function for the medium game that checks if the block is valid.
   * @param inputGame The game that the user is filling in.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param toggleNumber The number that is to be checked for validity at the specified index.
   * @return Returns true if number is valid and false otherwise.
   */
  private boolean checkBlockMedium(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock = 3;
    int colblock = 4;
    if (xcomponent < 3) {
      rowblock = 0;
    }
    if (ycomponent < 2) {
      colblock = 0;
    } else if (ycomponent < 4) {
      colblock = 2;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent
            && j != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * Function for the hard game that checks if the block is valid.
   * @param inputGame The game that the user is filling in.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param toggleNumber The number that is to be checked for validity at the specified index.
   * @return Returns true if number is valid and false otherwise.
   */
  private boolean checkBlockHard(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock = 6;
    int colblock = 6;
    if (xcomponent < 3) {
      rowblock = 0;
    } else if (xcomponent < 6) {
      rowblock = 3;
    }
    if (ycomponent < 3) {
      colblock = 0;
    } else if (ycomponent < 6) {
      colblock = 3;
    }
    for (int i = colblock; i < colblock + 3; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent && j != xcomponent
            && toggleNumber != 0) {
          return false;
        }
      }
    }

    return true;
  }
  
  /**
   * Function that is used to help generate an easy solution that checks
   *  the row and column for validity.
   * @param inputGame The game that the user is filling in.
   * @param index The index number of either the row or column - depends on checkComponent.
   * @param toggleNumber The number that is to be checked for validity.
   * @param checkComponent If 1 - check row - If 2 - check column.
   * @return Returns true if row or column is valid.
   */
  private boolean isPossibleComponentEasy(int[][] inputGame, int index,
      int toggleNumber, int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 4; i++) {
        if (inputGame[index][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < 4; i++) {
        if (inputGame[i][index] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Function that is used to help generate a medium solution that checks
   *  the row and column for validity.
   * @param inputGame The game that the user is filling in.
   * @param index The index number of either the row or column - depends on checkComponent.
   * @param toggleNumber The number that is to be checked for validity.
   * @param checkComponent If 1 - check row - If 2 - check column.
   * @return Returns true if row or column is valid.
   */
  private boolean isPossibleComponentMedium(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 6; i++) {
        if (inputGame[ycomponent][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < 6; i++) {
        if (inputGame[i][ycomponent] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Function that is used to help generate a hard solution that checks.
   *  the row and column for validity.
   * @param inputGame The game that the user is filling in.
   * @param index The index number of either the row or column - depends on checkComponent.
   * @param toggleNumber The number that is to be checked for validity.
   * @param checkComponent If 1 - check row - If 2 - check column.
   * @return Returns true if row or column is valid.
   */
  private boolean isPossibleComponentHard(int[][] inputGame, int ycomponent, int toggleNumber,
      int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 9; i++) {
        if (inputGame[ycomponent][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < 9; i++) {
        if (inputGame[i][ycomponent] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * Function that assists in the createGame function that checks if game is valid.
   * @param game The game that the user is filling in.
   * @param size The size of the puzzle.
   * @return Returns true if valid and false otherwise.
   */
  public boolean isValid(int[][] game, int size) {
    return isValid(game, 0, new int[] {0}, size);
  }

  /**
   * Recursive helper function of isValid that fills in zeroes on random spots
   *  to generate a game for the user to fill in based on the solution.
   * @param game The game that the user is filling in.
   * @param index The index of the game - goes up to size * size.
   * @param numberOfSolutions Int array that is used to check the number of solutions.
   * @param size The size of the puzzle.
   * @return returns true if valid and false otherwise.
   */
  public boolean isValid(int[][] game, int index, int[] numberOfSolutions, int size) {
    if (index > (size * size - 1)) {
      return ++numberOfSolutions[0] == 1;
    }
    int xcomponent = index % size;
    int ycomponent = index / size;

    if (game[ycomponent][xcomponent] == 0) {
      List<Integer> numbers = new ArrayList<>();
      for (int i = 1; i <= size; i++) {
        numbers.add(i);
      }
      while (!numbers.isEmpty()) {
        int number = solutionHelper(game, xcomponent, ycomponent, numbers, size);
        if (number == -1) {
          break;
        }
        game[ycomponent][xcomponent] = number;

        if (!isValid(game, index + 1, numberOfSolutions, size)) {
          game[ycomponent][xcomponent] = 0;
          return false;
        }
        game[ycomponent][xcomponent] = 0;
      }
    } else if (!isValid(game, index + 1, numberOfSolutions, size)) {
      return false;
    }
    return true;
  }

  /**
   * A function that creates an easy solution and an easy puzzle for the user to fill in.
   */
  public void newEasySudoku() {

    solution = solutionCreate(new int[4][4], 0, 4);
    game = createGame(copy(solution, 4), 4);
    setChanged();
    notifyObservers(ObserverInfo.NEW_EASY_GAME);
    print(game, 4);
  }

  /**
   * A function that creates a medium solution and a medium puzzle for the user to fill in.
   */
  public void newMediumSudoku() {

    solution = solutionCreate(new int[6][6], 0, 6);
    game = createGame(copy(solution, 6), 6);
    setChanged();
    notifyObservers(ObserverInfo.NEW_MEDIUM_GAME);
    print(game, 6);
  }


  /**
   * A function that creates a hard solution and a hard puzzle for the user to fill in.
   */
  public void newHardSudoku() {

    solution = solutionCreate(new int[9][9], 0, 9);
    game = createGame(copy(solution, 9), 9);
    setChanged();
    notifyObservers(ObserverInfo.NEW_HARD_GAME);
    print(game, 9);
  }

  /**
   * Randomly generates a sudoku solution.
   * @param game The game to fill in with a solution.
   * @param currIndex The index of the puzzle.
   * @param size The size of the puzzle.
   * @return Returns a solution to the puzzle.
   */
  private int[][] solutionCreate(int[][] game, int currIndex, int size) {
    if (currIndex > (size * size - 1)) {
      return game;
    }
    
    int xcomponent = currIndex % size;
    int ycomponent = currIndex / size;

    List<Integer> numbers = new ArrayList<>();
    for (int i = 1; i <= size; i++) {
      numbers.add(i);
    }
    Collections.shuffle(numbers);

    while (!numbers.isEmpty()) { 
      int number = solutionHelper(game, xcomponent, ycomponent, numbers, size);
      if (number == -1) {
        return new int[0][0];
      }
      game[ycomponent][xcomponent] = number;
      int[][] tmpGame = solutionCreate(game, currIndex + 1, size);
      if (tmpGame.length != 0) {
        return tmpGame;
      }
      game[ycomponent][xcomponent] = 0;
    }
    return new int[0][0];
  }

  /**
   * A helper function of the solution
   * @param game The game to fill in with a solution.
   * @param xcomponent The row index of the puzzle.
   * @param ycomponent The column index of the puzzle.
   * @param numbers Array List to choose random numbers between 1 and 9.
   * @param size THe size of the puzzle.
   * @return Returns the number that is valid for the index.
   */
  private int solutionHelper(int[][] game, int xcomponent, int ycomponent,
      List<Integer> numbers, int size) {
    while (!numbers.isEmpty()) {
      int number = numbers.remove(0);
      if (size == 4) {
        if (isPossibleComponentEasy(game, ycomponent, number, 1)
            && isPossibleComponentEasy(game, xcomponent, number, 0)
            && checkBlockEasy(game, xcomponent, ycomponent, number)) {
          return number;
        }
      } else if (size == 6) {
        if (isPossibleComponentMedium(game, ycomponent, number, 1)
            && isPossibleComponentMedium(game, xcomponent, number, 0)
            && checkBlockMedium(game, xcomponent, ycomponent, number)) {
          return number;
        }
      } else {
        if (isPossibleComponentHard(game, ycomponent, number, 1)
            && isPossibleComponentHard(game, xcomponent, number, 0)
            && checkBlockHard(game, xcomponent, ycomponent, number)) {
          return number;
        }
      }
    }
    return -1;
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
   * Main function for creating a game for the user to fill in.
   * @param game The game that is to be modified so the user can fill in the 0's.
   * @param size The size of the puzzle.
   * @return Returns the game that has been filled with 0's so the user can fill in.
   */
  public int[][] createGame(int[][] game, int size) {
    List<Integer> indexes = new ArrayList<>();
    for (int i = 0; i < (size * size); i++) {
      indexes.add(i);
    }
    Collections.shuffle(indexes);
    return createGame(game, indexes, size);
  }

  /**
   * Recursive helper function that randomly fills in 0's so the user can fill in.
   * @param game The game that needs to be modified with 0's.
   * @param indexes The ArrayList that randomly spits out numbers from 1 to 9.
   * @param size The size of the puzzle.
   * @return Returns the game that has been filled with 0's so the user can fill in.
   */
  public int[][] createGame(int[][] game, List<Integer> indexes, int size) {
    while (!indexes.isEmpty()) {
      int position = indexes.remove(0);
      int xcomponent = position % size;
      int ycomponent = position / size;
      int temp = game[ycomponent][xcomponent];
      game[ycomponent][xcomponent] = 0;

      if (!isValid(game, size)) {
        game[ycomponent][xcomponent] = temp;
      }
    }
    return game;
  }

  /**
   * Function to print the game.
   * @param game The game to be printed.
   * @param size The size of the puzzle.
   */
  private void print(int[][] game, int size) {
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
   * Function needed when creating a solution - copies the array.
   * @param inputGame The game represented in an array to be copied.
   * @param size The size of the puzzle.
   * @return Returns a copy of the array.
   */
  private int[][] copy(int[][] inputGame, int size) {
    int[][] copy = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        copy[i][j] = inputGame[i][j];
      }
    }
    return copy;
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
  
}