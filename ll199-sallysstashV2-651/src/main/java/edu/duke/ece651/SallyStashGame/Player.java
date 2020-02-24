package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
  
  public int sonar;
  public int move;
  public int goldremain;
  public String name;
  public Player rival;
  public Board ex_board;//the status of player's board
  public Board hid_board;//be shown to the rival
  public ArrayList<ArrayList<BoardCoord>> positionofgold;//for move purpose
  public ArrayList<Pattern> patternofstack;//for move purpose, store color string of each stack
  public int nofStacks;
  public boolean isHuman;

  public Player(String name_str) {
    this.sonar = 3;
    this.move = 3;
    this.goldremain = 43;
    this.name = name_str;
    this.ex_board = new Board();
    this.hid_board = new Board();
    this.positionofgold = new ArrayList<ArrayList<BoardCoord>>();
    this.patternofstack = new ArrayList<Pattern>();
    this.nofStacks = 0;
    this.isHuman = true;
  }

  public void setRival(Player p){
    this.rival = p;
  }

  public void setPatterns(Pattern pat, Scanner sc) {
    String str;
    BoardCoord bc = new BoardCoord();
    do {
      str = getplaceStr(sc, pat);
      bc.setCoord(str.substring(0, 2));//got a leagal input then set starting point
      Display.print_inputsuccess();
    } while (trySet(bc, pat.getPattern(str.charAt(2)),ex_board) == false);//try to see if can put a stack here
    set(bc, pat.getPattern(str.charAt(2)), pat.getColorchar());
    patternofstack.add(pat);
    nofStacks++;
    Display.print_placesuccess();
    Display.print_oneboard(ex_board);
  }
  
  public boolean trySet(BoardCoord bc, ArrayList<BoardCoord> bcArr,Board b) {
    for (BoardCoord patBC : bcArr) {
      BoardCoord destBC = bc.add(patBC);
      if (InputCheck.coord_rangecheck_silent(destBC)) {
        if (b.get(destBC) != ' ') {
          if (this.isHuman) {
            Display.print_occupiedbyotherstack();//it's been occupied
          }
          return false;
        }
      }
      else {
        if (this.isHuman) {
          Display.print_stackoutofbound();
        }
        return false;
      }
    }
    return true;
  }

  public void set(BoardCoord bc, ArrayList<BoardCoord> bcArr, char c) {//set patterns with char c
    ArrayList<BoardCoord> destBCArr = new ArrayList<BoardCoord>();
    for (BoardCoord patBC : bcArr) {
      BoardCoord destBC = bc.add(patBC);
      ex_board.set(destBC, c);
      destBCArr.add(destBC);
    }
    this.positionofgold.add(destBCArr);
  }

  public void loseGold() {
    this.goldremain--;
  }

  public void playTurn(Scanner sc,boolean firsttime) {
    Display.print_turninfo(this);
    Display.print_turnselection(name, move, sonar);
    String choose = getselectionStr(sc);
    boolean turnend = false;
    switch (choose) {
    case "D":
      turnend = digTurn(sc);
      break;
    case "M":
      turnend = moveTurn(sc);
      break;
    case "S":
      turnend = sonarTurn(sc);
      break;
    }

    if (turnend == false) {//not manage to end the turn, replay this turn again
      playTurn(sc,false);
    }
    if (firsttime) {
      Display.print_allset(sc);
      if (rival.isHuman) {
        Display.print_refreshscreen();//make sure p2 doesn't see p1's info
        Display.print_asktoswitchperson(rival.name, sc);//make sure p1 doesn't see p2's info
      }
    }
  }
  
  public boolean digTurn(Scanner sc) {
        
    Display.print_asktohit(this);

    String str=getInboundStr(sc);
    Display.print_inputsuccess();
    BoardCoord bc=new BoardCoord(str);
    boolean found=dig(bc);
    Display.print_hitornot(found);
    
    return true;
  }

  public boolean dig(BoardCoord bc) {
    if (rival.ex_board.get(bc) == ' ') {//miss
      rival.hid_board.set(bc, 'X');
      return false;
    }
    else {
      //found the gold
      if (rival.ex_board.get(bc) == '*') {//already found, could be from moving
        int stackN = rival.whichstack(bc);
        Pattern pat = rival.patternofstack.get(stackN);
        rival.hid_board.set(bc, pat.getColorchar());
      }
      else {
        rival.hid_board.set(bc, rival.ex_board.get(bc));//show the stack type
        rival.ex_board.set(bc, '*');//rival will see his gold is digged
        rival.loseGold();
      }
      return true;
    }
  }

  public boolean moveTurn(Scanner sc) {
    if (move == 0) {
      Display.print_nomoremove();
      return false;
    }

    Display.print_stackchoosetomove(positionofgold, nofStacks);
    String strcoord = getInboundStr(sc);//use choose a stack
    BoardCoord oldbc = new BoardCoord(strcoord);
    int stackindex = whichstack(oldbc);
    if (stackindex == -1) {
      Display.print_wrongstackchoose();
      return false;
    }
    Pattern oldpat=patternofstack.get(stackindex);

    Display.print_wheretomove();//player choose where to move
    String newplace = getplaceStr(sc, oldpat);
    Display.print_inputsuccess();
    BoardCoord newbc = new BoardCoord(newplace.substring(0, 2));
    if (tryMove(newbc, oldpat.getPattern(newplace.charAt(2)), positionofgold.get(stackindex)) == true) {
      move(newbc,oldpat.getPattern(newplace.charAt(2)),stackindex);
    }
    else {
      Display.print_stackmovefail();
      return false;
    }
    
    
    move--;
    return true;
  }

  public boolean tryMove(BoardCoord newbc, ArrayList<BoardCoord> newbcArr,ArrayList<BoardCoord> oldbcArr) {
    Board tmpb = new Board(ex_board);
    for (BoardCoord oldbc : oldbcArr) {
      tmpb.set(oldbc, ' ');//clear old stacks
    }
    
    return trySet(newbc, newbcArr, tmpb);
  }

  public void move(BoardCoord newbc,ArrayList<BoardCoord> newbcArr,int stackindex) {
    ArrayList<Character> oldstackchar=new ArrayList<Character>();//get old pattern info in order
    ArrayList<BoardCoord> destBCArr = new ArrayList<BoardCoord>();
    
    for (BoardCoord oldbc : positionofgold.get(stackindex)) {
      oldstackchar.add(ex_board.get(oldbc));//save old pattern info
      ex_board.set(oldbc, ' ');//clear old stacks
    }

    for (int i = 0; i < newbcArr.size();i++) {
      BoardCoord destBC = newbc.add(newbcArr.get(i));
      ex_board.set(destBC, oldstackchar.get(i));
      destBCArr.add(destBC);
    }

    Pattern oldpat = patternofstack.get(stackindex);
    patternofstack.remove(stackindex);
    positionofgold.remove(stackindex);//clean old stack info

    patternofstack.add(oldpat);
    positionofgold.add(destBCArr);
  }

  public int whichstack(BoardCoord oldbc) {//return which stack the bc is in, -1 if not found
    for (int i = 0; i < nofStacks; i++) {
      for (BoardCoord bc : positionofgold.get(i)) {
        if (bc.equal(oldbc)) {
          return i;
        }
      }
    }
    return -1;
  }
  
  public boolean sonarTurn(Scanner sc) {
    if (sonar == 0) {
      Display.print_nomoresonar();
      return false;
    }
    Display.print_wheretoscan();
    String censtr = getInboundStr(sc);
    BoardCoord cenbc = new BoardCoord(censtr);
    sonarInfo(cenbc);

    sonar--;
    return true;
  }

  public void sonarInfo(BoardCoord cenbc) {//display the sonar on the board and counts squares
    ArrayList<BoardCoord> sonarArr = new ArrayList<BoardCoord>();
    SonarStack ss = new SonarStack();
    for (BoardCoord patbc : ss.getPattern()) {
      BoardCoord destbc = cenbc.add(patbc);
      if (InputCheck.coord_rangecheck_silent(destbc)) {//see the bcs in the board, add to sonarArr
        sonarArr.add(destbc);
      }
    }
    Board tempb = new Board(rival.hid_board);
    int nofbs = 0;
    int nofrs = 0;
    int nofgs = 0;
    int nofps = 0;
    for (BoardCoord destbc : sonarArr) {
      tempb.set(destbc, ss.getColorchar());
      //get number of squares
      if (rival.ex_board.get(destbc) == 'g') {
        nofgs++;
      }
      if (rival.ex_board.get(destbc) == 'b') {
        nofbs++;
      }
      if (rival.ex_board.get(destbc) == 'p') {
        nofps++;
      }
      if (rival.ex_board.get(destbc) == 'r') {
        nofrs++;
      }
    }
    tempb.set(cenbc, 'C');
    Display.print_oneboard(tempb);
    Display.print_sonarcountinfo(nofgs, nofps, nofrs, nofbs);
  }

  public String getInboundStr(Scanner sc) {// get a in bound coordinates string
    String str;
    do{
      str = sc.nextLine();
      while (str.length() !=2){
        Display.print_nofcharsincorrect(2, str.length());
        str = sc.nextLine();
      }
      //got two characters
    }while(InputCheck.coord_rangecheck(str) == false);//see if the input is in bound
    return str;
  }

  public String getselectionStr(Scanner sc) {//get a legal mode selection string
    String str;
    do{
      str = sc.nextLine();
      while (str.length() !=1){
        Display.print_nofcharsincorrect(1, str.length());
        str = sc.nextLine();
      }
      System.out.println(str);
      //got 1 character
    }while(InputCheck.turn_selectionSTRcheck(str)== false);//see if the input is D M or S
    return str;
  }

  public String getplaceStr(Scanner sc, Pattern pat) {//get legal placement string
    String str;
    do {//get a leagal input
        str = sc.nextLine();
      } while (InputCheck.placement_inputcheck(str, pat.getMode()) == false);
    return str;
  }
}
