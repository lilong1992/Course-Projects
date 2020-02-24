package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTest {
  @Test
  public void test_Board_initilalization() {
    Board b = new Board();
    assertEquals(' ', b.grid[0][0]);
  }

  @Test
  public void test_BoardCoord_initilalization() {
    BoardCoord bc = new BoardCoord("B2");
    assertEquals(1, bc.getX());
    assertEquals(2, bc.getY());
    assertEquals("B2", bc.toStr());
    assertEquals(true, bc.equal(new BoardCoord(1,2)));
  }

}
