package logic;

import data.ObserverInfo;

import java.util.List;


/**
 * Class involved in state pattern for medium difficulty.
 * Extended class holds general methods for all difficulties.
 * @author Kyle R
 *
 */
public class MediumSudoku extends Sudoku {

  private static final int size = 6;
  
  /**
   * Passes the size to super class and invokes a new game method.
   */
  public MediumSudoku() {
    super(size);
    newSudoku();
  }

  /**
   * Checks all number possibilities for game[xcomponent][ycomponenent] and returns if valid number.
   * @param game The board being generated.
   * @param xcomponent The row index of the game.
   * @param ycomponent The column index of the game.
   * @param numbers An array filled with numbers from 1 - size.
   * @return Returns the number that is valid for that spot or -1 if no number is valid.
   */
  @Override
  public int solutionHelper(int[][] game, int xcomponent, int ycomponent, List<Integer> numbers) {
    
    while (!numbers.isEmpty()) {
      int number = numbers.remove(0);
      if (checkComponent(game, ycomponent, number, 1)
          && checkComponent(game, xcomponent, number, 0)
          && checkBlock(game, xcomponent, ycomponent, number)) {
        return number;
      }
    }
    return -1;
    
  }

  /**
   * Checks the entire sudoku block for validity of a toggleNumber.
   * @param inputGame Game that is being filled and needs to be checked for validity.
   * @param xcomponent Row index of number in inputGame
   * @param ycomponent Column index of number in inputGame
   * @param toggleNumber Number in the box that is being checked for validity.
   * @return Returns true if block is valid and false otherwise.
   */
  @Override
  public boolean checkBlock(int[][] inputGame, int xcomponent, int ycomponent, int toggleNumber) {

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
   * Checks the row or column for validity against sudoku rules for solution generation.
   * @param inputGame Board to be checked for validity.
   * @param index The row or column indexes that needs to be checked for validity.
   * @param toggleNumber The number in the box that needs to be checked for validity.
   * @param checkComponent If 0 - check column, if 1 - check row.
   * @return Returns true if valid or false otherwise.
   */
  @Override
  public boolean checkComponent(int[][] inputGame, int index, int toggleNumber,
      int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < size; i++) {
        if (inputGame[index][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < size; i++) {
        if (inputGame[i][index] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
    
  }

  /**
   * Stores a generated solution, stores the game board that the user is working on,
   *  calls setChanged() and prints the game.
   */
  @Override
  public void newSudoku() {
    
    solution = solutionCreate(new int[size][size], 0);
    game = createGame(copy(solution));
    setChanged();
    print(game);
    
  }
  
  /**
   * Notifies observers about a new easy game.
   */
  @Override
  public void notifyNewGame() {

    notifyObservers(ObserverInfo.NEW_MEDIUM_GAME);
    
  }

  /**
   * Checks to see if the number is valid for its row, column, and block.
   * @param xcomponent The row index of the number.
   * @param ycomponent The column index of the number.
   * @return Returns true if it is valid and false otherwise.
   */
  @Override
  public boolean checkSimple(int xcomponent, int ycomponent) {
    
    return checkRowCol(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent)
        && checkRowCol(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent)
        && checkBlock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
    
  }

  /**
   * Checks the validity of the row or column for an active game.
   * @param inputGame The game the user is working on.
   * @param ycomponent The row or column index of the number. Depends on checkComponent.
   * @param toggleNumber The number that needs to be checked.
   * @param checkComponent If 0 - checks column, if > 0, checks row.
   * @param xcomponent The row or column index of the number. The opposite of ycomponent.
   * @return Returns true if valid and false otherwise.
   */
  @Override
  public boolean checkRowCol(int[][] inputGame, int ycomponent, int toggleNumber,
      int checkComponent, int xcomponent) {

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
  
}
