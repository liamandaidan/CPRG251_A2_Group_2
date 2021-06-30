/**
 * 
 */
package sait.frms.exception;

/**
 * This exception is thrown when a flight object is null.
 * 
 * @author Liam McLaughlin
 *
 */
public class NullFlightException extends Exception {

	public NullFlightException() {
		super("There was an error with the flight. Data is missing.");
	}

}
