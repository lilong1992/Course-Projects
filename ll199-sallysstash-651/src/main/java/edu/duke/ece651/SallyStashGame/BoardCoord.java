package edu.duke.ece651.SallyStashGame;

public class BoardCoord {
  public int x;
  public int y;

  public BoardCoord() {
    this.x = 0;
    this.y = 0;
  }

  public BoardCoord(int xCoord, int yCoord) {
    this.setCoord(xCoord, yCoord);
  }

  public BoardCoord(String s) {
    this.setCoord(s);
  }

  public void setCoord(int xCoord, int yCoord) {
    this.x = xCoord;
    this.y = yCoord;
  }

  public void setCoord(String s) {
    this.x = s.charAt(0) - 'A';
    this.y = s.charAt(1) - '0';
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public BoardCoord add(BoardCoord bc) {
    BoardCoord sum = new BoardCoord(0, 0);
    sum.setCoord(this.x+bc.getX(), this.y+bc.getY());
    return sum;
  }
  
}
