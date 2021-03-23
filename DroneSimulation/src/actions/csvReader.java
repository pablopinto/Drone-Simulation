package actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import bussiness.Drone;
import bussiness.Tube;

public class csvReader {

	public static List<Drone> readDroneFile(String file) { 
		
		List<Drone> drone = readDroneFromCSV(file); // let's print all the person read from CSV file 
		return drone;
	}
	
	public static List<Tube> readTubeFile(String file) { 
		
		List<Tube> tube = readTubeFromCSV(file); // let's print all the person read from CSV file 
		return tube;
	}

	private static List<Drone> readDroneFromCSV(String fileName) {
		List<Drone> drones = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = br.readLine();

			while (line != null) {
				String[] attributes = line.split(",");
				
				//Drone File Reader
				Drone drone = createDrone(attributes);
				
				
				drones.add(drone);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return drones;
	}
	
	
	
	
	private static Drone createDrone(String[] metadata) { 
		int droneID = Integer.parseInt(metadata[0]); 
		String latitude = metadata[1];
		String longitude =  metadata[2];
		String Time = metadata [3];
		return new Drone(droneID, latitude, longitude, Time);
		}
	
	
	
	
	private static List<Tube> readTubeFromCSV(String fileName) {
		List<Tube> tubes = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			String line = br.readLine();

			while (line != null) {
				String[] attributes = line.split(",");
				
				//Tube File Reader
				Tube tube = createTube(attributes);
				
				
				tubes.add(tube);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return tubes;
	}
	
	
	
	private static Tube createTube(String[] metadata) { 
		String station = metadata [0];
		String latitude = metadata[1];
		String longitude =  metadata[2];
		return new Tube(station, latitude, longitude);
		}
	

	
	

}
