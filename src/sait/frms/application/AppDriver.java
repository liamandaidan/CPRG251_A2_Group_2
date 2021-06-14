package sait.frms.application;

import sait.frms.gui.MainWindow;
import sait.frms.gui.*;

/**
 * Application driver.
 * 
 */
public class AppDriver {

	/**
	 * Entry point to Java application.
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("HelloWorld!");//Just a Test
		MainWindow mainWindow = new MainWindow();
		mainWindow.display();
	}

}
