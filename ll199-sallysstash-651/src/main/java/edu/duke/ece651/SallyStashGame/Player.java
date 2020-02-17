package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;

public class Player {
  public int sonar;
  public int move;
  public int goldremain;
  public String name;
  public Player rival;
  public Board ex_board;//the status of player's board
  public Board hid_board;//be shown to the rival
  public ArrayList<ArrayList<BoardCoord>> positionofgold;//for move purpose

  public Player(String name_str) {
    this.sonar = 3;
    this.move = 3;
    this.goldremain = 37;
    this.name = name_str;
    this.ex_board = new Board();
    this.hid_board = new Board();
    this.positionofgold = new ArrayList<ArrayList<BoardCoord>>();
  }

  public void setRival(Player p){
    this.rival = p;
  }

  public boolean trySet(BoardCoord bc, ArrayList<BoardCoord> bcArr) {
    for (BoardCoord patBC : bcArr) {
      BoardCoord destBC = bc.add(patBC);
      if (InputCheck.coord_rangecheck(destBC)) {
        if (ex_board.grid[destBC.getX()][destBC.getY()] != ' ') {
          Display.print_occupiedbyotherstack();//it's been occupied
          return false;
        }
      }
      else {
        Display.print_stackoutofbound();
        return false;
      }
    }
    return true;
  }

  public void set(BoardCoord bc, ArrayList<BoardCoord> bcArr, char c) {
    ArrayList<BoardCoord> destBCArr = new ArrayList<BoardCoord>();
    for (BoardCoord patBC : bcArr) {
      BoardCoord destBC = bc.add(patBC);
      ex_board.grid[destBC.getX()][destBC.getY()] = c;
      destBCArr.add(destBC);
    }
    this.positionofgold.add(destBCArr);
  }

  public void loseGold() {
    this.goldremain--;
  }

  public boolean dig(BoardCoord bc) {
    if (rival.ex_board.grid[bc.getX()][bc.getY()] == ' ') {//miss
      rival.hid_board.grid[bc.getX()][bc.getY()] = 'X';
      return false;
    }
    //found the gold
    if (rival.ex_board.grid[bc.getX()][bc.getY()] == '*') {//already found
      return true;
    }
    rival.hid_board.grid[bc.getX()][bc.getY()] = rival.ex_board.grid[bc.getX()][bc.getY()];//show the stack type
    rival.ex_board.grid[bc.getX()][bc.getY()] = '*';//rival will see his gold is digged
    rival.loseGold();
    return true;
  }
}
