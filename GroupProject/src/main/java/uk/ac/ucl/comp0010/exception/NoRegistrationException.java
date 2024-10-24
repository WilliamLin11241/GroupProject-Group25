package uk.ac.ucl.comp0010.exception;

public class NoRegistrationException extends RuntimeException {

  private static final long serialVersionUID = 1L; // Added serialVersionUID

  public NoRegistrationException(String message) {
    super(message);
  }
}
