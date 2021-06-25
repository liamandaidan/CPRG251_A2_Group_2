package sait.frms.manager;

/**
 * This is the ReservationManager object that stores the data of everything inside of the object.
 * @author Liam McLaughlin
 * @version June, 25, 2021
 *
 */
public class ReservationManager {

	private String code, flightCode, airline, citizenship;
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
	public ReservationManager(String code, String flightCode, String airline, String citizenship, double cost,
			boolean active) {
		this.code = code;
		this.flightCode = flightCode;
		this.airline = airline;
		this.citizenship = citizenship;
		this.cost = cost;
		this.active = active;
	}

	/**
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the flightCode
	 */
	public String getFlightCode() {
		return flightCode;
	}

	/**
	 * @return the airline
	 */
	public String getAirline() {
		return airline;
	}

	/**
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
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
