package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.exception.InvalidFlightCodeException;
import sait.frms.problemdomain.*;

/**
 * Used to manage the flight objects. Loads the flights and airports into arrays 
 * to store them.
 * 
 * @author Ben and Mike
 *
 */
public class FlightManager {
	
	/**
	 * Name for searching the day of week.
	 */
	public static final String WEEKDAY_ANY = "Any";
	
	/**
	 * Name for searching the day of week.
	 */
	public static final String WEEKDAY_SUNDAY = "Sunday";
	
	/**
	 * Name for searching the day of week. 
	 */
	public static final String WEEKDAY_MONDAY = "Monday";
	
	/**
	 * Name for searching the day of week.
	 */
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	
	/**
	 * Name for searching the day of week.
	 */
		public static final String WEEKDAY_WEDNESDAY = "Wednesday";
		
	/**
	 * Name for searching the day of week. 
	 */
		public static final String WEEKDAY_THURSDAY = "Thursday";
		
	/**
	 * Name for searching the day of week.
	 */
	public static final String WEEKDAY_FRIDAY = "Friday";
	
	/**
	 * Name for searching the day of week.
	 */
	public static final String WEEKDAY_SATURDAY = "Saturday";
	
	/**
	 * File location for the flights csv.
	 */
	public static final String FLIGHTS_TEXT = "res/flights.csv";
	
	/**
	 * File location for the airports csv. 
	 */
	public static final String AIRPORTS_TEXT = "res/airports.csv";
	
	/**
	 *  A list of all of the flights.
	 */
	private ArrayList<Flight> flights = new ArrayList<Flight>();
	
	/**
	 * A list of all of the airports.
	 */
	private ArrayList<String> airports = new ArrayList<String>();

	/**
	 * No Arg Constructor, populates the airports and flights ArrayLists
	 * @throws InvalidFlightCodeException
	 * @throws FileNotFoundException
	 * 
	 */
	public FlightManager() {
		populateAirports();
		populateFlights();

	}

	/**
	 *  The list of all the airports. 
	 * @return An ArrayLst list of the valid airports.
	 */
	public ArrayList<String> getAirports() {

		return airports;
	}

	/**
	 * The list of valid flights.
	 * @return A list of valid flights.
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Looks for the airport by code.
	 * @param code The flight code.
	 * @return An airport based on the code.
	 */
	public String findAirportByCode(String code) {
		for (int i = 0; i < airports.size(); i++) {
			if (airports.get(i).substring(0, 3).equalsIgnoreCase(code)) {
				return airports.get(i);
			}
		}

		return null;
	}

	/**
	 *  Find the flight by code.
	 * @param code The flight code.
	 * @return A flight based on the code.
	 */
	public Flight findFlightByCode(String code) {
		for (int i = 0; i < flights.size(); i++) {
			if (flights.get(i).getCode().equals(code)) {
				return flights.get(i);
			} else {
				System.out.println("Flight not found.");
				return null;
			}
		}
		return null;
	}

	/**
	 * If user selects a specific day, then search includes a check that the day matches. If any day is selected, and all flight days with matching flights are included
	 * @param from Where the flight is from.
	 * @param to Where the flight is to. 
	 * @param weekday The day of the week the flight is. 
	 * @return An Array List of flights that match the search
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		ArrayList<Flight> foundFlights = new ArrayList<Flight>();
		if(!weekday.equals("Any"))
		{
			for (int i = 0; i < flights.size(); i++) {
				if (flights.get(i).getFrom().equals(from) && flights.get(i).getTo().equals(to)
						&& flights.get(i).getWeekday().equals(weekday)) {
	
					foundFlights.add(flights.get(i));
				}
				
			}
		} else 
		{
			for (int i = 0; i < flights.size(); i++) {
				if (flights.get(i).getFrom().equals(from) && flights.get(i).getTo().equals(to))
				{
	
					foundFlights.add(flights.get(i));
				}
				
			}
		}
		if (foundFlights.size() == 0) {
			System.out.println("No flights matching the search parameters were found.");
		}

		return foundFlights;
	}

	/**
	 * Populates The array list with all of the flights.
	 * @throws InvalidFlightCodeException
	 * 
	 */
	private void populateFlights() {
		String[] validFlightCodes = { "OA", "CA", "TB", "VA" };
		String[] airlineNameList = { "Otto Airlines", "Conned Air", "Try a Bus Airways", "Vertical Airways" };
		String airLineName = "";
		String flightCode;
		String departingCode;
		String arrivalCode;
		String weekday;
		String time;
		int seats;
		double cost;
		int numbOfFlights = 0;

		// Add flights to the list of flights
		try {
			Scanner flightsReader = new Scanner(new File(FLIGHTS_TEXT));
			boolean isValid = false; // validity flag
			while (flightsReader.hasNext()) {

				// Split the string into an array to save as variables
				String[] flightsInfo = flightsReader.nextLine().split(",");

				// check code to make sure the format matches
				
				if (flightsInfo[0].matches("[A-Z]{2}\\-[0-9]{4}")) {

					// checks for the correct airline name and saves the value
					for (int i = 0; i < validFlightCodes.length; i++) {
						if (flightsInfo[0].substring(0, 7).contains(validFlightCodes[i])) {
							isValid = true;
							String airlineCode = flightsInfo[0].substring(0, 2).toUpperCase();
							switch (airlineCode) {
							case "OA":
								airLineName = airlineNameList[0];
								break;

							case "CA":
								airLineName = airlineNameList[1];
								break;
							case "TB":
								airLineName = airlineNameList[2];
								break;

							case "VA":
								airLineName = airlineNameList[3];
								break;
							}
							break;
						} else {
							isValid = false;
						}
					}

					// assigns the code to the proper value
					if (isValid) {
						flightCode = (isValid) ? flightsInfo[0] : null;

						departingCode = flightsInfo[1];
						arrivalCode = flightsInfo[2];
						weekday = flightsInfo[3];
						time = flightsInfo[4];
						seats = Integer.parseInt(flightsInfo[5]);
						cost = Double.parseDouble(flightsInfo[6]);

						// creates and adds the flight to the ArrayList
						flights.add(new Flight(flightCode, airLineName, departingCode, arrivalCode, weekday, time,
								seats, cost));
						numbOfFlights++;
					} 

				} 

			}


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Populates the array list with all of the valid airports that flights may
	 * come from.
	 * @throws FileNotFoundException
	 * 
	 */
	private void populateAirports() {
		String airportInfo = "";

		/*
		 * create add items from the airport csv to airports array as a string that
		 * includes the airport code AND airport name
		 */

		try {
			Scanner airportReader = new Scanner(new File(AIRPORTS_TEXT));
			while (airportReader.hasNext()) {
				airportInfo = airportReader.nextLine();
				airports.add(airportInfo);
			}
			airportReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Double check the \"airports.csv\" file location");
			e.printStackTrace();
		}

	}
}
