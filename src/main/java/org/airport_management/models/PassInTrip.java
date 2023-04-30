package org.airport_management.models;

import java.sql.Timestamp;

public class PassInTrip {
    private int tripNumber;
    private int passengerNumber;
    private Timestamp date;
    private String place;

    /**
     * Default constructor;
     */
    public PassInTrip() {
    }

    /**
     * @param tripNumber;
     * @param passengerNumber;
     * @param date;
     * @param place;
     */
    public PassInTrip(int tripNumber, int passengerNumber, Timestamp date, String place) {
        this.tripNumber = tripNumber;
        this.passengerNumber = passengerNumber;
        this.date = date;
        this.place = place;
    }

    /**
     * @return the tripNumber;
     */
    public int getTripNumber() {
        return tripNumber;
    }

    /**
     * @param tripNumber the tripNumber  to set;
     */
    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    /**
     * @return the passengerNumber;
     */
    public int getPassengerNumber() {
        return passengerNumber;
    }

    /**
     * @param passengerNumber the passengerNumber to set;
     */
    public void setPassengerNumber(int passengerNumber) {
        this.passengerNumber = passengerNumber;
    }

    /**
     * @return the date;
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * @param date the date  to set;
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * @return the place;
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place the place to set;
     */
    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "PassInTrip {" + tripNumber + " " + passengerNumber + " " + date + " " + place + "}";
    }
}
