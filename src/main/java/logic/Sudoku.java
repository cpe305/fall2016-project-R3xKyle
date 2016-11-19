package logic;

import data.ObserverInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;



// All the game logic
public class Sudoku extends Observable {


  private int[][] solution;
  private int[][] game;
  private int toggleButton; 
  private int size;

  public Sudoku() {
    newHardSudoku();
    size = 9;
  }


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

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }


  public int getNumberSimple() {
    return toggleButton;
  }

  public int getNumberXY(int xComponent, int yComponent) {

    return game[yComponent][xComponent];
  }

  public void setNumberSimple(int number) {

    toggleButton = number;
    setChanged();
  }

  public void setNumberXY(int xComponent, int yComponent, int i) {

    game[yComponent][xComponent] = i;

  }

  public boolean isCheckEasy(int xcomponent, int ycomponent) {
    return isPossibleCheckCandidate(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size)
        && isPossibleCheckCandidate(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size)
        && isPossibleCheckEasyblock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  public boolean isCheckMedium(int xcomponent, int ycomponent) {
    return isPossibleCheckCandidate(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size)
        && isPossibleCheckCandidate(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size)
        && isPossibleCheckMediumBlock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  public boolean isCheckHard(int xcomponent, int ycomponent) {
    System.out.println("Size is + " + size);
    System.out.println(isPossibleCheckCandidate(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size));
    System.out.println(" " + isPossibleCheckCandidate(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size));
    System.out.println(" " + isPossibleCheckHardBlock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]));
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        System.out.print(game[i][j] + " ");
      }
      System.out.println();
    }
    System.out.print("X is " + xcomponent + " Y IS" + ycomponent + " game[ycomponent][xcomponent] is " + game[ycomponent][xcomponent]);

    return isPossibleCheckCandidate(game, ycomponent, game[ycomponent][xcomponent], 1, xcomponent, size) 
        && isPossibleCheckCandidate(game, xcomponent, game[ycomponent][xcomponent], 0, ycomponent, size) 
        && isPossibleCheckHardBlock(game, xcomponent, ycomponent, game[ycomponent][xcomponent]);
  }

  private boolean isPossibleCheckCandidate(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent, int xcomponent, int size) {

    if (checkComponent > 0) {
      for (int i = 0; i < size; i++) {
        if (inputGame[ycomponent][i] == toggleNumber && i != xcomponent && toggleNumber != 0) {
          System.out.println("\n this is checkComponent > 0, inputGame[ycomponent][i] = " + inputGame[ycomponent][i] 
              + " i is " + i + " ycomponent is "  + ycomponent + " xcomponent is " + xcomponent + "toggleNumber = "
              + toggleNumber);

          return false;
        }
      }
      return true;
    }
    else {
      for (int i = 0; i < size; i++) {
        if (inputGame[i][ycomponent] == toggleNumber && i != xcomponent && toggleNumber != 0) {
          System.out.println("\n this is checkComponent, game[i][ycomponent] = " + inputGame[i][ycomponent]
              + " i is " + i + " ycomponent is " + ycomponent + " xcomponent is " + xcomponent + " toggleNumber = "
              + toggleNumber);
          return false;
        }
      }
      return true;
    }
  }

  private boolean isPossibleCheckEasyblock(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 2) {
      rowblock = 0;
    } else {
      rowblock = 2;
    }
    if (ycomponent < 2) {
      colblock = 0;
    } else {
      colblock = 2;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 2; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent && j != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean isPossibleCheckMediumBlock(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 3) {
      rowblock = 0;
    } else {
      rowblock = 3;
    }
    if (ycomponent < 2) {
      colblock = 0;
    } else if (ycomponent < 4) {
      colblock = 2;
    } else {
      colblock = 4;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent && j != xcomponent && toggleNumber != 0) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean isPossibleCheckHardBlock(int[][] inputGame, int xcomponent,
      int ycomponent, int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 3) {
      rowblock = 0;
    } else if (xcomponent < 6) {
      rowblock = 3;
    } else {
      rowblock = 6;
    }
    if (ycomponent < 3) {
      colblock = 0;
    } else if (ycomponent < 6) {
      colblock = 3;
    } else {
      colblock = 6;
    }
    for (int i = colblock; i < colblock + 3; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber && i != ycomponent && j != xcomponent && toggleNumber != 0) {
          System.out.println("xcomponent is " + xcomponent + " Y is " + ycomponent + "i is " + i
              + " j is " + j + " inputGame[i][j] is " + inputGame[i][j]);

          return false;
        }
      }
    }

    return true;
  }

  public boolean isCandidateEasy(int xcomponent, int ycomponent) {
    return game[ycomponent][xcomponent] == 0
        && isPossibleComponentEasy(game, ycomponent, toggleButton, 1)
        && isPossibleComponentEasy(game, xcomponent, toggleButton, 0)
        && isPossibleBlockEasy(game, xcomponent, ycomponent, toggleButton);
  }

  public boolean isCandidateMedium(int xcomponent, int ycomponent) {
    return game[ycomponent][xcomponent] == 0
        && isPossibleComponentMedium(game, ycomponent, toggleButton, 1)
        && isPossibleComponentMedium(game, xcomponent, toggleButton, 0)
        && isPossibleBlockMedium(game, xcomponent, ycomponent, toggleButton);
  }

  public boolean isCandidateHard(int xcomponent, int ycomponent) {
    return game[ycomponent][xcomponent] == 0 
        && isPossibleComponentHard(game, ycomponent, toggleButton, 1)
        && isPossibleComponentHard(game, xcomponent, toggleButton, 0)
        && isPossibleBlockHard(game, xcomponent, ycomponent, toggleButton);
  }

  private boolean isPossibleComponentEasy(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 4; i++) {
        if (inputGame[ycomponent][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < 4; i++) {
        if (inputGame[i][ycomponent] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
  }

  private boolean isPossibleComponentMedium(int[][] inputGame, int ycomponent,
      int toggleNumber, int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 6; i++) {
        if (inputGame[ycomponent][i] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
    else {
      for (int i = 0; i < 6; i++) {
        if (inputGame[i][ycomponent] == toggleNumber) {
          return false;
        }
      }
      return true;
    }
  }

  private boolean isPossibleComponentHard(int[][] inputGame, int ycomponent, int toggleNumber,
      int checkComponent) {

    if (checkComponent > 0) {
      for (int i = 0; i < 9; i++) {
        if (inputGame[ycomponent][i] == toggleNumber) {
          //System.out.println("this is checkComponent > 0, game[ycomponent][i] = "
          //+ inputGame[ycomponent][i] + "i is " + i + "toggleNumber = " + toggleNumber);
          return false;
        }
      }
      return true;
    } else {
      for (int i = 0; i < 9; i++) {
        if (inputGame[i][ycomponent] == toggleNumber) {
          //System.out.println("this is checkComponent, game[ycomponent][i] = "
          //+ inputGame[ycomponent][i] + "i is " + i + "toggleNumber = " + toggleNumber);
          return false;
        }
      }
      return true;
    }
  }

  private boolean isPossibleBlockEasy(int[][] inputGame, int xcomponent, int ycomponent,
      int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 2) {
      rowblock = 0;
    } else {
      rowblock = 2;
    }
    if (ycomponent < 2) {
      colblock = 0;
    } else {
      colblock = 2;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 2; j++) {
        if (inputGame[i][j] == toggleNumber) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean isPossibleBlockMedium(int[][] inputGame, int xcomponent, int ycomponent,
      int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 3) {
      rowblock = 0;
    } else {
      rowblock = 3;
    }
    if (ycomponent < 2) {
      colblock = 0;
    } else if (ycomponent < 4) {
      colblock = 2;
    } else {
      colblock = 4;
    }
    for (int i = colblock; i < colblock + 2; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber) {
          return false;
        }
      }
    }

    return true;
  }

  private boolean isPossibleBlockHard(int[][] inputGame, int xcomponent, int ycomponent,
      int toggleNumber) {

    int rowblock;
    int colblock;
    if (xcomponent < 3) {
      rowblock = 0;
    } else if (xcomponent < 6) {
      rowblock = 3;
    } else {
      rowblock = 6;
    }
    if (ycomponent < 3) {
      colblock = 0;
    } else if (ycomponent < 6) {
      colblock = 3;
    } else {
      colblock = 6;
    }
    for (int i = colblock; i < colblock + 3; i++) {
      for (int j = rowblock; j < rowblock + 3; j++) {
        if (inputGame[i][j] == toggleNumber) {
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
    int xcomponent = index % size;
    int ycomponent = index / size;

    if (game[ycomponent][xcomponent] == 0) {
      ArrayList<Integer> numbers = new ArrayList<Integer>();
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
    int xcomponent = currIndex % size;
    int ycomponent = currIndex / size;

    ArrayList<Integer> numbers = new ArrayList<Integer>();
    for (int i = 1; i <= size; i++) {
      numbers.add(i);
    }
    Collections.shuffle(numbers);

    while (!numbers.isEmpty()) { 
      int number = solutionHelper(game, xcomponent, ycomponent, numbers, size);
      if (number == -1) {
        return null;
      }
      game[ycomponent][xcomponent] = number;
      int[][] tmpGame = solutionCreate(game, currIndex + 1, size);
      if (tmpGame != null) {
        return tmpGame;
      }
      game[ycomponent][xcomponent] = 0;
    }
    return null;
  }

  private int solutionHelper(int[][] game, int i, int j,
      ArrayList<Integer> numbers, int size) {
    while (!numbers.isEmpty()) {
      int number = numbers.remove(0);
      if (size == 4) {
        if (isPossibleComponentEasy(game, j, number, 1)
            && isPossibleComponentEasy(game, i, number, 0)
            && isPossibleBlockEasy(game, i, j, number)) {
          return number;
        }
      } else if (size == 6) {
        if (isPossibleComponentMedium(game, j, number, 1)
            && isPossibleComponentMedium(game, i, number, 0)
            && isPossibleBlockMedium(game, i, j, number)) {
          return number;
        }
      } else {
        if (isPossibleComponentHard(game, j, number, 1)
            && isPossibleComponentHard(game, i, number, 0)
            && isPossibleBlockHard(game, i, j, number)) {
          return number;
        }
      }
    }
    return -1;
  }


  public void checkSudoku() {
    toggleButton = 0;
    setChanged();
    notifyObservers(ObserverInfo.CHECK);
  }


  public int[][] createGame(int[][] game, int size) {
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    for (int i = 0; i < (size * size); i++) {
      indexes.add(i);
    }
    Collections.shuffle(indexes);
    return createGame(game, indexes, size);
  }

  public int[][] createGame(int[][] game, ArrayList<Integer> indexes, int size) {
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

  private void print(int[][] game, int size) {
    System.out.println("Printing the generated puzzle for user to solve "
        + "(" + size + " xcomponent " + size + ")");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(" " + game[i][j]);
      }
      System.out.println();
    }
    System.out.println("Printing a solution with the generated puzzle "
        + "(" + size + " xcomponent " + size + ")");
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(" " + solution[i][j]);
      }
      System.out.println();
    }
  }

  private int[][] copy(int[][] inputGame, int size) {
    int[][] copy = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        copy[i][j] = inputGame[i][j];
      }
    }
    return copy;
  }


  public void completeGameCheck() {
    // TODO Auto-generated method stub
    
  }

}