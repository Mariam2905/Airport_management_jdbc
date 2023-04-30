package org.airport_management.models;
public class Passenger {
    private int passengerId;
    private String passengerName;
    private String passengerPhone;
    private String country;
    private String city;

    /**
     * default constructor;
     */
    public Passenger() {
    }

    /**
     * @param passengerName;
     * @param passengerPhone;
     * @param country;
     * @param city;
     * @param passengerId;
     */
    public Passenger(String passengerName, String passengerPhone, String country, String city, int passengerId) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.country = country;
        this.city = city;
        this.passengerId = passengerId;
    }

    /**
     * @param passengerName;
     * @param passengerPhone;
     * @param country;
     * @param city;
     */
    public Passenger(String passengerName, String passengerPhone, String country, String city) {
        this.passengerName = passengerName;
        this.passengerPhone = passengerPhone;
        this.country = country;
        this.city = city;
    }

    /**
     * @param passengerId;
     */
    public Passenger(int passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * @return the passengerId;
     */
    public int getPassengerId() {
        return passengerId;
    }

    /**
     * @param passengerId the passengerId to set;
     */
    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    /**
     * @return the passengerName;
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * @param passengerName the passengerName to set;
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * @return the passengerPhone;
     */
    public String getPassengerPhone() {
        return passengerPhone;
    }

    /**
     * @param passengerPhone the passengerPhone to set;
     */
    public void setPassengerPhone(String passengerPhone) {
        this.passengerPhone = passengerPhone;
    }

    /**
     * @return the country;
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set;
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city;
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set;
     */
    public void setCity(String city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return "Passenger {" + passengerId + " " + passengerName + " " + passengerPhone + " " + country + " " + city + "}";
    }


//    @Override
//    public int compareTo(Object o) {
//        if (this.passenger_id > ((Passenger) o).getPassenger_id()){
//            return 1;
//        } else if (this.passenger_id < ((Passenger) o).getPassenger_id()){
//            return -1;
//        }
//        return 0;
//    }

}
