import java.util.Scanner;
import java.util.random.Random;

import javax.xml.crypto.Data;

public class Main {

    
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Scanner volbaSc = new Scanner(System.in);
        Databaze dat = new Databaze();
        

        while (true) {
            //tady bude hlavni menu
            String volba = volbaSc.nextLine();
            if (volba.equals("0")) {
                System.out.println("Ukončuji program...");
                break;
            }
            else if (volba.equals("1")) {
                dat.pridejZamestnance(null);
            }

        }

    }

}
