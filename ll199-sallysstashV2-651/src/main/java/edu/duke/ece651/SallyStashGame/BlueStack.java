package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;

public class BlueStack implements Pattern {
  private ArrayList<BoardCoord> patternu;
  private ArrayList<BoardCoord> patternd;
  private ArrayList<BoardCoord> patternl;
  private ArrayList<BoardCoord> patternr;
  private HashSet<Character> mode;
  
  public BlueStack() {
    
    patternu = new ArrayList<BoardCoord>();
    patternd = new ArrayList<BoardCoord>();
    patternl = new ArrayList<BoardCoord>();
    patternr = new ArrayList<BoardCoord>();

    for (int i = 0; i < 3; i++) {
      patternr.add(new BoardCoord(0, i));
      patternl.add(new BoardCoord(1, 4-i));
      patternd.add(new BoardCoord(4-i, -1));
      patternu.add(new BoardCoord(i, 0));

      patternr.add(new BoardCoord(-1, i+2));
      patternl.add(new BoardCoord(0, 2-i));
      patternd.add(new BoardCoord(2-i, 0));
      patternu.add(new BoardCoord(i+2, 1));
    }
    

    mode = new HashSet<Character>();
    mode.add('U');
    mode.add('D');
    mode.add('L');
    mode.add('R');
  }

	@Override
	public String getColor() {
		return "Blue";
	}


	@Override
	public char getColorchar() {
		return 'b';
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
