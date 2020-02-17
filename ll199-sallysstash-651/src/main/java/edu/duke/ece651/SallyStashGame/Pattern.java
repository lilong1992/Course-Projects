package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;
// a pattern is a set of board coordinates relative to a point, based on the mode input c, it will return different relative coordinates. The color is like its name.
interface Pattern {
  String getColor();
  
  char getColorchar();

  ArrayList<BoardCoord> getPattern(char c);

  HashSet<Character> getMode();
}
