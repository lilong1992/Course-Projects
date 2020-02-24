package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class InputCheckTest {
  @Test
  public void test_placementinput() {
    HashSet<Character> orien = new HashSet<>();
    orien.add('H');
    orien.add('V');
    assertEquals(false, InputCheck.placement_inputcheck("M3V3",orien));
    assertEquals(false, InputCheck.placement_inputcheck("Z33",orien));
    assertEquals(false, InputCheck.placement_inputcheck("Ta3",orien));
    assertEquals(false, InputCheck.placement_inputcheck("A9L",orien));
    assertEquals(true, InputCheck.placement_inputcheck("A0V",orien));
    assertEquals(true, InputCheck.NPC_selectionSTRcheck("1"));
    assertEquals(false, InputCheck.NPC_selectionSTRcheck("1123"));
    assertEquals(true, InputCheck.NPC_selectionSTRcheck("2"));
  }

}
