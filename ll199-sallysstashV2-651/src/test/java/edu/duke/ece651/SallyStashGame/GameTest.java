package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class GameTest {
  @Test
  public void test_setPatterns() {
    Scanner testsc = new Scanner("abcs\nabc\nAbc\nA0c\nB0H\nA0V\nT9V\nS9V\n");//B0H and S9V
    Game testg = new Game();
    GreenStack gs = new GreenStack();
    Player testp = new Player("test");
    testg.setPatterns(testp, 2, gs, testsc);
    assertEquals(' ', testp.ex_board.grid[0][0]);
    assertEquals('g', testp.ex_board.grid[1][0]);
    assertEquals('g', testp.ex_board.grid[19][9]);
    
    Scanner redsc = new Scanner("A3U\nA5R\nD0D\nD5L\n");
    RedStack rs = new RedStack();
    testg.setPatterns(testp, 4, rs, redsc);

    Scanner bluesc = new Scanner("G0U\nG5R\nM1D\nM5L\n");
    BlueStack bs = new BlueStack();
    testg.setPatterns(testp, 4, bs, bluesc);

    assertEquals('r', testp.ex_board.grid[4][4]);
    assertEquals('b', testp.ex_board.grid[13][9]);
    
  }
  
  @Test
  public void test_createHumanorNPC() {
    Scanner sc = new Scanner("12\n1\n2\n");
    Game g = new Game();
    g.p1=g.createHumanorNPCplayer(sc, "A");
    g.p2=g.createHumanorNPCplayer(sc, "B");
    assertEquals("A", g.p1.name);
    assertEquals(true, g.p1.isHuman);
    assertEquals("B", g.p2.name);
    assertEquals(false, g.p2.isHuman);
  }

  @Test
  public void test_NPCvsNPC() {
    Scanner sc = new Scanner("2\n2\n");
    Game g = new Game(sc);
    g.playerPrepare(g.p1, sc);
    g.playerPrepare(g.p2, sc);
    g.run(sc);
    assertEquals(true, g.isGameOver());
  }
  
  @Test
  public void test_fullgametest_tie() {
    Scanner sc = new Scanner("1\n1\n");
    Game g = new Game(sc);
    String prepare = "";
    for (char x='A';x<'F';x++){
      prepare=prepare+x+"0H\n";      
    }
    //A put H green and purple stacks from A0 to E0
    for (char x = 'F'; x < 'M'; x++) {
      prepare = prepare + x + "0R\n";// red at F0R, I0R,L0R
    }

    for (char x = 'F'; x < 'K'; x++) {
      prepare = prepare + x + "5L\n";//blue at F5L, H5L,J5L
    }
    prepare=prepare+"\n";
    prepare = prepare + prepare;//B do the same as A
    Scanner scprepare = new Scanner(prepare);
    g.playerPrepare(g.p1, scprepare);
    g.playerPrepare(g.p2, scprepare);
    assertEquals(43,g.p1.goldremain);
    assertEquals(43,g.p2.goldremain);

    String run = "";
    for (char x = 'A'; x <= 'R'; x++) {
      for (char y = '0'; y <= '9'; y++) {
        String onedig="D\n" + x + y + "\n\n\n";
        run=run+onedig+onedig;
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(0,g.p1.goldremain);
    assertEquals(0,g.p2.goldremain);
  }

  @Test
  public void test_fullgametest_PVE() {
    Scanner sc = new Scanner("1\n2\n");
    Game g = new Game(sc);
    String prepare = "";
    for (char x='A';x<'F';x++){
      prepare=prepare+x+"0H\n";      
    }
    //A put H green and purple stacks from A0 to E0
    for (char x = 'F'; x < 'M'; x++) {
      prepare = prepare + x + "0R\n";// red at F0R, I0R,L0R
    }

    for (char x = 'F'; x < 'K'; x++) {
      prepare = prepare + x + "5L\n";//blue at F5L, H5L,J5L
    }
    prepare=prepare+"\n";
    Scanner scprepare = new Scanner(prepare);
    g.playerPrepare(g.p1, scprepare);
    g.playerPrepare(g.p2, scprepare);
    assertEquals(43,g.p1.goldremain);
    assertEquals(43,g.p2.goldremain);

    String run = "M\nA0\nA9H\nM\nA0\nA1H\n\nM\nB0\nA0H\nM\nB0\nB1H\n\nM\nC0\nC1H\n\nM\n";//4 moves
    run = run + "S\nD3\n\nS\nI6\n\nS\nQ3\n\nS\n";//4 sonar
    for (char x = 'A'; x <= 'T'; x++) {
      for (char y = '0'; y <= '9'; y++) {
        String onedig="D\n" + x + y + "\n\n";
        run=run+onedig;
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(0,g.p2.goldremain);//A human player should win
  }

  @Test
  public void test_fullgametest_Bwin() {
    Scanner sc = new Scanner("1\n1\n");
    Game g = new Game(sc);
    String prepare = "";
    for (char x='A';x<'F';x++){
      prepare=prepare+x+"0H\n";      
    }
    //A put H green and purple stacks from A0 to E0
    for (char x = 'F'; x < 'M'; x++) {
      prepare = prepare + x + "0R\n";// red at F0R, I0R,L0R
    }

    for (char x = 'F'; x < 'K'; x++) {
      prepare = prepare + x + "5L\n";//blue at F5L, H5L,J5L
    }
    prepare=prepare+"\n";
    prepare = prepare + prepare;//B do the same as A
    Scanner scprepare = new Scanner(prepare);
    g.playerPrepare(g.p1, scprepare);
    g.playerPrepare(g.p2, scprepare);
    assertEquals(43,g.p1.goldremain);
    assertEquals(43,g.p2.goldremain);

    String run = "";
    for (char x = 'A'; x <= 'R'; x++) {
      for (char y = '0'; y <= '9'; y++) {
        String onedig="D\n" + x + y + "\n\n\n";
        run=run+"D\nA0\n\n\n"+onedig;//A aloways dig at A0
      }
    }
    Scanner scrun = new Scanner(run);
    g.run(scrun);
    assertEquals(0,g.p1.goldremain);
  }
  
  /*
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
  */
}
