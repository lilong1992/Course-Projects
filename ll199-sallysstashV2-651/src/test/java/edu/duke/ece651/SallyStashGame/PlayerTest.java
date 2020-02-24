package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class PlayerTest {
  @Test
  public void test_dig() {
    Player p1 = new Player("A");
    Player p2 = new Player("B");
    p1.setRival(p2);
    GreenStack gs = new GreenStack();
    BoardCoord bc = new BoardCoord(0,0);
    p2.set(bc, gs.getPattern('V'), gs.getColorchar());//0,0V
    BoardCoord digbc1 = new BoardCoord(0,0);
    BoardCoord digbc2 = new BoardCoord(0,1);
    BoardCoord digbc3 = new BoardCoord(1,0);
    assertEquals(true, p1.dig(digbc1));
    assertEquals(false, p1.dig(digbc2));
    assertEquals(true, p1.dig(digbc3));
    assertEquals('X', p2.hid_board.grid[0][1]);
    assertEquals(' ', p2.ex_board.grid[0][1]);
    assertEquals('g', p2.hid_board.grid[0][0]);
    assertEquals('*', p2.ex_board.grid[0][0]);
    assertEquals('g', p2.hid_board.grid[1][0]);
    assertEquals('*', p2.ex_board.grid[1][0]);
    assertEquals('g', p2.hid_board.grid[0][0]);
    assertEquals('*', p2.ex_board.grid[0][0]);
  }

  @Test
  public void test_getinput() {
    Player p = new Player("test");
    p.setRival(new NPCPlayer("B"));
    Scanner coordsc = new Scanner("123\n12\na0\nG8\n");
    assertEquals("G8", p.getInboundStr(coordsc));
    Scanner modesc = new Scanner("adf\nA\nS\n");
    assertEquals("S", p.getselectionStr(modesc));
    Scanner turnsc = new Scanner("DM\nM\nM8\nS\nG5\n\n");
    p.playTurn(turnsc,true);
    assertEquals(3, p.move);
    assertEquals(2, p.sonar);
  }
  
  @Test
  public void test_moveandsonar() {
    Player p = new Player("test");
    GreenStack gs = new GreenStack();
    Scanner sc1 = new Scanner("A0V");
    p.setPatterns(gs, sc1);
    Scanner sc2 = new Scanner("F5H");
    p.setPatterns(gs, sc2);
    Scanner scm1 = new Scanner("A0\nA2V\n");
    p.moveTurn(scm1);
    assertEquals(' ',p.ex_board.grid[0][0]);
    assertEquals('g',p.ex_board.grid[0][2]);
    Player pr = new Player("rival");
    pr.setRival(p);
    BoardCoord bcdig1 = new BoardCoord(0, 2);
    assertEquals(true, pr.dig(bcdig1));//dig at 0,2
    Scanner scm2 = new Scanner("B2\nA1H\n");
    p.moveTurn(scm2);
    assertEquals(2, p.nofStacks);
    assertEquals('*', p.ex_board.grid[0][1]);//0,2 moved to 0,1H
    assertEquals('g', p.ex_board.grid[0][2]);
    assertEquals('g', p.hid_board.grid[0][2]);//the hid board should not change
    assertEquals(1, p.move);
    Scanner scs1 = new Scanner("1231\nB2\n");
    pr.sonarTurn(scs1);
    Scanner scs2 = new Scanner("Z5\nG5\n");
    pr.sonarTurn(scs2);
    Scanner scm3 = new Scanner("A1\nP2H\n");
    p.moveTurn(scm3);
    assertEquals(' ', p.ex_board.grid[0][1]);
    Scanner scs3 = new Scanner("A2\n");
    pr.sonarTurn(scs3);
    }
}
