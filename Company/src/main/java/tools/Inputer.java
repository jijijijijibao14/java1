package tools;

import java.util.Scanner;

/**
 *
 * @author LAPTOP
 */
public class Inputer {
    private static final Scanner sc = new Scanner(System.in);
    
<<<<<<< HEAD
    public static String getString(String msg){
        System.out.print(msg);
        return sc.nextLine().trim();
    }
    public static String inputAndCheck(String msg, String regex){
        String data;
        while(true){
            data = getString(msg);
            if(data.matches(regex)) return data;
=======
    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
       
    }
    public static String inputAndCheck(String msg, String regex){
        String data;
        while (true){
            data = getString(msg);
            if (data.matches(regex)) return data;
>>>>>>> e47424b4bf77ba2cc84c22eee1baff5136ff4cd1
            System.out.println("Invalid format! Pls try again");
        }
    }
}
