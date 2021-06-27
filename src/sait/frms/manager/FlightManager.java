package sait.frms.manager;

import java.util.ArrayList;
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
	 * 
	 */
	public FlightManager()
	{
		
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getAirports()
	{
		return null;
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Flight> getFlights()
	{
		return null;
	}
	/**
	 * 
	 * @param code
	 * @return
	 */
	public String findAirportByCode(String code)
	{
		return null;
	}
	/**
	 * 
	 * @param code
	 * @return
	 */
	public Flight findFlightByCode(String code)
	{
		return null;
	}
	/**
	 * 
	 * @param from
	 * @param to
	 * @param weekday
	 * @return
	 */
	public ArrayList<Flight> findFlights(String from, String to, String weekday)
	{
		return null;
	}
	/**
	 * 
	 */
	private void populateFlights() 
	{
		
	}
	
	/**
	 * 
	 */
	private void populateAirports()
	{
		
	}
}
