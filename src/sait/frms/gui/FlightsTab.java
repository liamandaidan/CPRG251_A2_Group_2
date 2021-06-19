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
	 * 
	 * @param flightManager Instance of FlightManager.
	 * @param reservationManager Instance of ReservationManager
	 */
	public FlightsTab(FlightManager flightManager, ReservationManager reservationManager) {
		this.flightManager = flightManager;
		this.reservationManager = reservationManager;
		
		panel.setLayout(new BorderLayout());
		
		
		JPanel northPanel = createNorthPanel();
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel southPanel = createSouthPanel();
		panel.add(southPanel, BorderLayout.SOUTH);
		
		
		JPanel centerPanel = createCenterPanel();
		panel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel eastPanel = createEastPanel();
		//eastPanel.setBackground(Color.blue);//this is just to identify the area taken up by this panel
		panel.add(eastPanel, BorderLayout.EAST);
		
		panel.setVisible(true);//should make everything visible
	}
	
	/**
	 * Creates the north panel.
	 * This panel just holds the word 'FLIGHTS'
	 * @return JPanel that goes in north.
	 */
	private JPanel createNorthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flights", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		panel.setPreferredSize(new Dimension(800,40));
		return panel;
	}
	
	/**
	 * Creates the south panel.
	 * This panel is a placeholder for spacing
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel() 
	{
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Flight Finder", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.add(title);
		panel.setPreferredSize(new Dimension(800,150));
		return panel;
	}
	
	/**
	 * @author Ali, Ben, Mike
	 * Creates the center panel.
	 * This just contains the list box that will list flights that meet the search criteria
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
		panel.setBorder(BorderFactory.createEmptyBorder(15,15,55,15));
		panel.add(scrollPane);
		
		return panel;
	}
	
	/**
	 * @author Ben
	 * Contains 3 segments in a borderlayout: a simple title, a button at the bottom, and a set of text boxes which will either be filled by the user, or 
	 * by the user clicking on a flight in the center panel
	 * @return JPanel for the east segment
	 */
	/*private JPanel createEastPanel() 
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		//add title to top of section
		JLabel title = new JLabel("Reserve", SwingConstants.CENTER);
		title.setFont(new Font("Times New Roman", Font.BOLD, 24));
		panel.add(title, BorderLayout.NORTH);
		
		//create all the left-side labels and set editable or not. Only the bottom 2 are editable
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
		
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameText = new JTextField(15);
		JLabel citizenshipLabel = new JLabel("Citizenship: ");
		JTextField citizenshipText = new JTextField(15);
		
		//add the stuff to the center section
		JPanel stuff = new JPanel();
		stuff.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
	    //add labels
			cons.gridx = 0;
			cons.gridy = 1;
			cons.anchor = GridBagConstraints.LINE_END;//determine where horizontally things will be aligned inside the cell
			cons.fill = GridBagConstraints.NONE;//determine whether or not the element will attempt to fill the cell
			cons.insets = new Insets(2,2,2,2);
		stuff.add(flightLabel, cons);
			cons.gridy = 2;
		stuff.add(airlineLabel, cons);
			cons.gridy = 3;
		stuff.add(dayLabel, cons);
			cons.gridy = 4;
		stuff.add(timeLabel, cons);
			cons.gridy = 5;
		stuff.add(costLabel, cons);
			cons.gridy = 6;
		stuff.add(nameLabel, cons);
			cons.gridy = 7;
		stuff.add(citizenshipLabel, cons);
		
		//add text boxes
			cons.gridx = 1;
			cons.gridy = 1;
			cons.anchor = GridBagConstraints.LINE_START;
			cons.fill = GridBagConstraints.HORIZONTAL;
		stuff.add(flightText, cons);
			cons.gridy = 2;
		stuff.add(airlineText, cons);
			cons.gridy = 3;
		stuff.add(dayText, cons);
			cons.gridy = 4;
		stuff.add(timeText, cons);
			cons.gridy = 5;
		stuff.add(costText, cons);
			cons.gridy = 6;
		stuff.add(nameText, cons);
			cons.gridy = 7;
		stuff.add(citizenshipText, cons);
		panel.add(stuff, BorderLayout.CENTER);
		
		//add button to bottom
		JButton reserveButton = new JButton("Reserve");
		panel.add(reserveButton, BorderLayout.SOUTH);
		
		return panel;
	}*/
	
	private JPanel createEastPanel() 
	{
		JPanel panel = new JPanel();//create a new blank panel
		JLabel label = new JLabel("Reserve");
		label.setFont(new Font("Times New Roman", Font.BOLD, 24));//change font face and size of label
		panel.setLayout(new GridBagLayout());//set the layout for the interior of the east panel
		GridBagConstraints eastCon = new GridBagConstraints();

		//create each of the text fields and their accompanying labels. first block should not be editable. 
		//Its contents will be taken from the selected flight in the center block
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
		
		//these  text fields will be used by the operator to complete the reservation
		JLabel nameLabel = new JLabel("Name: ");
		JTextField nameText = new JTextField(15);
		JLabel citizenshipLabel = new JLabel("Citizenship: ");
		JTextField citizenshipText = new JTextField(15);
		
		//create button to make reservation
		JButton reserveButton = new JButton("Reserve");
		//add header label and button, since those ones are different
			eastCon.gridx = 0; //there's gotta be a way to use a for loop or something to make this shorter
			eastCon.gridy = 0; //x and y are arranged left to right, top to bottom
			eastCon.gridwidth = 2;//how many grid squares do the next object take up horizontally
			eastCon.weightx = 0.5;//affects horizontal distribution of each cell
			eastCon.insets = new Insets(0,50,30,50);//top, left, bottom, right spacing between elements
		panel.add(label, eastCon);
			eastCon.gridx = 0;
			eastCon.gridy = 8;
			eastCon.gridwidth = 2;
			eastCon.fill = GridBagConstraints.HORIZONTAL;
			eastCon.insets = new Insets(30,50,0,50);
		panel.add(reserveButton, eastCon);
		
		//add labels
			eastCon.gridx = 0;
			eastCon.gridy = 1;
			eastCon.gridwidth = 1;
			eastCon.anchor = GridBagConstraints.LINE_END;//determine where horizontally things will be aligned inside the cell
			eastCon.fill = GridBagConstraints.NONE;//determine whether or not the element will attempt to fill the cell
			eastCon.insets = new Insets(2,2,2,2);
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
		
		//add text boxes
			eastCon.gridx = 1;
			eastCon.gridy = 1;
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
		
		panel.setPreferredSize(new Dimension(250,300));
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