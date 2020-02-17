package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;

public class BlueStack implements Pattern {
  private ArrayList<BoardCoord> patternh;
  private ArrayList<BoardCoord> patternv;
  private HashSet<Character> mode;
  
  public BlueStack() {
    patternh = new ArrayList<BoardCoord>();
    patternv = new ArrayList<BoardCoord>();
    
    for (int i = 0; i < 6; i++) {
      patternh.add(new BoardCoord(0, i));
    }

    for (int i = 0; i < 6; i++) {
      patternv.add(new BoardCoord(i, 0));
    }

    mode = new HashSet<Character>();
    mode.add('V');
    mode.add('H');
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
      case 'V':
        return patternv;
      case 'H':
        return patternh;
      }
		return null;
	}
}
