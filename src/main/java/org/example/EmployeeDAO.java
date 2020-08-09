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


    // TODO: zmienić update; nazwa kolumny nie może być parametryzowana; albo rozbić update na wszystkie kolumny i wywołać odpowiedni, albo updatować wszystkie kolumny



    public void update(Connection connetion, int id) {
        try (
                PreparedStatement preparedStatement = connetion.prepareStatement("UPDATE employee SET ? = ? WHERE id = ?");
        ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which parameter you want to update (1. First name, 2. Last name, 3. Salary, 4. Date of employment, 5. Work position)");
            String option = scanner.nextLine();
            String column = "";
            switch (option) {
                case ("1"):
                    column = "first_name";
                    System.out.println("Input correct first name");
                    String firstNameUpdated = scanner.nextLine();
                    preparedStatement.setString(1, column);
                    preparedStatement.setString(2, firstNameUpdated);
                    break;
                case ("2"):
                    column = "last_name";
                    System.out.println("Input correct last name");
                    String lastNameUpdated = scanner.nextLine();
                    preparedStatement.setString(1, column);
                    preparedStatement.setString(2, lastNameUpdated);
                    break;
                case ("3"):
                    column = "salary";
                    System.out.println("Input correct salary");
                    float salaryUpdated = Float.parseFloat(scanner.nextLine());
                    preparedStatement.setString(1, column);
                    preparedStatement.setFloat(2, salaryUpdated);
                    break;
                case ("4"):
                    column = "employment_date";
                    System.out.println("Input correct date of employment (YYYY-MM-DD)");
                    Date employmentDateUpdated = Date.valueOf(scanner.nextLine());
                    preparedStatement.setString(1, column);
                    preparedStatement.setDate(2, employmentDateUpdated);
                    break;
                case ("5"):
                    column = "work_position";
                    System.out.println("Input correct work position");
                    String workPositionUpdated = scanner.nextLine();
                    preparedStatement.setString(1, column);
                    preparedStatement.setString(2, workPositionUpdated);
                    break;
            }


            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            System.out.println("Employee updated.");

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
