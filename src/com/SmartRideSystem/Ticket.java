package com.SmartRideSystem;

public class Ticket {
    private String passengerName;
    private String gettingInPoint;
    private String gettingOffPoint;
    private int travelTime;  // Travel time in minutes

    // Constructor to initialize ticket details
    public Ticket(String passengerName, String gettingInPoint, String gettingOffPoint, int travelTime) {
        this.passengerName = passengerName;
        this.gettingInPoint = gettingInPoint;
        this.gettingOffPoint = gettingOffPoint;
        this.travelTime = travelTime;
    }

    @Override
    public String toString() {
        return "Passenger: " + passengerName + ", Getting In: " + gettingInPoint + ", Getting Off: " + gettingOffPoint + ", Travel Time: " + travelTime + " mins";
    }

    // Getter for travel time
    public int getTravelTime() {
        return travelTime;
    }

    // Getter for passenger name
    public String getPassengerName() {
        return passengerName;
    }
}
