package org.airport_management.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.airport_management.connection.DbConnection;
import org.airport_management.models.Trip;

public class TripService {
    /**
     * Method gets by id Trip
     *
     * @param id
     * @return trip;
     */
    public Trip getById(int id) {
        Trip trip = null;
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM trip WHERE TripId = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            trip = new Trip();
            if (resultSet.next()) {
                trip.setTripId(resultSet.getInt("tripId"));
                trip.setCompanyNumber(resultSet.getInt("companyNumber"));
                trip.setAirplane(resultSet.getString("airplane"));
                trip.setCityFrom(resultSet.getString("cityFrom"));
                trip.setCityTo(resultSet.getString("cityTo"));
                trip.setTimeDeparture(resultSet.getDate("timeDeparture"));
                trip.setTimeArrival(resultSet.getDate("timeArrival"));
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
        return trip;
    }

    /**
     * Method gets all Trips from Table
     *
     * @return allTrips;
     */
    public Set<Trip> getAll() {
        Set<Trip> allTrips = new LinkedHashSet<>();
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM trip";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setTripId(resultSet.getInt("tripId"));
                trip.setCompanyNumber(resultSet.getInt("companyNumber"));
                trip.setAirplane(resultSet.getString("airplane"));
                trip.setCityFrom(resultSet.getString("cityFrom"));
                trip.setCityTo(resultSet.getString("cityTo"));
                trip.setTimeDeparture(resultSet.getDate("timeDeparture"));
                trip.setTimeArrival(resultSet.getDate("timeArrival"));
                allTrips.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return allTrips;
    }

    /**
     * Method returns a Set starting from a certain id
     *
     * @param offset;
     * @param perPage;
     * @param sort;
     * @return allTrips;
     */
    public Set<Trip> get(int offset, int perPage, String sort) {
        Set<Trip> allTrips = new LinkedHashSet<>();
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "SELECT * FROM trip WHERE TripId >= ? ORDER BY " + sort + " LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, perPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Trip trip = new Trip();
                trip.setTripId(resultSet.getInt("tripId"));
                trip.setCompanyNumber(resultSet.getInt("companyNumber"));
                trip.setAirplane(resultSet.getString("airplane"));
                trip.setCityFrom(resultSet.getString("cityFrom"));
                trip.setCityTo(resultSet.getString("cityTo"));
                trip.setTimeDeparture(resultSet.getDate("timeDeparture"));
                trip.setTimeArrival(resultSet.getDate("timeArrival"));
                allTrips.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return allTrips;
    }

    /**
     * Method saves new Trip
     *
     * @param trip
     * @return
     */
    public Trip save(Trip trip) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "INSERT INTO trip(companyNumber,airplane,cityFrom,cityTo,timeDeparture,timeArrival) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trip.getCompanyNumber());
            statement.setString(2, trip.getAirplane());
            statement.setString(3, trip.getCityFrom());
            statement.setString(4, trip.getCityTo());
            statement.setDate(5, trip.getTimeDeparture());
            statement.setDate(6, trip.getTimeArrival());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return trip;
    }

    /**
     * Method updates trip by ID.
     *
     * @param id;
     * @param trip;
     * @return trip;
     */
    public Trip update(int id, Trip trip) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE trip SET companyNumber = ?,airplane = ?,cityFrom = ?,cityTo = ?, timeDeparture = ?, timeArrival = ? WHERE tripNumber = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            Statement stResult = connection.createStatement();
            ResultSet rs = stResult.executeQuery("SELECT * FROM trip WHERE id = " + id);
            if (rs.next()) {
                statement.setInt(1, trip.getCompanyNumber());
                statement.setString(2, trip.getAirplane());
                statement.setString(3, trip.getCityFrom());
                statement.setString(4, trip.getCityTo());
                statement.setDate(5, trip.getTimeDeparture());
                statement.setDate(6, trip.getTimeArrival());
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
            DbConnection.getInstance().closeConnection();
        }
        return trip;
    }

    /**
     * Method removes trip by id
     *
     * @param tripId;
     */
    public void delete(int tripId) {
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM trip WHERE TripId = " + tripId);
            if (rs.next()) {
                String sql = "DELETE FROM trip WHERE tripId = " + tripId;
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
     * Method Gets all Trips from city
     *
     * @param city;
     * @return tripsFromList;
     */
    public List<Trip> getTripsFrom(String city) {
        List<Trip> tripsFromList = new ArrayList<>();
        Trip trip = null;
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM trip WHERE cityFrom = " + city;
            ResultSet resultSet = statement.executeQuery(sql);
            trip = new Trip();
            while (resultSet.next()) {
                trip.setTripId(resultSet.getInt("tripId"));
                trip.setCompanyNumber(resultSet.getInt("companyNumber"));
                trip.setAirplane(resultSet.getString("airplane"));
                trip.setCityFrom(resultSet.getString("cityFrom"));
                trip.setCityTo(resultSet.getString("cityTo"));
                trip.setTimeDeparture(resultSet.getDate("timeDeparture"));
                trip.setTimeArrival(resultSet.getDate("timeArrival"));
                tripsFromList.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return tripsFromList;
    }

    /**
     * Method Gets all Trips to city
     *
     * @param city;
     * @return tripsToList;
     */
    public List<Trip> getTripsTo(String city) {
        List<Trip> tripsToList = new ArrayList<>();
        Trip trip = null;
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM trip WHERE town_to = " + city;
            ResultSet resultSet = statement.executeQuery(sql);
            trip = new Trip();
            while (resultSet.next()) {
                trip.setTripId(resultSet.getInt("tripId"));
                trip.setCompanyNumber(resultSet.getInt("companyNumber"));
                trip.setAirplane(resultSet.getString("airplane"));
                trip.setCityFrom(resultSet.getString("cityFrom"));
                trip.setCityTo(resultSet.getString("cityTo"));
                trip.setTimeDeparture(resultSet.getDate("timeDeparture"));
                trip.setTimeArrival(resultSet.getDate("timeArrival"));
                tripsToList.add(trip);
            }
        } catch (SQLException e) {
            System.out.println("SQL command exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return tripsToList;
    }
}
