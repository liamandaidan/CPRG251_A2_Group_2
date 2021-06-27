package sait.frms.manager;
import java.util.*;
import sait.frms.problemdomain.*;

/**
 * This is the Reservation manager that manages the data from Reservation object
 * 
 * @author Robyn Balanag
 * @version June, 25, 2021
 */

public class ReservationManager extends Reservation {
	
	
	private ArrayList<Reservation> reservations = new ArrayList<>();
	

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
	public ArrayList<Reservation> findReservations(String code, String airline, String name){
		return reservations ;
	}
		
	/**
	 * @return the reservation using the code
	 */
	public Reservation findReservationByCode(String code) {
		Reservation reservationByCode = new Reservation();
		return reservationByCode;
		
	}
	
	/**
	 * 
	 */
	public void persist() {
		 
	}
	
	
	/**
	 * @return the number of available seats
	 */
	private int getAvailableSeats() {
		return 0;
	}
	
	/**
	 * @return a generated confirmation code
	 */
	private String generateReservationCode(Flight flight) {
		return getCode();
		
	}
	
	/**
	 * 
	 */
	private void populateFromBinary() {
		
	}
	
}
