/**
 * 
 */
package sait.frms.exception;

/**
 * This exception is thrown when the travelers name is missing.
 * @author Liam McLaughlin
 *
 */
public class InvalidNameException extends Exception{
	public InvalidNameException() {
		super("The traveler's name is missing. Please ensure that it is specified.");
	}
}
