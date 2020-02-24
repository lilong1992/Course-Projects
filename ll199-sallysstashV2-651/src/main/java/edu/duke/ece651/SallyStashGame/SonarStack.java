package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;

public class SonarStack {
  private ArrayList<BoardCoord> pattern;

  public SonarStack() {
    pattern = new ArrayList<BoardCoord>();
    for (int i = -3; i < 4; i++) {
      pattern.add(new BoardCoord(0, i));
    }
    for (int i = -2; i < 3; i++) {
      pattern.add(new BoardCoord(1, i));
      pattern.add(new BoardCoord(-1, i));
    }
    for (int i = -1; i < 2; i++) {
      pattern.add(new BoardCoord(2, i));
      pattern.add(new BoardCoord(-2, i));
    }
    pattern.add(new BoardCoord(-3, 0));
    pattern.add(new BoardCoord(3, 0));

  }




	public char getColorchar() {
		return '*';
	}


	public ArrayList<BoardCoord> getPattern() {
     
      return pattern;
	}
  
}
