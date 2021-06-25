package sait.frms.problemdomain;

/**
 * This is the Reservation object that stores the data of everything
 * inside of the object.
 * 
 * @author Liam McLaughlin
 * @version June, 25, 2021
 */
public class Reservation {

	private String code, flightCode, name, airline, citizenship;
	private double cost;
	private boolean active;

	/**
	 * Blank Constructor for now. Will change later on if needed.
	 */
	public Reservation() {
		super();
	}
	
	/**
	 * Initializes each parameter inside of Reservation without active parameter.
	 * 
	 * @param code        The reservation code will be generated when a reservation
	 *                    is created.
	 * @param flightCode  The flight code the reservation is for.
	 * @param airline     The airline who owns and operates the flight.
	 * @param name        The name of the traveler.
	 * @param citizenship The citizenship of the traveler.
	 * @param cost        The cost of the reservation.
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship,
			double cost) {
		this.code = code;
		this.flightCode = flightCode;
		this.name = name;
		this.airline = airline;
		this.citizenship = citizenship;
		this.cost = cost;

	}

	/**
	 * Initializes each parameter inside of Reservation with active parameter.
	 * 
	 * @param code        The reservation code will be generated when a reservation
	 *                    is created.
	 * @param flightCode  The flight code the reservation is for.
	 * @param airline     The airline who owns and operates the flight.
	 * @param name        The name of the traveler.
	 * @param citizenship The citizenship of the traveler.
	 * @param cost        The cost of the reservation.
	 * @param active      Whether the reservation is active or not.
	 */
	public Reservation(String code, String flightCode, String airline, String name, String citizenship,
			double cost, boolean active) {
		this.code = code;
		this.flightCode = flightCode;
		this.name = name;
		this.airline = airline;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * This method will return a String of the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method will return a String with the flightCode.
	 * 
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * This method will return a String with the airline.
	 * 
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * This method will return a String with the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method will return a String of the citizenship.
	 * 
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * This method will return as a double the cost.
	 * 
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * This method will return as a boolean if active.
	 * 
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * This method will set the name.
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method will set the type of Citizenship.
	 * 
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * This method will set active to a boolean.
	 * 
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * This method will write out the toString of Reservation.
	 * 
	 * @Override
	 */
	public String toString() {
		return "ReservationManager [code=" + code + ", flightCode=" + flightCode + ", name=" + name + ", airline="
				+ airline + ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}

}
