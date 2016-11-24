import static org.junit.Assert.assertEquals;

import org.junit.Test;

import presentation.Box;

public class BoxTest {

  @Test
  public void test() {
    int xcomponent = 5;
    int ycomponent = 6;
    Box box = new Box(xcomponent, ycomponent);
    assertEquals(box.getRowComponent(), xcomponent);
    assertEquals(box.getColComponent(), ycomponent);

  }

}
