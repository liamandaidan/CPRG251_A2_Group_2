package sait.frms.problemdomain;

/**
 * TODO flight class infrastructure.
 * 
 * @author
 */
public class Flight {
	
	/**
	 * The Flight code.
	 */
	private String code;
	
	/**
	 * The airline Name.
	 */
	private String airlineName;
	
	/**
	 * Where the flight is from.
	 */
	private String from;
	
	/**
	 * Where the flight is going to.
	 */
	private String to;
	
	/**
	 * The day of the week the flight is. 
	 */
	private String weekday;
	
	/**
	 * The time of the flight.
	 */
	private String time;
	
	/**
	 * The number of seats on the flight.
	 */
	private int seats;
	
	/**
	 * The cost per seat. 
	 */
	private double costPerSeat;
	
	/**
	 * No arg Constructor.
	 */
	public Flight() {
	}

	/**
	 * Creates a flight object with all the properties from the csv file.
	 * @param code The flight code.
	 * @param airlineName The name of the Airline.
	 * @param from Where the Flight is from.
	 * @param to Where the flight is going.
	 * @param weekday The day of the week the flight is.
	 * @param time the time of the flight.
	 * @param seats The number of seats available on the flight.
	 * @param costPerSeat The cost of the seat.
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
	 * Gets the flight code.
	 * @return the code.
	 */
	public String getCode() {
		return code;
	}

	/** 
	 * Gets get airline name.
	 * @return the airlineName.
	 */
	public String getAirlineName() {
		return airlineName;
	}

	/**
	 * Gets the from location.
	 * @return the from location.
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Gets where the flights are going to.
	 * @return the to location.
	 */
	public String getTo() {
		return to;
	}

	/**
	 * Gets the weekday of the flight.
	 * @return the weekday.
	 */
	public String getWeekday() {
		return weekday;
	}

	/**
	 * Gets the time of the flight. 
	 * @return the time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Gets the number of seats.
	 * @return the seats.
	 */
	public int getSeats() {
		return seats;
	}

	/** 
	 * Gets the cost per seat.
	 * @return the costPerSeat.
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
	 * Tells if the flight isa domestic. 
	 * @return boolean true if flight is domestic, false if not.
	 */
	public boolean isDomestic() {
		return true;
	}

	/**  Parse the flight Code to a usable format. 
	 * @param code The goal is probably to parse this to an int or a double.
	 */
	private void parseCode(String code) {

	}

	/**
	*@Override
	*@return Changed from default to be more readable. 
	*/
	public String toString() {
		return	String.format("%s, From: %s, To: %s, Day: %s, Cost: $%.2f" , code, from, to, weekday,costPerSeat);
		  
	}
	/**
	 * 
	 * @return Formatted display of the flight. 
	 */
	public String findFlightDisplay() {
		return code + ", From: " + from + ", Day: " + weekday + ", Cost: " +  costPerSeat ;
		
	}

}
