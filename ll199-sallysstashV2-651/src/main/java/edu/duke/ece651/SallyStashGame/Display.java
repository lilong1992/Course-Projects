package edu.duke.ece651.SallyStashGame;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Display {
  private static final int ROWS = 20;
  private static final int COLUMNS=10;
  private static final Map<Integer, String> counterMap=Map.of(
                                                                      1,"first",
                                                                      2,"second",
                                                                      3,"third",
                                                                      4,"fourth"
                                                                      );
  public Display() {// a class to print different things on the screen
    
  }



  public static void print_boardUDside(boolean nextline) {
    System.out.print("  0");
    for (int i = 1; i < COLUMNS; i++) {
      System.out.print("|");
      System.out.print(i);
    }
    System.out.print("  ");
    if (nextline) {
      System.out.print("\n");
    }
  }
  
  public static void print_oneboard(Board b) {
    print_boardUDside(true);
    for (int i = 0; i < ROWS; i++) {
      print_board_onerow(b, i);
      System.out.print("\n");
    }
    print_boardUDside(true);
  }

  public static void print_twoboards(Board b1, Board b2) {
    int boardtoboard_gap=10;
    print_two_UDsides(boardtoboard_gap);

    for (int i = 0; i < ROWS; i++) {
      print_board_onerow(b1, i);
      print_gap(boardtoboard_gap);
      print_board_onerow(b2, i);
      System.out.print("\n");
    }

    print_two_UDsides(boardtoboard_gap);
  }

  private static void print_two_UDsides(int boardtoboard_gap) {
    print_boardUDside(false);
    print_gap(boardtoboard_gap);
    print_boardUDside(true);
  }

  private static void print_board_onerow(Board b, int i) {
    int c = 'A' + i;
    System.out.print((char) c + " ");
    System.out.print(b.grid[i][0]);
    for (int j = 1; j < COLUMNS; j++) {
      System.out.print("|" + b.grid[i][j]);
    }
    System.out.print(" " + (char) c);
  }

  private static void print_gap(int gap) {
    for (int i = 0; i < gap; i++) {
      System.out.print(" ");
    }
  }

  public static void print_title() {
    System.out.println("Sally's Stash V2!!!!!!!!!!!!!!!");
  }
  
  public static void print_player_NPCcheckmsg(String s) {
    System.out.println("Is player "+s+" going to be a human or a bot? Enter 1 for human or 2 for bot.");
  }

  public static void print_welcome_msg(String nameA, String nameB) {
    System.out.println("Player " + nameA + ", you are going place Sally’s stash on the board. Make sure Player "
                       + nameB
                       + " isn’t looking! For each stack, type the coordinate of the upper left side of the stash (A-T, 0-9), followed by either H (for horizontal) or V (for vertical).In case of Blue and Red, the orientation can be U D L R. For example, M4H would place a stack horizontally starting at M4 and going to the right. You have ");
    System.out.println("2 Green stacks that are  1x2 ");
    System.out.println("3 Purple stacks that are  1x3 ");
    System.out.println("3 Red stacks that are  \"T\" like ");
    System.out.println("3 Blue stacks that are  \"Z\" like ");
    
  }

  public static void print_asktoputstack(int times, String name, String color) {
    System.out.println("Player "+name+", where do you want to place the "+counterMap.get(times)+" "+color+ " stack?");
  }

    
  public static void print_refreshscreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }

  public static void print_dashline() {
    for (int i = 0; i < 60; i++) {
      System.out.print('-');
    }
    System.out.println();
  }

  //game information

  public static void print_turninfo(Player p) {
    print_dashline();
    System.out.println("Player "+p.name+"'s turn:");
    print_gap(5);
    System.out.print("Your tree");
    print_gap(21);
    System.out.println("Player "+p.rival.name+"'s tree");
    print_twoboards(p.ex_board, p.rival.hid_board);
    print_dashline();
  }

  public static void print_asktohit(Player p) {
    System.out.println("Player "+ p.name +", choose a place in Player "+p.rival.name+" to hit");
  }


  public static void print_winning(Player p1, Player p2) {
    if (p1.goldremain == p2.goldremain) {
      System.out.println("Tied!");
      return;
    }
    
    if (p1.goldremain == 0) {
      System.out.println("Player "+p2.name+" wins!");
    }
    else {
      System.out.println("Player "+p1.name+" wins!");
    }
  }

  //some error info output
  public static void print_nofcharsincorrect(int expected, int seen) {
    System.out.println("Expected input length = "+expected+", but saw "+seen);
  }

  public static void print_modeerror(String s) {
    System.out.println("Non acceptable orientation, pick one from " + s);
  }

  public static void print_xoutofbound() {
    System.out.println("The first coordinate should be within A to "+ (char) ('A'+ROWS-1));
  }

  public static void print_youtofbound() {
    System.out.println("The second coordinate should be within 0 to "+ (char) ('0'+COLUMNS-1));
  }

  public static void print_occupiedbyotherstack() {
    System.out.println("There are already some stacks there.");
  }

  public static void print_stackoutofbound() {
    System.out.println("The stack you are going to put is out of bound.");
  }

  public static void print_errorinNPCselection() {
    System.out.println("Enter 1 for human or 2 for bot.");
  }

  public static void print_nomoremove() {
    System.out.println("You have no more moves choose another.");
  }

  public static void print_nomoresonar() {
    System.out.println("You have no more sonars choose another.");
  }

  public static void print_wrongstackchoose() {
     System.out.println("The coordinates you choose belongs to none of the stack.");
   }

   public static void print_stackmovefail() {
     System.out.println("Sorry, the place you are trying to move to is not valid.");
   }

   public static void print_invalidaction() {
     System.out.println("Sorry, please choose a mode from D M and S");
   }

  //some feedback info output
  public static void print_inputsuccess() {
    System.out.println("Input OK!");
  }

  public static void print_placesuccess() {
    System.out.println("Placement Success!");
  }

  public static void print_hitornot(boolean found) {
    if (found) {
      System.out.println("You found a stack!");
    }
    else {
      System.out.println("You missed!");
    }
  }

  public static void print_allset(Scanner sc) {
      System.out.println("You are done, press enter to switch players.");
      sc.nextLine();
    
  }

  public static void print_turnselection(String name,int move, int sonar) {
    System.out.println("Player "+name+", choose one action to take:");
    System.out.println("D Dig in a square");
    System.out.println("M Move a stack to another square ("+move+" remaining)");
    System.out.println("S Sonar scan ("+sonar+" remaining)");
  }

  public static void print_asktoswitchperson(String s,Scanner sc) {
    System.out.println("If you are Player "+s+", please hit enter...");
    sc.nextLine();
   }

  public static void print_stackchoosetomove(ArrayList<ArrayList<BoardCoord>> positionofgold,int nofStacks) {
    System.out.println("select a coordinate in one of the follwing stacks you want to move");
    for (int i = 0; i < nofStacks; i++) {
      System.out.print("Stack "+i+":");
      for (BoardCoord bc : positionofgold.get(i)) {
        System.out.print(bc.toStr()+" ");
      }
      System.out.println();
    }
   }

  public static void print_wheretomove() {
     System.out.println("Enter the new coordinate and orintation to put the stack.");
   }

   public static void print_wheretoscan() {
     System.out.println("Enter the coordinate of the sonar center.");
   }

  public static void print_sonarcountinfo(int nofgs,int nofps,int nofrs,int nofbs) {
     System.out.println("Green stacks occupy "+nofgs+" squares ");
     System.out.println("Purple stacks occupy "+nofps+" squares ");
     System.out.println("Red stacks occupy "+nofrs+" squares ");
     System.out.println("Blue stacks occupy "+nofbs+" squares ");
   }

   public static void print_NPCspecialaction(String name) {
     System.out.println("Player "+name+" used a special action");
   }

  public static void print_NPCfoundgold(String name,BoardCoord bc) {
     System.out.println("Player "+name+" found your Blue stack at "+bc.toStr()+"!");
   }
}
