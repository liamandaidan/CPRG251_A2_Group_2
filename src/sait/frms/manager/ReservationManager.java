package sait.frms.manager;

/**
 * This is the ReservationManager object that stores the data of everything inside of the object.
 * @author Liam McLaughlin
 * @version June, 25, 2021
 *
 */
public class ReservationManager {

	private String code, flightCode, name, airline, citizenship;
	private double cost;
	private boolean active;


	/**
	 * Initializes each parameter inside of ReservationManager
	 * @param code The code of the reservation.
	 * @param flightCode The code of the flight.
	 * @param airline The type of airline.
	 * @param citizenship The type of citizenship a client has.
	 * @param cost The cost of the reservation.
	 * @param active Whether the reservation is active or not.
	 */
	public ReservationManager(String code, String flightCode, String name, String airline, String citizenship, double cost,
			boolean active) {
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method will return a String with the flightCode.
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * This method will return a String with the airline.
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * This method will return a String with the name.
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method will return a String of the citizenship.
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}


	/**
	 * This method will return as a double the cost.
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * This method will return as a boolean if active.
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * This method will set the name.
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method will set the type of Citizenship.
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	/**
	 * This method will set active to a boolean.
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	
	/**
	 * This method will write out the toString of ReservationsManager.
	 * @Override
	 */
	public String toString() {
		return "ReservationManager [code=" + code + ", flightCode=" + flightCode + ", airline=" + airline
				+ ", citizenship=" + citizenship + ", cost=" + cost + ", active=" + active + "]";
	}

}
