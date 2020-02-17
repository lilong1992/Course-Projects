package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class GameTest {
  @Test
  public void test_setPatterns() {
    Scanner testsc = new Scanner("abcs\nabc\nAbc\nA0c\nB0H\nA0V\nT9V\nS9V");
    Game testg = new Game();
    GreenStack gs = new GreenStack();
    Player testp = new Player("test");
    testg.setPatterns(testp, 2, gs, testsc);
    assertEquals(' ', testp.ex_board.grid[0][0]);
    assertEquals('g', testp.ex_board.grid[1][0]);
    assertEquals('g', testp.ex_board.grid[19][9]);
  }

  @Test
  public void test_fullgametest_tie() {
    String prepare = "";
    for (char x='A';x<'K';x++){
      prepare=prepare+x+"0H\n";      
    }
    //A put H stacks from A0 to J0
    prepare=prepare+"\n";
    prepare=prepare+prepare;//B do the sameting as A
    Scanner scprepare = new Scanner(prepare);
    Game g = new Game(scprepare);

    String run = "";
    for (char x = 'A'; x < 'K'; x++) {
      for (char y = '0'; y < '7'; y++) {
        String onedig="\n" + x + y + "\n\n";
        run=run+onedig+onedig;
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(0,g.p1.goldremain);
    assertEquals(0,g.p2.goldremain);
  }

  @Test
  public void test_fullgametest_Awin() {
    String prepareB = "";
    for (char x='A';x<'K';x++){
      prepareB=prepareB+x+"0H\n";      
    }
    //B put H stacks from A0 to J0
    prepareB=prepareB+"\n";
    String prepareA = "";
    for (char y='0';y<='9';y++){
      prepareA=prepareA+"A"+y+"V\n";      
    }
    //A put V stacks from A1 to J0
    prepareA=prepareA+"\n";
    String prepare = prepareA + prepareB;
    Scanner scprepare = new Scanner(prepare);
    Game g = new Game(scprepare);

    String run = "";
    for (char x = 'A'; x < 'K'; x++) {
      for (char y = '0'; y < '7'; y++) {
        String Adig="\n" + x + y + "\n\n";
        String Bdig="\nA0\n\n";//B always dig A0
        run=run+Adig+Bdig;
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(36,g.p1.goldremain);
    assertEquals(0,g.p2.goldremain);
  }

  @Test
  public void test_fullgametest_Bwin() {
    //swap the order of input of A and B
    String prepareB = "";
    for (char x='A';x<'K';x++){
      prepareB=prepareB+x+"0H\n";      
    }
    //B put H stacks from A0 to J0
    prepareB=prepareB+"\n";
    String prepareA = "";
    for (char y='0';y<='9';y++){
      prepareA=prepareA+"A"+y+"V\n";      
    }
    //A put V stacks from A1 to J0
    prepareA=prepareA+"\n";
    String prepare = prepareB + prepareA;
    Scanner scprepare = new Scanner(prepare);
    Game g = new Game(scprepare);

    String run = "";
    for (char x = 'A'; x < 'K'; x++) {
      for (char y = '0'; y < '7'; y++) {
        String Adig="\n" + x + y + "\n\n";
        String Bdig="\noij\nAA\nA0\n\n";//B always dig A0, add incorrect inputs
        run=run+Bdig+Adig;
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(0,g.p1.goldremain);
    assertEquals(36,g.p2.goldremain);
  }

}
