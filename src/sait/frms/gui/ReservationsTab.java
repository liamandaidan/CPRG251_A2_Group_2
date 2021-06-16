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

	private JLabel reserveHeader, codeLabel, flightLabel, airlineLabel, costLabel, nameLabel, citzenLabel, statusLabel,
			title;
	private JTextArea reserveTextArea;
	private JTextField codeTextField, flightTextField, airlineTextField, costTextField, nameTextField, citzenTextField,
			statusTextField;
	private JTextField searchCodeField, searchAirlineField, searchNameField;
	private GridBagConstraints gbc;
	private final int TEXTFIELD_LENGTH = 120;
	private final int TEXTFIELD_HEIGHT = 24;

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
		panel.add(reserveTextArea);
		panel.setBackground(Color.CYAN);
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
		reserveHeader = new JLabel("Reserve");
		codeLabel = new JLabel("Code:");
		codeTextField = new JTextField();
		flightLabel = new JLabel("Flight:");
		flightTextField = new JTextField();
		airlineLabel = new JLabel("Airline:");
		airlineTextField = new JTextField();
		costLabel = new JLabel("Cost:");
		costTextField = new JTextField();
		codeTextField.setEditable(false);
		flightTextField.setEditable(false);
		airlineTextField.setEditable(false);
		costTextField.setEditable(false);
		codeTextField.setPreferredSize(new Dimension(TEXTFIELD_LENGTH, TEXTFIELD_HEIGHT));
		flightTextField.setPreferredSize(new Dimension(TEXTFIELD_LENGTH, TEXTFIELD_HEIGHT));
		airlineTextField.setPreferredSize(new Dimension(TEXTFIELD_LENGTH, TEXTFIELD_HEIGHT));
		costTextField.setPreferredSize(new Dimension(TEXTFIELD_LENGTH, TEXTFIELD_HEIGHT));
		
		reserveHeader.setFont(new Font("serif", Font.PLAIN, 24));
		gbc.gridx = 11;
		gbc.gridy = 1;
		formatPanel.add(reserveHeader, gbc);
		gbc.gridx = 10;
		gbc.gridy = 2;
		formatPanel.add(codeLabel, gbc);
		gbc.gridx = 11;
		gbc.gridy = 2;
		formatPanel.add(codeTextField, gbc);
		gbc.gridx = 10;
		gbc.gridy = 3;
		formatPanel.add(flightLabel, gbc);
		gbc.gridx = 11;
		gbc.gridy = 3;
		formatPanel.add(flightTextField, gbc);
		gbc.gridx = 10;
		gbc.gridy = 4;
		formatPanel.add(airlineLabel, gbc);
		gbc.gridx = 11;
		gbc.gridy = 4;
		formatPanel.add(airlineTextField, gbc);
		gbc.gridx = 10;
		gbc.gridy = 5;
		formatPanel.add(costLabel, gbc);
		gbc.gridx = 11;
		gbc.gridy = 5;
		formatPanel.add(costTextField, gbc);
		panel.add(formatPanel);
		
		panel.setPreferredSize(new Dimension(200, 100));
		panel.setBackground(Color.YELLOW);
		return panel;
	}

	/**
	 * Creates the south panel.
	 * 
	 * @return JPanel that goes in south.
	 */
	private JPanel createSouthPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 120));
		panel.setBackground(Color.GREEN);
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
		panel.setBackground(Color.RED);
		panel.add(title);

		return panel;
	}
}
