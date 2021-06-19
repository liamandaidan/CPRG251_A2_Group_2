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
	 */
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

		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);

		JPanel eastPanel = createEastPanel();
		panel.add(eastPanel, BorderLayout.EAST);

		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);

//		northPanel.setPreferredSize(new Dimension(0, 50));
//		eastPanel.setPreferredSize(new Dimension(200, 0));
//	southPanel.setPreferredSize(new Dimension(0, 150));
	}

	private JPanel createEastPanel() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(200, 100));
		JLabel reserveLabel = new JLabel("Reserve", SwingConstants.CENTER);
		reserveLabel.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(reserveLabel);
		panel.setBackground(Color.YELLOW);

		return panel;
	}

	private JPanel createSouthPanel() {

		String[] flightLocations = { "YYC", "YEG", "YYX", "YVR", "YWG" };
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
		panel.setPreferredSize(new Dimension(100, 200));

		// Add label to top panel
		flightFinderLabel.setFont(new Font("serif", Font.PLAIN, 29));
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
		fromLabel.setPreferredSize(new Dimension(10, 10));
		c.gridwidth = 1;
		c.ipady = 0; //reset
		c.gridx = 0;
		c.gridy = 1; 
		c.weightx = 0.0;
		midPanel.add(fromLabel, c);

		// from combo box
		c.fill = GridBagConstraints.HORIZONTAL;
		fromComboBox.setPreferredSize(new Dimension(500, 15));
		c.gridx = 1;
		c.gridy = 1; 
		c.weightx = 0.9;
		midPanel.add(fromComboBox, c);

		// To label
		toLabel.setHorizontalTextPosition(JLabel.RIGHT);
		c.gridx = 0;
		c.gridy = 1;
		midPanel.add(toLabel, c);

		// to combo box
		c.fill = GridBagConstraints.HORIZONTAL;
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
		c.gridx = 1;
		c.gridy = 2;
		midPanel.add(daysComboBox, c);
		
		// add mid panel to main
		panel.add(midPanel, BorderLayout.CENTER);

		// Bottom button
		panel.add(findReservationButton, BorderLayout.SOUTH);
		
		// check with ciolors
//		topPanel.setBackground(Color.blue);
//		midPanel.setBackground(Color.PINK);
//		midPanel.setPreferredSize( new Dimension(500,500));
		panel.setPreferredSize(new Dimension(700, 150));
		return panel;
	}

	/**
	 * Creates the north panel.
	 * 
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() {
		JPanel panel = new JPanel();
//		panel.setPreferredSize(new Dimension(100, 50));
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		panel.setBackground(Color.RED);

		return panel;
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();

		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(100,50));
		
		flightsModel = new DefaultListModel<>();
		flightsList = new JList<>(flightsModel);

		// User can only select one item at a time.
		flightsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.flightsList);

		flightsList.addListSelectionListener(new MyListSelectionListener());

		panel.add(scrollPane);
		panel.setBackground(Color.CYAN);
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