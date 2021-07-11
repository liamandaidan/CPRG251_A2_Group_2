/**
 * 
 */
package sait.frms.exception;

/**
 * This exception is thrown when citizenship is missing.
 * @author Liam McLaughlin
 *
 */
public class InvalidCitizenshipException extends Exception{

	/**
	 *Constructor for InvalidCitizenShipException that sends a message to the super. 
	 */
	public InvalidCitizenshipException() {
		super("Traveler's citizenship is missing. Please ensure the proper citizenship is inserted.");
	}
	
}
