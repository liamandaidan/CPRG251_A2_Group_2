package sait.frms.gui;

import java.awt.*;
import javax.swing.*;

import sait.frms.manager.ReservationManager;
import sait.frms.problemdomain.Reservation;

/**
 * Holds the components for the reservations tab. todo
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;

	private JList<Reservation> reservationsList;

	private JLabel reserveHeader, codeLabel, flightLabel, airlineLabel, costLabel, nameLabel, citizenshipLabel, statusLabel;
	private JButton updateButton, findReservationButton;
	private JComboBox statusBox; 
	private JTextField codeField, flightField, airlineField, costField, nameField, citizenshipField;

	private JTextArea reserveTextArea;
	private GridBagConstraints gbc;
	private final int TEXTFIELD_LENGTH = 11;

	

	/**
	 * Creates the components for reservations tab.
	 */
	public ReservationsTab(ReservationManager reservationManager) {
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
		panel.setVisible(true);
	}

	/**
	 * Creates the center panel.
	 * 
	 * @return JPanel that goes in the center.
	 */
	private JPanel createCenterPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 50));
		
		reserveTextArea = new JTextArea(15, 40);// height width
		reserveTextArea.setText("Just a test");
		panel.add(new JScrollPane(reserveTextArea));
		
		return panel;
	}

	/**
	 * Creates the east panel.
	 * 
	 * @return JPanel that goes in east.
	 */
	private JPanel createEastPanel() {
		JPanel panel = new JPanel();
		
		JPanel formatPanel = new JPanel();
		formatPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		
		reserveHeader = new JLabel("Reserve ");
		gbc.ipady = 30;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		reserveHeader.setFont(new Font("serif", Font.PLAIN, 25));
		formatPanel.add(reserveHeader, gbc);
		codeLabel = new JLabel("Code: ");
		gbc.anchor = GridBagConstraints.LINE_END; //align all text to the right
		gbc.ipady = 0;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		formatPanel.add(codeLabel, gbc);
		codeField = new JTextField(TEXTFIELD_LENGTH);
		codeField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 1;
		formatPanel.add(codeField, gbc);

		
		flightLabel = new JLabel("Flight: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		formatPanel.add(flightLabel, gbc);
		flightField = new JTextField(TEXTFIELD_LENGTH);
		flightField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 2;
		formatPanel.add(flightField, gbc);
		
		airlineLabel = new JLabel("Airline: ");
		gbc.gridx = 0;
		gbc.gridy = 3;
		formatPanel.add(airlineLabel, gbc);
		airlineField = new JTextField(TEXTFIELD_LENGTH);
		airlineField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 3;
		formatPanel.add(airlineField, gbc);
		
		costLabel = new JLabel("Cost: ");
		gbc.gridx = 0;
		gbc.gridy = 4;
		formatPanel.add(costLabel, gbc);
		costField = new JTextField(TEXTFIELD_LENGTH);
		costField.setEditable(false);
		gbc.gridx = 1;
		gbc.gridy = 4;
		formatPanel.add(costField, gbc);
		
		nameLabel = new JLabel("Name: ");
		gbc.gridx = 0;
		gbc.gridy = 5;
		formatPanel.add(nameLabel, gbc);
		nameField = new JTextField(TEXTFIELD_LENGTH);
		nameField.isEditable();
		gbc.gridx = 1;
		gbc.gridy = 5;
		formatPanel.add(nameField, gbc);
		
		citizenshipLabel = new JLabel("Citizenship: ");
		gbc.gridx = 0;
		gbc.gridy = 6;
		formatPanel.add(citizenshipLabel, gbc);
		citizenshipField = new JTextField(TEXTFIELD_LENGTH);
		citizenshipField.isEditable();
		gbc.gridx = 1;
		gbc.gridy = 6;
		formatPanel.add(citizenshipField, gbc);
		
		String[] status = {"Active", "Inactive"};
		
		statusLabel = new JLabel("Status: ");
		statusBox = new JComboBox(status);
		statusBox.setPreferredSize(new Dimension(125,23)); //dimensions width, height
		gbc.gridx = 0;
		gbc.gridy = 7;
		formatPanel.add(statusLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		formatPanel.add(statusBox, gbc);
		
		updateButton = new JButton("Update ");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2; //take up two cells
		gbc.insets = new Insets(20,0,0,0); //set margin (top, left, bottom, right
		gbc.fill = GridBagConstraints.HORIZONTAL; //fill cells horizontally
		formatPanel.add(updateButton, gbc);
		panel.add(formatPanel);
		
		panel.setPreferredSize(new Dimension(200, 100));
		return panel;
	}

	/**
	 * Creates the south panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL; //fill entire panel 
		
		JLabel searchTitle = new JLabel("Search "); 
		searchTitle.setVerticalAlignment(SwingConstants.CENTER);
		searchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.ipady = 20;
		gbc.weightx = 1.0;
		gbc.gridwidth = 2;
		searchTitle.setFont(new Font("serif", Font.PLAIN, 25));
		//searchTitle.	setBorder(BorderFactory.createLineBorder(Color.BLACK));
		bottomPanel.add(searchTitle, gbc);
		
		JLabel codeSearch = new JLabel("Code: ");
		codeSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridwidth = 1;
		gbc.ipady = 0; //reset
		gbc.gridx = 0;
		gbc.gridy = 1; 
		gbc.weightx = 0.0;
		bottomPanel.add(codeSearch, gbc);
		JTextField codeSearchField = new JTextField (59);
		gbc.gridx = 1;
		gbc.gridy = 1; 
		gbc.weightx = 0.9; //think as percentage - how much of the space should it take up. 
		//At least one needs to be define or they clump in the middle
		bottomPanel.add(codeSearchField, gbc);
		
		JLabel airlineSearch = new JLabel ("Airline: ");
		airlineSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 0;
		gbc.gridy = 2; 
		gbc.weightx = 0.0;
		bottomPanel.add(airlineSearch, gbc);
		JTextField airlineSearchField = new JTextField (59);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.9;
		bottomPanel.add(airlineSearchField, gbc);
		
		JLabel nameSearch = new JLabel ("Name: ");
		nameSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 0;
		gbc.gridy = 3; 
		gbc.weightx = 0.0;
		bottomPanel.add(nameSearch, gbc);
		JTextField nameSearchField = new JTextField (59);
		gbc.gridx = 1;
		gbc.gridy = 3; 
		gbc.weightx = 0.9;
		bottomPanel.add(nameSearchField, gbc);
		
		findReservationButton = new JButton("Find Reservation");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		bottomPanel.add(findReservationButton, gbc);
		
		panel.add(bottomPanel);
		
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

		JLabel title = new JLabel("Reservations", SwingConstants.CENTER);
		title.setFont(new Font("serif", Font.PLAIN, 29));
		panel.setPreferredSize(new Dimension(100, 50));
		panel.add(title);

		return panel;
	}
}
