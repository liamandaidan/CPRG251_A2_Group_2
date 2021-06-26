package sait.frms.manager;

import java.util.ArrayList;
import sait.frms.problemdomain.*;
/**
 * 
 * @author Ben and Mike
 *
 */
public class FlightManager {
	
	public final String WEEKDAY_ANY = "Any";
	public final String wEEKDAY_SUNDAY = "Sunday";
	public final String WEEKDAY_MONDAY = "Monday";
	public final String WEEKDAY_TUESDAY = "Tuesday";
	public final String WEEKDAY_WEDNESDAY = "Wedday";
	public final String WEEKDAY_THURSDAY = "Thursday";
	public final String WEEKDAY_FRIDAY = "Friday";
	public final String WEEKDAY_SATURDAY = "Saturday";
	
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
