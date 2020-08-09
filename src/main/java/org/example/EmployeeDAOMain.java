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

        Scanner input = new Scanner(System.in);
        boolean mainLoop = true;
        String choice;

        do {

            System.out.println("Choose operation:");
            System.out.println("1. Create new employee");
            System.out.println("2. Read employee from database");
            System.out.println("3. Update employee info");
            System.out.println("4. Delete employee from database");
            System.out.println("q. Exit");

            choice = input.nextLine();




            switch (choice) {

                // create Employee
                case ("1"):
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
                    LocalDate employmentDate = LocalDate.of(Integer.parseInt(splitDate[0]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
                    System.out.println("Input work position");
                    String workPosition = scanner.nextLine();

                    Employee employee = new Employee(id, firstName, lastName, salary, employmentDate, workPosition);
                    employeeDAO.create(connection, employee);

                    break;

                // read Employee
                case ("2"):
                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Input id:");
                    int idToRead = Integer.parseInt(scanner2.nextLine());
                    System.out.println(employeeDAO.read(connection, idToRead));
                    break;

                // update Employee
                case ("3"):
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("Input id");
                    int idToUpdate = Integer.parseInt(scanner3.nextLine());
                    employeeDAO.update(connection, idToUpdate);
                    break;

                case ("4"):
                    // delete Employee
                    Scanner scanner4 = new Scanner(System.in);
                    System.out.println("Input id");
                    int idToDelete = Integer.parseInt(scanner4.nextLine());
                    employeeDAO.delete(connection, idToDelete);
                    break;
                case("q"):
                    // exit
                    mainLoop = false;
                    break;
            }

        } while (!choice.equals("q"));
        connection.close();
    }
}
