package edu.duke.ece651.SallyStashGame;

import java.util.Scanner;

public class Game {
  public Player p1;
  public Player p2;
  public Display dp;

  public Game() {
    this.dp = new Display();
    //for testing
  }
  
  public Game(Scanner sc) {
    this.dp = new Display(); 
    this.p1 = new Player("A");
    this.p2 = new Player("B");
    p1.setRival(p2);
    p2.setRival(p1);
    playerPrepare(p1,sc);    
    playerPrepare(p2,sc);
  }

  public void playerPrepare(Player p,Scanner sc) {
    
    dp.print_welcome_msg(p.name, p.rival.name);
    //place the green stack
    GreenStack gs = new GreenStack();
    int nofgs=2;
    setPatterns(p, nofgs, gs,sc);
    
    PurpleStack ps = new PurpleStack();
    int nofps = 3;
    setPatterns(p, nofps, ps, sc);

    RedStack rs = new RedStack();
    int nofrs = 3;
    setPatterns(p, nofrs, rs, sc);

    BlueStack bs = new BlueStack();
    int nofbs = 2;
    setPatterns(p, nofbs, bs, sc);
    
    dp.print_allset(sc);
    dp.print_refreshscreen();//make sure p2 doesn't see p1's setup
    
  }

  public void setPatterns(Player p, int times, Pattern pat,Scanner sc) {
    for (int i = 0; i < times; i++) {
      dp.print_asktoputstack(i+1, p.name, pat.getColor());
      String str;
      BoardCoord bc = new BoardCoord();
      do{
        do{//get a leagal input
          str = sc.nextLine();
        } while (InputCheck.placement_inputcheck(str, pat.getMode()) == false );
        bc.setCoord(str.substring(0, 2));//got a leagal input then set starting point
        dp.print_inputsuccess();
      } while (p.trySet(bc, pat.getPattern(str.charAt(2))) == false);//try to see if can put a stack here
      p.set(bc,pat.getPattern(str.charAt(2)), pat.getColorchar());
      dp.print_placesuccess();
      dp.print_oneboard(p.ex_board);
    }
  }
  
  public void run(Scanner sc) {
    do{
      playerDig(p1, sc);
      playerDig(p2, sc);
    } while (!isGameOver());

    dp.print_winning(p1, p2);//print who wins
    
  }

  public boolean isGameOver() {
    if (p1.goldremain != 0 && p2.goldremain != 0) {
      return false;
    }
    return true;
  }

  public void playerDig(Player p, Scanner sc) {
    dp.print_asktoswitchperson(p.name,sc);
    dp.print_turninfo(p);
    dp.print_asktohit(p);
    String str;
    do{
      str = sc.nextLine();
      while (str.length() !=2){
        Display.print_nofcharsincorrect(2, str.length());
        str = sc.nextLine();
      }
      //got two characters
    }while(InputCheck.coord_rangecheck(str) == false);//see if the input is in bound
    dp.print_inputsuccess();
    BoardCoord bc=new BoardCoord(str);
    boolean found = p.dig(bc);
    dp.print_hitornot(found);
    dp.print_allset(sc);
    dp.print_refreshscreen();
  }
}
