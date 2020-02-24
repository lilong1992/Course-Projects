package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;

public class PurpleStack implements Pattern {

  private ArrayList<BoardCoord> patternh;
  private ArrayList<BoardCoord> patternv;
  private HashSet<Character> mode;
  
  public PurpleStack() {
    patternh = new ArrayList<BoardCoord>();
    patternv = new ArrayList<BoardCoord>();
    
    for (int i = 0; i < 3; i++) {
      patternh.add(new BoardCoord(0, i));
    }

    for (int i = 0; i < 3; i++) {
      patternv.add(new BoardCoord(i, 0));
    }

    mode = new HashSet<Character>();
    mode.add('V');
    mode.add('H');
  }

	@Override
	public String getColor() {
		return "Purple";
	}


	@Override
	public char getColorchar() {
		return 'p';
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
      default:
        return patternh;
      }
	}

}
