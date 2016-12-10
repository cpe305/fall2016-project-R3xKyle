import static org.junit.Assert.assertEquals;

import org.junit.Test;

import presentation.GamePanel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.MouseEvent;

public class ButtonTest {

  @Test
  public void testEasyButton() throws AWTException {
    GamePanel panel = new GamePanel();
    Robot bot = new Robot();
    bot.mouseMove(panel.getLocEasyRow(), panel.getLocEasyCol());
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
    bot.mouseMove(panel.getLocMediumRow(), panel.getLocMediumCol());
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
    bot.mouseMove(panel.getLocHardRow(), panel.getLocHardCol());
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
    bot.mouseMove(panel.getLocCompleteRow(), panel.getLocCompleteCol());
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
    bot.mouseMove(panel.getLocCheckRow(), panel.getLocCheckCol());
    bot.delay(750);
    bot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    bot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    bot.delay(750);
    String action = panel.getButtonActionCommand();
    assertEquals(action, "Check Game");
    
  }
  
  
  

}
