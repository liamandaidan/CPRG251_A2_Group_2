package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.*;
import sait.frms.problemdomain.*;

/**
 * This is the Reservation manager that manages the data from Reservation
 * object.
 * 
 * @author Robyn Balanag, Liam McLaughlin
 * @version June, 25, 2021
 * 
 */

public class ReservationManager {

	private ArrayList<Reservation> reservations = new ArrayList<>();
	private final String RESERVATIONS_FILEPATH = "res/reservations.dat";

	/**
	 * Default constructor used to create a reservation manager object.
	 * 
	 * @throws FileNotFoundException
	 * 
	 */
	public ReservationManager() {

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
		Reservation newReservation = new Reservation(reservationsCode, flight.getCode(), name, flight.getAirlineName(),
				citizenship, flight.getCostPerSeat(), true);
		return newReservation;

	}

	/**
	 * This will find and return an araylist of reservations that contain search
	 * results that match reservations.
	 * 
	 * @param code    the code to use
	 * @param airline the airline code to use
	 * @param name    the name to use
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
					|| reservations.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
				foundReservation.add(reservations.get(i));
			} else {
				System.out.println("No reservation found.");
			}
		}

		if (foundReservation.size() == 0) {
			System.out.println("No reservation found.");// need to catch this error

		}

		return foundReservation;

	}

	/**
	 * This method will find reservation by using a code. It returns a Reservations
	 * object.
	 * 
	 * @param code the code to use
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
	 * This method will save Reservations to binary file(RESERVATIONS_FILEPATH) when
	 * called.
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
				name += r.getName() + " ";
			}

			System.out.println("Done!, " + name + ". Has been added to save file.");
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("The File cannot be found: " + RESERVATIONS_FILEPATH);
		} catch (IOException e) {
			System.out.println("You have encountered an IOEXCEPTION. Invalid data in outputStream.");
			e.getStackTrace();
		}

	}

	/**
	 * This method will find return how many available seats are left.
	 * 
	 * @param flight the flight object
	 * @return the number of available seats
	 */
	private int getAvailableSeats(Flight flight) {
		return flight.getSeats();
	}

	/**
	 * This method will generate a reservation code and return a string.
	 * 
	 * @param flight the flight object
	 * @return a generated confirmation code
	 * @throws InvalidFlightCodeException
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
	 * This method will bring in the reservations from binary and add it to the
	 * arrayList.
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
	}

	/**
	 * This will return the populated Arraylist with all the items.
	 * 
	 * @return reservations the reservations Arraylist.
	 */
	public ArrayList<Reservation> getPopulated() {

		return reservations;
	}

	/**
	 * 
	 */
	public void addInactive(ArrayList<Reservation> r){
		
	}
}
