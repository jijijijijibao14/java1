package model;

import java.io.Serializable;
import java.time.LocalDate;
/**
 *
 * @author LAPTOP
 */
public class Employee {
    private String employeeId;
    private String departmentId;
    private String name;
    private LocalDate DoB;
    private double salary;
    public Employee(String employeeId, String departmentId, String name, LocalDate DoB, double salary) {
        this.employeeId = employeeId;
        this.departmentId = departmentId;
        this.name = name;
        this.DoB = DoB;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDoB() {
        return DoB;
    }

    public void setDoB(LocalDate DoB) {
        this.DoB = DoB;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %-20s %-12s %10s%n", employeeId, departmentId, name, DoB, salary);
    }
    
}
