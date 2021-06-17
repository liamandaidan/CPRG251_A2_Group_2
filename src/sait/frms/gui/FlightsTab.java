package sait.frms.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import sait.frms.manager.FlightManager;
import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Flight;

/**
 * Holds the components for the flights tab.
 * 
 */
public class FlightsTab extends TabBase 
{
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
	 * @param flightManager Instance of FlightManager.
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
//		southPanel.setPreferredSize(new Dimension(0, 100));
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
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 120));
		JLabel flightFinderLabel = new JLabel("Flight Finder", SwingConstants.CENTER);
		flightFinderLabel.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(flightFinderLabel);
		
		
		panel.setBackground(Color.GREEN);
		
		return panel;
	}

	/**
	 * Creates the north panel.
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 50));
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		panel.setBackground(Color.RED);
		
		return panel;
	}
	
	/**
	 * Creates the center panel.
	 * @return JPanel that goes in center.
	 */
	private JPanel createCenterPanel() 
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		
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
	
	private class MyListSelectionListener implements ListSelectionListener 
	{
		/**
		 * Called when user selects an item in the JList.
		 */
		@Override
		public void valueChanged(ListSelectionEvent e) {
			
		}
		
	}
}