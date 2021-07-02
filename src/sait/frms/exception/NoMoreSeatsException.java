package sait.frms.exception;

public class NoMoreSeatsException extends Exception {

	public NoMoreSeatsException() {
		super("The flight selected has no more seats available. ");
	}
}