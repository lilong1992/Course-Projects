package edu.duke.ece651.SallyStashGame;

//store stack information in a 2D grid
public class Board {
  private static final int ROWS = 20;
  private static final int COLUMNS=10;
  public char[][] grid;
  
  public Board(){
    grid = new char[ROWS][COLUMNS];
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        grid[i][j] = ' ';
      }
    }
  }

}
