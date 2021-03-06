package sait.frms.gui;

import java.util.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sait.frms.exception.*;
import sait.frms.manager.*;
import sait.frms.problemdomain.*;

/**
 * Holds the components for the reservations tab. todo
 */
public class ReservationsTab extends TabBase {
	/**
	 * Instance of reservation manager.
	 */
	private ReservationManager reservationManager;
	/**
	 * Contains Reservations found from the search function.
	 */
	private ArrayList<Reservation> foundReservation = new ArrayList<>();
	/**
	 * This arraylist is a direct reference to the main arraylist of
	 * ReservationManager.
	 */
	private ArrayList<Reservation> inventory;
	/**
	 * The heading for reserve.
	 */
	private JLabel reserveHeader;
	/**
	 * The label for code.
	 */
	private JLabel codeLabel;
	/**
	 * The label for flights.
	 */
	private JLabel flightLabel;
	/*
	 * The label for airlines
	 * 
	 */
	private JLabel airlineLabel;
	/*
	 * The label for cost.
	 */
	private JLabel costLabel;
	/*
	 * The label for name.
	 */
	private JLabel nameLabel;
	/*
	 * The label for citizenship.
	 */
	private JLabel citizenshipLabel;
	/*
	 * The label for status.
	 */
	private JLabel statusLabel;
	/*
	 * The button that contains update.
	 */
	private JButton updateButton;
	/*
	 * The button that contains find reservations.
	 */
	private JButton findReservationButton;
	/*
	 * The comboBox that contains the isActive.
	 */
	private JComboBox statusBox;
	/*
	 * The textField for code.
	 */
	private JTextField codeField;
	/*
	 * The textField for flights.
	 */
	private JTextField flightField;
	/*
	 * The textField for airline.
	 */
	private JTextField airlineField;
	/*
	 * The textField for cost.
	 */
	private JTextField costField;
	/*
	 * The textField for name.
	 */
	private JTextField nameField;
	/*
	 * The textField for citizenship.
	 */
	private JTextField citizenshipField;
	/*
	 * The textArea for all reservations.
	 */
	private JTextArea reserveTextArea;
	/*
	 * This keeps track of the grid bag constraints.
	 */
	private GridBagConstraints gbc;
	/*
	 * This contains the final value for our textfield length.
	 */
	private final int TEXTFIELD_LENGTH = 11;
	/*
	 * This houses the reservationsList thats used in relation with reservationModel.
	 */
	private JList<Reservation> reservationList;
	/*
	 * The reservationModel object that is used to display to text Area
	 */
	private DefaultListModel<Reservation> reservationModel;


	/**
	 * Creates the components for reservations tab.
	 * @param reservationManager
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
		panel.setLayout(new BorderLayout());
		panel.setPreferredSize(new Dimension(100, 50));

		reservationModel = new DefaultListModel<>();
		reservationList = new JList<Reservation>(reservationModel);

		reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Wrap JList in JScrollPane so it is scrollable.
		JScrollPane scrollPane = new JScrollPane(this.reservationList);

		// reserveTextArea = new JTextArea(17, 43);// height width
		reserveTextArea = new JTextArea(15, 35);
		panel.add(new JScrollPane(reserveTextArea));

		reservationList.addListSelectionListener(new MyListSelectionListener());
		panel.setBorder(BorderFactory.createEmptyBorder(5, 15, 30, 15));
		panel.add(scrollPane);
		return panel;

	}

	/**
	 * When the text area is clicked will listen.
	 *
	 */
	private class MyListSelectionListener implements ListSelectionListener {
	
		/**
		 * This will populate the fields on the east panel.
		 *@param ListSelectionEvent the ListSelectionEvent to select.
		 *@Override
		 */
		public void valueChanged(ListSelectionEvent e) {

			if (reservationList.getSelectedValue() != null) {
				// not sure if the getCode will work but we will see.
				Reservation r = reservationManager.findReservationByCode(reservationList.getSelectedValue().getCode());

				// codeField, flightField, airlineField, costField, nameField, citizenshipField;
				codeField.setText(r.getCode());
				flightField.setText(r.getFlightCode());
				airlineField.setText(r.getAirline());
				costField.setText(String.format("$%.2f", r.getCost()));
				nameField.setText(r.getName());
				citizenshipField.setText(r.getCitizenship());
				String x = "";
				if (r.isActive())
					x = "Active";
				else
					x = "Inactive";
				statusBox.setSelectedItem(x);
			}
		}

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
		gbc.anchor = GridBagConstraints.LINE_END; // align all text to the right
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

		String[] status = { "Active", "Inactive" };

		statusLabel = new JLabel("Status: ");
		statusBox = new JComboBox(status);
		statusBox.setPreferredSize(new Dimension(125, 23)); // dimensions width, height
		gbc.gridx = 0;
		gbc.gridy = 7;
		formatPanel.add(statusLabel, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		formatPanel.add(statusBox, gbc);

		updateButton = new JButton("Update ");
		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.gridwidth = 2; // take up two cells
		gbc.insets = new Insets(20, 0, 0, 0); // set margin (top, left, bottom, right
		gbc.fill = GridBagConstraints.HORIZONTAL; // fill cells horizontally
		formatPanel.add(updateButton, gbc);
		updateButton.addActionListener(new ActionListener() {

			/*
			 * This method will update the fields and save to binary.
			 * 
			 * @param ActionEvent the ActionEvent used.
			 * @Override
			 */
			public void actionPerformed(ActionEvent e) {
				// Reservation makeReservation(Flight flight, String name, String citizenship)
				Reservation updatedReservation;
				// check 3 things
				String updatedName = nameField.getText();
				String updatedCitizenship = citizenshipField.getText();
				String updatedActive = (String) statusBox.getSelectedItem();
				boolean isActive;

				if (updatedActive == "Active") {
					isActive = true;

				} else {
					isActive = false;
				}
				// these other fields are for the reservation object
				String code = codeField.getText();
				String flight = flightField.getText();
				String airline = airlineField.getText();
				double cost = Double.parseDouble(costField.getText().substring(1));
				// find in the list the updated
				try {
					if (updatedName.equals(""))
						throw new InvalidNameException();
					if (updatedCitizenship.equals(""))
						throw new InvalidCitizenshipException();
					Reservation temp = reservationManager.findReservationByCode(code);
					updatedReservation = new Reservation(code, flight, updatedName, airline, updatedCitizenship, cost,
							isActive);

					int i = 0;
					for (i = 0; i < inventory.size(); i++) {
						if (inventory.get(i).getCode() == temp.getCode()) {
							System.out.println("Success, this name will be updated: " + inventory.get(i).getName());
							inventory.remove(i);
							inventory.add(updatedReservation);
							System.out.println("Updated Reservation is now saved as: " + inventory.get(i).getName());
						}

					}

					reservationManager.persist();
					reservationModel.clear();
					reserveTextArea.setText("");
					codeField.setText("");
					flightField.setText("");
					airlineField.setText("");
					costField.setText("");
					nameField.setText("");
					citizenshipField.setText("");
				} catch (InvalidNameException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				} catch (InvalidCitizenshipException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}

		});

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
		JPanel panel = new JPanel(new BorderLayout());

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.HORIZONTAL; // fill entire panel

		JLabel searchTitle = new JLabel("Search ");
		searchTitle.setVerticalAlignment(SwingConstants.CENTER);
		searchTitle.setHorizontalAlignment(SwingConstants.CENTER);
		gbc.ipady = 20;
		gbc.weightx = 1.0;
		gbc.gridwidth = 2;
		searchTitle.setFont(new Font("serif", Font.PLAIN, 25));
		bottomPanel.add(searchTitle, gbc);

		JLabel codeSearch = new JLabel("Code: ");
		codeSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridwidth = 1;
		gbc.ipady = 0; // reset
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0.0;
		bottomPanel.add(codeSearch, gbc);
		JTextField codeSearchField = new JTextField(59);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 0.9; // think as percentage - how much of the space should it take up.
		// At least one needs to be define or they clump in the middle
		bottomPanel.add(codeSearchField, gbc);

		JLabel airlineSearch = new JLabel("Airline: ");
		airlineSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0.0;
		bottomPanel.add(airlineSearch, gbc);
		JTextField airlineSearchField = new JTextField(59);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.9;
		bottomPanel.add(airlineSearchField, gbc);

		JLabel nameSearch = new JLabel("Name: ");
		nameSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.weightx = 0.0;
		bottomPanel.add(nameSearch, gbc);
		JTextField nameSearchField = new JTextField(59);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.weightx = 0.9;
		bottomPanel.add(nameSearchField, gbc);

		findReservationButton = new JButton("Find Reservation");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		panel.add(findReservationButton, BorderLayout.SOUTH);
		findReservationButton.addActionListener(new ActionListener() {

			/**
			 * This method will find reservations and display them to textArea.
			 *
			 *@param ActionEvent the ActionEvent to use.
			 *@Override
			 */
			public void actionPerformed(ActionEvent e) {
				// reservationModel.clear();
				String code = codeSearchField.getText();
				String airline = airlineSearchField.getText();
				String name = nameSearchField.getText();
				reservationManager = new ReservationManager();

				// add found reservation to reservationModel
				reservationModel.addAll(reservationManager.findReservations(code, airline, name));
				inventory = reservationManager.getPopulated();

				// create temp to add to list for each
				for (int i = 0; i < reservationModel.size(); i++) {
					Reservation r = reservationModel.get(0);
					foundReservation.add(r);
				}
				// need to add reservation that was found into our list.

			}

		});

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
