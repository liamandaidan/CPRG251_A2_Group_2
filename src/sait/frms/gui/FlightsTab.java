package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase {
	/**
	 * Instance of flight manager.
	 */
	private FlightManager flightManager;

	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	/**
	 * List of flights.
	 */
	private JList<Flight> flightsList;

	private DefaultListModel<Flight> flightsModel;

	/**
	 * Creates the components for flights tab.
	 * 
	 * @param flightManager      Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(700, 500));

		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel eastPanel = createEastPanel();
		// eastPanel.setBackground(Color.blue);//this is just to identify the area taken
		// up by this panel
		panel.add(eastPanel, BorderLayout.EAST);

		panel.setVisible(true);// should make everything visible
	}

	private JPanel createSouthPanel() {

		String[] flightLocations = { "YYC", "YEG", "YUL", "YOW", "YYZ", "YVR", "YWG", "ATL", "PEK", "DXB", "HKG", "LHR",
				"HND", "ORD", "PVG", "CDG", "AMS", "DEL", "FRA", "DFW" };
		String[] daysOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };

		JPanel panel = new JPanel();
		JPanel topPanel = new JPanel();
		JPanel midPanel = new JPanel();
		JLabel flightFinderLabel = new JLabel("Flight Finder", SwingConstants.CENTER);
		JLabel fromLabel = new JLabel("From: ", SwingConstants.RIGHT);
		JLabel toLabel = new JLabel("To: ", SwingConstants.RIGHT);
		JLabel dayLabel = new JLabel("Day: ", SwingConstants.RIGHT);
		JComboBox fromComboBox = new JComboBox(flightLocations);
		JComboBox toComboBox = new JComboBox(flightLocations);
		JComboBox daysComboBox = new JComboBox(daysOfWeek);
		JButton findReservationButton = new JButton("Find Reservations");

		// Set up main panel
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(700, 150));

		// Add label to top panel
		flightFinderLabel.setFont(new Font("serif", Font.PLAIN, 25));
		topPanel.add(flightFinderLabel);

		// add Top panel to main component
		panel.add(topPanel, BorderLayout.NORTH);
		panel.setBackground(Color.GREEN);

		// Set up mid panel
		midPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		// From label
		fromLabel.setHorizontalTextPosition(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 0;
		midPanel.add(fromLabel, c);

		// from combobox
		c.fill = GridBagConstraints.HORIZONTAL;
		fromComboBox.setPreferredSize(new Dimension(660, 25));
		c.gridx = 1;
		c.gridy = 0;
		midPanel.add(fromComboBox, c);

		// To label
		toLabel.setHorizontalTextPosition(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		midPanel.add(toLabel, c);

		// to combobox
		c.fill = GridBagConstraints.HORIZONTAL;
		toComboBox.setPreferredSize(new Dimension(660, 25));
		c.gridx = 1;
		c.gridy = 1;
		midPanel.add(toComboBox, c);

		// Day label
		dayLabel.setHorizontalTextPosition(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 2;
		midPanel.add(dayLabel, c);

		// Day combo box
		c.fill = GridBagConstraints.HORIZONTAL;
		daysComboBox.setPreferredSize(new Dimension(660, 25));
		c.gridx = 1;
		c.gridy = 2;
		midPanel.add(daysComboBox, c);

		// add mid panel to main
		panel.add(midPanel, BorderLayout.CENTER);

		// Bottom button
		panel.add(findReservationButton, BorderLayout.SOUTH);

		// check with colors
//		topPanel.setBackground(Color.blue);
//		midPanel.setBackground(Color.PINK);
//		midPanel.setPreferredSize( new Dimension(500,500));
		panel.setPreferredSize(new Dimension(700, 150));
		return panel;
	}

	/**
	 * Creates the north panel. This panel just holds the word 'FLIGHTS'
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
//		panel.setPreferredSize(new Dimension(100, 50));
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		panel.setPreferredSize(new Dimension(100, 50));
		return panel;
	}

	/**
	 * @author Ali, Ben, Mike Creates the center panel. This just contains the list
	 *         box that will list flights that meet the search criteria
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(100, 50));

		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		// JScrollPane scrollPane = new JScrollPane(this.flightsList);

		JTextArea reserveTextArea = new JTextArea(15, 35);// height width
		panel.add(new JScrollPane(reserveTextArea));

		flightsList.addListSelectionListener(new MyListSelectionListener());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 30, 15));
		// panel.add(scrollPane);
//		panel.setBackground(Color.CYAN);
		return panel;
	}

	/**
	 * @author Ben Contains 3 segments in a borderlayout: a simple title, a button
	 *         at the bottom, and a set of text boxes which will either be filled by
	 *         the user, or by the user clicking on a flight in the center panel
	 * @return JPanel for the east segment
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();// create a new blank panel
		JLabel label = new JLabel("Reserve");
		label.setFont(new Font("serif", Font.PLAIN, 25));// change font face and size of label
		panel.setLayout(new GridBagLayout());// set the layout for the interior of the east panel
		GridBagConstraints eastCon = new GridBagConstraints();

		// create each of the text fields and their accompanying labels. first block
		// should not be editable.
		// Its contents will be taken from the selected flight in the center block
		JLabel flightLabel = new JLabel("Flight: ");
		JTextField flightText = new JTextField(15);
		flightText.setEditable(false);

		JLabel airlineLabel = new JLabel("Airline: ");
		JTextField airlineText = new JTextField(15);
		airlineText.setEditable(false);

		JLabel dayLabel = new JLabel("Day: ");
		JTextField dayText = new JTextField(15);
		dayText.setEditable(false);

		JLabel timeLabel = new JLabel("Time: ");
		JTextField timeText = new JTextField(15);
		timeText.setEditable(false);

		JLabel costLabel = new JLabel("Cost: ");
		JTextField costText = new JTextField(15);
		costText.setEditable(false);

		// these text fields will be used by the operator to complete the reservation
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameText = new JTextField(15);
		JLabel citizenshipLabel = new JLabel("Citizenship: ");
		JTextField citizenshipText = new JTextField(15);

		// create button to make reservation
		JButton reserveButton = new JButton("Reserve");
		// add header label and button, since those ones are different
		eastCon.gridx = 0; // there's gotta be a way to use a for loop or something to make this shorter
		eastCon.gridy = 0; // x and y are arranged left to right, top to bottom
		eastCon.gridwidth = 2;// how many grid squares do the next object take up horizontally
		eastCon.weightx = 0.5;// affects horizontal distribution of each cell
		eastCon.insets = new Insets(0, 30, 20, 30);// top, left, bottom, right spacing between elements
		panel.add(label, eastCon);
		eastCon.gridx = 0;
		eastCon.gridy = 8;
		eastCon.gridwidth = 2;
		eastCon.fill = GridBagConstraints.HORIZONTAL;
		eastCon.insets = new Insets(25, 5, 0, 5);
		panel.add(reserveButton, eastCon);

		// add labels
		eastCon.gridx = 0;
		eastCon.gridy = 1;
		eastCon.gridwidth = 1;
		eastCon.anchor = GridBagConstraints.LINE_END;// determine where horizontally things will be aligned inside the
														// cell
		eastCon.fill = GridBagConstraints.NONE;// determine whether or not the element will attempt to fill the cell
		eastCon.insets = new Insets(0, 2, 0, 0);
		panel.add(flightLabel, eastCon);
		eastCon.gridy = 2;
		panel.add(airlineLabel, eastCon);
		eastCon.gridy = 3;
		panel.add(dayLabel, eastCon);
		eastCon.gridy = 4;
		panel.add(timeLabel, eastCon);
		eastCon.gridy = 4;
		panel.add(timeLabel, eastCon);
		eastCon.gridy = 5;
		panel.add(costLabel, eastCon);
		eastCon.gridy = 6;
		panel.add(nameLabel, eastCon);
		eastCon.gridy = 7;
		panel.add(citizenshipLabel, eastCon);

		// add text boxes
		eastCon.gridx = 1;
		eastCon.gridy = 1;
		eastCon.ipadx = 100;
		eastCon.anchor = GridBagConstraints.LINE_START;
		eastCon.fill = GridBagConstraints.HORIZONTAL;
		panel.add(flightText, eastCon);
		eastCon.gridy = 2;
		panel.add(airlineText, eastCon);
		eastCon.gridy = 3;
		panel.add(dayText, eastCon);
		eastCon.gridy = 4;
		panel.add(timeText, eastCon);
		eastCon.gridy = 5;
		panel.add(costText, eastCon);
		eastCon.gridy = 6;
		panel.add(nameText, eastCon);
		eastCon.gridy = 7;
		panel.add(citizenshipText, eastCon);

		panel.setPreferredSize(new Dimension(200, 100));
		return panel;
	}

	private class MyListSelectionListener implements ListSelectionListener {
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {

		}

	}
}