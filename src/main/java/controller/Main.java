package controller;

import business.Mountains;
import business.Students;
import java.util.Scanner;
import tools.Inputer;
/**
 *
 * @author LAPTOP
 */
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
        System.out.print("Choose: ");

        return sc.nextInt();
    }


    private void exitProgram() {
        if (students.isChanged()
                && Inputer.getString("Save changed? (Y/N): ").equalsIgnoreCase("Y")) {
            students.saveToFile(REG_FILE);
        }
        System.out.println("Finished!");
    }
}
