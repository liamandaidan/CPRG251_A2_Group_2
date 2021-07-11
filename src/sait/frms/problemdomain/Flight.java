package sait.frms.problemdomain;

/**
 * TODO flight class infrastructure
 * 
 * @author
 */
public class Flight {
	private String code;
	private String airlineName;
	private String from;
	private String to;
	private String weekday;
	private String time;
	private int seats;
	private double costPerSeat;
	
	public Flight() {
	}

	/**
	 * Creates a flight object with all the properties from the csv file.
	 * @param code The flight code
	 * @param airlineName The name of the Airline
	 * @param from Where the Flight is from
	 * @param to Where the flight is going
	 * @param weekday The day of the week the flight is
	 * @param time the time of the flight
	 * @param seats The number of seats available on the flight
	 * @param costPerSeat The cost of the seat
	 */
	public Flight(String code, String airlineName, String from, String to, String weekday, String time, int seats,
			double costPerSeat) {
		this.code = code;
		this.airlineName = airlineName;
		this.from = from;
		this.to = to;
		this.weekday = weekday;
		this.time = time;
		this.seats = seats;
		this.costPerSeat = costPerSeat;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the airlineName
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @return the weekday
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * @return the costPerSeat
	 */
	public double getCostPerSeat() {
		return costPerSeat;
	}
	
	/**
	 * This will update the seats count.
	 * @param seats the seats to set.
	 */
	public void setSeat(int seats){
    	this.seats = seats;
  }

	/**
	 * @return boolean true if flight is domestic, false if not
	 */
	public boolean isDomestic() {
		return true;
	}

	/**
	 * @param String, the goal is probably to parse this to an int or a double
	 */
	private void parseCode(String code) {

	}

	/**
	*@Override
	*changed from default to be more readable
	*/
	public String toString() {
		return	String.format("%s, From: %s, To: %s, Day: %s, Cost: $%.2f" , code, from, to, weekday,costPerSeat);
		  
	}
	
	public String findFlightDisplay() {
		return code + ", From: " + from + ", Day: " + weekday + ", Cost: " +  costPerSeat ;
		
	}

}
