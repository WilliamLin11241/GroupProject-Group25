/**
 * Exception thrown when a student is not found in the system.
 * This exception extends {@link RuntimeException}.
 * 
 * @param message the detail message explaining the reason for the exception
 */
package uk.ac.ucl.comp0010.exception;

public class StudentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public StudentNotFoundException(String message) {
    super(message); // Passes the message to the RuntimeException constructor
  }
}
