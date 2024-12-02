/**
 * Exception thrown when no grade is available for a particular student or course.
 * This exception extends {@link RuntimeException}, indicating that it is an unchecked exception.
 * 
 * @param message The detail message explaining the reason for the exception.
 */
package uk.ac.ucl.comp0010.exception;

public class NoGradeAvailableException extends RuntimeException {

  private static final long serialVersionUID = 1L; // Added serialVersionUID

  public NoGradeAvailableException(String message) {
    super(message);
  }
}
