package sait.frms.manager;

import java.util.*;

import sait.frms.exception.*;
import sait.frms.problemdomain.*;

/**
 * This is the Reservation manager that manages the data from Reservation object
 * 
 * @author Robyn Balanag, Liam McLaughlin
 * @version June, 25, 2021
 * 
 *          TODO Refer to GITHub online for issues to be completed.
 */

public class ReservationManager {

	private ArrayList<Reservation> reservations = new ArrayList<>();

	/**
	 * 
	 */
	public ReservationManager() {
		
	}

	/**
	 * @return the Reservation
	 */
	public Reservation makeReservation(Flight flight, String name, String citizenship) {
		Reservation newReservation = new Reservation();

		return newReservation;

	}

	/**
	 * @return the list of reservations
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundReservation = new ArrayList<>();

		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getCode().equals(code) && reservations.get(i).getAirline().equals(airline)
					&& reservations.get(i).getName().equals(name)) {
				foundReservation.add(reservations.get(i));
			}
			if (foundReservation.size() == 0) {
				System.out.println("No reservation found.");
			}
		}

		return foundReservation;

	}

	/**
	 * @return the reservation using the code
	 */
	public Reservation findReservationByCode(String code) {

		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getCode().equals(code)) {
				return reservations.get(i);
			} else {
				System.out.println("No reservation found.");
				return null;
			}

		}

		return null;

	}

	/**
	 * 
	 */
	public void persist() {

	}

	/**
	 * @return the number of available seats
	 */
	private int getAvailableSeats(Flight flight) {
		return flight.getSeats();
	}

	/**
	 * The reservation code must use the format LDDDD, where L is either D for
	 * Domestic or I for International and DDDD is a random number between 1000 and
	 * 9999. Domestic Flights Start with Y AND from and to must both have Y.
	 * 
	 * 
	 * @return a generated confirmation code
	 */
	private String generateReservationCode(Flight flight) throws InvalidFlightCodeException {
		// Potentially Throws error of Having the wrong flight code.
		String code = "";
		char fromLetter = flight.getFrom().charAt(0);
		char toLetter = flight.getTo().charAt(0);
		if (fromLetter == toLetter && fromLetter == 'Y' && Character.isLetter(fromLetter)
				&& Character.isLetter(toLetter)) // if the first letter at From = first Letter of To AND if one letter
													// starts with Y
			code += "D";
		else if (Character.isLetter(fromLetter) && Character.isLetter(toLetter))// Check to see if both are Letter
																				// inputs.
			code += "I";
		else
			throw new InvalidFlightCodeException(flight.getFrom(), flight.getTo());
		code += (int) (Math.random() * 9000 + 1000); // 1000-9999

		return code;

	}

	/**
	 * 
	 */
	private void populateFromBinary() {

	}

}
