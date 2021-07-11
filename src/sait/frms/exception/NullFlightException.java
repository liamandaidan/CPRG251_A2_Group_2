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

	/**
	 *The constructor for NullFlightException that sends the message to the super.
	 */
	public NullFlightException() {
		super("There was an error with the flight. Data is missing.");
	}

}
