/**
 * Exception thrown when a registration is not found.
 * 
 * <p>This exception is a subclass of {@link RuntimeException} and is used to indicate
 * that a specific registration-related operation could not be completed because the
 * registration was not found.</p>
 * 
 * @see RuntimeException
 */
package uk.ac.ucl.comp0010.exception;

public class NoRegistrationException extends RuntimeException {

  private static final long serialVersionUID = 1L; // Added serialVersionUID

  public NoRegistrationException(String message) {
    super(message);
  }
}
