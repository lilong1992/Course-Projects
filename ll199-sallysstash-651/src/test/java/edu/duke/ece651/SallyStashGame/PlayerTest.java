package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {
  @Test
  public void test_dig() {
    Player p1 = new Player("A");
    Player p2 = new Player("B");
    p1.setRival(p2);
    GreenStack gs = new GreenStack();
    BoardCoord bc = new BoardCoord(0,0);
    p2.set(bc, gs.getPattern('V'), gs.getColorchar());
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
    assertEquals(true, p1.dig(digbc1));
    assertEquals('g', p2.hid_board.grid[0][0]);
    assertEquals('*', p2.ex_board.grid[0][0]);
  }

}
