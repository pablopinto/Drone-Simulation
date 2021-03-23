package actions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import bussiness.Drone;

public class writeReport {

	public static void createCSV(ArrayList<Drone> data, String CSV_OUTPUT) throws IOException {

		// CVS output
		File idea = new File(CSV_OUTPUT);

		// If the file already exists we just have to add rows to the file.
		if (idea.exists()) {

			// Function to add rows to the CVS file.
			writeNewRow(data, CSV_OUTPUT);

		} else {

			Drone drone1 = data.get(0);

			// We choose randomly the traffic conditions.
			final String[] proper_noun = { "HEAVY", "LIGHT", "MODERATE" };
			Random random = new Random();
			int index = random.nextInt(proper_noun.length);

			// We calculate the drone Speed.
			Double droneSpeed;

			// Function to calculate the Drone Speed.
			droneSpeed = speedCalcs.droneSpeed(data);

			// List of the parameters that are going to be added.
			List<List<String>> rows = Arrays.asList(Arrays.asList(String.valueOf(drone1.getDroneID()),
					String.valueOf(drone1.getTime()), String.valueOf(droneSpeed), proper_noun[index]));

			// Head of the CVS file.
			FileWriter csvWriter = new FileWriter(CSV_OUTPUT);
			csvWriter.append("Drone ID");
			csvWriter.append(",");
			csvWriter.append("Time");
			csvWriter.append(",");
			csvWriter.append("Speed m/s");
			csvWriter.append(",");
			csvWriter.append("Conditions of Traffic");
			csvWriter.append("\n");

			// Adds the elements to the CSV file
			for (List<String> rowData : rows) {
				csvWriter.append(String.join(",", rowData));
				csvWriter.append("\n");
			}

			// Closing the file
			csvWriter.flush();
			csvWriter.close();
		}

	}

	public static void writeNewRow(ArrayList<Drone> data, String CSV_OUTPUT) throws IOException {

		Drone drone1 = data.get(0);

		// We choose randomly the traffic conditions.
		final String[] proper_noun = { "HEAVY", "LIGHT", "MODERATE" };
		Random random = new Random();
		int index = random.nextInt(proper_noun.length);

		// We calculate the drone Speed
		Double droneSpeed;
		
		// Function to calculate the Drone Speed.
		droneSpeed = speedCalcs.droneSpeed(data);

		// List of the parameters that are going to be added.
		List<List<String>> rows = Arrays.asList(Arrays.asList(String.valueOf(drone1.getDroneID()),
				String.valueOf(drone1.getTime()), String.valueOf(droneSpeed), proper_noun[index]));
		
		// Using BufferedWriter to save the previous information stored in the file.
		Writer output = null;
		output = new BufferedWriter(new FileWriter(CSV_OUTPUT, true));

		// Adding the new row to the file.
		for (List<String> rowData : rows) {
			output.append(String.join(",", rowData));
			output.append("\n");
		}
		
		// Closing the file
		output.flush();
		output.close();
	}

}
