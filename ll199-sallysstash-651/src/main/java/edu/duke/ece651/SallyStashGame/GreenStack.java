package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;

public class GreenStack implements Pattern {
  private ArrayList<BoardCoord> patternh;
  private ArrayList<BoardCoord> patternv;
  private HashSet<Character> mode;
  
  public GreenStack() {
    patternh = new ArrayList<BoardCoord>();
    patternv = new ArrayList<BoardCoord>();
    
    BoardCoord bch1 = new BoardCoord(0, 0);
    BoardCoord bch2 = new BoardCoord(0, 1);

    BoardCoord bcv1 = new BoardCoord(0, 0);
    BoardCoord bcv2 = new BoardCoord(1, 0);

    patternh.add(bch1);
    patternh.add(bch2);

    patternv.add(bcv1);
    patternv.add(bcv2);

    mode = new HashSet<Character>();
    mode.add('V');
    mode.add('H');
  }

	@Override
	public String getColor() {
		return "Green";
	}


	@Override
	public char getColorchar() {
		return 'g';
	}

	@Override
	public HashSet<Character> getMode() {
		return mode;
	}

	@Override
	public ArrayList<BoardCoord> getPattern(char c) {
      switch (c) {
      case 'V':
        return patternv;
      case 'H':
        return patternh;
      }
      return null;
	}
  
}
