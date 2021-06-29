package sait.frms.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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

public class ReservationManager extends Reservation {

	private ArrayList<Reservation> reservations = new ArrayList<>();
	private final String RESERVATIONS_FILEPATH = "res/reservations.txt";
	/**
	 * 
	 */
	public ReservationManager() {
		super();
		// TODO Auto-generated constructor stub
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
		return reservations;
	}

	/**
	 * @return the reservation using the code
	 */
	public Reservation findReservationByCode(String code) {
		Reservation reservationByCode = new Reservation();
		return reservationByCode;

	}

	/**
	 * This method will save Reservations to binary file(RESERVATIONS_FILEPATH).
	 */
	public void persist() {
		
		String formated;

		File saveFile = new File(RESERVATIONS_FILEPATH); 
		try {
			PrintWriter output = new PrintWriter(saveFile);
			for(int i = 0; i < reservations.size();i++) {
				Reservation tempRes = reservations.get(i);
				formated = tempRes.toString();
				output.println(formated);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found! Check @"+RESERVATIONS_FILEPATH);
		}
		
	}

	/**
	 * 
	 * @return the number of available seats
	 */
	private int getAvailableSeats() {
		return 0;
	}

	/**
	 * The reservation code must use the format LDDDD, where L is either D for
	 * Domestic or I for International and DDDD is a random number between 1000 and
	 * 9999. Domestic Flights Start with Y AND from and to must both have Y.
	 * 
	 * 
	 * @return a generated confirmation code
	 */
	private String generateReservationCode(Flight flight) throws InvalidFlightCodeException{
		//Potentially Throws error of Having the wrong flight code.
		String code = "";
		char fromLetter = flight.getFrom().charAt(0);
		char toLetter = flight.getTo().charAt(0);
			if (fromLetter == toLetter && fromLetter == 'Y' && Character.isLetter(fromLetter)&&Character.isLetter(toLetter)) //if the first letter at From = first Letter of To AND if one letter starts with Y
				code += "D";
			else if(Character.isLetter(fromLetter)&&Character.isLetter(toLetter))//Check to see if both are Letter inputs.
				code += "I";
			else
				throw new InvalidFlightCodeException(flight.getFrom(),flight.getTo());
			code += (int)(Math.random()*9000+1000); //1000-9999

		return code;

	}

	/**
	 * 
	 */
	private void populateFromBinary() {

	}

}
