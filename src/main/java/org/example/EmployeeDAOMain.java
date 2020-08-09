package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeDAOMain {

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

        EmployeeDAO employeeDAO = new EmployeeDAO();


        // create Employee
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creating new employee");
        System.out.println("Input id");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Input first name");
        String firstName = scanner.nextLine();
        System.out.println("Input last name");
        String lastName = scanner.nextLine();
        System.out.println("Input salary");
        float salary = Float.parseFloat(scanner.nextLine());
        System.out.println("Input date of employment (YYYY,MM,DD)");
        String tempDate = scanner.nextLine();
        String[] splitDate = tempDate.split(",");
        LocalDate employmentDate = LocalDate.of(Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2]));
        System.out.println("Input work position");
        String workPosition = scanner.nextLine();

        Employee employee = new Employee(id,firstName,lastName,salary,employmentDate,workPosition);
        employeeDAO.create(connection,employee);

        // read Employee
        System.out.println("Input id:");
        int searchedId = Integer.parseInt(scanner.nextLine());
        System.out.println(employeeDAO.read(connection, searchedId));

        // update Employee
        // delete Employee

        connection.close();
    }
}
