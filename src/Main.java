import java.util.Scanner;
import java.util.random.Random;

import javax.xml.crypto.Data;

public class Main {

    
    public static void main(String[] args) {

        System.out.println("Hello, World!");
        Scanner volbaSc = new Scanner(System.in);
        Databaze dat = new Databaze();
        

        while (true) {
            System.out.println("\nZadejte 0 pro ukončení programu\n1 pro přidání zaměstnance\n2 pro zobrazení zaměstnance\n3 pro zobrazení zaměstnanců podle role\n11 pro inicializaci databáze:");
            String volba = volbaSc.nextLine();
            if (volba.equals("0")) {
                System.out.println("Ukončuji program...");
                break;
            }
            else if (volba.equals("1")) {
                dat.pridejZamestnance(null);
            }
            else if (volba.equals("2")) { //pridani spoluprace
                int id1, id2, sp;
                System.out.println("Zadejte ID prvního zaměstnance: ");
                id1 = volbaSc.nextInt();
                System.out.println("Zadejte ID druhého zaměstnance: ");
                id2 = volbaSc.nextInt();
                System.out.println("Zvolte úroveň spolupráce (1 - nízká, 2 - střední, 3 - vysoká): ");
                sp = volbaSc.nextInt();
                Databaze.UrovenSpoluprace uroven = null;
                switch (sp) {   
                    case 1 -> uroven = Databaze.UrovenSpoluprace.NIZKA;
                    case 2 -> uroven = Databaze.UrovenSpoluprace.STREDNI;
                    case 3 -> uroven = Databaze.UrovenSpoluprace.VYSOKA;
                    default -> System.out.println("Neplatná volba úrovně spolupráce.");
                }
                dat.novaSpoluprace(id1, id2, uroven);
            }
            else if (volba.equals("3")) { //odebrani
                System.out.println("Zadejte ID zaměstnance, kterého chcete smazat: ");
                int id = volbaSc.nextInt();
                dat.odeberZamestnance(id);
            }
            else if (volba.equals("4")) { //vyhledani                              
                System.out.println("Zadejte ID: ");
                int id = volbaSc.nextInt();
                dat.vypisZamestnance(id);
            }
            else if (volba.equals("5")) { //spusteni dovednosti
                
            }  
            else if (volba.equals("6")) { //vypis,
                System.out.println("Zvolte 1 pro zobrazení datových analytiků, 2 pro zobrazení bezpečnostních specialistů: ");
                int volbaAnalytik = volbaSc.nextInt();
                dat.vypis(volbaAnalytik == 1);
            }
            else if (volba.equals("7")) { //statistika, 
                }
            else if (volba.equals("8")) {//vypis poctu
                dat.pocetZamestnancu();
                }
            else if (volba.equals("9")) { //
            }
            else if (volba.equals("11")) {
                dat.inicializace();
            }

        }

    }

}
