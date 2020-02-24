package edu.duke.ece651.SallyStashGame;

import java.util.Scanner;

public class Game {
  public Player p1;
  public Player p2;

  public Game() {
    //for testing
  }
  
  public Game(Scanner sc) {
    Display.print_title();
    this.p1=createHumanorNPCplayer(sc,"A");
    this.p2=createHumanorNPCplayer(sc,"B");
       
    p1.setRival(p2);
    p2.setRival(p1);
    
  }

  public Player createHumanorNPCplayer(Scanner sc, String name) {
    Display.print_player_NPCcheckmsg(name);
    String NPCselection = sc.nextLine();
    while (InputCheck.NPC_selectionSTRcheck(NPCselection) == false) {
      NPCselection = sc.nextLine();
    }
    if (NPCselection.equals("1")) {
      return new Player(name);
    }
    else {
      return new NPCPlayer(name);
    }
  }

  public void playerPrepare(Player p,Scanner sc) {
    if (p.isHuman) {
      Display.print_welcome_msg(p.name, p.rival.name);
    }
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
    int nofbs = 3;
    setPatterns(p, nofbs, bs, sc);
    
    if (p.isHuman) {
      Display.print_allset(sc);
    }
    if (p.rival.isHuman) {
      Display.print_refreshscreen();//make sure p2 doesn't see p1's setup
    }
    
  }

  public void setPatterns(Player p, int times, Pattern pat,Scanner sc) {
      for (int i = 0; i < times; i++) {
        if (p.isHuman) {
          Display.print_asktoputstack(i + 1, p.name, pat.getColor());
        }
        p.setPatterns(pat, sc);
        
      }
  }
  
  public void run(Scanner sc) {
    do{
      p1.playTurn(sc,true);
      p2.playTurn(sc,true);
    } while (!isGameOver());

    Display.print_winning(p1, p2);//print who wins
    
  }

  public boolean isGameOver() {
    if (p1.goldremain != 0 && p2.goldremain != 0) {
      return false;
    }
    return true;
  }

  
}
