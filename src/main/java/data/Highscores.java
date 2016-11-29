
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class Highscores {
  
  long easyHighScore = -1;
  long mediumHighScore = -1;
  long hardHighScore = -1;
  String highscore = "";
  static Logger logger;
  
  /**
   * Reads from a file and returns the highscore. It is represented in the file
   *  as highscoreEasy:highscoreMedium:highscoreHard.
   * @param mode String representation of the current mode whether it is Easy, Medium, or Hard.
   * @return returns the highscore of the current mode.
   */
  public long getHighScore(String mode) {
    String highscoreLine = "";
    BufferedReader readFile = null;
    try (FileReader fileName = new FileReader("sudokuhighscore.txt")) { 
      readFile = new BufferedReader(fileName);
      highscoreLine = readFile.readLine();
      fileName.close();
    } catch (Exception exception) {
      logger.log(null, "readFile.readLine() failed", exception);
      return -1;
    } finally {
      try {
        if (readFile != null) {
          readFile.close();
        }

      } catch (IOException exception2) {
        logger.log(null, "readFile.close() failed", exception2);
      }
    }
    
    easyHighScore = Long.parseLong(highscoreLine.split(":")[0]);
    mediumHighScore = Long.parseLong(highscoreLine.split(":")[1]);
    hardHighScore = Long.parseLong(highscoreLine.split(":")[2]);
    if ("Easy".equals(mode)) {
      return easyHighScore;
    } else if ("Medium".equals(mode)) {
      return mediumHighScore;
    } else if ("Hard".equals(mode)) {
      return hardHighScore;
    }
    
    return -1;
  }
  
  /**
   * Checks to see if the score for the given mode beats the current high score.
   * @param mode String representation of the current mode whether it is Easy, Medium, or Hard.
   * @param score The current score to check - time in seconds.
   * @return Returns whether or not the highscore was updated (1 if updated, 0 if not).
   */
  public int checkScore(String mode, long score) {
    
    File highscoreFile = new File("sudokuhighscore.txt");
    BufferedWriter writeFile = null;
    int newScoreFlag = 0;
    String toWrite = "-1:-1:-1"; // initializes all highscores to -1. easy:medium:hard
    
    if (!highscoreFile.exists()) {
      try {
        boolean failure = highscoreFile.createNewFile();
        if (!failure) {
          logger.log(null, "createNewFile() failed");
        }
        
      } catch (IOException exception) {
        logger.log(null, "highscoreFile.createNewFile() failed", exception);
      }
    }
    if ("Easy".equals(mode)) {
      if (score < easyHighScore || easyHighScore == -1) {
        newScoreFlag = 1; // if score > highscore
        toWrite = score + ":" + mediumHighScore + ":" + hardHighScore;
        easyHighScore = score;
      }
    } else if ("Medium".equals(mode)) {
      if (score < mediumHighScore || mediumHighScore == -1) {
        newScoreFlag = 1;
        toWrite = easyHighScore + ":" + score + ":" + hardHighScore;
        mediumHighScore = score;
      }
    } else if ("Hard".equals(mode)) {
      if (score < hardHighScore || hardHighScore == -1) {
        newScoreFlag = 1;
        toWrite = easyHighScore + ":" + mediumHighScore + ":" + score;
        hardHighScore = score;
      }
    }
    
    if (newScoreFlag == 1) {
      try (FileWriter fileName = new FileWriter(highscoreFile)) {
        writeFile = new BufferedWriter(fileName);
        writeFile.write(toWrite);
        writeFile.close();
        fileName.close();
      } catch (Exception exception) {
        logger.log(null, "writeFile.write() failed", exception);
      }
    }
    return newScoreFlag;
  }
  
  public static void createFile() {
    String highscoreFile = "sudokuhighscore.txt";
    String toWrite = "-1:-1:-1";
    File file = new File(highscoreFile);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException exception) {
        logger.log(null, "file.createNewFile in createFile() failed", exception);
      }
      try (FileWriter fileWrite = new FileWriter(file)) {
        BufferedWriter writer = new BufferedWriter(fileWrite);
        writer.write(toWrite);
        writer.close();
      }
      catch (Exception exception) {
        logger.log(null, "new FileWriter in createFile() failed", exception);
      }
    }
  }
}
