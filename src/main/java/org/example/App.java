package org.example;

import java.sql.*;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_company?serverTimezone=Europe/Warsaw";
    private static final String USER = "jdbc_company_user";
    private static final String PASSWORD = "password1234";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        printTable(connection);
        updateSalary(connection, 8, 500);
        changeWorkPosition(connection, "Sales");

        connection.close();


    }

    private static void printTable(Connection connection) {
        //AutoCloseable
        try (
                Statement statement = connection.createStatement();
        ) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                float salary = resultSet.getFloat(4);
                Date employmentDate = resultSet.getDate(5);
                String workPosition = resultSet.getString(6);
                System.out.println("ID: " + id + " | First name: " + firstName + " | Last name: " + lastName + " | Salary: " + salary + " | Date of employment: " + employmentDate + " | Work position: " + workPosition);

            }
        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    private static void updateSalary(Connection connection, int monthWorked, float extraAmount) {
        //AutoCloseable
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET salary = salary + ? WHERE (SELECT TIMESTAMPDIFF(MONTH, employment_date, curdate())) > ?");
        ) {
            preparedStatement.setFloat(1, extraAmount);
            preparedStatement.setInt(2, monthWorked);
            int updatedRecords = preparedStatement.executeUpdate();
            System.out.println("Updated " + updatedRecords + " records.");

        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    private static void changeWorkPosition(Connection connection, String newWorkPosition){
        //AutoCloseable
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET work_position = ? WHERE id = ?");
        ) {

            for (int i = 1; i <= 5; i++) {
                preparedStatement.setString(1, newWorkPosition);
                preparedStatement.setInt(2, i);
                preparedStatement.executeUpdate();
            }


        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }


}
