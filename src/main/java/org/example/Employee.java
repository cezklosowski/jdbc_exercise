package org.example;

import java.time.LocalDate;
import java.util.Date;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private float salary;
    private LocalDate employmentDate;
    private String workPosition;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, float salary, LocalDate employmentDate, String workPosition) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.employmentDate = employmentDate;
        this.workPosition = workPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | First name: " + firstName + " | Last name: " + lastName + " | Salary: " + salary + " | Date of employment: " + employmentDate + " | Work position: " + workPosition;
    }
}
