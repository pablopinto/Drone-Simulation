package bussiness;

public class Tube {
	
	/*
	 * Drone Parameters
	 */
	public String station;
	public String latitude;
	public String longitude;
	
	
	public Tube(String station, String latitude, String longitude) {
		this.station = station;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	/* 
	 * Getter and Setters for the tube information 
	 */

	public String getStation() {
		return station;
	}


	public void setStation(String station) {
		this.station = station;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatidude(String latitude) {
		this.latitude = latitude;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	 

}
