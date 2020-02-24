package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class NPCTest {
  @Test
  public void test_randombcgenerator() {
    NPCPlayer p = new NPCPlayer("test");
    for (int i = 0; i < 2000; i++) {
      p.ex_board.set(p.randombcGenerator(), 'a');
    }
    for (int i = 0; i < 20; i++) {
      for (int j = 0; j < 10; j++) {
        assertEquals('a', p.ex_board.grid[i][j]);
      }
    }
   
  }

  @Test
  public void test_setPattern() {
    GreenStack gs = new GreenStack();
    Scanner sc = new Scanner("");
    NPCPlayer p = new NPCPlayer("test");
    p.setPatterns(gs, sc);
  }

}
