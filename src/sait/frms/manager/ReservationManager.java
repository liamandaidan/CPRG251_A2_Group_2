package sait.frms.manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	 * @throws FileNotFoundException
	 * 
	 */
	public ReservationManager() {
		/*
		 * try { populateFromBinary(); } catch (FileNotFoundException e) {
		 * System.out.println("Cant find file"); e.printStackTrace(); }
		 */
	}

	public ReservationManager(Reservation r) {
		this.reservations.add(r);
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
		if (getAvailableSeats(flight) <= 0) {
			throw new NoMoreSeatsException();
		}

		reservationsCode = generateReservationCode(flight);
		Reservation newReservation = new Reservation(reservationsCode, flight.getCode(), name.toLowerCase(), flight.getAirlineName(),
				citizenship, RESERVATION_COST, true);
		return newReservation;

	}

	/**
	 * @return the list of reservations
	 * 
	 */
	public ArrayList<Reservation> findReservations(String code, String airline, String name) {
		ArrayList<Reservation> foundReservation = new ArrayList<Reservation>();

		try {
			populateFromBinary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getCode().equals(code) || reservations.get(i).getAirline().equals(airline)
					|| reservations.get(i).getName().contains(name.toLowerCase())) {
				foundReservation.add(reservations.get(i));
			}else {
				System.out.println("No reservation found.");	
			}
		}
			
		if (foundReservation.size() == 0) {
			System.out.println("No reservation found.");//need to catch this error 
	
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
			}
		}
		return null;

	}

	/**
	 * This method will save Reservations to binary file(RESERVATIONS_FILEPATH).
	 */
	public void persist() {

		FileOutputStream fstream;
		try {
			fstream = new FileOutputStream(RESERVATIONS_FILEPATH);
			DataOutputStream outputStream = new DataOutputStream(fstream);
			System.out.println("Writing the words to a binary file");
			Reservation r;
			String name = "";
			for (int i = 0; i < reservations.size(); i++) {
				r = reservations.get(i);
				outputStream.writeUTF(r.getCode());
				outputStream.writeUTF(r.getFlightCode());
				outputStream.writeUTF(r.getName());
				outputStream.writeUTF(r.getAirline());
				outputStream.writeUTF(r.getCitizenship());
				outputStream.writeDouble(r.getCost());
				outputStream.writeBoolean(r.isActive());
				name += r.getName()+" ";
			}

			System.out.println("Done!, "+name+". Has been added to save file.");
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("The File cannot be found: " + RESERVATIONS_FILEPATH);
		} catch (IOException e) {
			System.out.println("You have encountered an IOEXCEPTION. Invalid data in outputStream.");
			e.getStackTrace();
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
	 * 
	 * @throws FileNotFoundException
	 */
	private void populateFromBinary() throws FileNotFoundException {
		Reservation r;
		FileInputStream fstream = new FileInputStream(RESERVATIONS_FILEPATH);
		DataInputStream inputStream = new DataInputStream(fstream);

		System.out.println("reading from a file");

		boolean endOfFile = false;
		String code, flightCode, airline, name, citizenship;
		double cost;
		boolean active;
		while (!endOfFile) {
			try {
				code = inputStream.readUTF();
				flightCode = inputStream.readUTF();
				name = inputStream.readUTF();
				airline = inputStream.readUTF();
				citizenship = inputStream.readUTF();
				cost = inputStream.readDouble();
				active = inputStream.readBoolean();
				r = new Reservation(code, flightCode, name, airline, citizenship, cost, active);
				reservations.add(r);
			} catch (IOException e) {
				endOfFile = true;
			}
		}
		System.out.println("Done");
		// System.out.println(reservations);
	}

	/**
	 * This will return the populated list. For now this is an extra method might
	 * get rid of it later
	 * 
	 * @return
	 */
	public ArrayList<Reservation> getPopulated() {

		return reservations;
	}

}
