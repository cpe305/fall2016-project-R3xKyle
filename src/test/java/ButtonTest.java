import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import org.junit.Test;

import presentation.GamePanel;



public class ButtonTest {

  @Test
  public void testEasyButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocEasyX(), panel.getLocEasyY());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "New Easy Game");
    
  }
  
  @Test
  public void testMediumButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocMediumX(), panel.getLocMediumY());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "New Medium Game");
    
  }
  
  @Test
  public void testHardButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocHardX(), panel.getLocHardY());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "New Hard Game");
    
  }
  
  @Test
  public void testCompleteButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocCompleteX(), panel.getLocCompleteY());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "Complete");
    
  }
  
  @Test
  public void testCheckButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocCheckX(), panel.getLocCheckY());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "Check Game");
    
  }
  
  
  

}
