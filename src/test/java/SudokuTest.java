import static org.junit.Assert.assertEquals;

import org.junit.Test;

import logic.Sudoku;

public class SudokuTest {

  @Test
  public void test() {
    int size = 9;
    Sudoku sudoku = new Sudoku(size);
    assertEquals(size, sudoku.getSize());
  }

  @Test
  public void test2() {
    int size = 6;
    Sudoku sudoku = new Sudoku(size);
    assertEquals(size, sudoku.getSize());
  }

  @Test
  public void test3() {
    int size = 4;
    Sudoku sudoku = new Sudoku(size);
    assertEquals(size, sudoku.getSize());
  }
  
  @Test
  public void test4() {
    int size = 4;
    Sudoku sudoku = new Sudoku(size);
    assertEquals(size, sudoku.getSize());
    size = 6;
    sudoku.setSize(size);
    assertEquals(size, sudoku.getSize());
  }

}
