package sait.frms.exception;

/**
 * This exception is thrown when there are no more seats available.
 * @author Robyn Balanag
 *
 */

public class NoMoreSeatsException extends Exception {

	public NoMoreSeatsException() {
		super("The flight selected has no more seats available. ");
	}
}