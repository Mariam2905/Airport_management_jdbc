package org.airport_management.models;
import java.security.Timestamp;
import java.sql.Date;

public class Trip {
    private int tripId;
    private int companyNumber;
    private String airplane;
    private String cityFrom;
    private String cityTo;
    private Timestamp timeDeparture;
    private Timestamp timeArrival;

    /**
     * Default constructor Trip
     */
    public Trip() {
    }


    /**
     * @param tripId;
     * @param companyId;
     * @param airplane;
     * @param townFrom;
     * @param townTo;
     * @param timeOut;
     * @param timeIn;
     */
    public Trip(int tripId, int companyId, String airplane, String townFrom, String townTo, Timestamp timeOut, Timestamp timeIn) {
        this.tripId = tripId;
        this.companyNumber = companyNumber;
        this.airplane = airplane;
        this.cityFrom = townFrom;
        this.cityTo = townTo;
        this.timeDeparture = timeOut;
        this.timeArrival = timeIn;
    }

    /**
     * @param triId;
     */
    public Trip(int triId) {
        this.tripId = triId;
    }


    /**
     * @return the tripId;
     */
    public int getTripId() {
        return tripId;
    }

    /**
     * @param tripId the tripId to set;
     */
    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    /**
     * @return the companyId;
     */
    public int getCompanyNumber() {
        return companyNumber;
    }

    /**
     * @param companyNumber the companyId to set;
     */
    public void setCompanyNumber(int companyNumber) {
        this.companyNumber = companyNumber;
    }

    /**
     * @return the Airplane;
     */
    public String getAirplane() {
        return airplane;
    }

    /**
     * @param airplane the airplane to set;
     */
    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    /**
     * @return the cityFrom;
     */
    public String getCityFrom() {
        return cityFrom;
    }

    /**
     * @param cityFrom the cityFrom to set;
     */
    public void setCityFrom(String cityFrom) {
        this.cityFrom = cityFrom;
    }

    /**
     * @return the cityTo;
     */
    public String getCityTo() {
        return cityTo;
    }

    /**
     * @param cityTo the cityTo set;
     */
    public void setCityTo(String cityTo) {
        this.cityTo = cityTo;
    }

    /**
     * @return the timeDeparture;
     */
    public Date getTimeDeparture() {
        return timeDeparture;
    }

    /**
     * @param timeDeparture the timeDeparture to set;
     */
    public void setTimeDeparture(Date timeDeparture) {
        this.timeDeparture = timeDeparture;
    }

    /**
     * @return the timeArrival;
     */
    public Date getTimeArrival() {
        return timeArrival;
    }

    /**
     * @param timeArrival the timeArrival to set;
     */
    public void setTimeArrival(Date timeArrival) {
        this.timeArrival = timeArrival;
    }

    @Override
    public String toString() {
        return "Trip {" + tripId + " " + companyNumber + " " + airplane + " " + cityFrom + " " + cityTo + " " + timeDeparture + " " + timeArrival + "}";
    }
}
