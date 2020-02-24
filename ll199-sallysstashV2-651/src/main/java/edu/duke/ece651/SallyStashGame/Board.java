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

  public Board(Board b) {//copy constructor
    this.grid = new char[ROWS][COLUMNS];
    for (int i = 0; i < ROWS; i++) {
      for (int j = 0; j < COLUMNS; j++) {
        this.grid[i][j] = b.grid[i][j];
      }
    }
  }

  public void set(BoardCoord bc, char c) {
    grid[bc.getX()][bc.getY()] = c;
  }

  public char get(BoardCoord bc) {
    return grid[bc.getX()][bc.getY()];
  }

}
