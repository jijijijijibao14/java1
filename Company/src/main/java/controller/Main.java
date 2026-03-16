package controller;

<<<<<<< HEAD
import business.Departments;
import business.Employees;
import java.util.Scanner;
import tools.Inputer;
import java.io.*;
import java.util.*;

=======
import business.Mountains;
import business.Students;
import java.util.Scanner;
import tools.Inputer;
>>>>>>> e47424b4bf77ba2cc84c22eee1baff5136ff4cd1
/**
 *
 * @author LAPTOP
 */
<<<<<<< HEAD
public class Main {
    private static final String EMPLOYEE_FILE = "D:\\LapProject\\Employee.txt";
    private static final String DEPARTMENT_FILE = "D:\\LapProject\\department.txt";
    
    private final Employees employees = new Employees(EMPLOYEE_FILE);
    private final Departments departments = new Departments(DEPARTMENT_FILE);
    private final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        new Main().run();
    }
    
    public void run(){
        departments.loadFromFileD(DEPARTMENT_FILE);
        employees.loadFromFileE(EMPLOYEE_FILE);
        
        int choice;
        do{
            choice = showMenu();
            
            switch (choice){
                case 1 -> departments.departmentDisplay();
                case 2 -> departments.addDepartment(employees);
                case 3 -> departments.searchDeById();
                case 4 -> departments.updateDepartment(departments);
                case 5 -> departments.searchDeByFloor();
                case 6 -> employees.EmployeeDisplay();
                case 7 -> employees.addEmployee(departments);
                case 8 -> employees.deleteEmployee();
                case 9 -> employees.updateEmployee();
                case 10 -> employees.EmployeeByDepartment();
                case 11 -> employees.searchByName();
                case 12 -> employees.sortBySalary();
                case 13 -> exitProgram(); 
                default -> System.out.println("Invalid choice");
            }
            
        }while (choice != 13);
    }
    private int showMenu(){
        System.out.println("---MENU---");
        System.out.println("1.  Display All Department");
        System.out.println("2.  Add new Department");
        System.out.println("3.  Search Department By Id");
        System.out.println("4.  Update Department By Id");
        System.out.println("5.  Search Department By Specific Location");
        System.out.println("6.  Display All Employee");
        System.out.println("7.  Add New Employee");
        System.out.println("8.  Delete Employee");
        System.out.println("9.  Update Employee");
        System.out.println("10. Filter Employee By Department ID");
        System.out.println("11. Search Employee By Name");
        System.out.println("12. Sort Employee By Salary");
        System.out.println("13.  Exit");
=======
public class Main{
    private static final String MOUNTAIN_FILE = "D:\\LapProject\\MountainList.csv";
    private static final String REG_FILE = "D:\\LapProject\\registration.dat";
    
    
    private final Mountains mountains = new Mountains(MOUNTAIN_FILE);
    private final Students students = new Students();
    private final Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        new Main().run();
    }


    public void run() {
        students.loadFromFile(REG_FILE);

        int choice;
        do {
            choice = showMenu();

            switch (choice) {
                case 1 -> students.addStudent(mountains);
                case 2 -> students.deleteStudent();
                case 3 -> students.updateStudent(mountains);
                case 4 -> students.searchByName();
                case 5 -> students.filterByCampus();
                case 6 -> students.statisticsByMountain(mountains);
                case 7 -> students.displayList();
                case 8 -> exitProgram();
                default -> System.out.println("Invalid choice");
            }

        } while (choice != 8);
    }

    private int showMenu() {
        System.out.println("----MENU----");
        System.out.println("1. ADD STUDENT");
        System.out.println("2. DELETE REGISTRATION");
        System.out.println("3. UPDATE STUDENT");
        System.out.println("4. SEARCH PARTICIPANTS BY NAME");
        System.out.println("5. FILTER DATA BY CAMPUS");
        System.out.println("6. STATISTICS OF REGISTRATION BY MOUNTAIN");
        System.out.println("7. DISPLAY ");
        System.out.println("8. EXIT");
>>>>>>> e47424b4bf77ba2cc84c22eee1baff5136ff4cd1
        System.out.print("Choose: ");

        return sc.nextInt();
    }
<<<<<<< HEAD
    
    private void exitProgram(){
        boolean needSave = employees.isChaged() || departments.isChaged();
        if(needSave && Inputer.getString("Save changed? (Y/N): ").equalsIgnoreCase("Y")){
            if(employees.isChaged()){
                employees.saveToFileE(EMPLOYEE_FILE);
            }
            if(departments.isChaged()){
                departments.saveToFileD(DEPARTMENT_FILE);
            }
        }
        System.out.println("SUCCESSFULLY!");
    }
}
=======


    private void exitProgram() {
        if (students.isChanged()
                && Inputer.getString("Save changed? (Y/N): ").equalsIgnoreCase("Y")) {
            students.saveToFile(REG_FILE);
        }
        System.out.println("Finished!");
    }
}
>>>>>>> e47424b4bf77ba2cc84c22eee1baff5136ff4cd1
