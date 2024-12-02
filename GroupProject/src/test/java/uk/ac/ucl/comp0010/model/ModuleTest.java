package uk.ac.ucl.comp0010.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModuleTest {

  private Module module;

  @BeforeEach
  public void setUp() {
    module = new Module();
    module.setCode("COMP0010");
    module.setName("Software Engineering");
    module.setMnc(true);
  }

  @Test
  public void testGetCode() {
    assertEquals("COMP0010", module.getCode());
  }

  @Test
  public void testSetCode() {
    module.setCode("COMP0020");
    assertEquals("COMP0020", module.getCode());
  }

  @Test
  public void testGetName() {
    assertEquals("Software Engineering", module.getName());
  }

  @Test
  public void testSetName() {
    module.setName("Advanced Software Engineering");
    assertEquals("Advanced Software Engineering", module.getName());
  }

  @Test
  public void testIsMnc() {
    assertTrue(module.isMnc());
  }

  @Test
  public void testSetMnc() {
    module.setMnc(false);
    assertFalse(module.isMnc());
  }

  @Test
  public void testGetGrades() {
    List<Grade> grades = new ArrayList<>();
    module.setGrades(grades);
    assertEquals(grades, module.getGrades());
  }

  @Test
  public void testSetGrades() {
    List<Grade> grades = new ArrayList<>();
    module.setGrades(grades);
    assertEquals(grades, module.getGrades());
  }
}
