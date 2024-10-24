package uk.ac.ucl.comp0010.exception;

public class NoGradeAvailableException extends RuntimeException {

  private static final long serialVersionUID = 1L; // Added serialVersionUID

  public NoGradeAvailableException(String message) {
    super(message);
  }
}
