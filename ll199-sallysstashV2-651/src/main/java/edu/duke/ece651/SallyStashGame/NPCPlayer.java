package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class NPCPlayer extends Player {
  private static final int ROWS = 20;
  private static final int COLUMNS=10;
  
  public NPCPlayer(String name_str) {
    super(name_str);
    this.isHuman = false;
  }

@Override
public void setPatterns(Pattern pat, Scanner sc) {
  BoardCoord bc = randombcGenerator();
  Object[] mode = pat.getMode().toArray();
  Random rand=new Random();
  char randOrien=(char) mode[rand.nextInt(mode.length)];
  while (trySet(bc, pat.getPattern(randOrien), ex_board) == false) {
    bc = randombcGenerator();
    randOrien=(char) mode[rand.nextInt(mode.length)];
  }
  set(bc, pat.getPattern(randOrien),pat.getColorchar());
  patternofstack.add(pat);
  nofStacks++;
}

@Override
public void playTurn(Scanner sc, boolean firsttime) {
  boolean firstAct = true;
  if (firstAct && timetosonar()) {
    firstAct = false;
    NPCsonar();
  }
  
  if (firstAct && timetomove()) {
    firstAct = false;
    NPCmove();
  }
  
  if (firstAct) {
    NPCdig();
  }
}

public boolean NPCdig() {
  BoardCoord bc = randombcGenerator();
  boolean found = dig(bc);
  if (found) {
    Display.print_NPCfoundgold(name, bc);
  }
  return true;
}
  
public boolean NPCmove() {
  Display.print_NPCspecialaction(name);
  move--;
	return true;
}

public boolean NPCsonar() {
  Display.print_NPCspecialaction(name);
  sonar--;
	return true;
}

  public boolean timetomove() {//dumb, always move if can
  if (move == 0) {
    return false;
  }
  return true;
}

  public boolean timetosonar() {//dumb, alwasy sonar if can
  if (sonar == 0) {
    return false;
  }
  return true;
}
  
public BoardCoord randombcGenerator() {
  Random rand=new Random();
  int x = rand.nextInt(ROWS);
  int y = rand.nextInt(COLUMNS);
  return new BoardCoord(x, y);
}
  
}
