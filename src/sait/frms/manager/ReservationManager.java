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

public class ReservationManager {

	private ArrayList<Reservation> reservations = new ArrayList<>();
	private final String RESERVATIONS_FILEPATH = "res/reservations.dat";
	private final double RESERVATION_COST = 189.00;

	/**
	 * 
	 */
	public ReservationManager() {

	}

	/**
	 * Will create a reservation object. With these parameters: String code, String
	 * flightCode, String airline, String name, String citizenship, double cost,
	 * boolean active
	 * 
	 * @return the Reservation
	 * @throws InvalidNameException
	 * @throws InvalidCitizenshipException
	 * @throws InvalidFlightCodeException
	 */

	public Reservation makeReservation(Flight flight, String name, String citizenship)
			throws InvalidNameException, InvalidCitizenshipException, InvalidFlightCodeException, NoMoreSeatsException {
		String reservationsCode;

		if (name.isBlank()) {
			throw new InvalidNameException();
		}
		if (citizenship.isBlank()) {
			throw new InvalidCitizenshipException();
		}
		if(getAvailableSeats(flight) <= 0) {
			throw new NoMoreSeatsException();
		}
		

		reservationsCode = generateReservationCode(flight);
		Reservation newReservation = new Reservation(reservationsCode, flight.getCode(), flight.getAirlineName(), name,
				citizenship, RESERVATION_COST, true);
		return newReservation;

	}

	/**
	 * @return the list of reservations
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundReservation = new ArrayList<>();

		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getCode().equals(code) || reservations.get(i).getAirline().equals(airline)
					|| reservations.get(i).getName().equals(name)) {
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
	 * This method will save Reservations to binary file(RESERVATIONS_FILEPATH).
	 */
	public void persist() {

		String formated;
		try {
			PrintWriter output = new PrintWriter(new File(RESERVATIONS_FILEPATH));
			for (int i = 0; i < reservations.size(); i++) {
				Reservation tempRes = reservations.get(i);
				formated = tempRes.toString();
				output.println(formated);
			}
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found! Can not save to file. Check @" + RESERVATIONS_FILEPATH);
		}

	}

	/**
	 * 
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
	 * This method will bring in the reservations from txt file. In the format of:
	 * this.code, this.flightCode, this.airline, this.name, this.citizenship,
	 * this.cost, this.active);
	 */
	private void populateFromBinary() {
		Reservation tempRes;// to be loaded into reservations later
		try {
			Scanner readIn = new Scanner(new File(RESERVATIONS_FILEPATH));

			while (readIn.hasNextLine()) {
				String readArr[] = readIn.nextLine().split(",");
				if (readArr.length == 7) {// check to make sure that our array has 7 items, if not its an error.
					tempRes = new Reservation(readArr[0], readArr[1], readArr[2], readArr[3], readArr[4],
							Double.parseDouble(readArr[5]), Boolean.parseBoolean(readArr[6]));
					reservations.add(tempRes);
				} else {
					System.out.println(
							"readArr is out of bounds. Contains more/less items then requried. Could not add items.");
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("File is not found! Can't read the save file. Check @" + RESERVATIONS_FILEPATH);
		}

	}

}
