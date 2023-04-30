package org.airport_management.connection;

import org.postgresql.copy.CopyManager;
import org.postgresql.core.BaseConnection;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.*;

public class CreateModels {

    /**
     * This method creates table Company with rows that are inserted from companies.txt file.
     */
    public void createTableCompany() {
        Connection connection = DbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "create table Company (companyId serial PRIMARY KEY," +
                    "companyName VARCHAR(50) NOT NULL, foundingDate DATE NOT NULL);";
            stmt.executeUpdate(query);
            System.out.println("The table company created");
            long rowsInserted = new CopyManager((BaseConnection) DbConnection.getConnection())
                    .copyIn("copy company(companyName, foundingDate) from STDIN (FORMAT CSV, HEADER, DELIMITER ',');",
                            new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\airport_management_system\\src\\main\\resources\\companies.txt")));
            System.out.println("Rows inserted");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method creates table Passenger with rows that are inserted from passengers.txt file.
     */
    public void createTablePassenger() {
        Connection connection = DbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "create table Passenger (passengerId serial PRIMARY KEY, passengerName VARCHAR(50) NOT NULL, passengerPhone VARCHAR(50) NOT NULL," +
                    "country VARCHAR(50) NOT NULL, city VARCHAR(50) NOT NULL);";
            stmt.executeUpdate(query);
            System.out.println("The table passenger created.");
            long rowsInserted = new CopyManager((BaseConnection) DbConnection.getConnection())
                    .copyIn("copy passenger(passengerName, passengerPhone, country, city) from STDIN (FORMAT CSV, HEADER, DELIMITER ',');",
                            new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\airport_management_system\\src\\main\\resources\\passengers.txt")));
            System.out.println("Rows inserted.");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * This method creates table Trip with rows that are inserted from trip.txt file.
     */
    public void createTableTrip() {
        Connection connection = DbConnection.getConnection();
        try {
            Statement stmt = connection.createStatement();
            String query = "create table Trip (tripId serial PRIMARY KEY, companyNumber INTEGER references company(companyId) NOT NULL," +
                    "airline VARCHAR(50) NOT NULL, cityFrom VARCHAR(50) NOT NULL, " +
                    "cityTo VARCHAR(50) NOT NULL, timeDeparture TIMESTAMP NOT NULL," +
                    "timeArrival TIMESTAMP NOT NULL);";
            stmt.executeUpdate(query);
            System.out.println("The table trip created․");
            long rowsInserted = new CopyManager((BaseConnection) connection)
                    .copyIn("copy trip(tripId, companyNumber, airline, cityFrom, cityTo, timeDeparture, timeArrival) from STDIN (FORMAT CSV, HEADER, DELIMITER ',');",
                            new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\airport_management_system\\src\\main\\resources\\trip.txt")));
            System.out.println("Rows inserted.");
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method creates table PassInTrip with rows that are inserted from pass_in_trip.txt file.
     */
    public void createTablePassInTrip() {
        try {
            Connection connection = DbConnection.getConnection();
            Statement stmt = connection.createStatement();
            String query = "create table PassInTrip (tripNumber INTEGER references Trip(tripId) NOT NULL," +
                    "passengerNumber INTEGER references Passenger(passengerId) NOT NULL, date TIMESTAMP NOT NULL, place VARCHAR(50) NOT NULL);";
            stmt.executeUpdate(query);
            System.out.println("The table PassInTrip created.");
            long rowsInserted = new CopyManager((BaseConnection) connection).copyIn("copy passInTrip(tripNumber, passengerNumber, date, place) from STDIN (FORMAT CSV, HEADER, DELIMITER ',');",
                    new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\airport_management_system\\src\\main\\resources\\pass_in_trip.txt")));
            System.out.println("Rows inserted.");
            connection.close();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
