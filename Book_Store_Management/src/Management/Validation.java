package Management;

import java.util.Scanner;
public class Validation {
    private static Scanner sc = new Scanner(System.in);
    
    public static int getInt(int min, int max){
        int n;
        while(true){
            try {
                n= Integer.parseInt(sc.nextLine());
                if(n>=min && n<=max)break;
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.err.println("Error Input.");
            }
        }
        return n;
    }
    
    public static String getWithRegex(String regex){
        String s;
        while (true) {            
            try {
                s = sc.nextLine().trim();
                if(s.matches(regex))break;
                throw new Exception();
            } catch (Exception e) {
                 System.err.println("Error Input.");
            }
        }
        return s;
    }
    
    public static double getReal(int min){
        double n;
      while(true){
            try {
                n= Double.parseDouble(sc.nextLine());
                if(n>min)break;
                throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.err.println("Error Input.");
            }
        }
        return n;
    }
    
    public static String getStatus(){
        String s ;
        while (true) {            
            try {
                s = sc.nextLine().trim().toUpperCase();
                if(s.equals("A")){
                    return s = "Available";
                }else if(s.equals("NA")){
                    return s = "Not Available";
                }
                throw new Exception();
            } catch (Exception e) {
                System.err.println("Please enter A/NA!");
            }
        }
    }  
    
    public static boolean getYN(){
        System.out.println("Do you want to continue?(Y/N)");
        while (true) {
            String s = sc.nextLine().toUpperCase();
            if(s.equalsIgnoreCase("Y")){
                return true;
            }else if(s.equalsIgnoreCase("N")){
                return false;
            }
            System.err.println("PLEASE INPUT Y/N!");
        }
    }
}
