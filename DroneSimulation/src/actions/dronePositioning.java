package actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import bussiness.Drone;

public class dronePositioning extends csvReader {

	public final static String EMPTY = "";

	public static ArrayList<Drone> sendCoordinate(ArrayList<Drone> droneInfo, String DRONE_FILE) {

		// Array with the drone Position
		ArrayList<Drone> drone1 = new ArrayList<Drone>();
		
		// Read the CSV files and stores them
		List<Drone> droneList = readDroneFile(DRONE_FILE);
		
		// Size of the file
		final int droneSize_5937 = droneList.size();
		
		// Drone ID
		int drone_id = 0;
		
		//Information of the first Drone
		Drone temp = null;
		if(!droneInfo.isEmpty()) {
			 temp = droneInfo.get(0);
		}

		// Last known position of the drone
		String posLon = EMPTY;
		String posLat = EMPTY;
		
		// Previous Drone position
		String posLastLon = EMPTY;
		String posLastLat = EMPTY;

		if (!droneInfo.isEmpty()) {
			// If there are data stored we recover it.
			posLat = temp.getLatitude();
			posLon = temp.getLongitude();

		}
		
		// Stored current Drone Time
		String droneTime = null;
		
		//Stored previous Drone Time
		String previousDroneTime = null;
		
		// Last known time of the drone
		long droneTime_H = 0;
		long droneTime_M = 0;
		
		// Last position of the array
		int index = 0;
		
		//Last index
		if (!droneInfo.isEmpty()) {
			
			index = temp.getIndex();
			
		}

		// StringFormatter for the TimeCalcs
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		// Boolean that controls the shutdown of the drone
		Boolean SHUTDOWN = false;

		// Starts reading a CSV file
		for (int i = index+1; i < droneSize_5937; i++) {

			// Gets one CSV row
			Drone drone = droneList.get(i);

			if (posLon.equals(EMPTY) && posLat.equals(EMPTY)) {
				
				// Sets the position if Empty
				posLon = drone.getLongitude();
				posLat = drone.getLatitude();
				
				// Sets the drone id
				drone_id = drone.getDroneID();
				
				//Sets the drone Time
				droneTime = drone.getTime();

				LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(drone.getTime().replaceAll("\"", "")));
				
				droneTime_H = localDateTime.getHour();
				droneTime_M = localDateTime.getMinute();
				
				break;


			} else if (posLon.equals(drone.getLongitude()) && posLat.equals(drone.getLatitude()) && i != index) {
				// Updates the new position
				
				if(i != droneSize_5937 -1 && i !=droneSize_5937) {
					Drone newDrone = droneList.get(i + 1);
					
					// Sets the new Drone position
					posLon = newDrone.getLongitude();
					posLat = newDrone.getLatitude();
					
					// Sets the old Drone position
					posLastLon = drone.getLongitude();
					posLastLat = drone.getLatitude();
					
					// Sets the drone id
					drone_id = drone.getDroneID();
					
					//Sets the current drone Time
					droneTime = newDrone.getTime();
					
					//Sets the previous drone Time
					previousDroneTime = drone.getTime();
					
					
				}

				LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(drone.getTime().replaceAll("\"", "")));

				droneTime_H = localDateTime.getHour();
				droneTime_M = localDateTime.getMinute();
				
				index = i;

				// If the drone reaches 8.10 register the SHUTDOWN will be initiated.
				if (droneTime_H == 8 && droneTime_M == 10) {

					SHUTDOWN = true;

				}
				
				break;
				
				

			}
						
		}
		
		

		// We send the information to the array
		Drone processedInfo = new Drone();
			processedInfo.setDroneID(drone_id);
			processedInfo.setLatitude(posLat);
			processedInfo.setLongitude(posLon);
			processedInfo.setSHUTDOWN(SHUTDOWN);
			processedInfo.setIndex(index);
			processedInfo.setTime(droneTime);
			processedInfo.setPreviousTime(previousDroneTime);
			processedInfo.setPrevious_Lat(posLastLat);
			processedInfo.setPrevious_Lon(posLastLon);
			
			drone1.add(processedInfo);
			

		return drone1;

	}

}
