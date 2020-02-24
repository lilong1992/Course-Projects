package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DisplayTest {
  @Test
  public void test_print_boards() {
    Player p1 = new Player("A");
    Player p2 = new Player("B");
    p1.setRival(p2);
    Display.print_turninfo(p1);
  }

}
