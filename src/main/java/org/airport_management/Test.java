package org.airport_management;

import org.airport_management.connection.CreateModels;
import org.airport_management.service.CompanyService;
import org.airport_management.service.PassengerService;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
//        CreateModels table1 = new CreateModels();
//        CreateModels table2 = new CreateModels();
//        CreateModels table3 = new CreateModels();
//        CreateModels table4 = new CreateModels();
//        table1.createTableCompany();
//        table2.createTablePassenger();
//        table3.createTableTrip();
//        table4.createTablePassInTrip();

        CompanyService cs = new CompanyService();
//        cs.getById(3);
//        cs.getAll();
//        cs.get(1, 3, "pass_city");
//        cs.saveCompany("AC", 1/1/86);
//        cs.update(1, "Zv",5/19/1986);
//        cs.delete(1);

        PassengerService ps = new PassengerService();
//        ps.getPassengersOfTrip(1);

    }
}