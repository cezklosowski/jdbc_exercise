package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeDAO implements Cloneable {

    public EmployeeDAO() {

    }

    public void create(Connection connetion, Employee employee) {
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

    public Employee read(Connection connetion, int searchedId) {
        Employee employee = new Employee();
        try (
                PreparedStatement preparedStatement = connetion.prepareStatement("SELECT * FROM employee WHERE id = ?");
        ) {
            preparedStatement.setInt(1, searchedId);
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


    public void update(Connection connection, int id) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which parameter you want to update (1. First name, 2. Last name, 3. Salary, 4. Date of employment, 5. Work position)");
            String option = scanner.nextLine();

            switch (option) {
                case ("1"):
                    PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE employee SET first_name = ? WHERE id = ?");
                    System.out.println("Input correct first name");
                    String firstNameUpdated = scanner.nextLine();
                    preparedStatement1.setString(1, firstNameUpdated);
                    preparedStatement1.setInt(2, id);
                    preparedStatement1.executeUpdate();
                    System.out.println("Employee updated.");
                    break;
                case ("2"):
                    PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE employee SET last_name = ? WHERE id = ?");
                    System.out.println("Input correct last name");
                    String lastNameUpdated = scanner.nextLine();
                    preparedStatement2.setString(1, lastNameUpdated);
                    preparedStatement2.setInt(2, id);
                    preparedStatement2.executeUpdate();
                    System.out.println("Employee updated.");
                    break;
                case ("3"):
                    PreparedStatement preparedStatement3 = connection.prepareStatement("UPDATE employee SET salary = ? WHERE id = ?");
                    System.out.println("Input correct salary");
                    float salaryUpdated = Float.parseFloat(scanner.nextLine());
                    preparedStatement3.setFloat(1, salaryUpdated);
                    preparedStatement3.setInt(2, id);
                    preparedStatement3.executeUpdate();
                    System.out.println("Employee updated.");
                    break;
                case ("4"):
                    PreparedStatement preparedStatement4 = connection.prepareStatement("UPDATE employee SET employment_date = ? WHERE id = ?");
                    System.out.println("Input correct date of employment (YYYY-MM-DD)");
                    Date employmentDateUpdated = Date.valueOf(scanner.nextLine());
                    preparedStatement4.setDate(1, employmentDateUpdated);
                    preparedStatement4.setInt(2, id);
                    preparedStatement4.executeUpdate();
                    System.out.println("Employee updated.");
                    break;
                case ("5"):
                    PreparedStatement preparedStatement5 = connection.prepareStatement("UPDATE employee SET work_position = ? WHERE id = ?");
                    System.out.println("Input correct work position");
                    String workPositionUpdated = scanner.nextLine();
                    preparedStatement5.setString(1, workPositionUpdated);
                    preparedStatement5.setInt(2, id);
                    preparedStatement5.executeUpdate();
                    System.out.println("Employee updated.");
                    break;
            }


        } catch (SQLException ex) {
            System.err.println("Error code: " + ex.getErrorCode());
            ex.printStackTrace();
        }

    }

    public void delete(Connection connetion, int id) {
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
