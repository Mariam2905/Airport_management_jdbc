package org.airport_management.connection;

import java.sql.*;

public class DbConnection {
    //    private static DbConnection dbConnection = null;
//    private String driverName;
//    private String userName;
//    private String password;
//    private String url;
//    private Connection connection = null;
//
//    public DbConnection() {
//    }
//
//    public static DbConnection getInstance() {
//        if (dbConnection == null) {
//            dbConnection = new DbConnection();
//        }
//        return dbConnection;
//    }
//
//    public Connection getConnection() {
//        readConnectionParams();
//        try {
//            Class.forName(driverName);
//            connection = DriverManager.getConnection(url, userName, password);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    public void readConnectionParams() {
//        InputStream inputStream = DbConnection.class.getClassLoader()
//                .getResourceAsStream("config.properties");
//        Properties properties = new Properties();
//
//        if (inputStream != null) {
//            try {
//                properties.load(inputStream);
//                driverName = properties.getProperty("driverName");
//                userName = properties.getProperty("postgres");
//                password = properties.getProperty("Mariam2905");
//                url = properties.getProperty("jdbc:postgresql://localhost:5432/AirportManagementSystem");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    private static final String url = "jdbc:postgresql://localhost:5432/airportmanagementsystem";
    private static final String user = "postgres";
    private static final String password = "Mariam2905";
    private static DbConnection instance = new DbConnection();
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    /**
     * Private constructor
     */
    private DbConnection() {
    }

    /**
     * This method get Connection.
     *
     * @return instance;
     */
    public static DbConnection getInstance() {
        if (instance == null) {
            instance = new DbConnection();
        }
        return instance;
    }

    /**
     * This method gets connection.
     *
     * @return connection;
     */
    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * This method close Connection.
     */
    public static void closeConnection() {
        if (connection == null) {
            throw new NullPointerException("The connection is interrupted.");
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
