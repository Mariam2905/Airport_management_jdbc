package org.airport_management.service;

import org.airport_management.connection.DbConnection;
import org.airport_management.models.Company;

import java.sql.*;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public class CompanyService {

    /**
     * Method gets by id Company
     *
     * @param id;
     * @return company;
     */
    public Company getById(int id) {
        Company company = null;
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM company WHERE companyId = " + id;
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                company = new Company();
                company.setCompanyId(resultSet.getInt("companyId"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setFoundingDate(resultSet.getDate("foundingDate"));
                System.out.println("Company found with id is: " + id + company);
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
        return company;
    }

    /**
     * Method gets all companies from Table.
     *
     * @return all users;
     */
    public Set<Company> getAll() {
        Set<Company> allUsers = new LinkedHashSet<Company>();
        Company company = null;
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM company";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                company = new Company();
                company.setCompanyId(resultSet.getInt("companyId"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setFoundingDate(resultSet.getDate("foundingDate"));
                allUsers.add(company);
                System.out.println(allUsers);
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
     * Method returns a Set starting from a certain id.
     *
     * @param offset;
     * @param perPage;
     * @param sort;
     * @return companyList;
     */
    public Set<Company> get(int offset, int perPage, String sort) {
        Set<Company> companyList = new LinkedHashSet<>();
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "SELECT * FROM company WHERE companyId >= ? ORDER BY " + sort + " LIMIT ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, perPage);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Company company = new Company();
                company.setCompanyId(resultSet.getInt("companyId"));
                company.setCompanyName(resultSet.getString("companyName"));
                company.setFoundingDate(resultSet.getDate("foundingDate"));
                companyList.add(company);
            }
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return companyList;
    }

    /**
     * Method saves new Company;
     *
     * @param company;
     * @return company;
     */
    public Company saveCompany(Company company) {
        java.sql.Date sqlDate = new java.sql.Date(company.getFoundingDate().getTime());
        try {
            Connection connection = DbConnection.getConnection();
            String sql = "INSERT INTO company(companyName,foundingDate) VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, company.getCompanyName());
            statement.setDate(2, sqlDate);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Command Exception");
        } catch (NullPointerException c) {
            throw new NullPointerException("Result Set,statement, or connection is Null");
        } finally {
            DbConnection.closeConnection();
        }
        return company;
    }

    /**
     * Method updates company by ID,
     *
     * @param id;
     * @param company;
     * @return company;
     */
    public Company update(int id, Company company) {
        java.sql.Date sqlDate = new java.sql.Date(company.getFoundingDate().getTime());
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            String sql = "UPDATE company SET companyName = ?,foundingDate = ? WHERE companyId = " + id;
            PreparedStatement statement = connection.prepareStatement(sql);
            Statement stResult = connection.createStatement();
            ResultSet rs = stResult.executeQuery("SELECT * FROM company WHERE id = " + id);
            if (rs.next()) {
                statement.setString(1, company.getCompanyName());
                statement.setDate(2, sqlDate);
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
        return company;
    }

    /**
     * Method removes company by id
     *
     * @param companyId;
     */
    public void delete(int companyId) {
        try {
            Connection connection = DbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM company WHERE companyId = " + companyId);
            String sql = "DELETE FROM company WHERE companyId = " + companyId;
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
}
