package uk.ac.ucl.comp0010.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MovieTest {

  @Test
  void getAverageTest() {
    Student student = null;
    assertEquals(4.0, student.computeAverage());
  }

}
