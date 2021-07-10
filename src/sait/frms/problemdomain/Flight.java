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
	 * @param code
	 * @param airlineName
	 * @param from
	 * @param to
	 * @param weekday
	 * @param time
	 * @param seats
	 * @param costPerSeat
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
