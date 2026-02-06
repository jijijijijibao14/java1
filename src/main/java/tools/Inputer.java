package tools;

import java.util.Scanner;

/**
 *
 * @author LAPTOP
 */
public class Inputer {
    private static final Scanner sc = new Scanner(System.in);
    
    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
       
    }
    public static String inputAndCheck(String msg, String regex){
        String data;
        while (true){
            data = getString(msg);
            if (data.matches(regex)) return data;
            System.out.println("Invalid format! Pls try again");
        }
    }
}
