package logic;

import java.util.List;

import data.ObserverInfo;

public class EasySudoku extends Sudoku {

  private static final int size = 4;
  
  public EasySudoku() {
    super(size);
    newSudoku();
  }

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

  @Override
  public boolean checkBlock(int[][] inputGame, int xcomponent, int ycomponent, int toggleNumber) {
    
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

  @Override
  public void newSudoku() {
    
    solution = solutionCreate(new int[size][size], 0);
    game = createGame(copy(solution));
    setChanged();
    print(game);
    
  }
  
  public void notifyNewGame() {
    
    notifyObservers(ObserverInfo.NEW_EASY_GAME);
    
  }

  @Override
  public boolean checkSimple(int xcomponent, int ycomponent) {
    
    return checkRowCol(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent)
        && checkRowCol(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent)
        && checkBlock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
    
  }

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
