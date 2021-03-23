package bussiness;

public class Drone {
	
	/*
	 * Drone Parameters
	 */
	public int DroneID;
	public String latitude;
	public String longitude;
	public String Time;
	
	/*
	 *  Valuable information
	 */
	
	public Boolean SHUTDOWN;
	public int index;
	public String previous_Lat;
	public String previous_Lon;
	public String previousTime;
	
	
	public Drone(int droneID, String latitude, String longitude, String Time) {
		this.DroneID = droneID;
		this.latitude = latitude;
		this.longitude = longitude;
		this.Time = Time;
	}
	
	public Drone() {
		
	}
	
	
	/* 
	 * Getter and Setters for the drone information 
	 */

	public int getDroneID() {
		return DroneID;
	}
	public void setDroneID(int droneID) {
		DroneID = droneID;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}
	
	
	/*
	 *  Getter and Setters for the Valuable information
	 */


	public Boolean getSHUTDOWN() {
		return SHUTDOWN;
	}


	public void setSHUTDOWN(Boolean sHUTDOWN) {
		SHUTDOWN = sHUTDOWN;
	}


	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}

	public String getPrevious_Lat() {
		return previous_Lat;
	}

	public void setPrevious_Lat(String previous_Lat) {
		this.previous_Lat = previous_Lat;
	}

	public String getPrevious_Lon() {
		return previous_Lon;
	}

	public void setPrevious_Lon(String previous_Lon) {
		this.previous_Lon = previous_Lon;
	}

	public String getPreviousTime() {
		return previousTime;
	}

	public void setPreviousTime(String previousTime) {
		this.previousTime = previousTime;
	}
	
	
	
	
	
	
	

}
