package sait.frms.exception;

/**
 * This exception is thrown when there are no more seats available.
 * @author Robyn Balanag
 *
 */

public class NoMoreSeatsException extends Exception {

	/**
	 *The constructor for NoMoreSeatsException that sends the message to the super.
	 */
	public NoMoreSeatsException() {
		super("The flight selected has no more seats available. ");
	}
}