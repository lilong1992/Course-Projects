package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;

public class RedStack implements Pattern {
  private ArrayList<BoardCoord> patternu;
  private ArrayList<BoardCoord> patternd;
  private ArrayList<BoardCoord> patternl;
  private ArrayList<BoardCoord> patternr;
  private HashSet<Character> mode;

  public RedStack() {
    patternu = new ArrayList<BoardCoord>();
    patternd = new ArrayList<BoardCoord>();
    patternl = new ArrayList<BoardCoord>();
    patternr = new ArrayList<BoardCoord>();

    //the adding order will matter for rotation
    patternu.add(new BoardCoord(0,0));
    patternr.add(new BoardCoord(1, 1));
    patternl.add(new BoardCoord(1, -1));
    patternd.add(new BoardCoord(1, 1));
    for (int i = 0; i < 3; i++) {
      patternu.add(new BoardCoord(1, i-1));
      patternr.add(new BoardCoord(i, 0));
      patternl.add(new BoardCoord(2-i, 0));
      patternd.add(new BoardCoord(0, 2-i));
    }
    
    

    mode = new HashSet<Character>();
    mode.add('U');
    mode.add('D');
    mode.add('L');
    mode.add('R');
  }

  @Override
  public String getColor() {
    return "Red";
  }

  @Override
  public char getColorchar() {
    return 'r';
  }

  @Override
  public HashSet<Character> getMode() {
    return mode;
  }

  @Override
  public ArrayList<BoardCoord> getPattern(char c) {
    switch (c) {
    case 'U':
      return patternu;
    case 'D':
      return patternd;
    case 'L':
      return patternl;
    default:
      return patternr;
    }
  }
}
