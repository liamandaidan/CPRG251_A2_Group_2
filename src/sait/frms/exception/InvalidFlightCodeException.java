package sait.frms.exception;

/**
 * @author Liam McLaughlin
 * @version June, 28, 2021
 *
 */
public class InvalidFlightCodeException extends Exception{

	public InvalidFlightCodeException() {
		super("Error: Flight code is not correct.");
	}
	public InvalidFlightCodeException(String from, String to) {
		super("Error: Flight code is not correct: From: "+ from+ ", To: "+to);
	}
	
}
