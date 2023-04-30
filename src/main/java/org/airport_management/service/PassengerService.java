package org.airport_management.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.airport_management.connection.DbConnection;
import org.airport_management.models.Passenger;
import org.airport_management.models.Trip;


public class PassengerService {
    /**
     * Method gets Passenger by passengerId.
     *
     * @param passengerId;
     * @return passenger;
     */
    public Passenger getById(int passengerId) {
        Passenger passenger = null;
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM passengers WHERE passengerId = " + passengerId;
            ResultSet resultSet = statement.executeQuery(sql);
            passenger = new Passenger(resultSet.getString("passengerName"), resultSet.getString("passengerPhone"), resultSet.getString("country"), resultSet.getString("city"));
            if (resultSet.next()) {
                passenger.setPassengerId(resultSet.getInt("passengerId"));
                passenger.setPassengerName(resultSet.getString("passengerName"));
                passenger.setPassengerPhone(resultSet.getString("passengerPhone"));
                passenger.setCountry(resultSet.getString("country"));
                passenger.setCity(resultSet.getString("city"));
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException a) {
            System.out.println("SQL Command Exception");
        } catch (NoSuchElementException b) {
            throw new NoSuchElementException("Can't find element in database");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return passenger;
    }


    /**
     * Method gets all passengers from Table
     *
     * @return allUsers;
     */
    public Set<Passenger> getAll() {
        Set<Passenger> allUsers = new LinkedHashSet<Passenger>();
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM passengers";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getString("passengerName"), resultSet.getString("passengerPhone"), resultSet.getString("country"), resultSet.getString("city"));
                passenger.setPassengerId(resultSet.getInt("passengerId"));
                passenger.setPassengerName(resultSet.getString("passengerName"));
                passenger.setPassengerPhone(resultSet.getString("passengerPhone"));
                passenger.setCountry(resultSet.getString("country"));
                passenger.setCity(resultSet.getString("city"));
                allUsers.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return allUsers;

    }

    /**
     * Method returns a Set starting from a certain passengerId
     *
     * @param offset;
     * @param perPage;
     * @param sort;
     * @return passengers;
     */
    public Set<Passenger> get(int offset, int perPage, String sort) {
        Set<Passenger> passengers = new LinkedHashSet<>();
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "SELECT * FROM passengers WHERE passengerId >= ? ORDER BY " + sort + " LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, perPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getString("passengerName"), resultSet.getString("passengerPhone"), resultSet.getString("country"), resultSet.getString("city"));
                passenger.setPassengerId(resultSet.getInt("passengerId"));
                passenger.setPassengerName(resultSet.getString("passengerName"));
                passenger.setPassengerPhone(resultSet.getString("passengerPhone"));
                passenger.setCountry(resultSet.getString("country"));
                passenger.setCity(resultSet.getString("city"));
                passengers.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return passengers;
    }


    /**
     * Method saves new Passenger.
     *
     * @param passenger;
     * @return passenger;
     */
    public Passenger save(Passenger passenger) {
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "INSERT INTO passengers(passengerName,passengerPhone,country,city) VALUES(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, passenger.getPassengerName());
            statement.setString(2, passenger.getPassengerPhone());
            statement.setString(3, passenger.getCountry());
            statement.setString(4, passenger.getCity());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return passenger;
    }

    /**
     * Method updates passenger by passengerId;
     *
     * @param id;
     * @param passenger;
     * @return passenger;
     */
    public Passenger update(int id, Passenger passenger) {
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "UPDATE passengers SET passengerName = ?, passengerPhone = ?, country = ?,city = ? WHERE passengerId = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM passengers WHERE passengerId = " + id);
            if (rs.next()) {
                statement.setString(1, passenger.getPassengerName());
                statement.setString(2, passenger.getPassengerPhone());
                statement.setString(3, passenger.getCountry());
                statement.setString(4, passenger.getCity());
                statement.executeUpdate();
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NoSuchElementException b) {
            throw new NoSuchElementException("Can't find element in database");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return passenger;
    }

    /**
     * Method removes passenger by id
     *
     * @param passengerId;
     */
    public void delete(int passengerId) {
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM passengers WHERE passengerId = " + passengerId;
            ResultSet rs = statement.executeQuery("SELECT from passengers WHERE passengerId = " + passengerId);
            if (rs.next()) {
                statement.executeUpdate(sql);
            } else {
                throw new NoSuchElementException();
            }
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NoSuchElementException b) {
            throw new NoSuchElementException("Can't find element in database");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
    }

    /**
     * Method get Passengers Of Trip
     *
     * @param tripNumber;
     * @return passengersOfTrip;
     */
    public List<Passenger> getPassengersOfTrip(int tripNumber) {
        List<Passenger> passengersOfTrip = new ArrayList<Passenger>();
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT p.passengerId, p.passengerName, p.passengerPhone, p.country, p.city "
                    + "FROM passengers p "
                    + "JOIN passInTrip pit ON p.id = pit.passengerId "
                    + "WHERE pit.tripId = " + tripNumber;
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getString("passengerName"), resultSet.getString("passengerPhone"), resultSet.getString("country"), resultSet.getString("city"));
                passenger.setPassengerId(resultSet.getInt("passengerId"));
                passengersOfTrip.add(passenger);
            }
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NoSuchElementException b) {
            throw new NoSuchElementException("Can't find element in database");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return passengersOfTrip;
    }

    /**
     * Method register new Trip, and passenger
     *
     * @param trip;
     * @param passenger;
     */
    public void registerTrip(Trip trip, Passenger passenger) {
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "INSERT INTO trip(id,companyId,airplane,cityFrom,cityTo,timeDeparture,timeArrival) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statementForCreateTrip = connection.prepareStatement(sql);
            statementForCreateTrip.setInt(1, trip.getTripId());
            statementForCreateTrip.setInt(2, trip.getCompanyNumber());
            statementForCreateTrip.setString(3, trip.getAirplane());
            statementForCreateTrip.setString(4, trip.getCityFrom());
            statementForCreateTrip.setString(5, trip.getCityTo());
            statementForCreateTrip.setString(6, String.valueOf(trip.getTimeDeparture()));
            statementForCreateTrip.setString(7, String.valueOf(trip.getTimeArrival()));
            statementForCreateTrip.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet2 = statement.executeQuery("SELECT * FROM passengers ORDER BY passengerId DESC LIMIT 1");
            if (resultSet2.next()) {
                passenger.setPassengerId(resultSet2.getInt("passengerId"));
            }


            /**
             * Calling our save method
             */
            save(passenger);
            String sql2 = "INSERT INTO passInTrip (tripId, passengerId, date, place) VALUES(?,?,?,?)";
            PreparedStatement statementForPassInTrip = connection.prepareStatement(sql2);
            statementForPassInTrip.setInt(1, trip.getTripId());
            statementForPassInTrip.setInt(2, passenger.getPassengerId() + 1);
            statementForPassInTrip.setString(3, "1900-01-01 08:01:00.000");
            statementForPassInTrip.setString(4, "7a");
            statementForPassInTrip.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.getInstance().closeConnection();
        }
    }

    /**
     * Method cancel Trip, deletes passenger and trip
     *
     * @param passengerId;
     * @param tripNumber;
     */
    public void cancelTrip(int passengerId, long tripNumber) {
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "DELETE from passInTrip WHERE tripId = " + tripNumber;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM passInTrip WHERE tripId = " + tripNumber);
            statement.executeUpdate(sql);
            sql = "DELETE FROM trip WHERE tripId = " + tripNumber;
            if (rs.next()) {
                statement.executeUpdate(sql);
            } else {
                throw new NoSuchElementException();
            }

            /**
             * Calling our passenger delete method
             */
            delete(passengerId);
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NoSuchElementException b) {
            throw new NoSuchElementException("Can't find element in database");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
    }
}
