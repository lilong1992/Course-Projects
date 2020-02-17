package edu.duke.ece651.SallyStashGame;

import java.util.HashMap;
import java.util.Scanner;

public class Display {
  private static final int ROWS = 20;
  private static final int COLUMNS=10;
  public HashMap<Integer, String> counterMap;
  
  public Display() {
    initialize_counterMap();
    
  }

  private void initialize_counterMap() {
    this.counterMap = new HashMap<Integer, String>();
    counterMap.put(1, "first");
    counterMap.put(2, "second");
    counterMap.put(3, "third");
  }

  public void print_boardUDside(boolean nextline) {
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
  
  public void print_oneboard(Board b) {
    this.print_boardUDside(true);
    for (int i = 0; i < ROWS; i++) {
      print_board_onerow(b, i);
      System.out.print("\n");
    }
    this.print_boardUDside(true);
  }

  public void print_twoboards(Board b1, Board b2) {
    int boardtoboard_gap=10;
    print_two_UDsides(boardtoboard_gap);

    for (int i = 0; i < ROWS; i++) {
      print_board_onerow(b1, i);
      this.print_gap(boardtoboard_gap);
      print_board_onerow(b2, i);
      System.out.print("\n");
    }

    print_two_UDsides(boardtoboard_gap);
  }

  private void print_two_UDsides(int boardtoboard_gap) {
    this.print_boardUDside(false);
    print_gap(boardtoboard_gap);
    this.print_boardUDside(true);
  }

  private void print_board_onerow(Board b, int i) {
    int c = 'A' + i;
    System.out.print((char) c + " ");
    System.out.print(b.grid[i][0]);
    for (int j = 1; j < COLUMNS; j++) {
      System.out.print("|" + b.grid[i][j]);
    }
    System.out.print(" " + (char) c);
  }

  private void print_gap(int gap) {
    for (int i = 0; i < gap; i++) {
      System.out.print(" ");
    }
  }

  public void print_welcome_msg(String nameA, String nameB) {
    System.out.println("Player "+nameA+", you are going place Sally’s stash on the board. Make sure Player "+nameB+" isn’t looking! For each stack, type the coordinate of the upper left side of the stash (A-T, 0-9), followed by either H (for horizontal) or V (for vertical). For example, M4H would place a stack horizontally starting at M4 and going to the right. You have ");
    System.out.println("2 Green stacks that are  1x2 ");
    System.out.println("3 Purple stacks that are  1x3 ");
    System.out.println("3 Red stacks that are  1x4 ");
    System.out.println("2 Blue stacks that are  1x6 ");
  }

  public void print_asktoputstack(int times, String name, String color) {
    System.out.println("Player "+name+", where do you want to place the "+counterMap.get(times)+" "+color+ " stack?");
  }

    
  public void print_refreshscreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }

  public void print_dashline() {
    for (int i = 0; i < 60; i++) {
      System.out.print('-');
    }
    System.out.println();
  }

  //game information

  public void print_turninfo(Player p) {
    print_dashline();
    System.out.println("Player "+p.name+"'s turn:");
    print_gap(5);
    System.out.print("Your tree");
    print_gap(21);
    System.out.println("Player "+p.rival.name+"'s tree");
    print_twoboards(p.ex_board, p.rival.hid_board);
    print_dashline();
  }

  public void print_asktohit(Player p) {
    System.out.println("Player "+ p.name +", choose a place in Player "+p.rival.name+" to hit");
  }


  public void print_winning(Player p1, Player p2) {
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

  //some feedback info output
  public void print_inputsuccess() {
    System.out.println("Input Success!");
  }

  public void print_placesuccess() {
    System.out.println("Placement Success!");
  }

  public void print_hitornot(boolean found) {
    if (found) {
      System.out.println("You found a stack!");
    }
    else {
      System.out.println("You missed!");
    }
  }

  public void print_allset(Scanner sc) {
    System.out.println("You are done, press enter to switch players.");
    sc.nextLine();
  }

  public void print_asktoswitchperson(String s,Scanner sc) {
    System.out.println("If you are Player "+s+", please hit enter...");
    sc.nextLine();
   }
}
