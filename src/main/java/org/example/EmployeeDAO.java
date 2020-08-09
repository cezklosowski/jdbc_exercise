package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeDAO implements Cloneable {

    public EmployeeDAO() {

    }

    public void create(Connection connetion, Employee employee){
        try (
                PreparedStatement preparedStatement = connetion.prepareStatement("INSERT INTO employee (id, first_name, last_name, salary, employment_date, work_position) VALUES (?,?,?,?,?,?)");
        ) {
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setFloat(4, employee.getSalary());
            preparedStatement.setDate(5, Date.valueOf(employee.getEmploymentDate()));
            preparedStatement.setString(6, employee.getWorkPosition());

            int updatedRecords = preparedStatement.executeUpdate();
            System.out.println("Added new employee.");

        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }


    }

    public Employee read(Connection connetion, int searchedId){
        Employee employee = new Employee();
        try (
                PreparedStatement preparedStatement = connetion.prepareStatement("SELECT * FROM employee WHERE id = ?");
        ) {
            preparedStatement.setInt(1,searchedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                float salary = resultSet.getFloat(4);
                Date tempEmploymentDate = resultSet.getDate(5);
                LocalDate employmentDate = tempEmploymentDate.toLocalDate();
                String workPosition = resultSet.getString(6);

                employee.setId(id);
                employee.setFirstName(firstName);
                employee.setLastName(lastName);
                employee.setSalary(salary);
                employee.setEmploymentDate(employmentDate);
                employee.setWorkPosition(workPosition);

            }

        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }

        return employee;

    }

    public void update(Connection connetion, int id){

    }

    public void delete(Connection connetion, int id){
        try (
                PreparedStatement preparedStatement = connetion.prepareStatement("DELETE FROM employee WHERE id = ?");
        ) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Employee deleted.");

        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }

    }
}
