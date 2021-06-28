package sait.frms.manager;

import java.io.*;
import java.util.*;

import sait.frms.problemdomain.*;

/**
 * 
 * @author Ben and Mike
 *
 */
public class FlightManager {

	public static final String WEEKDAY_ANY = "Any";
	public static final String WEEKDAY_SUNDAY = "Sunday";
	public static final String WEEKDAY_MONDAY = "Monday";
	public static final String WEEKDAY_TUESDAY = "Tuesday";
	public static final String WEEKDAY_WEDNESDAY = "Wednesday";
	public static final String WEEKDAY_THURSDAY = "Thursday";
	public static final String WEEKDAY_FRIDAY = "Friday";
	public static final String WEEKDAY_SATURDAY = "Saturday";

	public static final String FLIGHTS_TEXT = "res/flights.csv";
	public static final String AIRPORTS_TEXT = "res/airports.csv";

	private ArrayList<Flight> flights = new ArrayList<Flight>();
	private ArrayList<String> airports = new ArrayList<String>();

	/**
	 * @throws FileNotFoundException
	 * 
	 */
	public FlightManager() {
		populateAirports();
		populateFlights();
//		System.out.println(findFlightByCode("OA-7540").toString());

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getAirports() {

		return airports;
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public String findAirportByCode(String code) {
		return null;
	}

	/**
	 * 
	 * @param code
	 * @return
	 */
	public Flight findFlightByCode(String code) {
		for (int i = 0; i< flights.size(); i++ ) {
			if(flights.get(i).getCode().equals(code)) {
				return flights.get(i);
			}else {
				System.out.println("Flight not found.");
				return null;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param from
	 * @param to
	 * @param weekday
	 * @return
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday) {
		return null;
	}

	/**
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

				}  /*else {

					// this just for testing
					System.out.println("****************************************");
					System.out.println("****************************************");
					System.out.println("****************************************");
					System.out.println("This line is not printed" + flightsInfo[0]);
					System.out.println("****************************************");
				}*/

			}
			// for testing -> prints out all of the flights 
//			System.out.println(flights.get(0).toString());
//			for (Flight f : flights) {
//				System.out.println(f.toString());
//
//			}
//			System.out.println(numbOfFlights);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
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
