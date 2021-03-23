package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import actions.dronePositioning;
import actions.tubeCalcs;
import actions.writeReport;
import bussiness.Drone;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Drone simulation by Pablo Pinto");
		System.out.println("-------------------------------");
		System.out.println("Press any KEY to start the simulation");

		// Waiting for the KEY press
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Thread execution
		ExecutorService thread1 = Executors.newSingleThreadExecutor();
		thread1.submit(() -> {
			drone5937(thread1);
		});

		ExecutorService thread2 = Executors.newSingleThreadExecutor();
		thread2.submit(() -> {
			drone6043(thread2);
		});

	}

	public static void drone5937(ExecutorService thread1) {

		// Parameter that controls if a file is gonna be written or not
		Boolean writesFile = false;

		// Provided CVS File
		final String DRONE_FILE_5937 = "provided\\5937.csv";

		// CSV output file
		final String CSV_OUTPUT = "Output\\report_5937.csv";

		// Array with the drone positioning
		ArrayList<Drone> droneInfo = new ArrayList<Drone>();

		// We execute the method to know which one is the first drone position
		droneInfo = dronePositioning.sendCoordinate(droneInfo, DRONE_FILE_5937);

		Drone data = droneInfo.get(0);
		Boolean SHUTDOWN = data.getSHUTDOWN();

		while (!SHUTDOWN) {
			// Functions to calculate the known position and the next position of the drone.
			droneInfo = dronePositioning.sendCoordinate(droneInfo, DRONE_FILE_5937);

			data = droneInfo.get(0);
			SHUTDOWN = data.getSHUTDOWN();

			if (!SHUTDOWN) {
				// Functions to calculate the distance between drone and tube.
				writesFile = tubeCalcs.checkTube(droneInfo);

				if (writesFile) {
					try {
						//Function to write the CSV report
						writeReport.createCSV(droneInfo, CSV_OUTPUT);

					} catch (IOException e) {

						e.printStackTrace(); //Yeah i know, not controlled exceptions

					}
				}

			} else {
				System.out.println("Shutdown");
				SHUTDOWN = true;
				thread1.shutdown();
			}

		}

	}

	public static void drone6043(ExecutorService thread2) {

		Boolean writesFile = false;

		// Provided CVS File
		final String DRONE_FILE_6043 = "provided\\6043.csv";

		// CSV output file
		final String CSV_OUTPUT = "Output\\report_6043.csv";

		// Array with the drone positioning
		ArrayList<Drone> droneInfo = new ArrayList<Drone>();

		droneInfo = dronePositioning.sendCoordinate(droneInfo, DRONE_FILE_6043);

		Drone data = droneInfo.get(0);
		Boolean SHUTDOWN = data.getSHUTDOWN();

		while (!SHUTDOWN) {
			// Functions to calc. the know position and the next position of the drone.
			droneInfo = dronePositioning.sendCoordinate(droneInfo, DRONE_FILE_6043);

			data = droneInfo.get(0);
			SHUTDOWN = data.getSHUTDOWN();

			if (!SHUTDOWN) {
				// Functions to calculate the known position and the next position of the drone.
				writesFile = tubeCalcs.checkTube(droneInfo);

				if (writesFile) {
					//Function to write the CSV report
					try {
						//Function to write the CSV report
						writeReport.createCSV(droneInfo, CSV_OUTPUT);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {
				System.out.println("Shutdown -- The csv report files are stored in the 'Output' folder");
				SHUTDOWN = true;
				thread2.shutdown();
			}

		}

	}

}
