package edu.duke.ece651.SallyStashGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class InputCheckTest {
  @Test
  public void test_placementinput() {
    InputCheck ic=new InputCheck();
    HashSet<Character> orien = new HashSet<>();
    orien.add('H');
    orien.add('V');
    assertEquals(false, ic.placement_inputcheck("M3V3",orien));
    assertEquals(false, ic.placement_inputcheck("Z33",orien));
    assertEquals(false, ic.placement_inputcheck("Ta3",orien));
    assertEquals(false, ic.placement_inputcheck("A9L",orien));
    assertEquals(true, ic.placement_inputcheck("A0V",orien));
  }

}
