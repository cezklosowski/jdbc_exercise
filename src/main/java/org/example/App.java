package org.example;

import java.sql.*;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_company?serverTimezone=Europe/Warsaw";
    private static final String USER = "jdbc_company_user";
    private static final String PASSWORD = "password1234";

    public static void main(String[] args) {

        //AutoCloseable
        try (
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String salary = resultSet.getString(4);
                String employmentDate = resultSet.getString(5);
                String workPosition = resultSet.getString(6);
                System.out.println("ID: " + id + " | First name: " + firstName + " | Last name: " + lastName + " | Salary: " + salary + " | Date of employment: " + employmentDate + " | Work position: " + workPosition);
            }
        } catch (
                SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }


}
