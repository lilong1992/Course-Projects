package edu.duke.ece651.SallyStashGame;

import java.util.HashSet;

public class InputCheck {
  private static final int ROWS = 20;
  private static final int COLUMNS=10;

  public InputCheck() {
  }

  public static boolean placement_inputcheck(String s,HashSet<Character> orien) {
    if (s.length() != 3) {
      Display.print_nofcharsincorrect(3,s.length());
      return false;
    }

    if (coord_rangecheck(s.substring(0, 2))) {
      char s2 = s.charAt(2);
      if (!orien.contains(s2)) {
        Display.print_modeerror(orien.toString());
        return false;
      }
    }
    else {
      return false;
    }
    
    return true;
  }

  public static boolean coord_rangecheck(String s) {
    int s0 = s.charAt(0)-'A';
    int s1 = s.charAt(1) - '0';

    return coord_rangecheck(s0, s1);
  }

  public static boolean coord_rangecheck(BoardCoord bc) {
    return coord_rangecheck(bc.getX(), bc.getY());
  }

  public static boolean coord_rangecheck(int x, int y) {
    if (x < 0 || x >= ROWS) {
      Display.print_xoutofbound();
      return false;
    }

    if (y < 0 || y >= COLUMNS) {
      Display.print_youtofbound();;
      return false;
    }

    return true;
  }
}
