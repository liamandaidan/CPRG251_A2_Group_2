package sait.frms.exception;

/**
 * @author Liam McLaughlin
 * @version June, 28, 2021
 *
 */
public class InvalidFlightCodeException extends Exception{

	/**
	 * Constructor for InvalidFlightCodeException that sends a message to the super class.
	 */
	public InvalidFlightCodeException() {
		super("Error: Flight code is not correct.");
	}

	/**
	 * Constructor for InvalidFlightCodeException that sends a message to the super class.
	 * @param from the from location value to use.
	 * @param to the to location value to use.
	 */
	public InvalidFlightCodeException(String from, String to) {
		super("Error: Flight could not be generated From: "+ from+ ", To: "+to);
	}
	
}
