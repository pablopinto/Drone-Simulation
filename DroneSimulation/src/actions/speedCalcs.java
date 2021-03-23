package actions;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import bussiness.Drone;

public class speedCalcs {

	public static Double droneSpeed(ArrayList<Drone> data) {

		double distance = 0;

		Drone drone1 = data.get(0);

		// We need to know the distance between Drone position A and B

		// Drone position B
		Double currentDroneLat = Double.valueOf(drone1.getLatitude().replaceAll("\"", ""));
		Double currentDroneLon = Double.valueOf(drone1.getLongitude().replaceAll("\"", ""));

		// Drone position A
		Double previousDroneLat = Double.valueOf(drone1.getPrevious_Lat().replaceAll("\"", ""));
		Double previousDroneLon = Double.valueOf(drone1.getPrevious_Lon().replaceAll("\"", ""));
		
		// Distance between point A and B. Distance that the drone has traveled.
		distance = distanceCalcs.distance(currentDroneLat, previousDroneLat, currentDroneLon, previousDroneLon);
		
		// Time at point B
		String currentDroneTime = drone1.getTime();
		
		// Time at point A
		String previousDroneTime = drone1.getPreviousTime();

		// StringFormatter for the TimeCalcs
		String pattern = "yyyy-MM-dd HH:mm:ss";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		LocalDateTime ldt1 = LocalDateTime.from(formatter.parse(currentDroneTime.replaceAll("\"", "")));
		LocalDateTime ldt2 = LocalDateTime.from(formatter.parse(previousDroneTime.replaceAll("\"", "")));

		// Time interval in which the drone has traveled the distance.
		Duration d1 = Duration.between(ldt2, ldt1);
		
		//Drone Speed is = (KM * 1000) / Time.
		Double droneSpeed = (distance * 1000) / d1.getSeconds();

		return droneSpeed;
	}

}
