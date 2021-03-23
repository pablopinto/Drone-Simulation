package actions;

import java.util.ArrayList;
import java.util.List;

import bussiness.Drone;
import bussiness.Tube;

public class tubeCalcs extends distanceCalcs {

	// Provided CVS File
	public final static String TUBE_FILE = "provided\\tube.csv";

	public static Boolean checkTube(ArrayList<Drone> droneInfo) {
		
		
		Boolean writesFile = false;
		
		// Reading the provided CVS
		List<Tube> tubeFile = csvReader.readTubeFile(TUBE_FILE);
		
		Drone inf = droneInfo.get(0);
		
		// Number of rows in the file.
		int tubeSize = tubeFile.size();

		for (int i = 0; i < tubeSize; i++) {

			// Gets one CSV row
			Tube tube = tubeFile.get(i);
			
			// Position parameters of the Tube stations.
			Double lat = Double.valueOf(tube.getLatitude());
			Double lon = Double.valueOf(tube.getLongitude()); 
			
			// Position parameters of the Drone.
			Double latDrone = Double.valueOf(inf.getLatitude().replaceAll("\"", ""));
			Double lonDrone = Double.valueOf(inf.getLongitude().replaceAll("\"", ""));
			
			// Distance between Drone and Tube stations.
			double distanceDronTube = distanceCalcs.distance(latDrone, lat, lonDrone, lon);
			
			// If the distance is below 350m the information will be sent.
			if(distanceDronTube*1000 <= 350) {
				System.out.println("Sending data... at coordinates " + "'" + latDrone + "' "  + "'" +  + lonDrone  + "'" +". Data sent to " + "'" + tube.getStation()  + "'" +" station.");
				writesFile = true;
			}

			
		}
		
		return writesFile;
	}

}
